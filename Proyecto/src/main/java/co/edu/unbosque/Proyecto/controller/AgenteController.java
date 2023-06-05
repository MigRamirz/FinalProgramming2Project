package co.edu.unbosque.Proyecto.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Proyecto.model.Agente;
import co.edu.unbosque.Proyecto.repository.AgenteRepository;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/miguelapi")
public class AgenteController {

	@Autowired
	private AgenteRepository agrepo;
	private SimpleDateFormat sdf;

	@PostMapping("/agente")
	public ResponseEntity<String> agregar(@RequestParam String nombre, @RequestParam String cedula,
			@RequestParam Date fnacimiento, @RequestParam String sexo,
			@RequestParam Date fentrinstit,
			@RequestParam String rangoactual, @RequestParam String ncasosatendidos) {
		Agente ag = new Agente();
		ag.setNombre(nombre);
		ag.setCedula(cedula);
		Date fnaci = fnacimiento;
		ag.setFnacimiento(fnaci);
		ag.setEdad(calculoEdad(fnaci));
		ag.setSexo(sexo);
		ag.setFentradainstit(fentrinstit);
		ag.setRangoactual(rangoactual);
		int ncat = Integer.parseInt(ncasosatendidos);
		ag.setNcasosatendidos(ncat);
		agrepo.save(ag);
		return ResponseEntity.status(HttpStatus.CREATED).body("Agente agregado con éxito (CODE 201)\n");
	}

	public Date ingresoDeFecha(String yr, String mm, String dd) {
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = yr + "/" + mm + "/" + dd;
		Date f = null;
		try {
			f = new Date(sdf.parse(fecha).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return f;
	}

	public int calculoEdad(Date fna) {
		LocalDate fnac = fna.toLocalDate();
		LocalDate ahora = LocalDate.now();
		Period period = Period.between(fnac, ahora);
		int edad = Integer.parseInt(period.getYears() + "");
		return edad;
	}

	@GetMapping("/agente")
	public ResponseEntity<Iterable<Agente>> listarTodosLosAgentes() {
		List<Agente> all = (List<Agente>) agrepo.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}
	
	@GetMapping("/agente/ordencasos")
	public ResponseEntity<Iterable<Agente>> listarAgentesPorOrdenDeCasos() {
		List<Agente> all = (List<Agente>) agrepo.findByOrderByNcasosatendidosDesc();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}

	@DeleteMapping("/agente/{id}")
	public ResponseEntity<String> eliminarPorID(@RequestParam String id) {
		Integer id1 = Integer.parseInt(id);
		Optional<Agente> dato = agrepo.findById(id1);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar el dato");
		}
		agrepo.deleteById(id1);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Agente eliminado con éxito");
	}

	@PutMapping("/agente/cedula/{cedula}")
	public ResponseEntity<String> actualizarPorCedula(@RequestParam String cedula, @RequestParam String rangoactual) {
		Optional<Agente> dato = agrepo.findByCedula(cedula);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Agente temp = dato.get();
		temp.setRangoactual(rangoactual);
		agrepo.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado con éxito");
	}

	@GetMapping("/agente/{id}")
	public ResponseEntity<Optional<Agente>> mostarUnAgente(@PathVariable Integer id) {
		Optional<Agente> op = agrepo.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(op);
	}

	@GetMapping("/agente/ncasosatendidos/{ncasosatendidos}")
	public ResponseEntity<Optional<List<Agente>>> getByNCasosAtendidos(@PathVariable String ncasosatendidos) {
		Integer ncasos = Integer.parseInt(ncasosatendidos);
		Optional<List<Agente>> op = agrepo.findByNcasosatendidos(ncasos);
		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(op);
	}
}

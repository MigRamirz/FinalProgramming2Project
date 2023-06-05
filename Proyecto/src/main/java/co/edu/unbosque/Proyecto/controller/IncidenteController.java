package co.edu.unbosque.Proyecto.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Proyecto.model.Agente;
import co.edu.unbosque.Proyecto.model.Incidente;
import co.edu.unbosque.Proyecto.repository.AgenteRepository;
import co.edu.unbosque.Proyecto.repository.IncidenteRepository;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/miguelapi")
public class IncidenteController {

	@Autowired
	private IncidenteRepository increpo;
	@Autowired
	private AgenteRepository agrepo;
	private SimpleDateFormat sdf;

	@PostMapping("/incidente")
	public ResponseEntity<String> agregar(@RequestParam String nombredelcaso, @RequestParam String empresaafectada,
			@RequestParam String tiempodeafectacion, @RequestParam String cantpersonasafectadas, @RequestParam Date focurrencia, @RequestParam String barriodeloshechos,
			@RequestParam String nagentesenelcaso, @RequestParam String nombre, @RequestParam String cedula,
			@RequestParam Date fnacimiento, @RequestParam String sexo,
			@RequestParam Date fentrinstit,
			@RequestParam String rangoactual, @RequestParam String ncasosatendidos) {
		Incidente inc = new Incidente();
		inc.setNombredelcaso(nombredelcaso);
		inc.setEmpresaafectada(empresaafectada);
			int tafect = Integer.parseInt(tiempodeafectacion);
		inc.setTiempodeafectacion(tafect);
			int cntpafect = Integer.parseInt(cantpersonasafectadas);
		inc.setCantpersonasafectadas(cntpafect);
		inc.setFechadeocurrencia(focurrencia);
		inc.setBarriodeloshechos(barriodeloshechos);
			int nag = Integer.parseInt(nagentesenelcaso);
			inc.setAgentequeatendioelcaso(agregarAgente(nombre, cedula, fnacimiento, sexo, fentrinstit,
					rangoactual, ncasosatendidos));
		increpo.save(inc);
		return ResponseEntity.status(HttpStatus.CREATED).body("Incidente creado con éxito 201");
	}

	@PostMapping("/agenteincidente")
	public Agente agregarAgente(String nombre, String cedula, Date fnacimiento, String sexo, Date fentrinstit,
			String rangoactual, String ncasosatendidos) {
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
		agrepo.save(ag);
		return ag;
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

	public Date fechaDeOcurrencia(String yrO, String mesO, String diaO) {
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = yrO + "/" + mesO + "/" + diaO;
		Date f = null;
		try {
			f = new Date(sdf.parse(fecha).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return f;
	}

	@GetMapping("/incidente")
	public ResponseEntity<Iterable<Incidente>> listarTodosLosIncidentes() {
		List<Incidente> all = (List<Incidente>) increpo.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}

}

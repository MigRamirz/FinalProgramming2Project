package co.edu.unbosque.Proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyecto.model.Agente;

public interface AgenteRepository extends CrudRepository<Agente, Integer> {

	public Optional<Agente> findById(Integer id);

	public List<Agente> findAll();
	
	public List<Agente> findByOrderByNcasosatendidosDesc();

	public Optional<List<Agente>> findByNcasosatendidos(int ncasosatendidos);

	public Optional<Agente> findByCedula(String cedula);
}

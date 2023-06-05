package co.edu.unbosque.Proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyecto.model.Incidente;

public interface IncidenteRepository extends CrudRepository<Incidente, Integer>{
	
	public Optional<Incidente> findById(Integer id);

	public List<Incidente> findAll();

	public static List<Incidente> findViewImages(Integer id, int i) {
		return null;
	}
	
}

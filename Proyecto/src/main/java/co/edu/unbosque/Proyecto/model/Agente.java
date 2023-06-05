package co.edu.unbosque.Proyecto.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Agente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private String cedula;
	private Date fnacimiento;
	private int edad;
	private String sexo;
	private Date fentradainstit;
	private String rangoactual;
	private int ncasosatendidos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getFnacimiento() {
		return fnacimiento;
	}

	public void setFnacimiento(Date fnacimiento) {
		this.fnacimiento = fnacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFentradainstit() {
		return fentradainstit;
	}

	public void setFentradainstit(Date fentradainstit) {
		this.fentradainstit = fentradainstit;
	}

	public String getRangoactual() {
		return rangoactual;
	}

	public void setRangoactual(String rangoactual) {
		this.rangoactual = rangoactual;
	}

	public int getNcasosatendidos() {
		return ncasosatendidos;
	}

	public void setNcasosatendidos(int ncasosatendidos) {
		this.ncasosatendidos = ncasosatendidos;
	}

}

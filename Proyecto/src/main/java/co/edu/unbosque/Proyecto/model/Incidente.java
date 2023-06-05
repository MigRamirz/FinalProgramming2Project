package co.edu.unbosque.Proyecto.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Incidente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombredelcaso;
	private String empresaafectada;
	private int tiempodeafectacion;
	private int cantpersonasafectadas;
	private Date fechadeocurrencia;
	private String barriodeloshechos;
	@ManyToOne
	private Agente agentequeatendioelcaso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombredelcaso() {
		return nombredelcaso;
	}

	public void setNombredelcaso(String nombredelcaso) {
		this.nombredelcaso = nombredelcaso;
	}

	public String getEmpresaafectada() {
		return empresaafectada;
	}

	public void setEmpresaafectada(String empresaafectada) {
		this.empresaafectada = empresaafectada;
	}

	public int getTiempodeafectacion() {
		return tiempodeafectacion;
	}

	public void setTiempodeafectacion(int tiempodeafectacion) {
		this.tiempodeafectacion = tiempodeafectacion;
	}

	public int getCantpersonasafectadas() {
		return cantpersonasafectadas;
	}

	public void setCantpersonasafectadas(int cantpersonasafectadas) {
		this.cantpersonasafectadas = cantpersonasafectadas;
	}

	public Date getFechadeocurrencia() {
		return fechadeocurrencia;
	}

	public void setFechadeocurrencia(Date fechadeocurrencia) {
		this.fechadeocurrencia = fechadeocurrencia;
	}

	public String getBarriodeloshechos() {
		return barriodeloshechos;
	}

	public void setBarriodeloshechos(String barriodeloshechos) {
		this.barriodeloshechos = barriodeloshechos;
	}

	public Agente getAgentequeatendioelcaso() {
		return agentequeatendioelcaso;
	}

	public void setAgentequeatendioelcaso(Agente agentequeatendioelcaso) {
		this.agentequeatendioelcaso = agentequeatendioelcaso;
	}

}

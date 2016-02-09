package br.com.consultorio_odontologico.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.domain.EstadoCivil;

@ManagedBean(name="estadoCivil")
@ViewScoped
public class EstadoCivilBean {
	
	List<EstadoCivil> estadoCivil = new ArrayList<EstadoCivil>();

	@PostConstruct
	public void setEstadoCivil() {

		for (EstadoCivil e : EstadoCivil.values()) {
			estadoCivil.add(e);
		}

	}

	public List<EstadoCivil> getEstadoCivil() {
		return estadoCivil;
	}
}

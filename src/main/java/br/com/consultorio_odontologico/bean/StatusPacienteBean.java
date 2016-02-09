package br.com.consultorio_odontologico.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.domain.StatusPaciente;

@ManagedBean
@ViewScoped
public class StatusPacienteBean {

	List<StatusPaciente> statusPacientes = new ArrayList<StatusPaciente>();

	public List<StatusPaciente> getStatusPacientes() {
		return statusPacientes;
	}

	@PostConstruct
	public void setStatusPacientes() {

		for (StatusPaciente s : StatusPaciente.values()) {
			statusPacientes.add(s);
		}
	}

}

package br.com.consultorio_odontologico.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.domain.Escolaridade;
@ManagedBean(name="escolaridade")
@ViewScoped
public class EscolaridadeBean {
	List<String> escolaridades = new ArrayList<String>();

	@PostConstruct
	public void setEscolaridade() {

		for (Escolaridade e : Escolaridade.values()) {
			escolaridades.add(e.titulo);
		}

	}

	public List<String> getEscolaridade() {
		return escolaridades;
	}
}

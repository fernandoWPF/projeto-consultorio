package br.com.consultorio_odontologico.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.domain.Uf;

@ManagedBean(name = "UF")
@ViewScoped
public class UFBean {

	List<Uf> uf = new ArrayList<Uf>();

	@PostConstruct
	public void setUf() {

		for (Uf u : Uf.values()) {
			uf.add(u);
		}

	}

	public List<Uf> getUf() {
		return uf;
	}

}

package br.com.consultorio_odontologico.util;

import javax.persistence.Persistence;

public class GeraTabelas {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
	}
	
}

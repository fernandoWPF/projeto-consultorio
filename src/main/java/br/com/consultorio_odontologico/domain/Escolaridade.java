package br.com.consultorio_odontologico.domain;

public enum Escolaridade {

	SUPERIOR("SUPERIOR"), FUNDAMENTAL("FUNDAMENTAL"), ENSINO_MEDIO("ENSINO MÉDIO"), NAO_ALFABETIZADO(
			"NÃO ALFABETIZADO");

	public String titulo;

	Escolaridade(String titulo) {
		this.titulo = titulo;
	}

}

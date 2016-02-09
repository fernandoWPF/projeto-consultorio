package br.com.consultorio_odontologico.util;

public class Replace {
	
	public String replace(String string){
		String stringNova;
		stringNova = string.replace("(", "")
		.replace(")", "")
		.replace(".", "")
		.replace("-", "")
		.replace(" ", "");
		
		return stringNova;
	}

	
}

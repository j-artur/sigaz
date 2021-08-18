package model.vo;

import java.io.File;

public class StudentVO extends UserVO {
	private String address;
	private File history;
	private String registration;

	public String getRegistration() {
		return this.registration;
	}

	public String getAddress() {
		return this.address;
	}

	public File getHistory() {
		return this.history;
	}

	public void setAddress(String address) {
		if (address == null || address.trim().isEmpty())
			System.out.println("Endereço vazio");
		else
			this.address = address;
	}

	public void setHistory(File history) {
		if (history == null)
			System.out.println("Arquivo inválido");
		else
			this.history = history;
	}

	public void setRegistration(String registration) {
		if (registration == null)
			System.out.println("Valor inválido");
		else if (registration.length() != 10)
			System.out.println("Tamanho inválido, matrícula deve ter 10 caracteres");
		else if (registration.matches("\\D"))
			System.out.println("Caractere inválido, matrícula deve ter apenas dígitos");
		else
			this.registration = registration;
	}
}

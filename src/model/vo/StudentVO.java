package model.vo;

public class StudentVO extends UserVO {
	private String address;
	private String registration;

	public StudentVO() {
	}

	public StudentVO(UserVO data) throws Exception {
		this.setEmail(data.getEmail());
		this.setPassword(data.getPassword());
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) throws Exception {
		if (address == null || address.isEmpty())
			throw new Exception("Endereço vazio");
		else
			this.address = address;
	}

	public String getRegistration() {
		return this.registration;
	}

	public void setRegistration(String registration) throws Exception {
		if (registration == null)
			throw new Exception("Valor inválido");
		else if (registration.matches("\\D"))
			throw new Exception("Caractere inválido, matrícula deve ter apenas dígitos");
		else if (registration.length() != 10)
			throw new Exception("Tamanho inválido, matrícula deve ter 10 caracteres");
		else
			this.registration = registration;
	}

	public String toString() {
		return "ID: " + this.getId() + "\nNome: " + this.getName() + "\nEmail: " + this.getEmail() + "\nSenha: "
				+ this.getPassword() + "\nMatrícula: " + this.getRegistration() + "\nEndereço: " + this.getAddress();
	}
}

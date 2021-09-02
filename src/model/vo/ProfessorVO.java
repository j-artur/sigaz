package model.vo;

public class ProfessorVO extends UserVO {
	private String address;
	private String cpf;

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		if (address == null || address.isEmpty())
			System.out.println("Endereço inválido");
		else
			this.address = address;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		if (cpf == null || !cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$"))
			System.out.println("CPF inválido, deve estar no formato XXX.XXX.XXX-XX");
		else
			this.cpf = cpf;
	}

	public String toString() {
		return "ID: " + this.getId() + "\nNome: " + this.getName() + "\nEmail: " + this.getEmail() + "\nSenha: "
				+ this.getPassword() + "\nCPF: " + this.getCpf() + "\nEndereço: " + this.getAddress();
	}
}

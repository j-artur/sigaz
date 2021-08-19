package model.vo;

public class ProfessorVO extends UserVO {
	private String address;
	private String cpf;

	public String getAddress() {
		return this.address;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setAddress(String address) {
		if (address == null || address.trim().isEmpty())
			System.out.println("Endereço inválido");
		else
			this.address = address;
	}

	public void setCpf(String cpf) {
		if (cpf == null || !cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$"))
			System.out.println("CPF inválido, deve estar no formato XXX.XXX.XXX-XX");
		else
			this.cpf = cpf;
	}
}

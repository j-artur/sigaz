package model.vo;

public class PrincipalVO extends UserVO {
	public PrincipalVO() {
	}

	public PrincipalVO(UserVO data) throws Exception {
		this.setEmail(data.getEmail());
		this.setPassword(data.getPassword());
	}

	public String toString() {
		return "ID: " + this.getId() + "\nNome: " + this.getName() + "\nEmail: " + this.getEmail() + "\nSenha: "
				+ this.getPassword();
	}
}

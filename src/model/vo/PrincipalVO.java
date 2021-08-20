package model.vo;

public class PrincipalVO extends UserVO {
	public String toString() {
		return "ID: " + this.getId() + "\nNome: " + this.getName() + "\nEmail: " + this.getEmail() + "\nSenha: "
				+ this.getPassword();
	}
}

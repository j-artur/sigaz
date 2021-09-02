package model.vo;

public abstract class UserVO {
	private long id;
	private String name;
	private String email;
	private String password;

	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		if (id <= 0)
			System.out.println("ID inválido, deve ser inteiro positivo");
		else
			this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if (name == null || name.isEmpty())
			System.out.println("Nome vazio");
		else if (name.length() < 2 || name.length() > 255)
			System.out.println("Tamanho inválido, nome deve ter entre 2 e 255 caracteres");
		else
			this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		if (email == null || email.isEmpty() || !email.contains("@"))
			System.out.println("Email inválido, precisa conter @");
		else
			this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		if (password == null)
			System.out.println("Senha inválida");
		else if (password.length() < 6)
			System.out.println("Senha muito curta, mínimo 6 caracteres");
		else
			this.password = password;
	}
}

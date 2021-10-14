package model.vo;

public abstract class UserVO {
	private long id;
	private String name;
	private String email;
	private String password;

	public long getId() {
		return this.id;
	}

	public void setId(long id) throws Exception {
		if (id <= 0)
			throw new Exception("ID inválido, deve ser inteiro positivo");
		else
			this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) throws Exception {
		if (name == null || name.isEmpty())
			throw new Exception("Nome vazio");
		else if (name.length() < 2 || name.length() > 255)
			throw new Exception("Tamanho inválido, nome deve ter entre 2 e 255 caracteres");
		else
			this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) throws Exception {
		if (email == null || email.isEmpty() || !email.contains("@"))
			throw new Exception("Email inválido, precisa conter @");
		else
			this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) throws Exception {
		if (password == null)
			throw new Exception("Senha inválida");
		else if (password.length() < 6)
			throw new Exception("Senha muito curta, mínimo 6 caracteres");
		else
			this.password = password;
	}
}

package model.vo;

public class SubjectVO {
	private long id;
	private String code;
	private String name;

	public long getId() {
		return this.id;
	}

	public void setId(long id) throws Exception {
		if (id <= 0)
			throw new Exception("ID inválido, deve ser inteiro positivo");
		else
			this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) throws Exception {
		if (code == null || !code.matches("^[A-Z]{3}\\d{4}$"))
			throw new Exception("Código inválido!");
		else
			this.code = code;
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

	public String toString() {
		return this.getCode() + "\t" + this.getName();
	}
}

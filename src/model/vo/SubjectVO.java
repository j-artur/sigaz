package model.vo;

public class SubjectVO {
	private long id;
	private String code;
	private String name;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		if (id <= 0)
			System.out.println("ID inválido, deve ser inteiro positivo");
		else
			this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		if (code == null || !code.matches("^[A-Z]{3}\\d{4}$"))
			System.out.println("Código inválido!");
		else
			this.code = code;
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

	public String toString() {
		return "Código: " + code + "\nNome: " + name;
	}
}

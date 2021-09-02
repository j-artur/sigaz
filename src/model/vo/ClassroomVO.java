package model.vo;

public class ClassroomVO {
	private long id;
	private SubjectVO subject;
	private ProfessorVO professor;
	private StudentVO[] students;
	private String schedule;
	private String place;
	private boolean active;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		if (id <= 0)
			System.out.println("ID inválido!");
		else
			this.id = id;
	}

	public SubjectVO getSubject() {
		return this.subject;
	}

	public void setSubject(SubjectVO subject) {
		if (subject == null)
			System.out.println("A disciplina não pode ser nula!");
		else
			this.subject = subject;
	}

	public ProfessorVO getProfessor() {
		return this.professor;
	}

	public void setProfessor(ProfessorVO professor) {
		if (professor == null)
			System.out.println("O professor não pode ser nulo!");
		else
			this.professor = professor;
	}

	public StudentVO[] getStudents() {
		return this.students;
	}

	public void setStudents(StudentVO[] students) {
		boolean test = true;
		for (int i = 0; i < students.length; i++){
			if (students[i] == null){
				System.out.println("O campo de estudantes não pode ser nulo!");
				test = false;
				break;
			}
		}
		if (!test)
			System.out.println("Um dos estudantes não é válido!");
			else
				this.students = students;
		}
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		if (schedule == null || !schedule.matches("^[23456]{1,4}[MTN][12345]{1,5}$")) {
			System.out.println("Horário inválido, formate como [Dias da semana][Inicial do turno][Numeração dos horários]");
			System.out.println("Exemplo: 35T45 (Terça e Quinta, Tarde, 4º e 5º horários)");
		} else
			this.schedule = schedule;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		if (place == null || place.isEmpty())
			System.out.println("Local inválido!");
		else
			this.place = place;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return "ID: " + getId() + "\nDisciplina: " + getSubject().getName() + "\nProfessor: " + getProfessor().getName()
				+ "\nHorário: " + getSchedule() + "\nLugar: " + getPlace() + "\nStatus: " + (isActive() ? "ativo" : "desativo");
	}
}

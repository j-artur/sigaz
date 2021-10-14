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

	public void setId(long id) throws Exception {
		if (id <= 0)
			throw new Exception("ID inválido!");
		else
			this.id = id;
	}

	public SubjectVO getSubject() {
		return this.subject;
	}

	public void setSubject(SubjectVO subject) throws Exception {
		if (subject == null)
			throw new Exception("A disciplina não pode ser nula!");
		else
			this.subject = subject;
	}

	public ProfessorVO getProfessor() {
		return this.professor;
	}

	public void setProfessor(ProfessorVO professor) throws Exception {
		if (professor == null)
			throw new Exception("O professor não pode ser nulo!");
		else
			this.professor = professor;
	}

	public StudentVO[] getStudents() {
		return this.students;
	}

	public void setStudents(StudentVO[] students) throws Exception {
		if (students == null)
			throw new Exception("O campo de estudantes não pode ser nulo!");
		else {
			boolean test = true;
			for (int i = 0; i < students.length; i++) {
				if (students[i] == null) {
					test = false;
					break;
				}
			}
			if (!test)
				throw new Exception("Um dos estudantes não é válido!");
			else
				this.students = students;
		}
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) throws Exception {
		if (schedule == null || !schedule.matches("^[23456]{1,5}[MTN][12345]{1,5}$")) {
			throw new Exception("Horário inválido, formate como [Dias da semana][Turno][Horários]\nExemplo: 35T45 (Terça e Quinta, Tarde, 4º e 5º horários)");
		} else
			this.schedule = schedule;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) throws Exception {
		if (place == null || place.isEmpty())
			throw new Exception("Local inválido!");
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
		return "ID: " + this.getId() + "\nDisciplina: " + this.getSubject().getName() + "\nProfessor: "
				+ this.getProfessor().getName() + "\nHorário: " + this.getSchedule() + "\nLugar: " + this.getPlace()
				+ "\nStatus: " + (this.isActive() ? "ativo" : "desativo");
	}
}

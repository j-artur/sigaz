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
		return id;
	}
	
	public void setId(long id) {
		if(id <= 0)
			System.out.println("ID inválido!");
		else
			this.id = id;
	}
	
	public SubjectVO getSubject() {
		return subject;
	}
	
	public void setSubject(SubjectVO subject) {
		if(subject==null)
			System.out.println("A disciplina não pode ser nula!");
		else 
			this.subject = subject;
	}

	public ProfessorVO getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorVO professor) {
		if(professor==null)
			System.out.println("O professor não pode ser nulo!");
		else 
			this.professor = professor;
	}

	public StudentVO[] getStudents() {
		return students;
	}

	public void setStudents(StudentVO[] students) {
		if(students==null)
			System.out.println("O campo de estudantes não pode ser nulo!");
		else	
			this.students = students;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		boolean horarioFormatado = true;
		
		//Verifica se o horário tem o número de caracteres que deve ter
		if(schedule.length() != 5) {
			horarioFormatado = false;
			System.out.println("O horário deve seguir o formato XX:XX");
		} else {
			for(short i = 0; i < schedule.length(); i++) {
				if(i != 2) {
					char caractere = schedule.charAt(i);
					//Verifica se os caracteres do horário são dígitos
					if(!Character.isDigit(caractere)) {
						horarioFormatado = false;
						System.out.println("Os caracteres do horário não são dígitos");
					}
				} else {
					//Verifica se o horário contém o ":"
					char caractere = schedule.charAt(i);
					if(caractere != ':') {
						horarioFormatado = false;
						System.out.println("O horário deve seguir o formato XX:XX");
					}	
				}
			}
			if(horarioFormatado)
				this.schedule = schedule;
		}
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		if(place==null || place=="")
			System.out.println("Local inválido!");
		else
			this.place = place;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if(active != true || active != false)
			System.out.println("Código de ativo inválido!");
		else 
			this.active = active;
	}
	
}

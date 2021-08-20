package model.vo;

public class GradeVO {
	private StudentVO student;
	private ClassroomVO classroom;
	private int p1;
	private int p2;
	private int p3;
	private int finalGrade;
	private double attendance;

	private void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	private void calculateFinalGrade() {
		int gradeSum = this.getP1() + this.getP2() + this.getP3();
		int partialAverage = Double.valueOf(Math.ceil(gradeSum / 3.0)).intValue();

		this.setFinalGrade(partialAverage);
	}

	public StudentVO getStudent() {
		return this.student;
	}

	public ClassroomVO getClassroom() {
		return this.classroom;
	}

	public int getP1() {
		return this.p1;
	}

	public int getP2() {
		return this.p2;
	}

	public int getP3() {
		return this.p3;
	}

	public int getFinalGrade() {
		return this.finalGrade;
	}

	public double getAttendance() {
		return this.attendance;
	}

	public void setStudent(StudentVO student) {
		if (student == null)
			System.out.println("Aluno inválido");
		else
			this.student = student;
	}

	public void setClassroom(ClassroomVO classroom) {
		if (classroom == null)
			System.out.println("Turma inválida");
		else
			this.classroom = classroom;
	}

	public void setP1(int p1) {
		if (p1 < 0 || p1 > 100)
			System.out.println("Nota inválida, deve ser de 0 a 100");
		else {
			this.p1 = p1;
			this.calculateFinalGrade();
		}
	}

	public void setP2(int p2) {
		if (p2 < 0 || p2 > 100)
			System.out.println("Nota inválida, deve ser de 0 a 100");
		else {
			this.p2 = p2;
			this.calculateFinalGrade();
		}
	}

	public void setP3(int p3) {
		if (p3 < 0 || p3 > 100)
			System.out.println("Nota inválida, deve ser de 0 a 100");
		else {
			this.p3 = p3;
			this.calculateFinalGrade();
		}
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}

	public String toString() {
		return "Aluno: " + this.getStudent().getName() + "\nDisciplina: " + this.getClassroom().getSubject().getName()
				+ "\nP1: " + this.getP1() + "\nP2: " + this.getP2() + "\nP3: " + this.getP3() + "\nMédia Final: "
				+ this.getFinalGrade() + "\nFrequência: " + this.getAttendance() + "%";
	}
}

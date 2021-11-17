package model;

import model.vo.GradeVO;

public class GradeModel {
	private GradeVO grade;

	public GradeVO getGrade() {
		return grade;
	}

	public GradeModel(GradeVO grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return this.grade.getClassroom().getSubject().getName();
	}

	public String getN1() {
		return String.valueOf(grade.getN1());
	}

	public String getN2() {
		return String.valueOf(grade.getN2());
	}

	public String getN3() {
		return String.valueOf(grade.getN3());
	}

	public String getNFinal() {
		return String.valueOf(grade.getNFinal());
	}

	public String getFrequency() {
		return String.format("%.0f%%", grade.getFrequency() * 100);
	}

	public String getStatus() {
		if (grade.getClassroom().isActive())
			return "Cursando";
		else
			return this.grade.getNFinal() >= 70 ? "Aprovado" : "Reprovado";
	}
}

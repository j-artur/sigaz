package model;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.scene.control.TextField;
import model.vo.GradeVO;
import view.NumberTextField;

public class GradeStudentModel {
	private GradeVO grade;

	public GradeStudentModel(GradeVO grade) {
		this.grade = grade;
	}

	public String getRegistration() {
		return this.grade.getStudent().getRegistration();
	}

	public String getName() {
		return this.grade.getStudent().getName();
	}

	public String getN1() {
		int grade = this.grade.getN1();
		return Integer.toString(grade);
	}

	public String getN2() {
		int grade = this.grade.getN2();
		return Integer.toString(grade);
	}

	public String getN3() {
		int grade = this.grade.getN3();
		return Integer.toString(grade);
	}

	public String getNFinal() {
		int grade = this.grade.getNFinal();
		return Integer.toString(grade);
	}

	public TextField getFrequency() {
		double grade = this.grade.getFrequency();
		NumberFormat fmt = NumberFormat.getPercentInstance();
		// fmt.setMinimumFractionDigits(0);
		NumberTextField field = new NumberTextField(new BigDecimal(grade), fmt);
		return field;
	}
}

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.scene.control.TextField;
import model.vo.GradeVO;
import view.NumberTextField;

public class GradeStudentModel {
	private GradeVO grade;
	private TextField n1;
	private TextField n2;
	private TextField n3;
	private TextField nfinal;
	private TextField frequency;

	public GradeVO getGrade() {
		return grade;
	}

	public GradeStudentModel(GradeVO grade) {
		this.grade = grade;

		int n1 = this.grade.getN1();
		this.n1 = new NumberTextField(new BigDecimal(n1));
		int n2 = this.grade.getN2();
		this.n2 = new NumberTextField(new BigDecimal(n2));
		int n3 = this.grade.getN3();
		this.n3 = new NumberTextField(new BigDecimal(n3));
		int nfinal = this.grade.getNFinal();
		this.nfinal = new NumberTextField(new BigDecimal(nfinal));

		double frequency = this.grade.getFrequency();
		NumberFormat fmt = NumberFormat.getPercentInstance();
		this.frequency = new NumberTextField(new BigDecimal(frequency), fmt);
	}

	public String getRegistration() {
		return this.grade.getStudent().getRegistration();
	}

	public String getName() {
		return this.grade.getStudent().getName();
	}

	public TextField getN1() {
		return n1;
	}

	public TextField getN2() {
		return n2;
	}

	public TextField getN3() {
		return n3;
	}

	public TextField getNFinal() {
		return nfinal;
	}

	public TextField getFrequency() {
		return frequency;
	}
}

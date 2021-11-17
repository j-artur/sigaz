package model;

import javafx.scene.control.Button;
import model.vo.StudentVO;

public class AssociateStudentModel {
	private StudentVO student;
	private Button addButton;
	private Button removeButton;

	public StudentVO getStudent() {
		return this.student;
	}

	public AssociateStudentModel(StudentVO student) {
		this.student = student;
		this.addButton = new Button("+");
		this.removeButton = new Button("-");
	}

	public String getRegistration() {
		return this.student.getRegistration();
	}

	public String getName() {
		return this.student.getName();
	}

	public Button getAdd() {
		return this.addButton;
	}

	public Button getRemove() {
		return this.removeButton;
	}
}

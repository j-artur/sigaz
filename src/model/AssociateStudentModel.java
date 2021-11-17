package model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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

	public Node getNode() {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(addButton, removeButton);
		return hbox;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AssociateStudentModel obj) {
			return this.getStudent().getId() == obj.getStudent().getId();
		} else {
			return false;
		}
	}
}

package model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.vo.StudentVO;

public class StudentModel {
	private StudentVO student;
	private Button editButton;
	private Button deleteButton;
	private Hyperlink hyperlink;

	public StudentVO getStudent() {
		return student;
	}

	public Button getEditButton() {
		return editButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public StudentModel(StudentVO student) {
		this.student = student;

		Image edit = new Image(getClass().getResource("../view/resources/img/icons/edit.png").toExternalForm(), 14, 14,
				false, false);
		editButton = new Button();
		editButton.setGraphic(new ImageView(edit));

		Image trash = new Image(getClass().getResource("../view/resources/img/icons/trash.png").toExternalForm(), 14, 14,
				false, false);
		deleteButton = new Button();
		deleteButton.setGraphic(new ImageView(trash));

		hyperlink = new Hyperlink("Ver disciplinas");
	}

	public String getRegistration() {
		return student.getRegistration();
	}

	public String getName() {
		return student.getName();
	}

	public Hyperlink getSubjects() {
		return hyperlink;
	}

	public Node getNode() {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(editButton, deleteButton);
		return hbox;
	}
}

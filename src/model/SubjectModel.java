package model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.vo.SubjectVO;

public class SubjectModel {
	private SubjectVO subject;
	private Button editButton;
	private Button deleteButton;

	public SubjectVO getSubject() {
		return subject;
	}

	public Button getEditButton() {
		return editButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public SubjectModel(SubjectVO subject) {
		this.subject = subject;

		Image edit = new Image(getClass().getResource("../view/resources/img/icons/edit.png").toExternalForm(), 14, 14,
				false, false);
		editButton = new Button();
		editButton.setGraphic(new ImageView(edit));

		Image trash = new Image(getClass().getResource("../view/resources/img/icons/trash.png").toExternalForm(), 14, 14,
				false, false);
		deleteButton = new Button();
		deleteButton.setGraphic(new ImageView(trash));
	}

	public String getCode() {
		return subject.getCode();
	}

	public String getName() {
		return subject.getName();
	}

	public Node getNode() {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(editButton, deleteButton);
		return hbox;
	}
}

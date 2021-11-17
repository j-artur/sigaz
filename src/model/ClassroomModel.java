package model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.vo.ClassroomVO;

public class ClassroomModel {
	private ClassroomVO classroom;
	private Hyperlink hyperlink;
	private Button editButton;
	private Button deleteButton;

	public ClassroomModel(ClassroomVO classroom) {
		this.classroom = classroom;
		this.hyperlink = new Hyperlink(classroom.getSubject().getName());

		Image edit = new Image(getClass().getResource("../view/resources/img/icons/edit.png").toExternalForm(), 14, 14,
				false, false);
		editButton = new Button();
		editButton.setGraphic(new ImageView(edit));

		Image trash = new Image(getClass().getResource("../view/resources/img/icons/trash.png").toExternalForm(), 14, 14,
				false, false);
		deleteButton = new Button();
		deleteButton.setGraphic(new ImageView(trash));
	}

	public ClassroomVO getClassroom() {
		return this.classroom;
	}

	public Node getName() {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(new Label(classroom.getSubject().getCode() + "\t"), hyperlink);
		return hbox;
	}

	public Hyperlink getHyperlink() {
		return this.hyperlink;
	}

	public String getPlace() {
		return this.classroom.getPlace();
	}

	public String getSchedule() {
		return this.classroom.getSchedule();
	}

	public String getStatus() {
		return this.classroom.isActive() ? "Em andamento" : "Concluído";
	}

	public Node getNode() {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(editButton, deleteButton);
		return hbox;
	}
}

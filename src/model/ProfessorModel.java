package model;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.vo.ProfessorVO;

public class ProfessorModel {
	private ProfessorVO professor;
	private Hyperlink hyperlink;
	private Button editButton;
	private Button deleteButton;

	public ProfessorVO getProfessor() {
		return this.professor;
	}

	public ProfessorModel(ProfessorVO professor) {
		this.professor = professor;
		this.hyperlink = new Hyperlink("Ver turmas");

		Image edit = new Image(getClass().getResource("../view/resources/img/icons/edit.png").toExternalForm(), 14, 14,
				false, false);
		editButton = new Button();
		editButton.setGraphic(new ImageView(edit));

		Image trash = new Image(getClass().getResource("../view/resources/img/icons/trash.png").toExternalForm(), 14, 14,
				false, false);
		deleteButton = new Button();
		deleteButton.setGraphic(new ImageView(trash));
	}

	public String getName() {
		return this.professor.getName();
	}

	public Hyperlink getHyperlink() {
		return this.hyperlink;
	}
}

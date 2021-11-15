package model;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.vo.ClassroomVO;

public class ClassroomModel {
	private ClassroomVO classroom;
	private Hyperlink hyperlink;

	public ClassroomModel(ClassroomVO classroom) {
		this.classroom = classroom;
		this.hyperlink = new Hyperlink(classroom.getSubject().getName());
	}

	public ClassroomVO getClassroom() {
		return this.classroom;
	}

	public Node getNode() {
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
}

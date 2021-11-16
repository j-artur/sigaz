package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.bo.ISubjectBO;
import model.bo.SubjectBO;
import view.*;

public class SubjectController {
	ISubjectBO studentBo = new SubjectBO();

	// @FXML
	// Label error;
	@FXML
	Label userName;

	@FXML
	private Button createButton;
	@FXML
	private TextField searchField;
	@FXML
	private TextField name;
	@FXML
	private TextField code;

	@FXML
	public void initialize() {
		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}

		// if (createButton != null) {
		// if (View.getViewMode() == ViewMode.PRINCIPAL)
		// createButton.setOpacity(1);
		// else
		// createButton.setOpacity(0);
		// }
	}

	public void search(ActionEvent event) throws Exception {
		System.out.println(searchField.getText());
	}

	public void create(ActionEvent event) throws Exception {
		// if (View.getViewMode() == ViewMode.PRINCIPAL) {
		View.createSubject();
		// }
	}

	public void store(ActionEvent event) {
		System.out.println(name.getText());
		System.out.println(code.getText());
	}

	public void home(ActionEvent event) throws Exception {
		View.home();
	}

	public void classrooms(ActionEvent event) throws Exception {
		View.classrooms();
	}

	public void subjects(ActionEvent event) throws Exception {
		View.subjects();
	}

	public void professors(ActionEvent event) throws Exception {
		View.professors();
	}

	public void students(ActionEvent event) throws Exception {
		View.students();
	}

	public void logout(ActionEvent event) throws Exception {
		View.setViewMode(ViewMode.RESTRICTED);
		View.login();
	}
}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.bo.IStudentBO;
import model.bo.StudentBO;
import view.*;

public class StudentController {
	IStudentBO studentBo = new StudentBO();

	// @FXML
	// Label error;
	@FXML
	Label userName;

	@FXML
	private TextField searchField;

	@FXML
	public void initialize() {
		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}
	}

	public void search(ActionEvent event) throws Exception {
		System.out.println(searchField.getText());
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

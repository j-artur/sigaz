package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bo.ISubjectBO;
import model.bo.SubjectBO;
import model.vo.SubjectVO;
import view.*;

public class SubjectController {
	private ISubjectBO subjectBo = new SubjectBO();

	@FXML
	Label error;
	@FXML
	Label userName;

	@FXML
	TableView<SubjectVO> subjectTable;
	@FXML
	TableColumn<SubjectVO, String> subjectCode;
	@FXML
	TableColumn<SubjectVO, String> subjectName;

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

		if (subjectTable != null) {
			search(null);
		}

		if (createButton != null) {
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				createButton.setOpacity(1);
			else
				createButton.setOpacity(0);
		}
	}

	public void search(ActionEvent event) {
		error.setText("");

		ObservableList<SubjectVO> subjects = FXCollections.observableArrayList();

		try {
			if (searchField.getText().isEmpty()) {
				subjects.setAll(subjectBo.findAll());
			} else {
				SubjectVO student = new SubjectVO();
				student.setName(searchField.getText());
				subjects.setAll(subjectBo.findByName(student));
			}

			subjectCode.setCellValueFactory(new PropertyValueFactory<SubjectVO, String>("code"));
			subjectName.setCellValueFactory(new PropertyValueFactory<SubjectVO, String>("name"));

			subjectTable.setItems(subjects);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
		System.out.println(searchField.getText());
	}

	public void create(ActionEvent event) throws Exception {
		if (View.getViewMode() == ViewMode.PRINCIPAL) {
			View.createSubject();
		}
	}

	public void store(ActionEvent event) {
		try {
			SubjectVO subject = new SubjectVO();

			subject.setCode(code.getText());
			subject.setName(name.getText());

			subjectBo.create(subject);

			View.subjects();
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
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

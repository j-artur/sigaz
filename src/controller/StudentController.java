package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StudentModel;
import model.bo.IStudentBO;
import model.bo.StudentBO;
import model.vo.StudentVO;
import view.*;

public class StudentController {
	IStudentBO studentBo = new StudentBO();

	@FXML
	Label error;
	@FXML
	Label userName;
	@FXML
	Button registerStudentsByPrincipal;

	@FXML
	TextField searchField;

	@FXML
	TextField nameRegister;
	@FXML
	TextField cpfRegister;
	@FXML
	TextField addressRegister;

	@FXML
	TableView<StudentModel> studentsTable;
	@FXML
	TableColumn<StudentModel, String> studentRegistration;
	@FXML
	TableColumn<StudentModel, String> studentName;
	@FXML
	TableColumn<StudentModel, Node> buttons;

	@FXML
	public void initialize() {
		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}

		if (registerStudentsByPrincipal != null) {
			registerStudentsByPrincipal.setOnAction(e -> {
				try {
					View.createStudent();
				} catch (Exception err) {
					error.setText(err.getMessage());
				}
			});
		}
	}

	public void search(ActionEvent event) {
		error.setText("");

		ObservableList<StudentModel> students = FXCollections.observableArrayList();

		try {
			List<StudentVO> list;
			if (searchField.getText().isEmpty()) {
				list = studentBo.findAll();
			} else {
				StudentVO professor = new StudentVO();
				professor.setName(searchField.getText());
				list = studentBo.findByName(professor);
			}
			list.forEach(professor -> students.add(new StudentModel(professor)));

			students.forEach(student -> {
				student.getHyperlink().setOnAction(e -> {
					try {
						studentBo.delete(student.getStudent());
						View.students();
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
			});

			studentName.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("name"));
			studentRegistration.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("name"));
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				buttons.setCellValueFactory(new PropertyValueFactory<StudentModel, Node>("hyperlink"));

			studentsTable.setItems(students);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void register(ActionEvent event) {

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

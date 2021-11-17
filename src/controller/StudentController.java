package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

	private static StudentVO student;

	public static void setStudent(StudentVO arg) {
		student = arg;
	}

	@FXML
	Label error;

	@FXML
	Label userName;
	@FXML
	Button createButton;

	@FXML
	TextField searchField;

	@FXML
	TextField name;
	@FXML
	TextField registration;
	@FXML
	TextField address;
	@FXML
	TextField email;
	@FXML
	PasswordField password;

	@FXML
	TextField nameEdit;
	@FXML
	TextField registrationEdit;
	@FXML
	TextField addressEdit;

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
		if (student != null && nameEdit != null && registrationEdit != null && addressEdit != null) {
			nameEdit.setText(student.getName());
			registrationEdit.setText(student.getRegistration());
			addressEdit.setText(student.getAddress());
		}

		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}

		if (createButton != null) {
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				createButton.setOpacity(1);
			else
				createButton.setOpacity(0);
		}

		if (studentsTable != null) {
			search(null);
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
				StudentVO student = new StudentVO();
				student.setName(searchField.getText());
				list = studentBo.findByName(student);
			}
			list.forEach(student -> students.add(new StudentModel(student)));

			students.forEach(student -> {
				student.getEditButton().setOnAction(e -> {
					try {
						View.editStudent(student.getStudent());
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
				student.getDeleteButton().setOnAction(e -> {
					try {
						studentBo.delete(student.getStudent());
						View.students();
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
			});

			studentName.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("name"));
			studentRegistration.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("registration"));
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				buttons.setCellValueFactory(new PropertyValueFactory<StudentModel, Node>("node"));

			studentsTable.setItems(students);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void create(ActionEvent event) throws Exception {
		if (View.getViewMode() == ViewMode.PRINCIPAL) {
			View.createStudent();
		}
	}

	public void store(ActionEvent event) {
		try {
			StudentVO newStudent = new StudentVO();

			newStudent.setName(name.getText());
			newStudent.setAddress(address.getText());
			newStudent.setRegistration(registration.getText());
			newStudent.setEmail(email.getText());
			newStudent.setPassword(password.getText());

			studentBo.create(newStudent);

			View.students();
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void update(ActionEvent event) {
		try {
			StudentVO newStudent = new StudentVO();

			newStudent.setName(name.getText());
			newStudent.setAddress(address.getText());
			newStudent.setRegistration(registration.getText());
			newStudent.setEmail(student.getEmail());
			newStudent.setPassword(student.getPassword());

			studentBo.update(student, newStudent);

			View.students();
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

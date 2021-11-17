package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GradeModel;
import model.bo.ClassroomBO;
import model.vo.GradeVO;
import model.vo.StudentVO;
import view.*;

public class BoletimController {
	private ClassroomBO classroomBo = new ClassroomBO();

	private static StudentVO student;

	public static void setStudent(StudentVO arg) {
		student = arg;
	}

	@FXML
	Label error;
	@FXML
	Label userName;

	@FXML
	Label classroomCode;
	@FXML
	Label subjectName;

	@FXML
	TableView<GradeModel> table;
	@FXML
	TableColumn<GradeModel, String> subject;
	@FXML
	TableColumn<GradeModel, String> n1;
	@FXML
	TableColumn<GradeModel, String> n2;
	@FXML
	TableColumn<GradeModel, String> n3;
	@FXML
	TableColumn<GradeModel, String> nfinal;
	@FXML
	TableColumn<GradeModel, String> frequency;
	@FXML
	TableColumn<GradeModel, String> status;

	ObservableList<GradeModel> grades;

	@FXML
	public void initialize() {
		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}

		grades = FXCollections.observableArrayList();
		try {
			List<GradeVO> list = classroomBo.findGradesByStudent(student);

			list.forEach(grade -> grades.add(new GradeModel(grade)));

			subject.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("subject"));
			n1.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n1"));
			n2.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n2"));
			n3.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n3"));
			nfinal.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("nFinal"));
			frequency.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("frequency"));
			status.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("status"));

			table.setItems(grades);
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

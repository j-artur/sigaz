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
import model.bo.IClassroomBO;
import model.vo.GradeVO;
import model.vo.StudentVO;
import view.*;

public class ConcludedSubjectsController {
	private IClassroomBO classroomBo = new ClassroomBO();

	private static StudentVO student;

	public static void setStudent(StudentVO arg) {
		student = arg;
	}

	@FXML
	Label error;
	@FXML
	Label userName;
	@FXML
	Label studentName;

	@FXML
	TableView<GradeModel> ongoingSubjects;
	@FXML
	TableColumn<GradeModel, String> subjectName;
	@FXML
	TableColumn<GradeModel, String> P1;
	@FXML
	TableColumn<GradeModel, String> P2;
	@FXML
	TableColumn<GradeModel, String> P3;
	@FXML
	TableColumn<GradeModel, String> average;
	@FXML
	TableColumn<GradeModel, String> frequency;
	@FXML
	TableColumn<GradeModel, String> status;

	@FXML
	TableView<GradeModel> concludedSubjects;
	@FXML
	TableColumn<GradeModel, String> subjectName1;
	@FXML
	TableColumn<GradeModel, String> P11;
	@FXML
	TableColumn<GradeModel, String> P21;
	@FXML
	TableColumn<GradeModel, String> P31;
	@FXML
	TableColumn<GradeModel, String> average1;
	@FXML
	TableColumn<GradeModel, String> frequency1;
	@FXML
	TableColumn<GradeModel, String> status1;

	ObservableList<GradeModel> ongoing;
	ObservableList<GradeModel> concluded;

	@FXML
	public void initialize() {
		userName.setText(AuthController.getLoggedUser().getName());
		studentName.setText(student.getName() + " - Disciplinas");

		ongoing = FXCollections.observableArrayList();
		concluded = FXCollections.observableArrayList();

		try {
			List<GradeVO> list = classroomBo.findGradesByStudent(student);

			list.forEach(grade -> {
				if (grade.getClassroom().isActive())
					ongoing.add(new GradeModel(grade));
				else
					concluded.add(new GradeModel(grade));
			});

			subjectName.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("subject"));
			P1.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n1"));
			P2.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n2"));
			P3.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n3"));
			average.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("nFinal"));
			frequency.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("frequency"));
			status.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("status"));

			subjectName1.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("subject"));
			P11.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n1"));
			P21.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n2"));
			P31.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("n3"));
			average1.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("nFinal"));
			frequency1.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("frequency"));
			status1.setCellValueFactory(new PropertyValueFactory<GradeModel, String>("status"));

			ongoingSubjects.setItems(ongoing);
			concludedSubjects.setItems(concluded);
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

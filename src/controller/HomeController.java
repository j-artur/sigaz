package controller;

import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import model.ClassroomModel;
import model.bo.*;
import model.vo.*;
import view.*;

public class HomeController {
	private IClassroomBO classroomBo = new ClassroomBO();

	@FXML
	Label error;
	@FXML
	Label userName;

	@FXML
	Button seeMineButton;

	@FXML
	TableView<ClassroomModel> classroomsTable;
	@FXML
	TableColumn<ClassroomModel, Node> classroomName;
	@FXML
	TableColumn<ClassroomModel, String> classroomPlace;
	@FXML
	TableColumn<ClassroomModel, String> classroomSchedule;
	@FXML
	TableColumn<ClassroomModel, String> classroomStatus;

	private ObservableList<ClassroomModel> classrooms = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		UserVO user = AuthController.getLoggedUser();
		userName.setText(user.getName());

		try {
			if (user instanceof PrincipalVO) {
				classroomsTable.setOpacity(0);
				seeMineButton.setOpacity(0);
				return;
			}

			if (user instanceof StudentVO student) {
				List<ClassroomVO> list = classroomBo.findByStudent(student);
				List<ClassroomModel> classes = new ArrayList<ClassroomModel>();
				list.forEach(classroom -> classes.add(new ClassroomModel(classroom)));
				classrooms.setAll(classes);
				seeMineButton.setOpacity(1);
				seeMineButton.setOnAction(ev -> {
					try {
						View.subjects(student);
					} catch (Exception e) {
						error.setText(e.getMessage());
					}
				});
			}
			if (user instanceof ProfessorVO professor) {
				List<ClassroomVO> list = classroomBo.findByProfessor(professor);
				List<ClassroomModel> classes = new ArrayList<ClassroomModel>();
				list.forEach(classroom -> classes.add(new ClassroomModel(classroom)));
				classrooms.setAll(classes);
				seeMineButton.setOpacity(0);
			}

			classrooms.forEach(classroom -> {
				classroom.getHyperlink().setOnAction(e -> {
					try {
						View.classroom(classroom.getClassroom());
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
			});

			classroomName.setCellValueFactory(new PropertyValueFactory<ClassroomModel, Node>("name"));
			classroomPlace.setCellValueFactory(new PropertyValueFactory<ClassroomModel, String>("place"));
			classroomSchedule.setCellValueFactory(new PropertyValueFactory<ClassroomModel, String>("schedule"));
			classroomStatus.setCellValueFactory(new PropertyValueFactory<ClassroomModel, String>("status"));

			classroomsTable.setItems(classrooms);
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

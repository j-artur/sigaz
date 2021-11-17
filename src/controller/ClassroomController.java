package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.bo.*;
import model.vo.*;
import view.*;

public class ClassroomController {
	private IClassroomBO classroomBo = new ClassroomBO();
	private IProfessorBO professorBo = new ProfessorBO();

	private static ClassroomVO classroom;
	private static ProfessorVO professor;

	public static void setClassroom(ClassroomVO arg) {
		classroom = arg;
	}

	public static void setProfessor(ProfessorVO arg) {
		professor = arg;
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
	Label professorName;

	@FXML
	private Button gradeButton;
	@FXML
	private Button createButton;
	@FXML
	private ComboBox<ProfessorVO> searchBox;

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
	@FXML
	TableColumn<ClassroomModel, Node> buttons;

	@FXML
	public void initialize() {
		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}

		if (classroom != null) {
			try {
				classroomCode.setText(classroom.getSubject().getCode());
				subjectName.setText(classroom.getSubject().getName());
				professorName.setText(classroom.getProfessor().getName());

				if (gradeButton != null)
					if (View.getViewMode() == ViewMode.PROFESSOR)
						gradeButton.setOpacity(1);
					else
						gradeButton.setOpacity(0);
			} catch (Exception e) {
				if (error != null)
					error.setText(e.getMessage());
				else
					e.printStackTrace();
			}
		} else {
			if (createButton != null)
				if (View.getViewMode() == ViewMode.PRINCIPAL)
					createButton.setOpacity(1);
				else
					createButton.setOpacity(0);

			if (searchBox != null) {
				searchBox.setOnAction(e -> {
					professor = searchBox.getValue();
					search(null);
				});

				try {
					ObservableList<ProfessorVO> professors = FXCollections.observableArrayList();
					professors.add(null);
					professors.addAll(professorBo.findAll());
					searchBox.setItems(professors);

					search(null);
				} catch (Exception e) {
					error.setText(e.getMessage());
				}
			}
		}
	}

	public void search(ActionEvent event) {
		error.setText("");
		List<ClassroomVO> list;
		ObservableList<ClassroomModel> classrooms = FXCollections.observableArrayList();
		try {
			if (professor == null) {
				list = classroomBo.findAll();
			} else {
				list = classroomBo.findByProfessor(professor);
			}
			list.forEach(classroom -> classrooms.add(new ClassroomModel(classroom)));

			classrooms.forEach(classroom -> {
				classroom.getHyperlink().setOnAction(e -> {
					try {
						View.classroom(classroom.getClassroom());
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
				classroom.getEditButton().setOnAction(e -> {
					try {
						View.editClassroom(classroom.getClassroom());
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
				classroom.getDeleteButton().setOnAction(e -> {
					try {
						classroomBo.delete(classroom.getClassroom());
						View.classrooms();
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
			});

			classroomName.setCellValueFactory(new PropertyValueFactory<ClassroomModel, Node>("name"));
			classroomPlace.setCellValueFactory(new PropertyValueFactory<ClassroomModel, String>("place"));
			classroomSchedule.setCellValueFactory(new PropertyValueFactory<ClassroomModel, String>("schedule"));
			classroomStatus.setCellValueFactory(new PropertyValueFactory<ClassroomModel, String>("status"));
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				buttons.setCellValueFactory(new PropertyValueFactory<ClassroomModel, Node>("node"));

			classroomsTable.setItems(classrooms);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void create(ActionEvent event) throws Exception {
		if (View.getViewMode() == ViewMode.PRINCIPAL) {
			View.createClassroom();
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

	public void grade(ActionEvent event) throws Exception {
		if (View.getViewMode() == ViewMode.PROFESSOR)
			View.grade();
	}

	public void logout(ActionEvent event) throws Exception {
		View.setViewMode(ViewMode.RESTRICTED);
		View.login();
	}
}

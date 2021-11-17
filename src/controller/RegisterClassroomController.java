package controller;

import java.util.Arrays;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AssociateStudentModel;
import model.bo.*;
import model.vo.*;
import view.View;
import view.ViewMode;

public class RegisterClassroomController {
	private IClassroomBO classroomBo = new ClassroomBO();
	private IProfessorBO professorBo = new ProfessorBO();
	private ISubjectBO subjectBo = new SubjectBO();
	private IStudentBO studentBo = new StudentBO();

	private static ClassroomVO classroom;

	public static void setClassroom(ClassroomVO arg) {
		classroom = arg;
	}

	@FXML
	private Label error;
	@FXML
	private Label userName;

	@FXML
	private ComboBox<SubjectVO> subjectBox;
	@FXML
	private ComboBox<ProfessorVO> professorBox;
	@FXML
	private TextField schedule;
	@FXML
	private TextField place;

	@FXML
	private TextField search;

	@FXML
	private TableView<AssociateStudentModel> studentsTable;
	@FXML
	private TableColumn<AssociateStudentModel, String> studentRegistration;
	@FXML
	private TableColumn<AssociateStudentModel, String> studentName;
	@FXML
	private TableColumn<AssociateStudentModel, Node> buttons;

	private ObservableList<AssociateStudentModel> selectedStudents;

	@FXML
	private void initialize() {
		if (userName != null) {
			userName.setText(AuthController.getLoggedUser().getName());
		}

		if (subjectBox != null && professorBox != null) {
			try {
				ObservableList<SubjectVO> subjects = FXCollections.observableArrayList();
				subjects.addAll(subjectBo.findAll());
				subjectBox.setItems(subjects);

				ObservableList<ProfessorVO> professors = FXCollections.observableArrayList();
				professors.addAll(professorBo.findAll());
				professorBox.setItems(professors);
			} catch (Exception e) {
				error.setText(e.getMessage());
			}
		}

		selectedStudents = FXCollections.observableArrayList();

		if (studentsTable != null)
			search(null);
	}

	public void search(ActionEvent event) {
		error.setText("");

		ObservableList<AssociateStudentModel> students = FXCollections.observableArrayList();
		try {
			List<StudentVO> list;
			if (classroom == null) {
				if (search.getText().isEmpty()) {
					list = studentBo.findAll();
				} else {
					StudentVO student = new StudentVO();
					student.setName(search.getText());
					list = studentBo.findByName(student);
				}
			} else {
				list = Arrays.asList(classroom.getStudents());
			}
			list.forEach(student -> students.add(new AssociateStudentModel(student)));

			students.forEach(student -> {
				Button addButton = student.getAdd();
				Button removeButton = student.getRemove();

				addButton.setOnAction(e -> {
					selectedStudents.add(student);
					addButton.setOpacity(0);
					removeButton.setOpacity(1);
				});

				removeButton.setOnAction(e -> {
					selectedStudents.remove(student);
					addButton.setOpacity(1);
					removeButton.setOpacity(0);
				});

				if (selectedStudents.contains(student)) {
					addButton.setOpacity(0);
				} else {
					removeButton.setOpacity(0);
				}
			});

			studentRegistration.setCellValueFactory(new PropertyValueFactory<AssociateStudentModel, String>("registration"));
			studentName.setCellValueFactory(new PropertyValueFactory<AssociateStudentModel, String>("name"));
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				buttons.setCellValueFactory(new PropertyValueFactory<AssociateStudentModel, Node>("node"));

			studentsTable.setItems(students);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void saveEditions(ActionEvent event) {
		View.closeSecondaryWindow();
	}

	public void registerClassroom(ActionEvent event) {
		try {
			ClassroomVO newClassroom = new ClassroomVO();

			newClassroom.setSubject(subjectBox.getValue());
			newClassroom.setProfessor(professorBox.getValue());
			newClassroom.setActive(true);
			newClassroom.setSchedule(schedule.getText());
			newClassroom.setPlace(place.getText());

			StudentVO[] students = new StudentVO[selectedStudents.size()];
			for (int i = 0; i < students.length; i++) {
				students[i] = selectedStudents.get(i).getStudent();
			}

			newClassroom.setStudents(students);

			classroomBo.create(newClassroom);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void editClassroom(ActionEvent event) {
		try {
			ClassroomVO newClassroom = new ClassroomVO();

			newClassroom.setSubject(subjectBox.getValue());
			newClassroom.setProfessor(professorBox.getValue());
			newClassroom.setActive(true);
			newClassroom.setSchedule(schedule.getText());
			newClassroom.setPlace(place.getText());

			StudentVO[] students = new StudentVO[selectedStudents.size()];
			for (int i = 0; i < students.length; i++) {
				students[i] = selectedStudents.get(i).getStudent();
			}

			newClassroom.setStudents(students);

			classroomBo.update(classroom, newClassroom);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void selectStudents(ActionEvent event) throws Exception {
		View.associateStudents();
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

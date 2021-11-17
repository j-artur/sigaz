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
import model.SubjectModel;
import model.bo.ISubjectBO;
import model.bo.SubjectBO;
import model.vo.SubjectVO;
import view.*;

public class SubjectController {
	private ISubjectBO subjectBo = new SubjectBO();

	private static SubjectVO subject;

	public static void setSubject(SubjectVO arg) {
		subject = arg;
	}

	@FXML
	Label error;

	@FXML
	Label userName;
	@FXML
	TableView<SubjectModel> subjectTable;
	@FXML
	TableColumn<SubjectModel, String> subjectCode;
	@FXML
	TableColumn<SubjectModel, String> subjectName;
	@FXML
	TableColumn<SubjectModel, Node> buttons;
	@FXML
	private Button createButton;
	@FXML
	private TextField searchField;

	@FXML
	private TextField name;
	@FXML
	private TextField code;

	@FXML
	private TextField nameEdit;
	@FXML
	private TextField codeEdit;

	@FXML
	public void initialize() {
		if (subject != null && nameEdit != null && codeEdit != null) {
			nameEdit.setText(subject.getName());
			codeEdit.setText(subject.getCode());
		}

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

		ObservableList<SubjectModel> subjects = FXCollections.observableArrayList();

		try {
			List<SubjectVO> list;
			if (searchField.getText().isEmpty()) {
				list = subjectBo.findAll();
			} else {
				SubjectVO student = new SubjectVO();
				student.setName(searchField.getText());
				list = subjectBo.findByName(student);
			}
			list.forEach(subject -> subjects.add(new SubjectModel(subject)));

			subjects.forEach(subject -> {
				subject.getEditButton().setOnAction(e -> {
					try {
						View.editSubject(subject.getSubject());
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
				subject.getDeleteButton().setOnAction(e -> {
					try {
						subjectBo.delete(subject.getSubject());
						View.subjects();
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
			});

			subjectCode.setCellValueFactory(new PropertyValueFactory<SubjectModel, String>("code"));
			subjectName.setCellValueFactory(new PropertyValueFactory<SubjectModel, String>("name"));
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				buttons.setCellValueFactory(new PropertyValueFactory<SubjectModel, Node>("node"));

			subjectTable.setItems(subjects);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
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

	public void update(ActionEvent event) {
		try {
			SubjectVO newSubject = new SubjectVO();

			newSubject.setCode(codeEdit.getText());
			newSubject.setName(nameEdit.getText());

			subjectBo.update(subject, newSubject);

			View.subjects();
		} catch (Exception e) {
			e.printStackTrace();
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

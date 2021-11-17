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
import model.ProfessorModel;
import model.bo.IProfessorBO;
import model.bo.ProfessorBO;
import model.vo.ProfessorVO;
import view.*;

public class ProfessorController {
	private IProfessorBO professorBo = new ProfessorBO();

	private static ProfessorVO professor;

	public static void setProfessor(ProfessorVO arg) {
		professor = arg;
	}

	@FXML
	Label error;
	@FXML
	Label userName;

	@FXML
	TableView<ProfessorModel> professorsTable;
	@FXML
	TableColumn<ProfessorModel, String> professorName;
	@FXML
	TableColumn<ProfessorModel, Node> buttons;
	@FXML
	private Button createButton;
	@FXML
	private TextField searchField;

	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField cpf;
	@FXML
	private TextField address;
	@FXML
	private PasswordField password;

	@FXML
	private TextField nameEdit;
	@FXML
	private TextField cpfEdit;
	@FXML
	private TextField addressEdit;

	@FXML
	public void initialize() {
		if (professor != null && nameEdit != null && cpfEdit != null && addressEdit != null) {
			nameEdit.setText(professor.getName());
			cpfEdit.setText(professor.getCpf());
			addressEdit.setText(professor.getAddress());
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

		if (professorsTable != null) {
			search(null);
		}
	}

	public void search(ActionEvent event) {
		error.setText("");

		ObservableList<ProfessorModel> professors = FXCollections.observableArrayList();

		try {
			List<ProfessorVO> list;
			if (searchField.getText().isEmpty()) {
				list = professorBo.findAll();
			} else {
				ProfessorVO professor = new ProfessorVO();
				professor.setName(searchField.getText());
				list = professorBo.findByName(professor);
			}
			list.forEach(professor -> professors.add(new ProfessorModel(professor)));

			professors.forEach(professor -> {
				professor.getEditButton().setOnAction(e -> {
					try {
						View.editProfessor(professor.getProfessor());
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
				professor.getDeleteButton().setOnAction(e -> {
					try {
						professorBo.delete(professor.getProfessor());
						View.professors();
					} catch (Exception err) {
						error.setText(err.getMessage());
					}
				});
			});

			professorName.setCellValueFactory(new PropertyValueFactory<ProfessorModel, String>("name"));
			if (View.getViewMode() == ViewMode.PRINCIPAL)
				buttons.setCellValueFactory(new PropertyValueFactory<ProfessorModel, Node>("node"));

			professorsTable.setItems(professors);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void create(ActionEvent event) throws Exception {
		if (View.getViewMode() == ViewMode.PRINCIPAL) {
			View.createProfessor();
		}
	}

	public void store(ActionEvent event) {
		try {
			ProfessorVO newProfessor = new ProfessorVO();

			newProfessor.setName(name.getText());
			newProfessor.setAddress(address.getText());
			newProfessor.setCpf(cpf.getText());
			newProfessor.setEmail(email.getText());
			newProfessor.setPassword(password.getText());

			professorBo.create(newProfessor);

			View.professors();
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void update(ActionEvent event) {
		try {
			ProfessorVO newProfessor = new ProfessorVO();

			newProfessor.setName(nameEdit.getText());
			newProfessor.setAddress(addressEdit.getText());
			newProfessor.setCpf(cpfEdit.getText());
			newProfessor.setEmail(professor.getEmail());
			newProfessor.setPassword(professor.getPassword());

			professorBo.update(professor, newProfessor);

			View.professors();
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

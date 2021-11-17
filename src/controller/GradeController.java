package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GradeStudentModel;
import model.bo.ClassroomBO;
import model.bo.IClassroomBO;
import model.vo.ClassroomVO;
import model.vo.GradeVO;

public class GradeController {
	private IClassroomBO classroomBo = new ClassroomBO();

	private static ClassroomVO classroom;

	public static void setClassroom(ClassroomVO arg) {
		classroom = arg;
	}

	@FXML
	Label error;

	@FXML
	Label classroomCode;
	@FXML
	Label subjectName;

	@FXML
	TableView<GradeStudentModel> table;
	@FXML
	TableColumn<GradeStudentModel, String> registration;
	@FXML
	TableColumn<GradeStudentModel, String> name;
	@FXML
	TableColumn<GradeStudentModel, TextField> n1;
	@FXML
	TableColumn<GradeStudentModel, TextField> n2;
	@FXML
	TableColumn<GradeStudentModel, TextField> n3;
	@FXML
	TableColumn<GradeStudentModel, TextField> nfinal;
	@FXML
	TableColumn<GradeStudentModel, TextField> frequency;

	ObservableList<GradeStudentModel> grades;

	@FXML
	public void initialize() {
		classroomCode.setText(classroom.getSubject().getCode());
		subjectName.setText(classroom.getSubject().getName());

		grades = FXCollections.observableArrayList();
		try {
			List<GradeVO> list = classroomBo.findAllGrades(classroom);

			list.forEach(grade -> grades.add(new GradeStudentModel(grade)));

			grades.forEach(grade -> {
				grade.getN1().textProperty().addListener((ob, old, value) -> {
					try {
						grade.getGrade().setN1(Integer.valueOf(value));
						int nfinal = Double
								.valueOf(
										Math.ceil((grade.getGrade().getN1() + grade.getGrade().getN2() + grade.getGrade().getN3()) / 3))
								.intValue();
						grade.getGrade().setNFinal(nfinal);
						grade.getNFinal().setText(String.valueOf(grade.getGrade().getNFinal()));
					} catch (Exception e) {
					}
				});
				grade.getN2().textProperty().addListener((ob, old, value) -> {
					try {
						grade.getGrade().setN2(Integer.valueOf(value));
						int nfinal = Double
								.valueOf(
										Math.ceil((grade.getGrade().getN1() + grade.getGrade().getN2() + grade.getGrade().getN3()) / 3))
								.intValue();
						grade.getGrade().setNFinal(nfinal);
						grade.getNFinal().setText(String.valueOf(grade.getGrade().getNFinal()));
					} catch (Exception e) {
					}
				});
				grade.getN3().textProperty().addListener((ob, old, value) -> {
					try {
						grade.getGrade().setN3(Integer.valueOf(value));
						int nfinal = Double
								.valueOf(
										Math.ceil((grade.getGrade().getN1() + grade.getGrade().getN2() + grade.getGrade().getN3()) / 3))
								.intValue();
						grade.getGrade().setNFinal(nfinal);
						grade.getNFinal().setText(String.valueOf(grade.getGrade().getNFinal()));
					} catch (Exception e) {
					}
				});

			});

			registration.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, String>("registration"));
			name.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, String>("name"));
			n1.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, TextField>("n1"));
			n2.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, TextField>("n2"));
			n3.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, TextField>("n3"));
			nfinal.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, TextField>("nFinal"));
			frequency.setCellValueFactory(new PropertyValueFactory<GradeStudentModel, TextField>("frequency"));

			table.setItems(grades);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void save(ActionEvent event) {
		try {
			grades.forEach(grade -> {
				try {
					classroomBo.grade(grade.getGrade());
				} catch (Exception e) {
					error.setText(e.getMessage());
				}
			});
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	public void finishClassroom(ActionEvent event) throws Exception {
		classroom.setActive(false);
		classroomBo.update(classroom, classroom);
	}
}

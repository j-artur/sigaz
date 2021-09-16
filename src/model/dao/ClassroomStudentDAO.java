package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.vo.ClassroomVO;
import model.vo.StudentVO;

public class ClassroomStudentDAO extends BaseDAO {
	public void create(ClassroomVO classroom, StudentVO student) {
		String query = "INSERT INTO students_classrooms (student_id, classroom_id) VALUES (?, ?)";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, student.getId());
			statement.setLong(2, classroom.getId());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível adicionar o aluno à turma!");
			e.printStackTrace();
		}
	}

	public void delete(ClassroomVO classroom, StudentVO student) {
		String query = "DELETE FROM students_classrooms WHERE student_id = ? AND classroom_id = ?";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, student.getId());
			statement.setLong(2, classroom.getId());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível remover o aluno da turma!");
			e.printStackTrace();
		}
	}

	public void delete(ClassroomVO classroom) {
		String query = "DELETE FROM students_classrooms WHERE classroom_id = ?";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, classroom.getId());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível remover os alunos da turma!");
			e.printStackTrace();
		}
	}

	public void delete(StudentVO student) {
		String query = "DELETE FROM students_classrooms WHERE student_id = ?";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, student.getId());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível remover o aluno das turmas!");
			e.printStackTrace();
		}
	}
}

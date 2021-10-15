package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.vo.ClassroomVO;
import model.vo.StudentVO;

public class ClassroomStudentDAO extends BaseDAO {
	public void create(ClassroomVO classroom, StudentVO student) throws SQLException {
		String query = "INSERT INTO students_classrooms (student_id, classroom_id) VALUES (?, ?)";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setLong(1, student.getId());
		statement.setLong(2, classroom.getId());
		statement.execute();
	}

	public void delete(ClassroomVO classroom, StudentVO student) throws SQLException {
		String query = "DELETE FROM students_classrooms WHERE student_id = ? AND classroom_id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setLong(1, student.getId());
		statement.setLong(2, classroom.getId());
		statement.execute();
	}

	public void delete(ClassroomVO classroom) throws SQLException {
		String query = "DELETE FROM students_classrooms WHERE classroom_id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setLong(1, classroom.getId());
		statement.execute();
	}

	public void delete(StudentVO student) throws SQLException {
		String query = "DELETE FROM students_classrooms WHERE student_id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setLong(1, student.getId());
		statement.execute();
	}
}

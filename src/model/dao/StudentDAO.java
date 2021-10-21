package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.StudentVO;
import model.vo.ClassroomVO;

public class StudentDAO extends BaseDAO implements IUserDAO<StudentVO> {

	public void create(StudentVO student) throws SQLException {
		String query = "INSERT INTO students (name, email, password, registration, address) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, student.getName());
		statement.setString(2, student.getEmail());
		statement.setString(3, student.getPassword());
		statement.setString(4, student.getRegistration());
		statement.setString(5, student.getAddress());
		statement.execute();
	}

	public List<StudentVO> findAll() throws SQLException {
		String query = "SELECT * FROM students";
		List<StudentVO> studentList = new ArrayList<StudentVO>();

		Statement statement = this.getConnection().createStatement();
		ResultSet set = statement.executeQuery(query);

		try {
			while (set.next()) {
				StudentVO student = new StudentVO();
				student.setId(set.getLong("id"));
				student.setName(set.getString("name"));
				student.setEmail(set.getString("email"));
				student.setPassword(set.getString("password"));
				student.setRegistration(set.getString("registration"));
				student.setAddress(set.getString("address"));
				studentList.add(student);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return studentList;
	}

	public List<StudentVO> findByName(StudentVO data) throws SQLException {
		String query = "SELECT * FROM students WHERE name LIKE ?";
		List<StudentVO> studentList = new ArrayList<StudentVO>();

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, "%" + data.getName() + "%");
		ResultSet set = statement.executeQuery();

		try {
			while (set.next()) {
				StudentVO student = new StudentVO();
				student.setId(set.getLong("id"));
				student.setEmail(set.getString("email"));
				student.setPassword(set.getString("password"));
				student.setRegistration(set.getString("registration"));
				student.setAddress(set.getString("address"));
				studentList.add(student);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return studentList;
	}

	public StudentVO findByEmail(StudentVO data) throws SQLException {
		String sql = "SELECT * FROM students WHERE email = ?";
		StudentVO student = null;

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setString(1, data.getEmail());
		ResultSet set = statement.executeQuery();

		try {
			if (set.next()) {
				student = new StudentVO();
				student.setId(set.getLong("id"));
				student.setName(set.getString("name"));
				student.setEmail(set.getString("email"));
				student.setPassword(set.getString("password"));
				student.setRegistration(set.getString("registration"));
				student.setAddress(set.getString("address"));
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return student;
	}

	public List<StudentVO> findByClassroom(ClassroomVO classroom) throws SQLException {
		String sql = "SELECT * FROM students, students_classrooms WHERE students_classrooms.classroom_id = ? AND students.id = students_classrooms.student_id";
		List<StudentVO> studentList = new ArrayList<StudentVO>();

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, classroom.getId());
		ResultSet set = statement.executeQuery();

		try {
			while (set.next()) {
				StudentVO student = new StudentVO();
				student.setId(set.getLong("students.id"));
				student.setName(set.getString("students.name"));
				student.setEmail(set.getString("students.email"));
				student.setPassword(set.getString("students.password"));
				student.setRegistration(set.getString("students.registration"));
				student.setAddress(set.getString("students.address"));
				studentList.add(student);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return studentList;
	}

	public void update(StudentVO student, StudentVO data) throws SQLException {
		String query = "UPDATE students SET name = ?, email = ?, password = ?, registration = ?, address = ? WHERE id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, data.getName());
		statement.setString(2, data.getEmail());
		statement.setString(3, data.getPassword());
		statement.setString(4, data.getRegistration());
		statement.setString(5, data.getAddress());
		statement.setLong(6, student.getId());
		statement.executeUpdate();
	}

	public void delete(StudentVO student) throws SQLException {
		String query = "DELETE FROM students WHERE id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setLong(1, student.getId());
		statement.executeUpdate();
	}

}

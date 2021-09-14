package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends BaseDAO{
	
	public void create(StudentVO student) {
		String query = "INSERT INTO students (name, email, password, registration, address) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setString(1, student.getName());
			statement.setString(2, student.getEmail());
			statement.setString(3, student.getPassword());
			statement.setString(4, student.getRegistration());
			statement.setString(5, student.getAdress());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível adicionar o aluno!");
			e.printStackTrace();
		}
	}
	
	public List<StudentVO> findAll() {
		String query = "SELECT * FROM students";
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		
		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet set = statement.executeQuery(query);

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

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar os alunos!");
			e.printStackTrace();
		}

		return studentList;
	}
	
	public void update(StudentVO student) {
		String query = "UPDATE students SET name = ?, email = ?, password = ?, registration = ?, address = ? WHERE id = ?";
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setString(1, student.getName());
			statement.setString(2, student.getEmail());
			statement.setString(3, student.getPassword());
			statement.setString(4, student.getRegistration());
			statement.setString(5, student.getAddress());
			statement.setLong(6, student.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar o aluno!");
			e.printStackTrace();
		}
	}
	
	public void delete(StudentVO student) {
		String query = "DELETE FROM students WHERE id = ?";
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, student.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível excluir o aluno!");
			e.printStackTrace();
		}
	}
}

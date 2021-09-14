package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.GradeVO;

public class GradeDAO extends BaseDAO {
	
	public void create(GradeVO grade) {
		String query = "INSERT INTO grades (student_id, classroom_id, n1, n2, n3, nfinal, frequency) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, grade.getStudent().getId());
			statement.setLong(2, grade.getClassroom().getId());
			statement.setInt(3, grade.getN1());
			statement.setInt(4, grade.getN2());
			statement.setInt(5, grade.getN3());
			statement.setInt(6, grade.getNFinal());
			statement.setDouble(7, grade.getFrequency());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível adicionar o boletim!");
			e.printStackTrace();
		}
	}
	
	public List<GradeVO> findAll() {
		String query = "SELECT * FROM grades";
		List<GradeVO> gradeList = new ArrayList<GradeVO>();

		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet set = statement.executeQuery(query);

			while (set.next()) {
				GradeVO grade = new GradeVO();
				grade.setId(set.getLong("id"));
				grade.setN1(set.getInt("n1"));
				grade.setN2(set.getInt("n2"));
				grade.setN3(set.getInt("n3"));
				grade.setNFinal(set.getInt("nfinal"));
				grade.setFrequency(set.getDouble("frequency"));
				gradeList.add(grade);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar os boletins!");
			e.printStackTrace();
		}

		return gradeList;
	}
	
	public void update(GradeVO grade) {
		String query = "UPDATE grades SET classroom_id = ?, n1 = ?, n2 = ?, n3 = ?, nfinal = ?, frequency = ? WHERE id = ?";
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, grade.getClassroom().getId());
			statement.setInt(2, grade.getN1());
			statement.setInt(3, grade.getN2());
			statement.setInt(4, grade.getN3());
			statement.setInt(5, grade.getNFinal());
			statement.setDouble(6, grade.getFrequency());
			statement.setLong(7, grade.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar o boletim!");
			e.printStackTrace();
		}
	}
	
	public void delete(GradeVO grade) {
		String query = "DELETE FROM grades WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, grade.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o boletim!");
			e.printStackTrace();
		}
	}
	
}

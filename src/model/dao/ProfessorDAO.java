package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ProfessorVO;

public class ProfessorDAO extends BaseDAO {
	public void create(ProfessorVO professor) {
		String sql = "INSERT INTO professors (name, email, password, address, cpf) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, professor.getName());
			statement.setString(2, professor.getEmail());
			statement.setString(3, professor.getPassword());
			statement.setString(4, professor.getAddress());
			statement.setString(5, professor.getCpf());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível salvar o professor!");
			e.printStackTrace();
		}
	}

	public List<ProfessorVO> findAll() {
		String sql = "SELECT * FROM professors";
		List<ProfessorVO> professorList = new ArrayList<ProfessorVO>();

		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet set = statement.executeQuery(sql);

			while (set.next()) {
				ProfessorVO professor = new ProfessorVO();
				professor.setId(set.getLong("id"));
				professor.setName(set.getString("name"));
				professor.setEmail(set.getString("email"));
				professor.setPassword(set.getString("password"));
				professor.setAddress(set.getString("address"));
				professor.setCpf(set.getString("cpf"));
				professorList.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar os professores!");
			e.printStackTrace();
		}

		return professorList;
	}

	public List<ProfessorVO> findByName(ProfessorVO data) {
		String sql = "SELECT * FROM professors WHERE name LIKE ?";
		List<ProfessorVO> professorList = new ArrayList<ProfessorVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, "%" + data.getName() + "%");
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				ProfessorVO professor = new ProfessorVO();
				professor.setId(set.getLong("id"));
				professor.setName(set.getString("name"));
				professor.setEmail(set.getString("email"));
				professor.setPassword(set.getString("password"));
				professor.setAddress(set.getString("address"));
				professor.setCpf(set.getString("cpf"));
				professorList.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar o professor!");
			e.printStackTrace();
		}

		return professorList;
	}

	public ProfessorVO findByEmail(ProfessorVO data) {
		String sql = "SELECT * FROM professors WHERE email = ?";
		ProfessorVO professor = null;

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, data.getEmail());
			ResultSet set = statement.executeQuery();

			if (set.next()) {
				professor = new ProfessorVO();
				professor.setId(set.getLong("id"));
				professor.setName(set.getString("name"));
				professor.setEmail(set.getString("email"));
				professor.setPassword(set.getString("password"));
				professor.setAddress(set.getString("address"));
				professor.setCpf(set.getString("cpf"));
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar o professor!");
			e.printStackTrace();
		}

		return professor;
	}

	public void update(ProfessorVO professor, ProfessorVO data) {
		String sql = "UPDATE professors SET name = ?, email = ?, password = ?, address = ?, cpf = ? WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, data.getName());
			statement.setString(2, data.getEmail());
			statement.setString(3, data.getPassword());
			statement.setString(4, data.getAddress());
			statement.setString(5, data.getCpf());
			statement.setLong(6, professor.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar o professor!");
			e.printStackTrace();
		}
	}

	public void delete(ProfessorVO professor) {
		String sql = "DELETE FROM professors WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, professor.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível excluir o professor!");
			e.printStackTrace();
		}
	}
}

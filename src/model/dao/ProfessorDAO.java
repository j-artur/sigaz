package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ProfessorVO;

public class ProfessorDAO implements IUserDAO<ProfessorVO> {
	public void create(ProfessorVO professor) throws SQLException {
		String sql = "INSERT INTO professors (name, email, password, address, cpf) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, professor.getName());
		statement.setString(2, professor.getEmail());
		statement.setString(3, professor.getPassword());
		statement.setString(4, professor.getAddress());
		statement.setString(5, professor.getCpf());
		statement.execute();
	}

	public List<ProfessorVO> findAll() throws SQLException {
		String sql = "SELECT * FROM professors";
		List<ProfessorVO> professorList = new ArrayList<ProfessorVO>();

		Statement statement = BaseDAO.getInstance().getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);

		try {
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
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return professorList;
	}

	public List<ProfessorVO> findByName(ProfessorVO data) throws SQLException {
		String sql = "SELECT * FROM professors WHERE name LIKE ?";
		List<ProfessorVO> professorList = new ArrayList<ProfessorVO>();

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, "%" + data.getName() + "%");
		ResultSet set = statement.executeQuery();

		try {
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
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return professorList;
	}

	public ProfessorVO findByEmail(ProfessorVO data) throws SQLException {
		String sql = "SELECT * FROM professors WHERE email = ?";
		ProfessorVO professor = null;

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, data.getEmail());
		ResultSet set = statement.executeQuery();

		try {
			if (set.next()) {
				professor = new ProfessorVO();
				professor.setId(set.getLong("id"));
				professor.setName(set.getString("name"));
				professor.setEmail(set.getString("email"));
				professor.setPassword(set.getString("password"));
				professor.setAddress(set.getString("address"));
				professor.setCpf(set.getString("cpf"));
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return professor;
	}

	public void update(ProfessorVO professor, ProfessorVO data) throws SQLException {
		String sql = "UPDATE professors SET name = ?, email = ?, password = ?, address = ?, cpf = ? WHERE id = ?";

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, data.getName());
		statement.setString(2, data.getEmail());
		statement.setString(3, data.getPassword());
		statement.setString(4, data.getAddress());
		statement.setString(5, data.getCpf());
		statement.setLong(6, professor.getId());
		statement.executeUpdate();
	}

	public void delete(ProfessorVO professor) throws SQLException {
		String sql = "DELETE FROM professors WHERE id = ?";

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setLong(1, professor.getId());
		statement.executeUpdate();
	}
}

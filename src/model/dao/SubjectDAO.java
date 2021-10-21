package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.SubjectVO;

public class SubjectDAO implements IDAO<SubjectVO> {
	public void create(SubjectVO subject) throws SQLException {
		String sql = "INSERT INTO subjects (code, name) VALUES (?, ?)";

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, subject.getCode());
		statement.setString(2, subject.getName());
		statement.execute();

	}

	public List<SubjectVO> findAll() throws SQLException {
		String sql = "SELECT * FROM subjects";
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();

		Statement statement = BaseDAO.getInstance().getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);

		try {
			while (set.next()) {
				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("id"));
				subject.setCode(set.getString("code"));
				subject.setName(set.getString("name"));
				subjectList.add(subject);
			}

		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return subjectList;
	}

	public List<SubjectVO> findByName(SubjectVO data) throws SQLException {
		String sql = "SELECT * FROM subjects WHERE name LIKE ?";
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, "%" + data.getName() + "%");
		ResultSet set = statement.executeQuery();

		try {
			while (set.next()) {
				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("id"));
				subject.setCode(set.getString("code"));
				subject.setName(set.getString("name"));
				subjectList.add(subject);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return subjectList;
	}

	public void update(SubjectVO subject, SubjectVO data) throws SQLException {
		String sql = "UPDATE subjects SET name = ?, code = ? WHERE id = ?";

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setString(1, data.getName());
		statement.setString(2, data.getCode());
		statement.setLong(3, subject.getId());
		statement.executeUpdate();

	}

	public void delete(SubjectVO subject) throws SQLException {
		String sql = "DELETE FROM subjects WHERE id = ?";

		PreparedStatement statement = BaseDAO.getInstance().getConnection().prepareStatement(sql);
		statement.setLong(1, subject.getId());
		statement.executeUpdate();
	}
}

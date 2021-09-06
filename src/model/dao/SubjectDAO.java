package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.SubjectVO;

public class SubjectDAO extends BaseDAO {
	public SubjectDAO() {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS subjects (id INT AUTO_INCREMENT, code VARCHAR(7) NOT NULL UNIQUE, name VARCHAR(128) NOT NULL, CONSTRAINT id_pk PRIMARY KEY (id))";
			this.getConnection().prepareStatement(sql).execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível criar a entidade `subjects`");
		}
	}

	public void create(SubjectVO subject) {
		String sql = "INSERT INTO subjects (code, name) VALUES (?, ?)";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, subject.getCode());
			statement.setString(2, subject.getName());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível salvar a disciplina");
		}
	}

	public List<SubjectVO> getAll() {
		String sql = "SELECT * FROM subjects";
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("id"));
				subject.setCode(set.getString("code"));
				subject.setName(set.getString("name"));
				subjectList.add(subject);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível encontrar disciplinas");
		}

		return subjectList;
	}

	public void update(SubjectVO subject, SubjectVO data) {
		String sql = "UPDATE subjects SET name = ?, code = ? WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, data.getName());
			statement.setString(2, data.getCode());
			statement.setLong(3, subject.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar a disciplina");
		}
	}

	public void delete(SubjectVO subject) {
		String sql = "DELETE FROM subjects WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, subject.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível excluir a disciplina");
		}
	}
}

package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClassroomVO;
import model.vo.SubjectVO;

public class SubjectDAO extends BaseDAO {
	public void create(SubjectVO subject) {
		String sql = "INSERT INTO subjects (code, name) VALUES (?, ?)";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, subject.getCode());
			statement.setString(2, subject.getName());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível salvar a disciplina");
			e.printStackTrace();
		}
	}

	public List<SubjectVO> findAll() {
		String sql = "SELECT * FROM subjects";
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();

		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet set = statement.executeQuery(sql);

			while (set.next()) {
				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("id"));
				subject.setCode(set.getString("code"));
				subject.setName(set.getString("name"));
				subjectList.add(subject);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar disciplinas");
			e.printStackTrace();
		}

		return subjectList;
	}

	public List<SubjectVO> findByName(SubjectVO data) {
		String sql = "SELECT * FROM subjects WHERE name LIKE ?";
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, "%" + data.getName() + "%");
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("id"));
				subject.setCode(set.getString("code"));
				subject.setName(set.getString("name"));
				subjectList.add(subject);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar a disciplina");
			e.printStackTrace();
		}

		return subjectList;
	}

	public SubjectVO findByClassroom(ClassroomVO classroom) {
		String sql = "SELECT * FROM subjects, classrooms WHERE classrooms.id = ? AND subjects.id = classrooms.subject_id";
		SubjectVO subject = null;

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, classroom.getId());
			ResultSet set = statement.executeQuery();

			if (set.next()) {
				subject = new SubjectVO();
				subject.setId(set.getLong("subjects.id"));
				subject.setCode(set.getString("subjects.code"));
				subject.setName(set.getString("subjects.name"));
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar a disciplina");
			e.printStackTrace();
		}

		return subject;
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
}

package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClassroomVO;
import model.vo.SubjectVO;

public class ClassroomSubjectDAO extends BaseDAO {
	public List<ClassroomVO> findClassroomsBySubject(SubjectVO subject) {
		String sql = "SELECT * FROM classrooms WHERE subject_id = ?";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, subject.getId());
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("id"));
				classroom.setSchedule(set.getString("schedule"));
				classroom.setPlace(set.getString("place"));
				classroom.setActive(set.getBoolean("active"));

				classroom.setSubject(subject);
				classroomList.add(classroom);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar salas de aula");
			e.printStackTrace();
		}

		return classroomList;
	}

	public SubjectVO findSubjectByClassroom(ClassroomVO classroom) {
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
}

package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClassroomVO;

public class ClassroomDAO extends BaseDAO {
	public void create(ClassroomVO classroom) {
		String sql = "INSERT INTO classrooms (subject_id, professor_id, schedule, place, active) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, classroom.getSubject().getId());
			statement.setLong(2, classroom.getProfessor().getId());
			statement.setString(3, classroom.getSchedule());
			statement.setString(4, classroom.getPlace());
			statement.setBoolean(5, classroom.isActive());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível salvar a sala de aula");
			e.printStackTrace();
		}
	}

	public List<ClassroomVO> findAll() {
		String sql = "SELECT * FROM classrooms";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();

		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet set = statement.executeQuery(sql);

			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("id"));
				classroom.setSchedule(set.getString("schedule"));
				classroom.setPlace(set.getString("place"));
				classroom.setActive(set.getBoolean("active"));
				classroomList.add(classroom);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar salas de aula");
			e.printStackTrace();
		}

		return classroomList;
	}

	public void update(ClassroomVO classroom, ClassroomVO data) {
		String sql = "UPDATE classrooms SET subject_id = ?, professor_id = ?, schedule = ?, place = ?, active = ? WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, data.getSubject().getId());
			statement.setLong(2, data.getProfessor().getId());
			statement.setString(3, data.getSchedule());
			statement.setString(4, data.getPlace());
			statement.setBoolean(5, data.isActive());
			statement.setLong(7, classroom.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar a sala de aula");
			e.printStackTrace();
		}
	}

	public void delete(ClassroomVO classroom) {
		String sql = "DELETE FROM classrooms WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, classroom.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível excluir a sala de aula");
			e.printStackTrace();
		}
	}
}

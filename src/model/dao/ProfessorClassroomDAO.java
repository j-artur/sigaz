package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ProfessorVO;
import model.vo.ClassroomVO;

public class ProfessorClassroomDAO extends BaseDAO {
	
	public ProfessorVO findProfessorByClassroom(ClassroomVO classroom) {
		String query = "SELECT * FROM professors, classrooms WHERE classrooms.id = ? AND professors.id = classrooms.professor_id";
		ProfessorVO professor;
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, classroom.getId());
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				professor = new ProfessorVO();
				professor.setId(set.getLong("professors.id"));
				professor.setName(set.getString("professors.name"));
				professor.setEmail(set.getString("professors.email"));
				professor.setPassword(set.getString("professors.password"));
				professor.setAddress(set.getString("professors.address"));
				professor.setCpf(set.getString("professors.cpf"));
			}
			
		} catch(SQLException e) {
			System.out.println("Não foi possível buscar o professor dessa turma!");
			e.printStackTrace();
		}
		
		return professor;
	}
	
	public List<ClassroomVO> findClassroomsByProfessor(ProfessorVO professor) {
		String query = "SELECT * FROM classrooms WHERE professor_id = ?";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();
		
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, professor.getId());
			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("id"));
				classroom.setSchedule(set.getString("schedule"));
				classroom.setPlace(set.getString("place"));
				classroom.setActive(set.getBoolean("active"));
				classroom.setProfessor(professor);
				classroomList.add(classroom);
			}
			
		} catch(SQLException e) {
			System.out.println("Não foi possível buscar turmas para esse professor!");
			e.printStackTrace();
		}
		
		return classroomList;
	}
}

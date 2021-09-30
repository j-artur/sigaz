package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.GradeVO;
import model.vo.StudentVO;
import model.vo.ClassroomVO;

public class GradeDAO extends BaseDAO {
	ClassroomDAO classroomDao = new ClassroomDAO();

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

	public List<GradeVO> findByClassroom(ClassroomVO classroom) {
		String sql = "SELECT * FROM grades, students WHERE grades.classroom_id = ? AND students.id = grades.student_id";
		List<GradeVO> gradeList = new ArrayList<GradeVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, classroom.getId());
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				GradeVO grade = new GradeVO();
				grade.setN1(set.getInt("grades.n1"));
				grade.setN2(set.getInt("grades.n2"));
				grade.setN3(set.getInt("grades.n3"));
				grade.setNFinal(set.getInt("grades.nfinal"));
				grade.setFrequency(set.getDouble("grades.frequency"));

				StudentVO student = new StudentVO();
				student.setId(set.getLong("students.id"));
				student.setName(set.getString("students.name"));
				student.setEmail(set.getString("students.email"));
				student.setPassword(set.getString("students.password"));
				student.setRegistration(set.getString("students.registration"));
				student.setAddress(set.getString("students.address"));

				grade.setClassroom(classroom);
				grade.setStudent(student);
				gradeList.add(grade);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível buscar as notas");
		}

		return gradeList;
	}

	public List<GradeVO> findByStudent(StudentVO student) {
		String sql = "SELECT * FROM grades WHERE student_id = ?";
		List<GradeVO> gradeList = new ArrayList<GradeVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, student.getId());
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				GradeVO grade = new GradeVO();
				grade.setN1(set.getInt("n1"));
				grade.setN2(set.getInt("n2"));
				grade.setN3(set.getInt("n3"));
				grade.setNFinal(set.getInt("nfinal"));
				grade.setFrequency(set.getDouble("frequency"));

				grade.setStudent(student);
				grade.setClassroom(this.classroomDao.findById(set.getLong("classroom_id")));

				gradeList.add(grade);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível buscar as notas");
		}

		return gradeList;
	}

	public GradeVO findByStudentInClassroom(StudentVO student, ClassroomVO classroom) {
		String sql = "SELECT * FROM grades WHERE student_id = ? AND classroom_id = ?";
		GradeVO grade = null;

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, student.getId());
			statement.setLong(2, classroom.getId());
			ResultSet set = statement.executeQuery();

			if (set.next()) {
				grade = new GradeVO();
				grade.setN1(set.getInt("n1"));
				grade.setN2(set.getInt("n2"));
				grade.setN3(set.getInt("n3"));
				grade.setNFinal(set.getInt("nfinal"));
				grade.setFrequency(set.getDouble("frequency"));

				grade.setStudent(student);
				grade.setClassroom(classroom);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível buscar a nota");
		}

		return grade;
	}

	public void update(GradeVO grade, GradeVO data) {
		String query = "UPDATE grades SET n1 = ?, n2 = ?, n3 = ?, nfinal = ?, frequency = ? WHERE student_id = ? AND classroom_id = ?";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setInt(1, data.getN1());
			statement.setInt(2, data.getN2());
			statement.setInt(3, data.getN3());
			statement.setInt(4, data.getNFinal());
			statement.setDouble(5, data.getFrequency());
			statement.setLong(6, grade.getStudent().getId());
			statement.setLong(7, grade.getClassroom().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar o boletim!");
			e.printStackTrace();
		}
	}

	public void delete(GradeVO grade) {
		String query = "DELETE FROM grades WHERE student_id = ? AND classroom_id = ?";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(query);
			statement.setLong(1, grade.getStudent().getId());
			statement.setLong(2, grade.getClassroom().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o boletim!");
			e.printStackTrace();
		}
	}
}

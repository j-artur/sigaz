package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClassroomVO;
import model.vo.GradeVO;
import model.vo.StudentVO;

public class StudentClassroomGradeDAO extends BaseDAO {
	public List<GradeVO> findGradesByStudent(StudentVO student) {
		String sql = "SELECT * FROM grades, classrooms WHERE student_id = ?";
		List<GradeVO> gradeList = new ArrayList<GradeVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, student.getId());
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				GradeVO grade = new GradeVO();
				grade.setP1(set.getInt("grades.n1"));
				grade.setP2(set.getInt("grades.n2"));
				grade.setP3(set.getInt("grades.n3"));
				grade.setFinalGrade(set.getInt("grades.nfinal"));
				grade.setAttendance(set.getDouble("grades.frequency"));

				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("classrooms.id"));
				classroom.setSchedule(set.getString("classrooms.schedule"));
				classroom.setPlace(set.getString("classrooms.place"));
				classroom.setActive(set.getBoolean("classrooms.active"));

				grade.setStudent(student);
				grade.setClassroom(classroom);
				gradeList.add(grade);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível buscar as notas");
		}

		return gradeList;
	}

	public List<GradeVO> findGradesByClassroom(ClassroomVO classroom) {
		String sql = "SELECT * FROM grades, students WHERE classroom_id = ?";
		List<GradeVO> gradeList = new ArrayList<GradeVO>();

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, classroom.getId());
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				GradeVO grade = new GradeVO();
				grade.setP1(set.getInt("grades.n1"));
				grade.setP2(set.getInt("grades.n2"));
				grade.setP3(set.getInt("grades.n3"));
				grade.setFinalGrade(set.getInt("grades.nfinal"));
				grade.setAttendance(set.getDouble("grades.frequency"));

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

	public GradeVO findGradeByStudentAndClassroom(StudentVO student, ClassroomVO classroom) {
		String sql = "SELECT * FROM grades WHERE student_id = ? AND classroom_id = ?";
		GradeVO grade = null;

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, student.getId());
			statement.setLong(2, classroom.getId());
			ResultSet set = statement.executeQuery();

			if (set.next()) {
				grade = new GradeVO();
				grade.setP1(set.getInt("n1"));
				grade.setP2(set.getInt("n2"));
				grade.setP3(set.getInt("n3"));
				grade.setFinalGrade(set.getInt("nfinal"));
				grade.setAttendance(set.getDouble("frequency"));

				grade.setStudent(student);
				grade.setClassroom(classroom);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível buscar a nota");
		}

		return grade;
	}
}

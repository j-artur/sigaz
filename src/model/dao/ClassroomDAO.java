package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClassroomVO;
import model.vo.ProfessorVO;
import model.vo.StudentVO;
import model.vo.SubjectVO;

public class ClassroomDAO extends BaseDAO implements IDAO<ClassroomVO> {
	StudentDAO studentDao = new StudentDAO();

	public void create(ClassroomVO classroom) throws SQLException {
		String sql = "INSERT INTO classrooms (subject_id, professor_id, schedule, place, active) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, classroom.getSubject().getId());
		statement.setLong(2, classroom.getProfessor().getId());
		statement.setString(3, classroom.getSchedule());
		statement.setString(4, classroom.getPlace());
		statement.setBoolean(5, classroom.isActive());
		statement.execute();
	}

	public List<ClassroomVO> findAll() throws SQLException {
		String sql = "SELECT * FROM classrooms, subjects, professors " + "WHERE subjects.id = classrooms.subject_id "
				+ "AND professors.id = classrooms.professor_id";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();

		Statement statement = this.getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);

		try {
			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("classrooms.id"));
				classroom.setSchedule(set.getString("classrooms.schedule"));
				classroom.setPlace(set.getString("classrooms.place"));
				classroom.setActive(set.getBoolean("classrooms.active"));

				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("subjects.id"));
				subject.setCode(set.getString("subjects.code"));
				subject.setName(set.getString("subjects.name"));

				ProfessorVO professor = new ProfessorVO();
				professor.setId(set.getLong("professors.id"));
				professor.setName(set.getString("professors.name"));
				professor.setEmail(set.getString("professors.email"));
				professor.setPassword(set.getString("professors.password"));
				professor.setAddress(set.getString("professors.address"));
				professor.setCpf(set.getString("professors.cpf"));

				classroom.setSubject(subject);
				classroom.setProfessor(professor);

				List<StudentVO> studentList = this.studentDao.findByClassroom(classroom);
				StudentVO[] students = new StudentVO[studentList.size()];
				studentList.toArray(students);
				classroom.setStudents(students);

				classroomList.add(classroom);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return classroomList;
	}

	public List<ClassroomVO> findByStudent(StudentVO student) throws SQLException {
		String sql = "SELECT * FROM students_classrooms, classrooms, subjects, professors "
				+ "WHERE students_classrooms.student_id = ? " + "AND classrooms.id = students_classrooms.classroom_id "
				+ "AND subjects.id = classrooms.subject_id " + "AND professors.id = classrooms.professor_id";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, student.getId());
		ResultSet set = statement.executeQuery();

		try {
			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("classrooms.id"));
				classroom.setSchedule(set.getString("classrooms.schedule"));
				classroom.setPlace(set.getString("classrooms.place"));
				classroom.setActive(set.getBoolean("classrooms.active"));

				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("subjects.id"));
				subject.setCode(set.getString("subjects.code"));
				subject.setName(set.getString("subjects.name"));

				ProfessorVO professor = new ProfessorVO();
				professor.setId(set.getLong("professors.id"));
				professor.setName(set.getString("professors.name"));
				professor.setEmail(set.getString("professors.email"));
				professor.setPassword(set.getString("professors.password"));
				professor.setAddress(set.getString("professors.address"));
				professor.setCpf(set.getString("professors.cpf"));

				classroom.setSubject(subject);
				classroom.setProfessor(professor);

				List<StudentVO> studentList = this.studentDao.findByClassroom(classroom);
				StudentVO[] students = new StudentVO[studentList.size()];
				studentList.toArray(students);
				classroom.setStudents(students);

				classroomList.add(classroom);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return classroomList;
	}

	public List<ClassroomVO> findByProfessor(ProfessorVO professor) throws SQLException {
		String sql = "SELECT * FROM classrooms, subjects " + "WHERE classrooms.professor_id = ? "
				+ "AND subjects.id = classrooms.subject_id";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, professor.getId());
		ResultSet set = statement.executeQuery();

		try {
			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("classrooms.id"));
				classroom.setSchedule(set.getString("classrooms.schedule"));
				classroom.setPlace(set.getString("classrooms.place"));
				classroom.setActive(set.getBoolean("classrooms.active"));

				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("subjects.id"));
				subject.setCode(set.getString("subjects.code"));
				subject.setName(set.getString("subjects.name"));

				classroom.setSubject(subject);
				classroom.setProfessor(professor);

				List<StudentVO> studentList = this.studentDao.findByClassroom(classroom);
				StudentVO[] students = new StudentVO[studentList.size()];
				studentList.toArray(students);
				classroom.setStudents(students);

				classroomList.add(classroom);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return classroomList;
	}

	public List<ClassroomVO> findBySubject(SubjectVO subject) throws SQLException {
		String sql = "SELECT * FROM classrooms, professors " + "WHERE classrooms.subject_id = ? "
				+ "AND professors.id = classrooms.professor_id";
		List<ClassroomVO> classroomList = new ArrayList<ClassroomVO>();

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, subject.getId());
		ResultSet set = statement.executeQuery();

		try {

			while (set.next()) {
				ClassroomVO classroom = new ClassroomVO();
				classroom.setId(set.getLong("classrooms.id"));
				classroom.setSchedule(set.getString("classrooms.schedule"));
				classroom.setPlace(set.getString("classrooms.place"));
				classroom.setActive(set.getBoolean("classrooms.active"));

				ProfessorVO professor = new ProfessorVO();
				professor.setId(set.getLong("professors.id"));
				professor.setName(set.getString("professors.name"));
				professor.setEmail(set.getString("professors.email"));
				professor.setPassword(set.getString("professors.password"));
				professor.setAddress(set.getString("professors.address"));
				professor.setCpf(set.getString("professors.cpf"));

				classroom.setSubject(subject);
				classroom.setProfessor(professor);

				List<StudentVO> studentList = this.studentDao.findByClassroom(classroom);
				StudentVO[] students = new StudentVO[studentList.size()];
				studentList.toArray(students);
				classroom.setStudents(students);

				classroomList.add(classroom);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return classroomList;
	}

	public ClassroomVO findById(long id) throws SQLException {
		String sql = "SELECT * FROM classrooms, subjects, professors " + "WHERE classrooms.id = ? "
				+ "AND subjects.id = classrooms.subject_id " + "AND professors.id = classrooms.professor_id";
		ClassroomVO classroom = null;

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet set = statement.executeQuery();

		try {
			while (set.next()) {
				classroom = new ClassroomVO();
				classroom.setId(set.getLong("classrooms.id"));
				classroom.setSchedule(set.getString("classrooms.schedule"));
				classroom.setPlace(set.getString("classrooms.place"));
				classroom.setActive(set.getBoolean("classrooms.active"));

				SubjectVO subject = new SubjectVO();
				subject.setId(set.getLong("subjects.id"));
				subject.setCode(set.getString("subjects.code"));
				subject.setName(set.getString("subjects.name"));

				ProfessorVO professor = new ProfessorVO();
				professor.setId(set.getLong("professors.id"));
				professor.setName(set.getString("professors.name"));
				professor.setEmail(set.getString("professors.email"));
				professor.setPassword(set.getString("professors.password"));
				professor.setAddress(set.getString("professors.address"));
				professor.setCpf(set.getString("professors.cpf"));

				classroom.setSubject(subject);
				classroom.setProfessor(professor);

				List<StudentVO> studentList = this.studentDao.findByClassroom(classroom);
				StudentVO[] students = new StudentVO[studentList.size()];
				studentList.toArray(students);
				classroom.setStudents(students);
			}
		} catch (Exception e) {
			throw new SQLException("Erro crítico, dados inválidos salvos no banco");
		}

		return classroom;
	}

	public void update(ClassroomVO classroom, ClassroomVO data) throws SQLException {
		String sql = "UPDATE classrooms SET subject_id = ?, professor_id = ?, schedule = ?, place = ?, active = ? WHERE id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, data.getSubject().getId());
		statement.setLong(2, data.getProfessor().getId());
		statement.setString(3, data.getSchedule());
		statement.setString(4, data.getPlace());
		statement.setBoolean(5, data.isActive());
		statement.setLong(6, classroom.getId());
		statement.executeUpdate();

	}

	public void delete(ClassroomVO classroom) throws SQLException {
		String sql = "DELETE FROM classrooms WHERE id = ?";

		PreparedStatement statement = this.getConnection().prepareStatement(sql);
		statement.setLong(1, classroom.getId());
		statement.executeUpdate();

	}
}

package model.bo;

import java.sql.SQLException;
import java.util.List;

import exceptions.AuthError;
import exceptions.AuthenticationException;
import model.dao.StudentDAO;
import model.dao.ClassroomStudentDAO;
import model.vo.StudentVO;
import model.vo.ClassroomVO;

public class StudentBO implements IStudentBO {

	private StudentDAO studentDAO = new StudentDAO();
	private ClassroomStudentDAO classroomStudentDAO = new ClassroomStudentDAO();

	@Override
	public StudentVO authenticate(StudentVO data) throws AuthenticationException {
		try {
			StudentVO student = this.studentDAO.findByEmail(data);

			if (student == null)
				throw new AuthenticationException(AuthError.NOT_FOUND);
			if (!data.getPassword().equals(student.getPassword()))
				throw new AuthenticationException(AuthError.WRONG_PASSWORD);

			return student;
		} catch (SQLException e) {
			throw new AuthenticationException(AuthError.INTERNAL);
		}
	}

	@Override
	public List<StudentVO> findAll() throws Exception {
		return this.studentDAO.findAll();
	}

	@Override
	public List<StudentVO> findByName(StudentVO data) throws Exception {
		return this.studentDAO.findByName(data);
	}

	@Override
	public List<StudentVO> findByClassroom(ClassroomVO classroom) throws Exception {
		return this.studentDAO.findByClassroom(classroom);
	}

	@Override
	public void register(ClassroomVO classroom, StudentVO student) throws Exception {
		this.classroomStudentDAO.create(classroom, student);
	}

	@Override
	public void unregister(ClassroomVO classroom, StudentVO student) throws Exception {
		this.classroomStudentDAO.delete(classroom, student);
	}

	@Override
	public void create(StudentVO student) throws Exception {
		if (this.studentDAO.findByEmail(student) != null)
			throw new Exception("Email já cadastrado!");

		this.studentDAO.create(student);
	}

	@Override
	public void update(StudentVO student, StudentVO data) throws Exception {
		this.studentDAO.update(student, data);
	}

	@Override
	public void delete(StudentVO student) throws Exception {
		this.studentDAO.delete(student);
	}

}

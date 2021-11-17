package model.bo;

import java.util.Arrays;
import java.util.List;

import model.dao.ClassroomDAO;
import model.dao.ClassroomStudentDAO;
import model.dao.GradeDAO;
import model.vo.ClassroomVO;
import model.vo.GradeVO;
import model.vo.ProfessorVO;
import model.vo.StudentVO;
import model.vo.SubjectVO;

public class ClassroomBO implements IClassroomBO {
	private ClassroomDAO classroomDao = new ClassroomDAO();
	private ClassroomStudentDAO classroomStudentDao = new ClassroomStudentDAO();
	private GradeDAO gradeDao = new GradeDAO();

	@Override
	public void create(ClassroomVO classroom) throws Exception {
		this.classroomDao.create(classroom);
		Arrays.asList(classroom.getStudents()).forEach(student -> {
			try {
				this.add(classroom, student);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public List<ClassroomVO> findAll() throws Exception {
		return this.classroomDao.findAll();
	}

	@Override
	public List<ClassroomVO> findByProfessor(ProfessorVO professor) throws Exception {
		return this.classroomDao.findByProfessor(professor);
	}

	@Override
	public List<ClassroomVO> findByStudent(StudentVO student) throws Exception {
		return this.classroomDao.findByStudent(student);
	}

	@Override
	public List<ClassroomVO> findBySubject(SubjectVO subject) throws Exception {
		return this.classroomDao.findBySubject(subject);
	}

	@Override
	public List<GradeVO> findAllGrades(ClassroomVO classroom) throws Exception {
		return this.gradeDao.findByClassroom(classroom);
	}

	@Override
	public List<GradeVO> findGradesByStudent(StudentVO student) throws Exception {
		return this.gradeDao.findByStudent(student);
	}

	@Override
	public void update(ClassroomVO classroom, ClassroomVO data) throws Exception {
		this.classroomDao.update(classroom, data);
	}

	@Override
	public void delete(ClassroomVO classroom) throws Exception {
		this.classroomStudentDao.delete(classroom);
		this.classroomDao.delete(classroom);
	}

	@Override
	public void add(ClassroomVO classroom, StudentVO student) throws Exception {
		this.classroomStudentDao.create(classroom, student);

		GradeVO grade = new GradeVO();
		grade.setStudent(student);
		grade.setClassroom(classroom);
		grade.setN1(0);
		grade.setN2(0);
		grade.setN3(0);
		grade.setNFinal(0);
		grade.setFrequency(1);

		this.gradeDao.create(grade);
	}

	@Override
	public void grade(GradeVO grade) throws Exception {
		this.gradeDao.update(grade, grade);
	}

	@Override
	public void remove(ClassroomVO classroom, StudentVO student) throws Exception {
		this.classroomStudentDao.delete(classroom, student);
	}
}

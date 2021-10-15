package model.bo;

import java.util.List;

import model.vo.ClassroomVO;
import model.vo.GradeVO;
import model.vo.ProfessorVO;
import model.vo.StudentVO;
import model.vo.SubjectVO;

public interface IClassroomBO extends IBO<ClassroomVO> {
	public List<ClassroomVO> findByProfessor(ProfessorVO professor) throws Exception;

	public List<ClassroomVO> findBySubject(SubjectVO subject) throws Exception;

	public List<ClassroomVO> findByStudent(StudentVO student) throws Exception;

	public void add(ClassroomVO classroom, StudentVO student) throws Exception;

	public void remove(ClassroomVO classroom, StudentVO student) throws Exception;

	public void grade(GradeVO grade) throws Exception;
}

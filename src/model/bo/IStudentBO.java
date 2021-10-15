package model.bo;

import java.util.List;

import model.vo.StudentVO;
import model.vo.ClassroomVO;

public interface IStudentBO extends IUserBO<StudentVO> {
	
	public List<StudentVO> findByName (StudentVO student) throws Exception;
	public List<StudentVO> findByClassroom(ClassroomVO classroom) throws Exception;
	public void register(ClassroomVO classroom, StudentVO student) throws Exception;
	public void unregister(ClassroomVO classroom, StudentVO student) throws Exception;
	
}

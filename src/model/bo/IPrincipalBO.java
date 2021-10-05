package model.bo;

import model.vo.ClassroomVO;
import model.vo.PrincipalVO;
import model.vo.SubjectVO;

public interface IPrincipalBO extends IUserBO<PrincipalVO> {
	public void createClassroom(ClassroomVO classroom) throws Exception;

	public void createSubject(SubjectVO subject) throws Exception;
}

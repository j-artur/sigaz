package model.bo;

import model.vo.SubjectVO;

public interface ISubjectBO extends IBO<SubjectVO> {
	public List<SubjectVO> findByName (SubjectVO subject) throws Exception;
}
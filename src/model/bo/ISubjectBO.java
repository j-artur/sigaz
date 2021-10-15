package model.bo;

import java.util.List;

import model.vo.SubjectVO;

public interface ISubjectBO extends IBO<SubjectVO> {
	public List<SubjectVO> findByName (SubjectVO subject) throws Exception;
}
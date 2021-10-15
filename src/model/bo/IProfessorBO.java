package model.bo;

import java.util.List;

import model.vo.ProfessorVO;

public interface IProfessorBO extends IUserBO<ProfessorVO> {
	public List<ProfessorVO> findByName (ProfessorVO professor) throws Exception;
}
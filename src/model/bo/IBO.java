package model.bo;

import java.util.List;

public interface IBO<VO> {
	public void create(VO vo) throws Exception;

	public List<VO> findAll() throws Exception;

	public void update(VO vo, VO data) throws Exception;

	public void delete(VO vo) throws Exception;
}

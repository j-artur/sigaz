package model.dao;

import java.util.List;

public interface IDAO<VO> {
	public void create(VO vo);

	public List<VO> findAll();

	public void update(VO vo, VO data);

	public void delete(VO vo);
}

package model.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<VO> {
	public void create(VO vo) throws SQLException;

	public List<VO> findAll() throws SQLException;

	public void update(VO vo, VO data) throws SQLException;

	public void delete(VO vo) throws SQLException;
}

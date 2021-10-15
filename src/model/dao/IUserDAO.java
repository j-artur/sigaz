package model.dao;

import java.sql.SQLException;

import model.vo.UserVO;

public interface IUserDAO<VO extends UserVO> extends IDAO<VO> {
	public VO findByEmail(VO vo) throws SQLException;
}

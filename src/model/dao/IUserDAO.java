package model.dao;

import model.vo.UserVO;

public interface IUserDAO<VO extends UserVO> extends IDAO<VO> {
	public VO findByEmail(VO vo);
}

package model.bo;

import exceptions.AuthenticationException;
import model.vo.UserVO;

public interface IUserBO<VO extends UserVO> extends IBO<VO> {
	public VO authenticate(VO vo) throws AuthenticationException;
}

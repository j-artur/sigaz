package model.bo;

import java.util.List;

import exceptions.AuthError;
import exceptions.AuthenticationException;
import model.dao.ClassroomDAO;
import model.dao.PrincipalDAO;
import model.dao.SubjectDAO;
import model.vo.ClassroomVO;
import model.vo.PrincipalVO;
import model.vo.SubjectVO;

public class PrincipalBO implements IPrincipalBO {
	private PrincipalDAO principalDao = new PrincipalDAO();
	private ClassroomDAO classroomDao = new ClassroomDAO();
	private SubjectDAO subjectDao = new SubjectDAO();

	@Override
	public PrincipalVO authenticate(PrincipalVO data) throws AuthenticationException {
		try {
			PrincipalVO principal = this.principalDao.findByEmail(data);

			if (principal == null)
				throw new AuthenticationException(AuthError.NOT_FOUND);
			if (data.getPassword() != principal.getPassword())
				throw new AuthenticationException(AuthError.WRONG_PASSWORD);

			return principal;
		} catch (Exception e) {
			throw new AuthenticationException(AuthError.INTERNAL);
		}
	}

	@Override
	public void create(PrincipalVO principal) throws Exception {
		if (this.principalDao.findByEmail(principal) != null)
			throw new Exception("Email já cadastrado");

		this.principalDao.create(principal);
	}

	@Override
	public List<PrincipalVO> findALl() throws Exception {
		return this.principalDao.findAll();
	}

	@Override
	public void update(PrincipalVO principal, PrincipalVO data) throws Exception {
		this.principalDao.update(principal, data);
	}

	@Override
	public void delete(PrincipalVO principal) throws Exception {
		this.principalDao.delete(principal);
	}

	@Override
	public void createClassroom(ClassroomVO classroom) throws Exception {
		this.classroomDao.create(classroom);
	}

	@Override
	public void createSubject(SubjectVO subject) throws Exception {
		this.subjectDao.create(subject);
	}
}

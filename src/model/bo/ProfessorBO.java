package model.bo;

import java.sql.SQLException;
import java.util.List;

import exceptions.AuthError;
import exceptions.AuthenticationException;
import model.dao.ProfessorDAO;
import model.vo.ProfessorVO;

public class ProfessorBO implements IProfessorBO {
	private ProfessorDAO professorDao = new ProfessorDAO();

	@Override
	public ProfessorVO authenticate(ProfessorVO data) throws AuthenticationException {
		try {
			ProfessorVO professor = this.professorDao.findByEmail(data);

			if (professor == null)
				throw new AuthenticationException(AuthError.NOT_FOUND);
			if (!data.getPassword().equals(professor.getPassword()))
				throw new AuthenticationException(AuthError.WRONG_PASSWORD);

			return professor;
		} catch (SQLException e) {
			throw new AuthenticationException(AuthError.INTERNAL);
		}
	}

	@Override
	public void create(ProfessorVO professor) throws Exception {
		this.professorDao.create(professor);
	}

	@Override
	public List<ProfessorVO> findAll() throws Exception {
		return this.professorDao.findAll();
	}

	@Override
	public List<ProfessorVO> findByName(ProfessorVO data) throws Exception {
		return this.professorDao.findByName(data);
	}

	@Override
	public void update(ProfessorVO professor, ProfessorVO data) throws Exception {
		this.professorDao.update(professor, data);
	}

	@Override
	public void delete(ProfessorVO professor) throws Exception {
		this.professorDao.delete(professor);
	}
}

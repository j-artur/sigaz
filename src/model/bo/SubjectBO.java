package model.bo;

import java.util.List;

import model.dao.SubjectDAO;
import model.vo.SubjectVO;

public class SubjectBO implements ISubjectBO {
    private SubjectDAO subjectDao = new SubjectDAO();

    @Override
	public void create(SubjectVO subject) throws Exception {
		this.subjectDao.create(subject);
	}

    @Override
	public List<SubjectVO> findAll() throws Exception {
		return this.subjectDao.findAll();
	}

    @Override
	public List<SubjectVO> findByName(SubjectVO data) throws Exception {
		return this.subjectDao.findByName(data);
	}

    @Override
	public void update(SubjectVO subject, SubjectVO data) throws Exception {
		this.subjectDao.update(subject, data);
	}

    @Override
	public void delete(SubjectVO subject) throws Exception {
		this.subjectDao.delete(subject);
	}
}
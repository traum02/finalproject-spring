package spring.project.work;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class IcommentDao extends SqlSessionDaoSupport implements IcommentDaoInter {

	@Override
	public List<IcommentDto> getIcommnetList(int iboard_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectListOfIcomment",iboard_num);
	}

	@Override
	public void insertIcomment(IcommentDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfIcomment",dto);
	}
	
}

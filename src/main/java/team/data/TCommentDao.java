package team.data;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TCommentDao extends SqlSessionDaoSupport
 implements TCommentDaoInter
 
{

	@Override
	public List<TCommentDto> getTcommnetList(int tboard_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectListOfTcomment",tboard_num);
	}

	@Override
	public void insertTcomment(TCommentDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfTcomment",dto);
	}

	@Override
	public void deleteTcomment(int tcomment_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfTcomment",tcomment_num);
		
	}

	@Override
	public int getTotalCount(int tboard_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("TotalCountComment",tboard_num);
	}

}

package team.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TboardDao extends SqlSessionDaoSupport 
	implements TboardDaoInter 
	
{

	@Override
	public List<TboardDto> getAllDatas(int start) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectAllOfTboard",start);
	}

	@Override
	public List<TboardDto> getTeamDatas(int start, String tboard_public) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("tboard_public",tboard_public);
		
		
		return getSqlSession().selectList("selectTeamOfTboard",map);
	}

	@Override
	public void insertTboard(TboardDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfTboard", dto);
		
	}

	@Override
	public void deleteTboard(int tboard_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfTboard", tboard_num);
	}

	@Override
	public void updateViewcount(int tboard_num) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateViewCountOfTboard",tboard_num);
		
	}

	@Override
	public TboardDto getData(int tboard_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfTboard",tboard_num);
	}

	@Override
	public void updateTboard(TboardDto dto) {
		// TODO Auto-generated method stub
		
		getSqlSession().update("updateOfTboard", dto);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("totalCountOfTboard");
	}

	@Override
	public List<TboardDto> getSearchDatas(String option, String keyword,int start) {
		// TODO Auto-generated method stub
		System.out.println(option);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("option",option);
		map.put("keyword",keyword);
		map.put("start",start);
		return getSqlSession().selectList("searchOfTbaord",map);
	}

	@Override
	public int getTotalCountSearch(String option, String keyword) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("option",option);
		map.put("keyword",keyword);
		return getSqlSession().selectOne("totalCountOfTboardSearch",map);
	}

	@Override
	public int getTeamTotalCount(String tboard_public) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("totalCountOfTT",tboard_public);
	}


}

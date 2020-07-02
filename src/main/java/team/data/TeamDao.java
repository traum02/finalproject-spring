package team.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDao extends SqlSessionDaoSupport
	implements TeamDaoInter
	
{

	@Override
	public List<TeamDto> getAllDatas(int start) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectAllOfTeam",start);
	}

	@Override
	public void insertTeam(TeamDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfTeam",dto);
	}

	@Override
	public void deleteTeam(int team_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfTeam",team_num);
	}

	

	@Override
	public TeamDto getData(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneTeam",team_num);
	}

	@Override
	public int namecheck(String team_name) {
		// TODO Auto-generated method stub
		System.out.println(team_name);
		return getSqlSession().selectOne("checkOfTeamName",team_name);
		
	}

	@Override
	public List<TeamDto> getSearchTeamName(String keyword, int start) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("keyword",keyword);
		map.put("start",start);	
		return getSqlSession().selectList("searchOfTeam",map);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("totalCountTeam");
	}

	@Override
	public void updateIntro(TeamDto dto) {
		// TODO Auto-generated method stub
	
		getSqlSession().update("updateOfTeamIntro",dto);
		
	}
	
	@Override
	public void updateLogo(TeamDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateOfLogo",dto);
	}


	//창단자 팀 넘버 구하기
	@Override
	public int MasterTeamNum(String creator) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("CreatorTeamNum",creator);
	}

	@Override
	public String getTeamName(String team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getTeamName",team_num);
	}

	
	

	
	
	


}

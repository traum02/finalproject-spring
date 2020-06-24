package league.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueDao extends SqlSessionDaoSupport implements LeagueDaoInter {

	@Override
	public List<LeagueRankingDto> getDatas(int league_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfLeagueRanking",league_id);
	}

	@Override
	public int getLeagueMaxId() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getLeagueMaxId");
	}

	@Override
	public void makePlan(LeagueRoundDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfLeaguePlan",dto);
	}

	@Override
	public List<LeagueRoundDto> getPlan(int league_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getLeagueRound",league_id);
	}

	@Override
	public String getLeagueDate(int league_id,int pageNum) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("league_id",league_id);
		map.put("pageNum",pageNum);
		return getSqlSession().selectOne("getLeagueDate",map);
	}

	@Override
	public void joinLeague(LeagueRankingDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("joinLeague",dto);
	}

	@Override
	public LeagueDto getLeagueName(int pageNum) {
		// TODO Auto-generated method stub

		return getSqlSession().selectOne("getLeagueName",pageNum);
	}

}

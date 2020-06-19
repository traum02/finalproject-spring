package league.data;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueDao extends SqlSessionDaoSupport implements LeagueDaoInter {

	@Override
	public List<LeagueRankingDto> getDatas() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfLeagueRanking");
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

}

package league.data;

import java.util.List;

public interface LeagueDaoInter {
	
	public List<LeagueRankingDto> getDatas();
	public int getLeagueMaxId();
	public void makePlan(LeagueRoundDto dto);
}

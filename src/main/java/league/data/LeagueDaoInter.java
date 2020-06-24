package league.data;

import java.util.List;

public interface LeagueDaoInter {
	
	public List<LeagueRankingDto> getDatas(int league_id);
	public int getLeagueMaxId();
	public void makePlan(LeagueRoundDto dto);
	public List<LeagueRoundDto> getPlan(int league_id);
	public String getLeagueDate(int league_id,int pageNum);
	public LeagueDto getLeagueName(int pageNum);
	public void joinLeague(LeagueRankingDto dto);
}

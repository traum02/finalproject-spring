package league.data;

public class LeagueRankingDto {
	private int league_team_id;
	private String league_team_name;
	private int team_num;
	private char league_join;
	public int getLeague_team_id() {
		return league_team_id;
	}
	public void setLeague_team_id(int league_team_id) {
		this.league_team_id = league_team_id;
	}
	public String getLeague_team_name() {
		return league_team_name;
	}
	public void setLeague_team_name(String league_team_name) {
		this.league_team_name = league_team_name;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public char getLeague_join() {
		return league_join;
	}
	public void setLeague_join(char league_join) {
		this.league_join = league_join;
	}

}

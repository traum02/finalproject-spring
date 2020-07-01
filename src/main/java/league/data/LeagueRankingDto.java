package league.data;

public class LeagueRankingDto {
	private int league_team_id;
	private String league_team_name;
	private int team_num;
	private int league_id;
	private int team_win;
	private int team_draw;
	private int team_lose;
	private int team_score;
	private String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTeam_score() {
		return team_score;
	}
	public void setTeam_score(int team_score) {
		this.team_score = team_score;
	}
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
	public int getLeague_id() {
		return league_id;
	}
	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}
	public int getTeam_win() {
		return team_win;
	}
	public void setTeam_win(int team_win) {
		this.team_win = team_win;
	}
	public int getTeam_draw() {
		return team_draw;
	}
	public void setTeam_draw(int team_draw) {
		this.team_draw = team_draw;
	}
	public int getTeam_lose() {
		return team_lose;
	}
	public void setTeam_lose(int team_lose) {
		this.team_lose = team_lose;
	}

}

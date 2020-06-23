package league.data;

public class LeagueRoundDto {
	private int round_id;
	private int round_cnt;
	private String round_date;
	private int league_id;
	private int league_team1;
	private int league_team2;
	private String team1_name;
	private String team2_name;
	private int league_team1goal;
	private int league_team2goal;
	private char round_status;
	
	public String getTeam1_name() {
		return team1_name;
	}
	public void setTeam1_name(String team1_name) {
		this.team1_name = team1_name;
	}
	public String getTeam2_name() {
		return team2_name;
	}
	public void setTeam2_name(String team2_name) {
		this.team2_name = team2_name;
	}
	public int getRound_id() {
		return round_id;
	}
	public void setRound_id(int round_id) {
		this.round_id = round_id;
	}
	public int getRound_cnt() {
		return round_cnt;
	}
	public void setRound_cnt(int round_cnt) {
		this.round_cnt = round_cnt;
	}
	public String getRound_date() {
		return round_date;
	}
	public void setRound_date(String round_date) {
		this.round_date = round_date;
	}
	public int getLeague_id() {
		return league_id;
	}
	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}
	public int getLeague_team1() {
		return league_team1;
	}
	public void setLeague_team1(int league_team1) {
		this.league_team1 = league_team1;
	}
	public int getLeague_team2() {
		return league_team2;
	}
	public void setLeague_team2(int league_team2) {
		this.league_team2 = league_team2;
	}
	public int getLeague_team1goal() {
		return league_team1goal;
	}
	public void setLeague_team1goal(int league_team1goal) {
		this.league_team1goal = league_team1goal;
	}
	public int getLeague_team2goal() {
		return league_team2goal;
	}
	public void setLeague_team2goal(int league_team2goal) {
		this.league_team2goal = league_team2goal;
	}
	public char getRound_status() {
		return round_status;
	}
	public void setRound_status(char round_status) {
		this.round_status = round_status;
	}
	
}

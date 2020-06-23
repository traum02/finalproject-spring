package place.data;

public class ReservationDto {
	private int res_id;
	private String member_id;
	private String home_member_id;
	private String away_member_id;
	private String place_id;
	private String res_type;
	private String res_team1;
	private String res_team2;
	private String res_time;
	private int time_id;
	private String res_date;
	private String res_etc;
	private String selectTeam;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getHome_member_id() {
		return home_member_id;
	}
	public void setHome_member_id(String home_member_id) {
		this.home_member_id = home_member_id;
	}
	public String getAway_member_id() {
		return away_member_id;
	}
	public void setAway_member_id(String away_member_id) {
		this.away_member_id = away_member_id;
	}
	public String getSelectTeam() {
		return selectTeam;
	}
	public void setSelectTeam(String selectTeam) {
		this.selectTeam = selectTeam;
	}
	public int getRes_id() {
		return res_id;
	}
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String getRes_type() {
		return res_type;
	}
	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}
	public String getRes_team1() {
		return res_team1;
	}
	public void setRes_team1(String res_team1) {
		this.res_team1 = res_team1;
	}
	public String getRes_team2() {
		return res_team2;
	}
	public void setRes_team2(String res_team2) {
		this.res_team2 = res_team2;
	}
	public String getRes_time() {
		return res_time;
	}
	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}
	public int getTime_id() {
		return time_id;
	}
	public void setTime_id(int time_id) {
		this.time_id = time_id;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}
	public String getRes_etc() {
		return res_etc;
	}
	public void setRes_etc(String res_etc) {
		this.res_etc = res_etc;
	}
	
}

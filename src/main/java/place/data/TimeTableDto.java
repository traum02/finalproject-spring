package place.data;

public class TimeTableDto {
	private int place_id;
	private String time_val;//시간*~* 표시
	private int time_id;//시간 고유값
	private String res_time;//예약 경기 시간
	private String res_type;//개인 팀 여부
	private int res_team1;
	private int res_team2;
	private String home_member_id;
	private String away_member_id;
	
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
	public int getRes_team1() {
		return res_team1;
	}
	public void setRes_team1(int res_team1) {
		this.res_team1 = res_team1;
	}
	public int getRes_team2() {
		return res_team2;
	}
	public void setRes_team2(int res_team2) {
		this.res_team2 = res_team2;
	}
	public String getRes_type() {
		return res_type;
	}
	public void setRes_type(String res_type) {
		this.res_type = res_type;
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
	public int getPlace_id() {
		return place_id;
	}
	public void setPlace_id(int place_id) {
		this.place_id = place_id;
	}
	public String getTime_val() {
		return time_val;
	}
	public void setTime_val(String time_val) {
		this.time_val = time_val;
	}
}

package team.data;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TeamDto {

	private int team_num;
	private String team_name;
	private int team_win;
	private int team_lose;
	private int team_draw;
	private String team_area;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp team_date;
	private String team_photo;
	
	private String team_age;
	private String team_intro;
	private String creator;
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getTeam_intro() {
		return team_intro;
	}
	public void setTeam_intro(String team_intro) {
		this.team_intro = team_intro;
	}
	public String getTeam_age() {
		return team_age;
	}
	public void setTeam_age(String team_age) {
		this.team_age = team_age;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public int getTeam_win() {
		return team_win;
	}
	public void setTeam_win(int team_win) {
		this.team_win = team_win;
	}
	public int getTeam_lose() {
		return team_lose;
	}
	public void setTeam_lose(int team_lose) {
		this.team_lose = team_lose;
	}
	public int getTeam_draw() {
		return team_draw;
	}
	public void setTeam_draw(int team_draw) {
		this.team_draw = team_draw;
	}
	public String getTeam_area() {
		return team_area;
	}
	public void setTeam_area(String team_area) {
		this.team_area = team_area;
	}
	public Timestamp getTeam_date() {
		return team_date;
	}
	public void setTeam_date(Timestamp team_date) {
		this.team_date = team_date;
	}
	
	public String getTeam_photo() {
		return team_photo;
	}
	public void setTeam_photo(String team_photo) {
		this.team_photo = team_photo;
	}
	@Override
	public String toString() {
		return "TeamDto [team_num=" + team_num + ", team_name=" + team_name + ", team_win=" + team_win + ", team_lose="
				+ team_lose + ", team_draw=" + team_draw + ", team_area=" + team_area + ", team_date=" + team_date
				+ ", team_photo=" + team_photo + ", team_age=" + team_age + ", team_intro=" + team_intro
				+ ", totalCount=" + totalCount + "]";
	}
	
	
	
}

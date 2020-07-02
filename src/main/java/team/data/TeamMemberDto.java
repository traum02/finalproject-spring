package team.data;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TeamMemberDto {

	private int team_member_num;
	private String member_id; //양도받은 사람
	private int team_num;
	private String team_member_grade;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp team_member_date;
	private String beforemaster; //전팀장의 아이디.
	private int totalCount;
	
	
	
	


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getBeforemaster() {
		return beforemaster;
	}

	public void setBeforemaster(String beforemaster) {
		this.beforemaster = beforemaster;
	}

	

	

	@Override
	public String toString() {
		return "TeamMemberDto [team_member_num=" + team_member_num + ", member_id=" + member_id + ", team_num="
				+ team_num + ", team_member_grade=" + team_member_grade + ", team_member_date=" + team_member_date
				+ ", beforemaster=" + beforemaster + ", totalCount=" + totalCount + "]";
	}

	public int getTeam_member_num() {
		return team_member_num;
	}

	public void setTeam_member_num(int team_member_num) {
		this.team_member_num = team_member_num;
	}

	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Timestamp getTeam_member_date() {
		return team_member_date;
	}
	public void setTeam_member_date(Timestamp team_member_date) {
		this.team_member_date = team_member_date;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public String getTeam_member_grade() {
		return team_member_grade;
	}
	public void setTeam_member_grade(String team_member_grade) {
		this.team_member_grade = team_member_grade;
	}
	
	
}

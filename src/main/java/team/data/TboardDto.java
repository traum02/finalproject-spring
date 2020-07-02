package team.data;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TboardDto {

	private int tboard_num;
	private String member_id;
	private int team_num;
	private String tboard_title;
	private String tboard_content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp tboard_date;
	private int tboard_viewcount;
	private String tboard_photo;
	private String tboard_public;
	private String tboard_notice;
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getTboard_notice() {
		return tboard_notice;
	}

	public void setTboard_notice(String tboard_notice) {
		this.tboard_notice = tboard_notice;
	}

	@Override
	public String toString() {
		return "TboardDto [tboard_num=" + tboard_num + ", member_id=" + member_id + ", team_num=" + team_num
				+ ", tboard_title=" + tboard_title + ", tboard_content=" + tboard_content + ", tboard_date="
				+ tboard_date + ", tboard_viewcount=" + tboard_viewcount + ", tboard_photo=" + tboard_photo
				+ ", tboard_public=" + tboard_public + ", tboard_notice=" + tboard_notice + "]";
	}
	
	public int getTboard_num() {
		return tboard_num;
	}
	public void setTboard_num(int tboard_num) {
		this.tboard_num = tboard_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public String getTboard_title() {
		return tboard_title;
	}
	public void setTboard_title(String tboard_title) {
		this.tboard_title = tboard_title;
	}
	public String getTboard_content() {
		return tboard_content;
	}
	public void setTboard_content(String tboard_content) {
		this.tboard_content = tboard_content;
	}
	public Timestamp getTboard_date() {
		return tboard_date;
	}
	public void setTboard_date(Timestamp tboard_date) {
		this.tboard_date = tboard_date;
	}
	public int getTboard_viewcount() {
		return tboard_viewcount;
	}
	public void setTboard_viewcount(int tboard_viewcount) {
		this.tboard_viewcount = tboard_viewcount;
	}
	public String getTboard_photo() {
		return tboard_photo;
	}
	public void setTboard_photo(String tboard_photo) {
		this.tboard_photo = tboard_photo;
	}
	public String getTboard_public() {
		return tboard_public;
	}
	public void setTboard_public(String tboard_public) {
		this.tboard_public = tboard_public;
	}
}

package spring.project.work;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IboardDto {
	
	
	private int iboard_num;
	private String member_id;
	private String iboard_title;
	private String iboard_content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp iboard_date;
	private int iboard_viewcount;
	private String iboard_type;
	private int iboard_sec;
	private String iboard_secpwd;
	private String iboard_notice;
	private int totalCount;
	private int preidx;
	
	public int getPreidx() {
		return preidx;
	}
	public void setPreidx(int preidx) {
		this.preidx = preidx;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getIboard_num() {
		return iboard_num;
	}
	public void setIboard_num(int iboard_num) {
		this.iboard_num = iboard_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getIboard_title() {
		return iboard_title;
	}
	public void setIboard_title(String iboard_title) {
		this.iboard_title = iboard_title;
	}
	public String getIboard_content() {
		return iboard_content;
	}
	public void setIboard_content(String iboard_content) {
		this.iboard_content = iboard_content;
	}
	public Timestamp getIboard_date() {
		return iboard_date;
	}
	public void setIboard_date(Timestamp iboard_date) {
		this.iboard_date = iboard_date;
	}
	public int getIboard_viewcount() {
		return iboard_viewcount;
	}
	public void setIboard_viewcount(int iboard_viewcount) {
		this.iboard_viewcount = iboard_viewcount;
	}
	public String getIboard_type() {
		return iboard_type;
	}
	public void setIboard_type(String iboard_type) {
		this.iboard_type = iboard_type;
	}
	public int getIboard_sec() {
		return iboard_sec;
	}
	public void setIboard_sec(int iboard_sec) {
		this.iboard_sec = iboard_sec;
	}
	public String getIboard_secpwd() {
		return iboard_secpwd;
	}
	public void setIboard_secpwd(String iboard_secpwd) {
		this.iboard_secpwd = iboard_secpwd;
	}
	public String getIboard_notice() {
		return iboard_notice;
	}
	public void setIboard_notice(String iboard_notice) {
		this.iboard_notice = iboard_notice;
	}
}

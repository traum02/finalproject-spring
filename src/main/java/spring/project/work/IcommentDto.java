package spring.project.work;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IcommentDto {
	private int icomment_num;
	private String member_id;
	private int iboard_num;
	private String icomment_content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp icomment_date;
	
	
	public int getIcomment_num() {
		return icomment_num;
	}
	public void setIcomment_num(int icomment_num) {
		this.icomment_num = icomment_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getIboard_num() {
		return iboard_num;
	}
	public void setIboard_num(int iboard_num) {
		this.iboard_num = iboard_num;
	}
	public String getIcomment_content() {
		return icomment_content;
	}
	public void setIcomment_content(String icomment_content) {
		this.icomment_content = icomment_content;
	}
	public Timestamp getIcomment_date() {
		return icomment_date;
	}
	public void setIcomment_date(Timestamp icomment_date) {
		this.icomment_date = icomment_date;
	}
}

package team.data;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TCommentDto {

	private int tcomment_num;
	private String member_id;
	private int tboard_num;
	private String tcomment_content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp tcomment_date;
	private String tcomment_photo;
	@Override
	public String toString() {
		return "TCommentDto [tcomment_num=" + tcomment_num + ", member_id=" + member_id + ", tboard_num=" + tboard_num
				+ ", tcomment_content=" + tcomment_content + ", tcomment_date=" + tcomment_date + ", tcomment_photo="
				+ tcomment_photo + "]";
	}
	public int getTcomment_num() {
		return tcomment_num;
	}
	public void setTcomment_num(int tcomment_num) {
		this.tcomment_num = tcomment_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getTboard_num() {
		return tboard_num;
	}
	public void setTboard_num(int tboard_num) {
		this.tboard_num = tboard_num;
	}
	public String getTcomment_content() {
		return tcomment_content;
	}
	public void setTcomment_content(String tcomment_content) {
		this.tcomment_content = tcomment_content;
	}
	public Timestamp getTcomment_date() {
		return tcomment_date;
	}
	public void setTcomment_date(Timestamp tcomment_date) {
		this.tcomment_date = tcomment_date;
	}
	public String getTcomment_photo() {
		return tcomment_photo;
	}
	public void setTcomment_photo(String tcomment_photo) {
		this.tcomment_photo = tcomment_photo;
	}
}

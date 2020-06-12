package place.data;

public class TimeTableDto {
	private int place_id;
	private String time_val;
	private int time_id;
	private String res_time;
	private String res_type;
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

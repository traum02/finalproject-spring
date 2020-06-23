package place.data;

import java.util.List;

public class WeatherDto {
	private List<Item> item;

	public WeatherDto() {
		
	}
	
	public WeatherDto(List<Item> item) {
		super();
		this.item = item;
	}


	public List<Item> getItem() {
		return item;
	}


	public void setItem(List<Item> item) {
		this.item = item;
	}


	public class Item{
		private String category;
		private String fcstDate;
		private String fcstTime;
		private String fcstValue;
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getFcstDate() {
			return fcstDate;
		}
		public void setFcstDate(String fcstDate) {
			this.fcstDate = fcstDate;
		}
		public String getFcstTime() {
			return fcstTime;
		}
		public void setFcstTime(String fcstTime) {
			this.fcstTime = fcstTime;
		}
		public String getFcstValue() {
			return fcstValue;
		}
		public void setFcstValue(String fcstValue) {
			this.fcstValue = fcstValue;
		}
		
	}
	
}

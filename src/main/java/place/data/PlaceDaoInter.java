package place.data;

import java.util.List;

public interface PlaceDaoInter {
	public List<PlaceDto> list(String place_addr,String place_name,int pageNum);
	public PlaceDto getData(int id);
	public List<TimeTableDto> getTimes(TimeTableDto dto);
	public void addRes(ReservationDto dto);
	public void updateRes(ReservationDto dto);

}

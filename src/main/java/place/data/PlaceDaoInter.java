package place.data;

import java.util.List;

public interface PlaceDaoInter {
	public List<PlaceDto> list(String place_addr,String place_name);
	public PlaceDto getData(int id);
	public List<TimeTableDto> getTimes(TimeTableDto dto);

}

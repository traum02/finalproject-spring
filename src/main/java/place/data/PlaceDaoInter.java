package place.data;

import java.util.List;

public interface PlaceDaoInter {
	public List<PlaceDto> list(String place_addr,String place_name,int pageNum);
	public PlaceDto getData(int id);
	public List<TimeTableDto> getTimes(TimeTableDto dto);
	public void addRes(ReservationDto dto);
	public void updateResHome(ReservationDto dto);
	public void updateResAway(ReservationDto dto);
	public ReservationDto getOneRes(ReservationDto dto);
	public List<ReservationDto> getDatasForBanner();
}

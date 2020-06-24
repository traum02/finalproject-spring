package place.data;

import java.util.List;

public interface PlaceDaoInter {
<<<<<<< HEAD
	public List<PlaceDto> list(String place_addr,String place_name);
	public PlaceDto getData(int id);
	public List<TimeTableDto> getTimes(TimeTableDto dto);
=======
	public List<PlaceDto> list(String place_addr,String place_name,int pageNum);
	public PlaceDto getData(int id);
	public List<TimeTableDto> getTimes(TimeTableDto dto);
	public void addRes(ReservationDto dto);
<<<<<<< Updated upstream
	public void updateRes(ReservationDto dto);
>>>>>>> Match

=======
	public void updateResHome(ReservationDto dto);
	public void updateResAway(ReservationDto dto);
	public ReservationDto getOneRes(ReservationDto dto);
	public List<ReservationDto> getDatasForBanner();
>>>>>>> Stashed changes
}

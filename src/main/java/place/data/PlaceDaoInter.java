package place.data;

import java.util.List;

import team.data.TeamDto;

public interface PlaceDaoInter {
	public List<PlaceDto> list(String place_addr,String place_name,int pageNum);
	public PlaceDto getData(int id);
	public List<TimeTableDto> getTimes(TimeTableDto dto);
	public void addRes(ReservationDto dto);
	public void updateResHome(ReservationDto dto);
	public void updateResAway(ReservationDto dto);
	public ReservationDto getOneRes(ReservationDto dto);
	public List<ReservationDto> getDatasForBanner(String date,String time);
	public List<ReservationDto> getMyRes(String member_id,String team_id,int pageNum,String res_type,String fromDate,String untilDate,String resStatus);
	public List<ReservationDto> getAllRes(int pageNum,String res_type,String fromDate,String untilDate);
	public List<ReservationDto> getResForStandby(int pageNum,String res_type,String fromDate,String untilDate);
	public int getTotalOfMyRes(String member_id, String team_id,String res_type,String fromDate,String untilDate,String resStatus);
	public int getTotalOfAllRes(String res_type,String fromDate,String untilDate);
	public int getTotalForStandby(String res_type,String fromDate,String untilDate);
	public void addPlace(PlaceDto dto);
	public int getMaxNumOfPlace();
	public void addPlaceTime(String time_val,int place_id);
	public void updatePlace(PlaceDto dto);
	public void updateMngRes(ReservationDto dto);
	public List<Integer> getAllPlaceId();
	
	
	public TeamDto getTeamInfo(int team_num);
}

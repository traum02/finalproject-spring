package team.data;

import java.util.List;

import place.data.ReservationDto;

public interface MatchHistoryDaoInter {

	
	public List<ReservationDto> getMyRes(String member_id,String team_id,
			int pageNum,String res_type,String fromDate,String untilDate, String resStatus);
	
	public int getTotalOfMyRes(String member_id, String team_id,
			String res_type,String fromDate,String untilDate,String resStatus);
	
	public TeamDto getTeamInfo(int team_num);
	
	//달력용. 우리팀 일정 가져오기
	public List<ReservationDto> getTeamRes(int team_num);
}

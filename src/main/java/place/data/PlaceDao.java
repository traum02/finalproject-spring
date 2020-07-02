package place.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import team.data.TeamDto;

@Repository
public class PlaceDao extends SqlSessionDaoSupport implements PlaceDaoInter{

	@Override
	public List<PlaceDto> list(String place_addr,String place_name,int pageNum) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("place_addr",place_addr);
		map.put("place_name",place_name);
		map.put("pageNum",pageNum);
		return getSqlSession().selectList("selectAllOfPlace",map);
	}

	@Override
	public PlaceDto getData(int id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOfPlace", id);
	}

	@Override
	public List<TimeTableDto> getTimes(TimeTableDto dto) {
		// TODO Auto-generated method stub
		HashMap<String , String> map=new HashMap<String, String>();
		map.put("place_id",Integer.toString(dto.getPlace_id()));
		map.put("res_time",dto.getRes_time());
		map.put("res_type",dto.getRes_type());
		return getSqlSession().selectList("getResTimeOfPlace", map);
	}

	@Override
	public void addRes(ReservationDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("addReservation", dto);
	}

	@Override
	public void updateResHome(ReservationDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateReservationOfHome", dto);
	}
	@Override
	public void updateResAway(ReservationDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateReservationOfAway", dto);
	}

	@Override
	public ReservationDto getOneRes(ReservationDto dto) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getOneRes",dto);
	}

	@Override
	public List<ReservationDto> getDatasForBanner(String date,String time) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("date",date);
		map.put("time",time);

		return getSqlSession().selectList("selectForBanner",map);
	}

	@Override
	public List<ReservationDto> getMyRes(String member_id,String team_id,int pageNum,String res_type,String fromDate,String untilDate, String resStatus) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("member_id","%"+member_id+"%");
		map.put("team_id",team_id);
		map.put("pageNum",pageNum);
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);
		map.put("res_status",resStatus);
		return getSqlSession().selectList("getMyRes",map);
	}
	
	@Override
	public int getTotalOfMyRes(String member_id, String team_id,String res_type,String fromDate,String untilDate,String resStatus) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("member_id","%"+member_id+"%");
		map.put("team_id",team_id);
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);
		map.put("res_status",resStatus);
		return getSqlSession().selectOne("getTotalMyRes",map);
	}

	@Override
	public void addPlace(PlaceDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("addPlace",dto);
	}

	@Override
	public int getMaxNumOfPlace() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMaxNumOfPlace");
	}

	@Override
	public void addPlaceTime(String time_val, int place_id) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("time_val",time_val);
		map.put("place_id",place_id);
		getSqlSession().insert("addPlaceTime",map);
	}

	@Override
	public void updatePlace(PlaceDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updatePlace", dto);
	}

	@Override
	public List<ReservationDto> getAllRes(int pageNum, String res_type, String fromDate, String untilDate) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("pageNum",pageNum);
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);

		return getSqlSession().selectList("getAllRes", map);
	}

	@Override
	public int getTotalOfAllRes(String res_type, String fromDate, String untilDate) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);

		return getSqlSession().selectOne("getTotalOfAllRes",map);
	}

	@Override
	public void updateMngRes(ReservationDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateMngRes", dto);
	}
	
	@Override
	public List<ReservationDto> getResForStandby(int pageNum, String res_type, String fromDate, String untilDate) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("pageNum",pageNum);
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);

		return getSqlSession().selectList("getResForStandby",map);
	}
	@Override
	public int getTotalForStandby(String res_type, String fromDate, String untilDate) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);

		return getSqlSession().selectOne("getTotalForStandby",map);
	}
	
	@Override
	public List<Integer> getAllPlaceId() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getAllplaceid");
	}
	
	
	
	@Override
	public TeamDto getTeamInfo(int team_num) {
		// TODO Auto-generated method stub
		System.out.println("asdaaaa");
		return getSqlSession().selectOne("getInfoOfTeam", team_num);
	}
}

package place.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDao extends SqlSessionDaoSupport implements PlaceDaoInter{

	@Override
<<<<<<< HEAD
	public List<PlaceDto> list(String place_addr,String place_name) {
		// TODO Auto-generated method stub
		HashMap<String , String> map=new HashMap<String, String>();
		map.put("place_addr",place_addr);
		map.put("place_name",place_name);
=======
	public List<PlaceDto> list(String place_addr,String place_name,int pageNum) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("place_addr",place_addr);
		map.put("place_name",place_name);
		map.put("pageNum",pageNum);
>>>>>>> Match
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
<<<<<<< HEAD
=======

	@Override
	public void addRes(ReservationDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("addReservation", dto);
	}

	@Override
	public void updateRes(ReservationDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateReservation", dto);
	}
<<<<<<< Updated upstream
>>>>>>> Match
	
=======

	@Override
	public ReservationDto getOneRes(ReservationDto dto) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getOneRes",dto);
	}

	@Override
	public List<ReservationDto> getDatasForBanner() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectForBanner");
	}

>>>>>>> Stashed changes
	

}

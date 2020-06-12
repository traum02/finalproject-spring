package place.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDao extends SqlSessionDaoSupport implements PlaceDaoInter{

	@Override
	public List<PlaceDto> list(String place_addr,String place_name) {
		// TODO Auto-generated method stub
		HashMap<String , String> map=new HashMap<String, String>();
		map.put("place_addr",place_addr);
		map.put("place_name",place_name);
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
	
	

}

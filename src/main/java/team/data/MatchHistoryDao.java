package team.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import place.data.ReservationDto;

@Repository
public class MatchHistoryDao extends SqlSessionDaoSupport
	implements MatchHistoryDaoInter{

	@Override
	public List<ReservationDto> getMyRes(String member_id, String team_id, int pageNum, String res_type,
			String fromDate, String untilDate, String resStatus) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("member_id","%"+member_id+"%");
		map.put("team_id",team_id);
		map.put("pageNum",pageNum);
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);
		map.put("res_status",resStatus);
		return getSqlSession().selectList("getMyPrivateRes",map);
	}

	@Override
	public int getTotalOfMyRes(String member_id, String team_id, String res_type, String fromDate, String untilDate,
			String resStatus) {
		// TODO Auto-generated method stub
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("member_id","%"+member_id+"%");
		map.put("team_id",team_id);
		map.put("res_type",res_type);
		map.put("fromDate",fromDate);
		map.put("untilDate",untilDate);
		map.put("res_status",resStatus);
		return getSqlSession().selectOne("getTotalOfMyRes",map);
	}

	@Override
	public TeamDto getTeamInfo(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getTeamInfo", team_num);
	}

	@Override
	public List<ReservationDto> getTeamRes(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("myTeamRes",team_num);
	}

}

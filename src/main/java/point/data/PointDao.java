package point.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import spring.project.work.MemberDto;

@Repository
public class PointDao extends SqlSessionDaoSupport implements PointDaoInter {

   @Override
   public List<PointDto> getDatas(String id,String type,String sort,String fromDate,String toDate) {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("id", id);
      map.put("type", type);
      map.put("sort", sort);
      map.put("fromDate", fromDate);
      map.put("toDate", toDate);
      System.out.println(fromDate+","+toDate);
      return getSqlSession().selectList("selectAllOfPoint",map);
   }

	@Override
	public void chargePoint(MemberDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("chargePoint",dto);
	}

}
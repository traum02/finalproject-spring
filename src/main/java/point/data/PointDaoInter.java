package point.data;

import java.util.List;

import spring.project.work.MemberDto;

public interface PointDaoInter {

   public List<PointDto> getDatas(String id,String type,String sort,String fromDate,String toDate);
   
   public void chargePoint(MemberDto dto);
   
}
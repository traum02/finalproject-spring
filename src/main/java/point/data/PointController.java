package point.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.work.MemberDto;

@RestController
@CrossOrigin
public class PointController {

   @Autowired
   private PointDaoInter dao;
   
   @GetMapping("/pointhistory/all")
   public List<PointDto> getDatas(@RequestParam String id,@RequestParam(required = false, defaultValue = "all") String type,
         @RequestParam String sort,
         @RequestParam(required = false, defaultValue = "all") String fromDate,
         @RequestParam(required = false, defaultValue = "all") String toDate
         ){
      System.out.println("react >> pointlist:"+id);
      System.out.println("type:"+type);
      System.out.println("sort:"+sort);
      System.out.println("fromDate:"+fromDate);
      System.out.println("toDate:"+toDate);
      List<PointDto> list =  dao.getDatas(id,type,sort,fromDate,toDate);
      return list;
   }
   
   @PostMapping("/mypage/chargepoint")
   public void chargetPoint(@RequestBody MemberDto dto) {
	   System.out.println(dto.getId());
	   System.out.println(dto.getPoint());
	   dao.chargePoint(dto);
   }
   
   
}
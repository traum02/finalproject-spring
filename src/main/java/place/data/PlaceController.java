package place.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PlaceController {

	@Autowired
	PlaceDaoInter dao;
	
	@GetMapping("/placelist")
	public List<PlaceDto> list(@RequestParam(value="place_name",defaultValue = "") String place_name,@RequestParam(defaultValue = "") String place_addr){
//		System.out.println(place_name);
		System.out.println(place_addr+"지역");
		place_name="%"+place_name+"%";
		place_addr="%"+place_addr+"%";
//		System.out.println(sdf.format(today));
//		System.out.println(type);
//		System.out.println(res_time);
		return dao.list(place_addr,place_name);
	}
	
	@GetMapping("/placelist/detail")
	public PlaceDto detail(@RequestParam int id){
//		System.out.println(id);
//		System.out.println(dao.getData(id).getPlace_name());
		
		return dao.getData(id);
	}
	@GetMapping("/placelist/gettime")
	public List<TimeTableDto> getTime(@ModelAttribute TimeTableDto dto){
		System.out.println(dto.getPlace_id()+"id");
		if(dto.getRes_time().equals("")||dto.getRes_time().equals("undefined")) {
			Date today=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			dto.setRes_time(sdf.format(today));
		}
		
		return dao.getTimes(dto);
	}
	
}

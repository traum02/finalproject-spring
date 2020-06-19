package place.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> Match
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PlaceController {

	@Autowired
<<<<<<< HEAD
	PlaceDaoInter dao;
	
	@GetMapping("/placelist")
	public List<PlaceDto> list(@RequestParam(value="place_name",defaultValue = "") String place_name,@RequestParam(defaultValue = "") String place_addr){
//		System.out.println(place_name);
		System.out.println(place_addr+"지역");
=======
	private PlaceDaoInter dao;
	
	@GetMapping("/placelist")
	public List<PlaceDto> list(@RequestParam(value="place_name",defaultValue = "") String place_name,@RequestParam(defaultValue = "") String place_addr,@RequestParam int pageNum){
//		System.out.println(place_name);
		if(place_addr.equals("서울시 전체"))
			place_addr="서울";
>>>>>>> Match
		place_name="%"+place_name+"%";
		place_addr="%"+place_addr+"%";
//		System.out.println(sdf.format(today));
//		System.out.println(type);
//		System.out.println(res_time);
<<<<<<< HEAD
		return dao.list(place_addr,place_name);
=======
		return dao.list(place_addr,place_name,pageNum);
>>>>>>> Match
	}
	
	@GetMapping("/placelist/detail")
	public PlaceDto detail(@RequestParam int id){
//		System.out.println(id);
//		System.out.println(dao.getData(id).getPlace_name());
		
		return dao.getData(id);
	}
	@GetMapping("/placelist/gettime")
	public List<TimeTableDto> getTime(@ModelAttribute TimeTableDto dto){
<<<<<<< HEAD
		System.out.println(dto.getPlace_id()+"id");
=======
//		System.out.println(dto.getPlace_id()+"id, type="+dto.getRes_type());
>>>>>>> Match
		if(dto.getRes_time().equals("")||dto.getRes_time().equals("undefined")) {
			Date today=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			dto.setRes_time(sdf.format(today));
		}
		
		return dao.getTimes(dto);
	}
	
<<<<<<< HEAD
}
=======
	@PostMapping("/placelist/addRes")
	public void addRes(@RequestBody ReservationDto dto) {
//		System.out.println(dto.getSelectTeam()+"addres, type="+dto.getRes_type());
		if(dto.getSelectTeam().equals("1팀")) {
			dto.setRes_team1("1");
			dto.setRes_team2("0");
		}else {
			dto.setRes_team1("0");
			dto.setRes_team2("1");
		}
		dao.addRes(dto);
	}
	
	@PostMapping("/placelist/updateRes")
	public void updateRes(@RequestBody ReservationDto dto) {
		System.out.println(dto.getSelectTeam()+"update, type="+dto.getRes_type());
		if(dto.getSelectTeam().equals("1팀")) {
			dto.setRes_team1("1");
			dto.setRes_team2("0");
		}else {
			dto.setRes_team1("0");
			dto.setRes_team2("1");
		}
		dao.updateRes(dto);
	}

}

>>>>>>> Match

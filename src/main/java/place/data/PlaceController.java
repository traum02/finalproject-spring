package place.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PlaceController {

	@Autowired
	private PlaceDaoInter dao;
	
	@GetMapping("/placelist")
	public List<PlaceDto> list(@RequestParam(value="place_name",defaultValue = "") String place_name,@RequestParam(defaultValue = "") String place_addr,@RequestParam int pageNum){
//		System.out.println(place_name);
		if(place_addr.equals("서울시 전체"))
			place_addr="서울";
		place_name="%"+place_name+"%";
		place_addr="%"+place_addr+"%";
//		System.out.println(sdf.format(today));
//		System.out.println(type);
//		System.out.println(res_time);
		return dao.list(place_addr,place_name,pageNum);
	}
	
	@GetMapping("/placelist/detail")
	public PlaceDto detail (@RequestParam int id){
//		System.out.println(id);
//		System.out.println(dao.getData(id).getPlace_name());
		return dao.getData(id);
	}
	
	@GetMapping("/getWeather")
	public String getWeather() {
//		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst";//초단기실황
//		String apiUrl="http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst";//초단기예보
		String apiUrl="http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";//동네
		// 홈페이지에서 받은 키
		String serviceKey = "Ww7kszIH%2F5XnxfK1RnyZkQh1CWTlD03AG1FX8wqJlvcV2t4P%2BeMNC%2BwRjsRrPKCVIZjOHyGOuengQ9QdXW4xAA%3D%3D";
		String nx = "60";	//위도
		String ny = "127";	//경도
		String baseDate = "20200623";	//조회하고싶은 날짜
		String baseTime = "0800";	//조회하고싶은 시간
		String type = "json";	//타입 xml, json 등등 ..
		String result=null;
		
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));	/* 타입 */
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("250", "UTF-8"));	/* 타입 */
//        http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Ww7kszIH%2F5XnxfK1RnyZkQh1CWTlD03AG1FX8wqJlvcV2t4P%2BeMNC%2BwRjsRrPKCVIZjOHyGOuengQ9QdXW4xAA%3D%3D&
//        	numOfRows=20&pageNo=3&base_date=20200619&base_time=2330&nx=60&ny=127
        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        //어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
//        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
        		sb.append(line);
        }
        rd.close();
        conn.disconnect();
        result= sb.toString();
//        System.out.println("result : "+result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /*
         * POP	강수확률	 %
         * PTY	강수형태	코드값    - 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
         * R06	6시간 강수량	범주 (1 mm)
         * REH	습도	 %
         * S06	6시간 신적설	범주(1 cm)
         * SKY	하늘상태	코드값	-	맑음(1), 구름많음(3), 흐림(4) 
         * T3H	3시간 기온	 ℃
         * TMN	아침 최저기온	 ℃
         * TMX	낮 최고기온	 ℃
         * UUU	풍속(동서성분)	 m/s
         * VVV	풍속(남북성분)	 m/s
         */
        
        
//        System.out.println(result.substring(result.indexOf("items")+8,result.lastIndexOf("}]")+2));
        String tmp="{"+result.substring(result.indexOf("items")+8,result.lastIndexOf("}]")+2)+"}";
        System.out.println(tmp);
//        ObjectMapper mapper=new ObjectMapper();
//        try {
//			WeatherDto wdto=mapper.readValue(tmp, WeatherDto.class);
//			System.out.println(wdto);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		return tmp;

	}
	
	@GetMapping("/placelist/gettime")
	public List<TimeTableDto> getTime(@ModelAttribute TimeTableDto dto){
		System.out.println(dto.getPlace_id()+"id");
//		System.out.println(dto.getPlace_id()+"id, type="+dto.getRes_type());
		if(dto.getRes_time().equals("")||dto.getRes_time().equals("undefined")) {
			Date today=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			dto.setRes_time(sdf.format(today));
		}
		
		return dao.getTimes(dto);
	}
	
	@PostMapping("/placelist/addRes")
	public void addRes(@RequestBody ReservationDto dto) {
//		System.out.println(dto.getSelectTeam()+"addres, type="+dto.getRes_type());
		if(dto.getSelectTeam().equals("1팀")) {
			dto.setRes_team1("1");
			dto.setRes_team2("0");
			dto.setHome_member_id(dto.getMember_id());
			dto.setAway_member_id("");
		}else {
			dto.setRes_team1("0");
			dto.setRes_team2("1");
			dto.setHome_member_id("");
			dto.setAway_member_id(dto.getMember_id());
		}
		dao.addRes(dto);
	}
	
	@PostMapping("/placelist/updateRes")
	public void updateRes(@RequestBody ReservationDto dto) {
		System.out.println(dto.getSelectTeam()+"update, type="+dto.getRes_type());
		if(dto.getSelectTeam().equals("1팀")) {
			dto.setRes_team1("1");
			dto.setRes_team2("0");
			if(dao.getOneRes(dto).getHome_member_id().equals("")) {
				dto.setHome_member_id(dto.getMember_id());	
			}else {
				dto.setHome_member_id("/"+dto.getMember_id());
			}
			dto.setAway_member_id("");
			dao.updateResHome(dto);
		}else {
			dto.setRes_team1("0");
			dto.setRes_team2("1");
			dto.setHome_member_id("");
			if(dao.getOneRes(dto).getAway_member_id().equals("")) {
				dto.setAway_member_id(dto.getMember_id());
			}else {
				dto.setAway_member_id("/"+dto.getMember_id());
			}
			dao.updateResAway(dto);
		}
	}

}


package place.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
<<<<<<< Updated upstream
=======
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
		String baseTime = "2300";	//조회하고싶은 시간
		String type = "json";	//타입 xml, json 등등 ..
		String result=null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1=new SimpleDateFormat("HH00");
		Date date=new Date();
		String t=sdf1.format(date);
		baseTime=Integer.toString(Integer.parseInt(t)/300*300-100);
		
		
		String today=sdf.format(date);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		date = c.getTime();

		if(baseTime.equals("-100")) {
			baseTime="2300";
			today=sdf.format(date);
		}
		
		if(baseTime.length()==3) {
			baseTime="0"+baseTime;
		}
		
		
		
		
		System.out.println(today+"asdasd");
		System.out.println(baseTime+"ttttt");

		
		
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /* 조회하고싶은 날짜*/
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
//        System.out.println(tmp);
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
	
	@GetMapping(value="/getWeather2", produces="text/plain;charset=UTF-8")
	public String getWeather2() {
//		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst";//초단기실황
//		String apiUrl="http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst";//초단기예보
		String apiUrl="http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";//동네
		// 홈페이지에서 받은 키
		String serviceKey = "Ww7kszIH%2F5XnxfK1RnyZkQh1CWTlD03AG1FX8wqJlvcV2t4P%2BeMNC%2BwRjsRrPKCVIZjOHyGOuengQ9QdXW4xAA%3D%3D";
		String baseDate = "20200623";	//조회하고싶은 날짜
		String baseTime = "1800";	//조회하고싶은 시간
		String type = "json";	//타입 xml, json 등등 ..
		String result=null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		SimpleDateFormat sdf1=new SimpleDateFormat("HH00");
		String t=sdf1.format(date);
		String today=sdf.format(date);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		
		if(Integer.parseInt(t)<0600) {
			baseTime="1800";
			baseDate=sdf.format(date);
		}else if(Integer.parseInt(t)<1800) {
			baseTime="0600";
			baseDate=today;
		}else if(Integer.parseInt(t)>1800) {
			baseTime="1800";
			baseDate=today;
		}
		

		System.out.println(baseDate+"/"+baseTime+"asdasd");
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        try {
        	urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
            urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11B00000", "UTF-8")); /*하단 참고자료 참조*/
            urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(baseDate+baseTime, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력-최근 24시간 자료만 제공*/
            
            
//            http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey=Ww7kszIH%2F5XnxfK1RnyZkQh1CWTlD03AG1FX8wqJlvcV2t4P%2BeMNC%2BwRjsRrPKCVIZjOHyGOuengQ9QdXW4xAA%3D%3D
//            	&numOfRows=100&pageNo=1&regId=11B00000&tmFc=202006231800
        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        //어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
        System.out.println(url);
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
        String tmp=result.substring(result.indexOf("item")+16,result.lastIndexOf("}]"))+",\"today\":\""+baseDate+"\"}";
        
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

>>>>>>> Stashed changes
		
		return dao.getData(id);
	}
<<<<<<< Updated upstream
=======
	
	
	@GetMapping("/forbanner")
	public List<ReservationDto> getForBanner(){
		return dao.getDatasForBanner();
	}
	
>>>>>>> Stashed changes
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

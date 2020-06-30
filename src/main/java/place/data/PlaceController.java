package place.data;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.project.work.MemberDaoInter;

@RestController
@CrossOrigin
public class PlaceController {

	@Autowired
	private PlaceDaoInter dao;
	@Autowired
	private MemberDaoInter mdao;
	
	@PostMapping("/manage/updateres")
	public void updatemngRes(@RequestBody ReservationDto dto) {
//		System.out.println(dto.getRes_id());
//		System.out.println(dto.getRes_team1goal());
//		System.out.println(dto.getRes_team2goal());
		dao.updateMngRes(dto);
	}
	
	@GetMapping("/mngres")
	public List<ReservationDto> getAllRes(@RequestParam int pageNum,@RequestParam(defaultValue = "",required = false) String res_type,@RequestParam(defaultValue = "20200101",required = false) String fromDate,@RequestParam(defaultValue = "20501231",required = false) String untilDate,@RequestParam String res_status) {
//		System.out.println("res_type: "+res_type+" 시간"+fromDate+"~"+untilDate);
//		System.out.println(res_status);
		List<ReservationDto> list=null;
		if(res_status.equals("1")) {
			list=dao.getResForStandby((pageNum-1)*10, res_type, fromDate, untilDate);
		}else {
			list=dao.getAllRes((pageNum-1)*10, res_type, fromDate, untilDate);
		}
		return list;
	}

	@GetMapping("/totalallres")
	public int getTotalOfAllRes(@RequestParam(defaultValue = "",required = false) String res_type,@RequestParam(defaultValue = "20200101",required = false) String fromDate,@RequestParam(defaultValue = "20501231",required = false) String untilDate,@RequestParam String res_status) {
		int totalcount=0;
		if(res_status.equals("1")) {
			totalcount=dao.getTotalForStandby(res_type, fromDate, untilDate);
		}else {
			totalcount=dao.getTotalOfAllRes(res_type, fromDate, untilDate);
		}

		return totalcount;
	}

	@GetMapping("/myhistory/teaminfo")
	public TeamDto teamInfo(int team_num) {
		
		return dao.getTeamInfo(team_num);
	}
	
	
	@GetMapping("/myres")
	public List<ReservationDto> getMyRes(@RequestParam(defaultValue = "") String member_id,@RequestParam(defaultValue = "0") String team_id,@RequestParam(defaultValue = "0",required = false) int pageNum,@RequestParam(defaultValue = "",required = false) String res_type,@RequestParam(defaultValue = "20200101",required = false) String fromDate,@RequestParam(defaultValue = "20501231",required = false) String untilDate,@RequestParam(required = false,defaultValue = "") String resStatus) {
		team_id=Integer.toString(mdao.getMemberData(member_id).getTeam_int());
		System.out.println(resStatus);
		List<ReservationDto> list=dao.getMyRes(member_id,team_id,(pageNum-1)*10, res_type, fromDate, untilDate,resStatus);
		System.out.println(member_id+","+team_id+"--teamid");
		System.out.println("res_type: "+res_type+" 시간"+fromDate+"~"+untilDate);
//		아이디 포함 값중 실제 동일여부 체크
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getRes_type().equals("0")) {
				String[] homeMember=list.get(i).getHome_member_id().split("/");
				String[] awayMember=list.get(i).getAway_member_id().split("/");
				boolean chk=false;
				if(Arrays.asList(homeMember).contains(member_id)) {
					chk=true;
				}
				if(Arrays.asList(awayMember).contains(member_id)) {
					chk=true;
				}
				if(chk==false) {
					list.remove(i);
				}
			}
		}
		return list;
	}
	
	@GetMapping("/totalmyres")
	public int getTotalOfMyRes(@RequestParam(defaultValue = "") String member_id,@RequestParam(defaultValue = "0") String team_id,@RequestParam(defaultValue = "",required = false) String res_type,@RequestParam(defaultValue = "20200101",required = false) String fromDate,@RequestParam(defaultValue = "20501231",required = false) String untilDate,@RequestParam(required = false,defaultValue = "") String resStatus) {
		team_id=Integer.toString(mdao.getMemberData(member_id).getTeam_int());
		return dao.getTotalOfMyRes(member_id,team_id, res_type, fromDate, untilDate,resStatus);
	}
	
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
		SimpleDateFormat sdf1=new SimpleDateFormat("HHmm");
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
		
		System.out.println("today:"+today);
		System.out.println("today:"+t);
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

		
		return tmp;

	}
	
	private FileOutputStream fos;
	public void writeFile(MultipartFile file,String path)
	{
		//파일명
		Date today=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
//		String fileName=sdf.format(today)+file.getOriginalFilename();
		String fileName=file.getOriginalFilename();

		try {
			byte []fileData=file.getBytes();
			fos=new FileOutputStream(path+"\\"+fileName);
			fos.write(fileData);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@RequestMapping(value = "/place/uploadpic", headers = ("content-type=multipart/*"),method = RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest request, @RequestParam MultipartFile[] uploadFile) {
		String oriFileName="";
		for(int i=0;i<uploadFile.length;i++) {
			System.out.println("file: "+uploadFile[i].getOriginalFilename());
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
			String rename=sdf.format(date)+uploadFile[i].getOriginalFilename();
			String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image");
			oriFileName+=uploadFile[i].getOriginalFilename()+"/";
			System.out.println(path);
			writeFile(uploadFile[i], path);
		}
		oriFileName=oriFileName.substring(0,oriFileName.lastIndexOf("/"));
		
		return oriFileName;
	}
	
	@PostMapping("/insertplace")
	public void addPlace(@RequestBody PlaceDto dto) {
		String[] etc=dto.getPlace_etc().split(",");
		String place_etc="";
		for(String s: etc) {
			if(s.equals("")) {
				place_etc+=s;
			}else {
				place_etc+=s+"/";
			}
		}
		if(place_etc.contains("/")) {
			place_etc=place_etc.substring(0, place_etc.length()-1);
		}
		place_etc=place_etc.replace("0", "주차");
		place_etc=place_etc.replace("1", "공");
		place_etc=place_etc.replace("2", "샤워");
		place_etc=place_etc.replace("3", "풋살화");
		place_etc=place_etc.replace("4", "유니폼");

		System.out.println("place_etc="+place_etc);
		System.out.println("place_price="+dto.getPlace_price());
		System.out.println("place_max="+dto.getPlace_max());
		
		dto.setPlace_etc(place_etc);
		dao.addPlace(dto);

		//시간테이블 생성
		int place_maxid=dao.getMaxNumOfPlace();
		
		for(int i=1;i<=23;i+=2) {
			String time_val=((i+"").length()>1?i+"":"0"+i)+((i+2+"").length()>1?(i+2+""):"0"+(i+2));
			if(i+2==25) {
				time_val=time_val.replace("25", "01");
			}
			dao.addPlaceTime(time_val, place_maxid);
		}
		
	}
	
	@PostMapping("/updateplace")
	public void updatePlace(@RequestBody PlaceDto dto) {
		String[] etc=dto.getPlace_etc().split(",");
		String place_etc="";
		for(String s: etc) {
			if(s.equals("")) {
				place_etc+=s;
			}else {
				place_etc+=s+"/";
			}
		}
		if(place_etc.contains("/")) {
			place_etc=place_etc.substring(0, place_etc.length()-1);
		}

		System.out.println("place_etc="+place_etc);
		System.out.println("place_price="+dto.getPlace_price());
		System.out.println("place_max="+dto.getPlace_max());
		
		dto.setPlace_etc(place_etc);
		dao.updatePlace(dto);

//		//시간테이블 생성
//		int place_maxid=dao.getMaxNumOfPlace();
//		
//		for(int i=1;i<=23;i+=2) {
//			String time_val=((i+"").length()>1?i+"":"0"+i)+((i+2+"").length()>1?(i+2+""):"0"+(i+2));
//			if(i+2==25) {
//				time_val=time_val.replace("25", "01");
//			}
//			dao.addPlaceTime(time_val, place_maxid);
//		}
		
	}

	
	
	@GetMapping("/forbanner")
	public List<ReservationDto> getForBanner(String date,String time){
		System.out.println(date+time);
		return dao.getDatasForBanner(date,time);
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


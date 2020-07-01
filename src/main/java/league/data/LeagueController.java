package league.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import place.data.PlaceDaoInter;
import place.data.ReservationDto;

@RestController
@CrossOrigin
public class LeagueController {

	@Autowired
	private LeagueDaoInter dao;
	@Autowired
	private PlaceDaoInter pdao;
	
	@GetMapping("/leagueRanking")
	public List<LeagueRankingDto> getDatas(@RequestParam(defaultValue = "5") int id){
		return dao.getDatas(id);
	}
	
	@GetMapping("/leaguePlan")
	public List<LeagueRoundDto> getPlan(){
		int id=dao.getLeagueMaxId();
		return dao.getPlan(id);
	}
	
	@GetMapping("/leagueName")
	public LeagueDto getLeagueName(@RequestParam int pageNum){
//		System.out.println(pageNum);
		return dao.getLeagueName(pageNum);
	}
	
	@GetMapping("/leagueDate")
	public String getLeagueDate(@RequestParam int id,int pageNum){
		return dao.getLeagueDate(id, pageNum);
	}
	
	@PostMapping("/joinLeague")
	public void joinLeague() {
		LeagueRankingDto dto=new LeagueRankingDto();
		dto.setLeague_team_id(11);
		dto.setLeague_team_name("test2");
		dto.setLeague_id(dao.getLeagueMaxId());
//		System.out.println(dao.getLeagueMaxId());
		dao.joinLeague(dto);
	}
	
	
	@PostMapping("/makeLeaguePlan")
	public void makePlan() {
		List<LeagueRankingDto> ranklist=dao.getDatas(dao.getLeagueMaxId());
		
		List<Object> teamList=new Vector<Object>();
		for(int i=0;i<ranklist.size();i++) {
//			if(ranklist.get(i).getLeague_id()==dao.getLeagueMaxId())
				teamList.add(ranklist.get(i).getLeague_team_id());
		}
		
		List<String> list=new Vector<String>();
		for(int i=0;i<teamList.size();i++) {
			for(int j=0;j<teamList.size();j++) {
				if(i==j) {
					continue;
				}
				list.add(teamList.get(i)+"vs"+teamList.get(j));
			}
		}
//		System.out.println(list);
		
		List<String> planList=new Vector<String>();
		int round=0;
		for(int i=0;i<list.size();i++) {
			
			String[] a=list.get(i).split("vs");
//				System.out.println(a[0]+","+a[1]);
			for(int j=0;j<list.size();j++) {
				if(!(list.get(j).contains(a[0])||list.get(j).contains(a[1]))) {
//						System.out.println(list.get(j));
					round++;
					planList.add("round:"+round+"/"+list.get(i)+"/"+list.get(j));
//					System.out.println(list.get(j));
					list.remove(j);
					list.remove(i);
					i=-1;
					break;
				}
			}
		}
		
		ReservationDto resdto=new ReservationDto();
		
		
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 5, 28);//2020년 6월 28일
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		LeagueRoundDto rdto=new LeagueRoundDto();
		String roundCnt;
		String[] teams;
		String team1,team2;
		rdto.setLeague_id(dao.getLeagueMaxId());
		
		List<Integer> place_id=pdao.getAllPlaceId();
		System.out.println(place_id.get((int)(Math.random()*place_id.size())));
		
		for(String s:planList) {
			roundCnt=s.substring(6,7);
			teams=s.substring(8).split("/");
			System.out.println("----------");
			for(String a:teams) {
				String placeid=place_id.get((int)(Math.random()*place_id.size())).toString();
				System.out.println(roundCnt+"round");
				team1=a.substring(0,a.indexOf("vs"));
				team2=a.substring(a.indexOf("vs")+2);
				System.out.println("team1="+team1);
				System.out.println("team2="+team2);
				rdto.setRound_cnt(Integer.parseInt(roundCnt));
				rdto.setLeague_team1(Integer.parseInt(team1));
				rdto.setLeague_team2(Integer.parseInt(team2));
				String strDate = df.format(cal.getTime());

				rdto.setRound_date(strDate);
				System.out.println(strDate);
				//res table에 insert
				
				resdto.setHome_member_id(team1);
				resdto.setAway_member_id(team2);
				resdto.setPlace_id(placeid);//장소
				resdto.setRes_type("1");
				resdto.setRes_team1("1");
				resdto.setRes_team2("1");
				resdto.setRes_time(strDate);
				resdto.setTime_id(dao.getTimeId(placeid));//시간
				resdto.setRes_etc("2");
				
				pdao.addRes(resdto);
				
				
				dao.makePlan(rdto);
			}
			
			cal.add(Calendar.DATE, 7);
			System.out.println("after: " + df.format(cal.getTime()));
		}
		System.out.println("==================");
	}
}

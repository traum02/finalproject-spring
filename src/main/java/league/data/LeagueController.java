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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LeagueController {

	@Autowired
	private LeagueDaoInter dao;
	
	@GetMapping("/leagueRanking")
	public List<LeagueRankingDto> getDatas(){
		return dao.getDatas();
	}
	
	@GetMapping("/leaguePlan")
	public List<LeagueRoundDto> getPlan(){
		return null;
	}
	
	@PostMapping("/makeLeaguePlan")
	public void makePlan() {
		List<LeagueRankingDto> ranklist=dao.getDatas();
		
		List<Object> teamList=new Vector<Object>();
		for(int i=0;i<ranklist.size();i++) {
			if(ranklist.get(i).getLeague_join()=='Y')
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
		
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 5, 28);//2020년 6월 28일
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		LeagueRoundDto rdto=new LeagueRoundDto();
		String roundCnt;
		String[] teams;
		String team1,team2;
		rdto.setLeague_id(dao.getLeagueMaxId());
		
		for(String s:planList) {
			roundCnt=s.substring(6,7);
			teams=s.substring(8).split("/");
			System.out.println("----------");
			for(String a:teams) {
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
				dao.makePlan(rdto);
			}
			
			cal.add(Calendar.DATE, 7);
			System.out.println("after: " + df.format(cal.getTime()));
		}
		System.out.println("==================");

	}
}

package league.data;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

public class MakePlan {

	@Autowired
	LeagueDao dao;
	public List<LeagueRoundDto> makePlan(List<LeagueRankingDto> dto,int maxId){
		List<Object> teamList=new Vector<Object>();
		for(int i=0;i<dto.size();i++) {
//			if(dto.get(i).getLeague_join()=='Y')
				teamList.add(dto.get(i).getLeague_team_id());
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
		
		
//		int aa=dao.getLeagueMaxId();
		System.out.println(dao.getLeagueMaxId());
		List<LeagueRoundDto> rlist=new Vector<LeagueRoundDto>();
		LeagueRoundDto rdto=new LeagueRoundDto();
		String roundCnt;
		String[] teams;
		String team1,team2;
		rdto.setLeague_id(maxId);
		
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
				rdto.setLeague_team1(Integer.parseInt(team2));
				rlist.add(rdto);
			}
		}
		System.out.println("==================");
		
		return rlist;
	}

}

package team.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import place.data.ReservationDto;
import spring.project.work.MemberDaoInter;

@RestController
@CrossOrigin
public class MatchHistoryController {
	
	@Autowired
	private MemberDaoInter mdao;
	
	@Autowired
	private MatchHistoryDaoInter dao;

	@GetMapping("/team/myhistory/teaminfo")
	public TeamDto teamInfo(int team_num) {
		
		return dao.getTeamInfo(team_num);
	}
	
	
	@GetMapping("/team/myres")
	public List<ReservationDto> getMyRes(@RequestParam(defaultValue = "") String member_id,@RequestParam(defaultValue = "0") String team_id,@RequestParam int pageNum,@RequestParam(defaultValue = "",required = false) String res_type,@RequestParam(defaultValue = "20200101",required = false) String fromDate,@RequestParam(defaultValue = "20501231",required = false) String untilDate,@RequestParam(required = false,defaultValue = "") String resStatus) {
		team_id=Integer.toString(mdao.getMemberData(member_id).getTeam_int());
//		team_id="22";
		System.out.println(resStatus);
		List<ReservationDto> list=dao.getMyRes(member_id,team_id,(pageNum-1)*10, res_type, fromDate, untilDate,resStatus);
		System.out.println(member_id+","+team_id+"--teamid");
		System.out.println("res_type: "+res_type+" �ð�"+fromDate+"~"+untilDate);
//		���̵� ���� ���� ���� ���Ͽ��� üũ
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
	
	@GetMapping("/team/totalmyres")
	public int getTotalOfMyRes(@RequestParam(defaultValue = "") String member_id,@RequestParam(defaultValue = "0") String team_id,@RequestParam(defaultValue = "",required = false) String res_type,@RequestParam(defaultValue = "20200101",required = false) String fromDate,@RequestParam(defaultValue = "20501231",required = false) String untilDate,@RequestParam(required = false,defaultValue = "") String resStatus) {
		team_id=Integer.toString(mdao.getMemberData(member_id).getTeam_int());
		return dao.getTotalOfMyRes(member_id,team_id, res_type, fromDate, untilDate,resStatus);
	}
	
	//�޷¿�
	@GetMapping("/team/myteamres")
	public  List<ReservationDto> myteamres(@RequestParam int team_num)
	{
		return dao.getTeamRes(team_num);
	}
}

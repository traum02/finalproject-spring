package team.data;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamMemberController {

	@Autowired
	private TeamMemberDaoInter dao;
	
	//구성원출력
	@GetMapping("/teammember/list")
	@ResponseBody
	public List<HashMap<String, Object>> list(@RequestParam(value="start",defaultValue = "0")int start,
			@RequestParam(value="team_num",defaultValue = "0")int team_num)
	{
		System.out.println("리액트에서 보낸값 팀 구성원"+team_num);
		List<HashMap<String, Object>> list = dao.getAllDatas(team_num, start);
		
		System.out.println("s"+list.size());
		return list;
	}
	
	
	//승인 대기자 출력
		@GetMapping("/teammember/applylist")
		@ResponseBody
		public List<HashMap<String, Object>> applylist(@RequestParam(value="start",defaultValue = "0")int start,
				@RequestParam(value="team_num",defaultValue = "0")int team_num)
		{
			System.out.println("대기자 구성원"+team_num);
			List<HashMap<String, Object>> list = dao.getAllDatasApply(team_num, start);
			return list;
		}
	
	//팀 제명
	@RequestMapping(value = "/teammember/delete",method = RequestMethod.DELETE)
	public void delete(@RequestParam int team_member_num)
	{
		System.out.println(team_member_num);
		dao.outTeamMember(team_member_num);
	}
	
	//권한주기
	@PutMapping("/teammember/update")
	public void update(@RequestBody TeamMemberDto dto)
	{
		System.out.println(dto);
		System.out.println("리액트에서 보낸 값 권한변경");
		System.out.println(dto.getTeam_member_grade()); //master까지 들어오는거 확인
		
		//준 권한이 팀장이었다면 전 팀장의 권한을 내림
		if(dto.getTeam_member_grade().equals("master"))
		{
				System.out.println("전 팀장 세션"+dto.getBeforemaster()); 
					dao.masterChange(dto.getBeforemaster()); // 이미 양도한 전 팀장의 권한을 멤버로 내림
		}
		dao.gradeChangeTeamMember(dto); //권한 바꾸려는 대상 권한 변경
		
		
		
	
	}
	
	//팀 생성한 사람한테 마스터 권한주기
	@PutMapping("/teammember/birthmaster")
	public void birthmaster(@RequestBody TeamMemberDto dto)
	{
		dao.birthMaster(dto);
	}
	
	//팀 인원 세기
		@GetMapping("/teammember/count")
		public int countTeamMember(@RequestParam int team_num)
		{
			System.out.println("리액트에서 보낸값 카운트");
			return dao.getTotalMember(team_num);
		}
		
	//팀장 정보 보기
		@GetMapping("/teammember/master")
		public TeamMemberDto viewmaster(@RequestParam int team_num)
		{
			System.out.println("리액트에서 보낸값 마스터"+team_num);
			return dao.selectMaster(team_num);
		}
	//검색
		@ResponseBody
		@GetMapping("teammember/searchlist")
		public List<HashMap<String, Object>> searchList(@RequestParam
				String option,String keyword,int start)
		{
			List<HashMap<String, Object>> list =dao.getSearchDatas(option, keyword, start);
			System.out.println("멤버 검색 옵션:"+option+"키워드:"+keyword+start);
			return list;
		}
		
	//가입신청
		@PostMapping("/teammember/join")
		public void join(@RequestBody TeamMemberDto dto)
		{
			System.out.println(dto);
			System.out.println("리액트에서 보낸 값 join+섹션"+dto.getMember_id());
			dao.joinTeamMember(dto);
		}
		
	//팀 생성자 팀장으로서 테이블에 추가	
		@GetMapping("/teammember/createmaster")
		public void createmaster(@RequestParam String member_id,int team_num)
		{
			System.out.println("팀 생성자 권한 마스터?"+member_id+team_num);
			dao.joinTeamMaster(team_num, member_id);	
		}
		
		//소속된 팀 num 구하기
		@GetMapping("teammember/myteamnum")
		public int teamnum(@RequestParam String member_id)
		{
			System.out.println("세션값 id=="+member_id);
			return dao.myTeamNum(member_id);
		}
	
		//권한양도 페이지 접속자 grade 구하기 (팀장)
		@GetMapping("teammember/teammaster")
		public String master(@RequestParam String member_id)
		{
			System.out.println("세션값 팀장 id ==" + member_id);
			return dao.marsterGrade(member_id);
		}
		
		//검색 전체 갯수 구하기
		@GetMapping("teammember/searchtotal")
		public int searchtotal(@RequestParam String option,String keyword)
		{
			return dao.getTotalCountSearch(option, keyword);
		}
}

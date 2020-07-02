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
	
	//���������
	@GetMapping("/teammember/list")
	@ResponseBody
	public List<HashMap<String, Object>> list(@RequestParam(value="start",defaultValue = "0")int start,
			@RequestParam(value="team_num",defaultValue = "0")int team_num)
	{
		System.out.println("����Ʈ���� ������ �� ������"+team_num);
		List<HashMap<String, Object>> list = dao.getAllDatas(team_num, start);
		
		System.out.println("s"+list.size());
		return list;
	}
	
	
	//���� ����� ���
		@GetMapping("/teammember/applylist")
		@ResponseBody
		public List<HashMap<String, Object>> applylist(@RequestParam(value="start",defaultValue = "0")int start,
				@RequestParam(value="team_num",defaultValue = "0")int team_num)
		{
			System.out.println("����� ������"+team_num);
			List<HashMap<String, Object>> list = dao.getAllDatasApply(team_num, start);
			return list;
		}
	
	//�� ����
	@RequestMapping(value = "/teammember/delete",method = RequestMethod.DELETE)
	public void delete(@RequestParam int team_member_num)
	{
		System.out.println(team_member_num);
		dao.outTeamMember(team_member_num);
	}
	
	//�����ֱ�
	@PutMapping("/teammember/update")
	public void update(@RequestBody TeamMemberDto dto)
	{
		System.out.println(dto);
		System.out.println("����Ʈ���� ���� �� ���Ѻ���");
		System.out.println(dto.getTeam_member_grade()); //master���� �����°� Ȯ��
		
		//�� ������ �����̾��ٸ� �� ������ ������ ����
		if(dto.getTeam_member_grade().equals("master"))
		{
				System.out.println("�� ���� ����"+dto.getBeforemaster()); 
					dao.masterChange(dto.getBeforemaster()); // �̹� �絵�� �� ������ ������ ����� ����
		}
		dao.gradeChangeTeamMember(dto); //���� �ٲٷ��� ��� ���� ����
		
		
		
	
	}
	
	//�� ������ ������� ������ �����ֱ�
	@PutMapping("/teammember/birthmaster")
	public void birthmaster(@RequestBody TeamMemberDto dto)
	{
		dao.birthMaster(dto);
	}
	
	//�� �ο� ����
		@GetMapping("/teammember/count")
		public int countTeamMember(@RequestParam int team_num)
		{
			System.out.println("����Ʈ���� ������ ī��Ʈ");
			return dao.getTotalMember(team_num);
		}
		
	//���� ���� ����
		@GetMapping("/teammember/master")
		public TeamMemberDto viewmaster(@RequestParam int team_num)
		{
			System.out.println("����Ʈ���� ������ ������"+team_num);
			return dao.selectMaster(team_num);
		}
	//�˻�
		@ResponseBody
		@GetMapping("teammember/searchlist")
		public List<HashMap<String, Object>> searchList(@RequestParam
				String option,String keyword,int start)
		{
			List<HashMap<String, Object>> list =dao.getSearchDatas(option, keyword, start);
			System.out.println("��� �˻� �ɼ�:"+option+"Ű����:"+keyword+start);
			return list;
		}
		
	//���Խ�û
		@PostMapping("/teammember/join")
		public void join(@RequestBody TeamMemberDto dto)
		{
			System.out.println(dto);
			System.out.println("����Ʈ���� ���� �� join+����"+dto.getMember_id());
			dao.joinTeamMember(dto);
		}
		
	//�� ������ �������μ� ���̺� �߰�	
		@GetMapping("/teammember/createmaster")
		public void createmaster(@RequestParam String member_id,int team_num)
		{
			System.out.println("�� ������ ���� ������?"+member_id+team_num);
			dao.joinTeamMaster(team_num, member_id);	
		}
		
		//�Ҽӵ� �� num ���ϱ�
		@GetMapping("teammember/myteamnum")
		public int teamnum(@RequestParam String member_id)
		{
			System.out.println("���ǰ� id=="+member_id);
			return dao.myTeamNum(member_id);
		}
	
		//���Ѿ絵 ������ ������ grade ���ϱ� (����)
		@GetMapping("teammember/teammaster")
		public String master(@RequestParam String member_id)
		{
			System.out.println("���ǰ� ���� id ==" + member_id);
			return dao.marsterGrade(member_id);
		}
		
		//�˻� ��ü ���� ���ϱ�
		@GetMapping("teammember/searchtotal")
		public int searchtotal(@RequestParam String option,String keyword)
		{
			return dao.getTotalCountSearch(option, keyword);
		}
}

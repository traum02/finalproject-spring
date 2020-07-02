package team.data;

import java.util.List;
import java.util.Vector;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@CrossOrigin
public class TeamController {
	
	@Autowired
	private TeamDaoInter dao;
	@Autowired
	private TeamMemberDaoInter tmdao;
	
	//��ȯŸ�� dto
	@GetMapping("/team/list")
	public List<TeamDto> list(@RequestParam(value = "start",defaultValue = "0")int start)
	{
		System.out.println("����Ʈ���� ������ team list"+start);
		
		List<TeamDto> list = dao.getAllDatas(start);
		list.get(0).setTotalCount(dao.getTotalCount());
		return list;
	}
	
	//�� ����
	@PostMapping("/team/add")
	public void insert(@RequestBody TeamDto dto)
	{
		System.out.println(dto);
		System.out.println("����Ʈ���� ������ insert");
		dao.insertTeam(dto);
	}
	
	//����
	@RequestMapping(value = "/team/delete",method = RequestMethod.DELETE)
	public void delete(@RequestParam int team_num)
	{
		System.out.println(team_num);
		dao.deleteTeam(team_num);
	}
	
	//�� ���� ������
	@GetMapping("/team/updateform")
	public TeamDto updateform(@RequestParam int team_num)
	{
		System.out.println(team_num);
		System.out.println("����Ʈ���� ������ updateform");
		System.out.println(dao.getData(team_num).getTeam_num());
		return dao.getData(team_num);
	}
	
	//�Ұ��� ����
	@PutMapping("/team/update")
	public void update(@RequestBody TeamDto dto)
	{
		
		
		System.out.println("����Ʈ���� ������ update. ��Ʈ��" + dto);
		dao.updateIntro(dto);
	}
	
	//�ΰ� ����
	@PutMapping("/team/updatelogo")
	public void updateLogo(@RequestBody TeamDto dto)
	{
		
		
		System.out.println("����Ʈ���� ������ update. ����" + dto);
		dao.updateLogo(dto);
	}
	
	//�� �̹��� ���ε�
	@ResponseBody
	@RequestMapping(value = "/team/upload",
			consumes = {"multipart/form-data"},
			method = RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest request,
			@RequestParam MultipartFile uploadFile)
	
	{
		System.out.println("���ε����� Ȯ��"+uploadFile.getOriginalFilename());
		//���ε� ��� ���ϱ�`
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/image");
		System.out.println(path);
		
		SpringFileWrite sfw = new SpringFileWrite();
		
		sfw.writeFile(uploadFile, path);
		
		
		return uploadFile.getOriginalFilename();
	}
	
	//������ ����
	@GetMapping("/team/select")
	public TeamDto select(@RequestParam int team_num)
	{
		System.out.println("����Ʈ���� ������ select++");
		return dao.getData(team_num);
	}
	
	//�� �������� ����
		@GetMapping("/teampage")
		public TeamDto selectTeampage(@RequestParam int team_num)
		{
			System.out.println("����Ʈ���� ������ team select");
			return dao.getData(team_num);
		}
	
	//���� �ߺ�üũ
	@GetMapping("/team/check")
	public int check(@RequestParam String team_name)
	{
		System.out.println("����Ʈ���� ������ check");
		return dao.namecheck(team_name);
	}
	
	//�� �˻�
	@PostMapping("team/searchlist")
	public List<TeamDto> searchList(@RequestParam String keyword,int start)
	{
		System.out.println(keyword);
		return dao.getSearchTeamName(keyword, start);
	}
	
	//â���� �� �ѹ� ���ϱ�
	@GetMapping("team/creatornum")
	public int creatornum(@RequestParam String creator)
	{
		System.out.println("â���ڸ�:"+creator);
		return dao.MasterTeamNum(creator);
	}
	
	//���� ī��Ʈ
	@GetMapping("team/resultcount")
	public List<Integer> resultcount(@RequestParam int team_num)
	{
		List<Integer> list = new Vector<Integer>();
		list.add(tmdao.getHwin(team_num)+tmdao.getAwin(team_num));
		list.add(tmdao.getDraw(team_num));
		list.add(tmdao.getHlose(team_num)+tmdao.getAlose(team_num));
		System.out.println("승"+team_num+tmdao.getHwin(team_num));
		return list;
	}
	
	//team_num�� ���� �� �̸� ���ϱ�
	@GetMapping("team/getteamname")
//	public String getteamname(@RequestParam String team_num)
	public TeamDto getteamname(@RequestParam String team_num)
	{
		System.out.println("팀이름"+dao.getTeamName(team_num));
		return dao.getData(Integer.parseInt(team_num));
	}
}

package spring.project.matchplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.work.MemberDaoInter;
import spring.project.work.MemberDto;

@RestController
@CrossOrigin
public class MemberController {
	@Autowired
	private MemberDaoInter dao;
	
	//추가
	@GetMapping("/memberdata")
	public MemberDto getData(String id) {
		return dao.getMemberData(id);
	}
	
	@GetMapping("/usepoint")
	public void usePoint(@RequestParam String id, String usingPoint) {
		System.out.println("id"+id);
		System.out.println("usingPoint"+usingPoint);
		dao.usePoint(id, usingPoint);
	}
	
	@GetMapping("/member/list")
	public List<MemberDto> list()
	{
		System.out.println("react>>list");
		return dao.getAllDatas();
	}
	
	@GetMapping("/login/session")
	public List<MemberDto> getInfo()
	{
		System.out.println("react>>getInfo");
		return dao.getDatas();
	}
	
	
	@PostMapping("/member/add")
	public void insert(@RequestBody MemberDto dto)
	{
		System.out.println("react>>add");
		dao.insertMember(dto);
	}
	
	@GetMapping("/member/select")
	public int select(@RequestParam String id)
	{
		System.out.println("react>>select");
		//데이타 반환
		return dao.getData(id);
	}
	
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.DELETE)
	public void delete(@RequestParam String id)
	{
		System.out.println("react>>delete");
		dao.deleteMember(id);
	}
	
	//selPwd
	@GetMapping("/login/selPwd")
	public int selPwd(@RequestParam String id, @RequestParam String pwd)
	{
		System.out.println("react>>pwd");
		//데이타 반환
		return dao.getPwd(id, pwd);
	}
	
	//selBirth
	@GetMapping("/member/selBirth")
	public int selBirth(@RequestParam String name, @RequestParam String birth)
	{
		System.out.println("react>>birth");
		//데이터 반환
		return dao.getBirth(name, birth);
	}
	//searchEmail
	@GetMapping("/member/searchEmail")
	public int searchEmail(@RequestParam String name, @RequestParam String email)
	{
		System.out.println("react>>email");
		//데이터 반환
		return dao.searchEmail(name, email);
	}
	
	//searchName
	@GetMapping("/member/searchName")
	public int searchName(@RequestParam String id, @RequestParam String name)
	{
		System.out.println("react>>name");
		
		return dao.searchName(id, name);
	}
	
	
	@GetMapping("/login/selId")
	public int chkId(@RequestParam String id)
	{
		System.out.println("react>>selId");
		//데이타 반환
		return dao.chkId(id);
	}
	
	//selName
	@GetMapping("/member/selName")
	public int selName(@RequestParam String name)
	{
		System.out.println("react>>name");
		return dao.getName(name);
	}
	
	//findId
	@GetMapping("/member/findId")
	public String findId(@RequestParam String email)
	{
		System.out.println("react>>findId");
		return dao.findId(email);
	}
	
	
	//findPwd
	@GetMapping("/member/findPwd")
	public String findPwd(@RequestParam String id)
	{
		System.out.println("react>>findPwd");
		return dao.findPwd(id);
	}
	
	//selEmail
	@GetMapping("/member/selEmail")
	public int selEmail(@RequestParam String email)
	{
		System.out.println("react>>email");
		//데이타 반환
		return dao.getEmail(email);
	}
	
	@GetMapping("/member/findGrade")
	public int findGrade(@RequestParam String id)
	{
		System.out.println("react>>findGrade");
		//데이타 반환
		return dao.getGrade(id);
	} 
	
	
}
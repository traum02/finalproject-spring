package spring.project.matchplay;

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
import org.springframework.web.bind.annotation.RestController;



import spring.project.work.IboardDaoInter;
import spring.project.work.IboardDto;

@RestController
@CrossOrigin
public class IboardController {

	@Autowired
	private IboardDaoInter dao;
	
	@PostMapping("/iboard/add")
	public void insert(@RequestBody IboardDto dto)
	{
		System.out.println("react>>add");
		dao.insertIboard(dto);
	}
	
	@GetMapping("/iboard/list")
	public List<IboardDto> list(@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "preidx", defaultValue = "0") int preidx)
	{
		System.out.println("react>>IboardList");
		System.out.println("start="+start);
		System.out.println("preidx=" + preidx);
		List<IboardDto> list=dao.getAllDatas(start,preidx);
		
		list.get(0).setTotalCount(dao.getTotalCount());
		return list;
	}
	
	@PostMapping("/iboard/searchlist")
	public List<IboardDto> SearchList(@RequestParam String option, String keyword, int start) 
	{		
		System.out.println("react>>SearchList");
		return dao.getSearchDatas(option, keyword, start);
	}
	
	@GetMapping("/iboard/select")
	public IboardDto select(@RequestParam int iboard_num,
							@RequestParam int preidx)
	{
		System.out.println("react>>select");

		//dao.updateViewcount(iboard_num);
		return dao.getData(iboard_num, preidx);
	}
	
	@GetMapping("/iboard/select1")
	public IboardDto select(@RequestParam int iboard_num)
	{
		System.out.println("react>>select");

		//dao.updateViewcount(iboard_num);
		return dao.getData1(iboard_num);
	}
	
	
	@RequestMapping(value = "/iboard/delete",method = RequestMethod.DELETE)
	public void delete(@RequestParam int iboard_num)
	{
		System.out.println(iboard_num);
		dao.deleteIboard(iboard_num);
	}

	@GetMapping("/iboard/checkPwd")
	public int selPwd(@RequestParam int iboard_num, @RequestParam String iboard_secpwd)
	{
		System.out.println("react>>ibord_secpwd--Check");
		return dao.getPwd(iboard_num, iboard_secpwd);
	}
	
	
	
	@PutMapping("/iboard/update")
	public void update(@RequestBody IboardDto dto)
	{
		System.out.println("React>>Update");
		dao.updateIboard(dto);
	}
}

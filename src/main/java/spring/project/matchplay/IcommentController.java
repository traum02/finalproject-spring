package spring.project.matchplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.work.IcommentDaoInter;
import spring.project.work.IcommentDto;



@RestController
@CrossOrigin
public class IcommentController {

	
	@Autowired
	private IcommentDaoInter dao;

	
	@GetMapping("/select/iboard/icomment")
	public List<IcommentDto> list(@RequestParam int iboard_num)
	{
		System.out.println("React>>icommentList");
		return dao.getIcommnetList(iboard_num);
	}
	
	// 
	@PostMapping("/select/iboard/addcomment")
	public void insert(@RequestBody IcommentDto dto)
	{
		System.out.println("React>>review-insert");
		dao.insertIcomment(dto);
	}
	
	@GetMapping("/icomment/add")
	public List<IcommentDto> list1(@RequestParam int iboard_num)
	{
		System.out.println("React>>icommentList");
		return dao.getIcommnetList(iboard_num);
	}
	
	
}

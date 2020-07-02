package team.data;

import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TCommentController {

	@Autowired
	private TCommentDaoInter dao;
	
	@GetMapping("/select/tboard/tcomment")
	public List<TCommentDto> list(@RequestParam int tboard_num)
	{
		System.out.println("����Ʈ���� ���� ��� list �� + tboard_num="+tboard_num);
		System.out.println(dao.getTcommnetList(tboard_num).toString());
		return dao.getTcommnetList(tboard_num);
	}
	
	//��� �߰�
	@PostMapping("/select/tboard/addcomment")
	public void insert(@RequestBody TCommentDto dto)
	{
		System.out.println(dto);
		System.out.println("����Ʈ���� ���� ����=="+dto.getMember_id());
		System.out.println("����Ʈ���� ���� �� ��� insert");
		dao.insertTcomment(dto);
	}
	
	@RequestMapping(value = "/select/tboard/deletecomment",method = RequestMethod.DELETE)
	public void delete(@RequestParam int tcomment_num)
	{
		System.out.println("����Ʈ���� ���� tcomment_num:"+tcomment_num);
		dao.deleteTcomment(tcomment_num);
	}
	
	@GetMapping("/select/tboard/tcount")
	public int count(@RequestParam int tboard_num)
	{
		System.out.println("��۰������ �� �Ѿ���� Ȯ��"+tboard_num);
		return dao.getTotalCount(tboard_num);
	}
	
	
}

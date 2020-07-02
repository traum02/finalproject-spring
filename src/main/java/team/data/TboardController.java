package team.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class TboardController {

	@Autowired
	private TboardDaoInter dao;
	
	@Autowired
	private TCommentDaoInter tcdao;
	
	//��ȯŸ�� dto
		@GetMapping("/tboard/list")
		public List<TboardDto> list(@RequestParam(value="start",defaultValue ="0") int start) 
		{		
			System.out.println("start="+start);
			
			List<TboardDto> list=dao.getAllDatas(start);
			
			list.get(0).setTotalCount(dao.getTotalCount());
		
			return list;
		}
		
	//�� ���� �ø� �Խù��� ����
	@GetMapping("/tboard/list/team")
	public List<TboardDto> listteam(@RequestParam(value="start",defaultValue ="0") int start, String tboard_public) 
	{		
		System.out.println("start="+start+"�ۺ�="+tboard_public);
		
		List<TboardDto> list=dao.getTeamDatas(start,tboard_public);
		
		list.get(0).setTotalCount(dao.getTeamTotalCount(tboard_public));
	
		return list;
	}
	
	//�˻��� dto
	@PostMapping("/tboard/searchlist")
	public List<TboardDto> SearchList(@RequestParam
		 String option, String keyword, int start) 
	{		
		
		List<TboardDto> list=dao.getSearchDatas(option, keyword, start);
		System.out.println("����Ʈ���� ������ SearchList +option:" + option + "keyword:" + keyword);
		list.get(0).setTotalCount(dao.getTotalCountSearch(option, keyword));
		return list;
	}
	
	@PostMapping("/tboard/add")
	public void insert(@RequestBody TboardDto dto)
	{
		System.out.println(dto);
		System.out.println("����Ʈ���� ������ insert");
		dao.insertTboard(dto);
		
	}
	
	@RequestMapping(value = "/tboard/delete",method = RequestMethod.DELETE)
	public void delete(@RequestParam int tboard_num)
	{
		System.out.println(tboard_num);
		dao.deleteTboard(tboard_num);
	}
	
	@GetMapping("/tboard/select")
	public TboardDto select(@RequestParam int tboard_num)
	{
		System.out.println("����Ʈ���� ������ select");
		System.out.println(dao.getData(tboard_num).getTboard_photo()+"photo");
		
		
		dao.updateViewcount(tboard_num);
		return dao.getData(tboard_num);
	}
	
	@GetMapping("/tboard/updateform")
	public TboardDto updateform(@RequestParam int tboard_num)
	{
		System.out.println(tboard_num);
		System.out.println("����Ʈ���� ������ updateform");
		System.out.println(dao.getData(tboard_num).getTboard_num());
		return dao.getData(tboard_num);
	}
	
	@PutMapping("/tboard/update")
	public void update(@RequestBody TboardDto dto)
	{
		
		System.out.println(dto.getTboard_num());
		System.out.println("��Ʈ�ѷ�:"+dto);
		System.out.println("����Ʈ���� ������ update");
		dao.updateTboard(dto);
	}
	
	//�̹��� ���ε�
	@ResponseBody
	@RequestMapping(value = "/tboard/upload",
			consumes = {"multipart/form-data"},
			method = RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest request,
			@RequestParam MultipartFile[] uploadFile)
	
	{
		String oriFileName="";
		
		 for(int i=0;i<uploadFile.length;i++) {
	         System.out.println("file: "+uploadFile[i].getOriginalFilename());
	         Date date=new Date();
	         SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
	         
	         String rename=sdf.format(date)+uploadFile[i].getOriginalFilename();
	         String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image");
	         oriFileName+=uploadFile[i].getOriginalFilename()+"/";
	         System.out.println(path);
	         
	         SpringFileWrite sfw = new SpringFileWrite();
	       
	         sfw.writeFile(uploadFile[i], path);
	      }

		
		
		return oriFileName;
	}
	
	
}

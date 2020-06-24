package spring.project.work;

import java.util.List;

public interface MemberDaoInter {
	public List<MemberDto> getAllDatas();
	public void insertMember(MemberDto dto);
	public void deleteMember(String id);
	public int getData(String id);
	public int getEmail(String email);
	
	public int chkId(String id);
	
	public int getPwd(String id, String pwd);
	
	public int getBirth(String name, String birth);
	
	public int getName(String name);
	
	public int searchEmail(String name, String email);
	
	public int searchName(String id, String name);
	
	public String findId(String email);
	
	public String findPwd(String id);
	
	public List<MemberDto> getDatas();
	
	
	public int getGrade(String id);
	
	public MemberDto getMemberData(String id);
}

package team.data;

import java.util.HashMap;
import java.util.List;

public interface TeamMemberDaoInter {
	
	public List<HashMap<String, Object>> getAllDatas(int team_num,int start);
	public List<HashMap<String, Object>> getAllDatasApply(int team_num,int start);
	public void joinTeamMember(TeamMemberDto dto);
	
	public void outTeamMember(int team_member_num);
	public void gradeChangeTeamMember(TeamMemberDto dto);
	public int getTotalMember(int team_num);
	public TeamMemberDto selectMaster(int team_num);
	public List<HashMap<String, Object>> getSearchDatas(String option,String keyword, int start);
	public int myTeamNum(String member_id);
	public String marsterGrade(String member_id);
	public void masterChange(String beforemaster);
	public void birthMaster(TeamMemberDto dto);
	public void joinTeamMaster(int team_num, String member_id);
	public int getTotalCountSearch(String option,String keyword); 
	
	
	public int getHwin(int team_num);
	public int getAwin(int team_num);
	public int getHlose(int team_num);
	public int getAlose(int team_num);
	public int getDraw(int team_num);
	
	
	

}

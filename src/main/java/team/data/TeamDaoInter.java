package team.data;

import java.util.List;

public interface TeamDaoInter {

	public List<TeamDto> getAllDatas(int start);
	public void insertTeam(TeamDto dto);
	public void deleteTeam(int team_num);
	public void updateIntro(TeamDto dto);
	public void updateLogo(TeamDto dto);
	public TeamDto getData(int team_num);
	public int namecheck(String team_name);
	public List<TeamDto> getSearchTeamName(String keyword,int start);
	public int getTotalCount();
	public int MasterTeamNum(String creator);
	public String getTeamName(String team_num);
}

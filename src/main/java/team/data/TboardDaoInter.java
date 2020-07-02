package team.data;

import java.util.List;

public interface TboardDaoInter {
	
	public List<TboardDto> getAllDatas(int start);
	public List<TboardDto> getTeamDatas(int start, String tboard_public);
	public List<TboardDto> getSearchDatas(String option,String keyword, int start);
	public void insertTboard(TboardDto dto);
	public void deleteTboard(int tboard_num);
	public void updateViewcount(int tboard_num);
	public TboardDto getData(int tboard_num);
	public void updateTboard(TboardDto dto);
	public int getTotalCount(); 
	public int getTeamTotalCount(String tboard_public);
	public int getTotalCountSearch(String option,String keyword); 
}

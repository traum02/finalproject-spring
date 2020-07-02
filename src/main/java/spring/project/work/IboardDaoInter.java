package spring.project.work;

import java.util.List;

public interface IboardDaoInter {
	public void insertIboard(IboardDto dto);
	public List<IboardDto> getAllDatas(int start, int preidx);
	public List<IboardDto> getSearchDatas(String option, String keyword, int start);
	public IboardDto getData(int iboard_num, int preidx);
	public IboardDto getData1(int iboard_num);
	
	
	public void deleteIboard(int iboard_num);
	public int getPwd(int iboard_num, String iboard_secpwd);
	
	public void updateIboard(IboardDto dto);
	public int getTotalCount();
}
 
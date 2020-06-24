package spring.project.work;

import java.util.List;





public interface IboardDaoInter {
	public void insertIboard(IboardDto dto);
	public List<IboardDto> getAllDatas(int start);
	public List<IboardDto> getSearchDatas(String option, String keyword, int start);
	public IboardDto getData(int iboard_num);
	public void deleteIboard(int iboard_num);
	public int getPwd(int iboard_num, String iboard_secpwd);
}

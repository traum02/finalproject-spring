package spring.project.work;

import java.util.List;

public interface IcommentDaoInter {
	public List<IcommentDto> getIcommnetList(int iboard_num);
	public void insertIcomment(IcommentDto dto);
}



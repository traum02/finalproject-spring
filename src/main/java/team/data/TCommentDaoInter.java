package team.data;

import java.util.List;

public interface TCommentDaoInter {

	public List<TCommentDto> getTcommnetList(int tboard_num);
	public void insertTcomment(TCommentDto dto);
	public void deleteTcomment(int tcomment_num);
	public int getTotalCount(int tboard_num);
}

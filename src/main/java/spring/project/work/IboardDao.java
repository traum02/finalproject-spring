package spring.project.work;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class IboardDao extends SqlSessionDaoSupport implements IboardDaoInter {

	

	@Override
	public List<IboardDto> getAllDatas(int start) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectAllOfIboard",start);
	}
	
	@Override
	public IboardDto getData(int iboard_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfIboard",iboard_num);
	}

	@Override
	public void deleteIboard(int iboard_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfIboard", iboard_num);
	}

	@Override
	public int getPwd(int iboard_num, String iboard_secpwd) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("iboard_num", iboard_num);
		map.put("iboard_secpwd", iboard_secpwd);
		return getSqlSession().selectOne("iboardSelectOneOfPwd", map);
	}
	

	@Override
	public List<IboardDto> getSearchDatas(String option, String keyword, int start) {
		// TODO Auto-generated method stub
		System.out.println(option);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("option",option);
		map.put("keyword",keyword);
		map.put("start",start);
		return getSqlSession().selectList("searchOfIbaord",map);
	}
	
	@Override
	public void insertIboard(IboardDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfIboard", dto);
	}
	
}

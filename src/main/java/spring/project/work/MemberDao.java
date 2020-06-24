package spring.project.work;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao extends SqlSessionDaoSupport implements MemberDaoInter{
	@Override
	public List<MemberDto> getAllDatas(){
		return getSqlSession().selectList("selectAllOfMember");
	}
	
	

	@Override
	public int getBirth(String name, String birth) {
		// TODO Auto-generated method stub
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("name", name);
		map.put("birth", birth);
		
		return getSqlSession().selectOne("selectOneOfSearchBirth", map);
	}



	@Override
	public int searchName(String id, String name) {
		// TODO Auto-generated method stub
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
	
		return getSqlSession().selectOne("selectOneOfSearchName", map);
	}



	@Override  
	public int searchEmail(String name, String email) {
		// TODO Auto-generated method stub
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		return getSqlSession().selectOne("selectOneOfSearchEmail", map);
	}



	@Override
	public String findId(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("findOneOfId",email);
	}



	@Override
	public void insertMember(MemberDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfMember",dto);
	}

	@Override
	public void deleteMember(String id) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfMember",id);
	}

	@Override
	public int getData(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfMember",id);
	}
	
	@Override
	public int getEmail(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfEmail",email);
	}

	@Override
	public int getPwd(String id, String pwd) {
		// TODO Auto-generated method stub
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		return getSqlSession().selectOne("selectOneOfPwd", map);
	}

	@Override
	public List<MemberDto> getDatas() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectDataOfMember");
	}
	
	@Override
	public int chkId(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfId",id);
	}

	@Override
	public String findPwd(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("findOneOfPwd", id);
	}


	@Override
	public int getName(String name) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfName", name);
	}
	
	@Override
	public int getGrade(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("findOfGrade", id);
	}



	@Override
	public MemberDto getMemberData(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMemberData", id);
	}
	
	
	
}
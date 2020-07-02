package team.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TeamMemberDao extends SqlSessionDaoSupport 
implements TeamMemberDaoInter

{
	//팀 멤버 리스트 출력
	@Override
	public List<HashMap<String,Object>> getAllDatas(int team_num, int start) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("team_num",team_num);
		map.put("start",start);
		return getSqlSession().selectList("selectAllOfTeamMember",map);
	}
	
//	@Override
//	public List<TeamMemberDto> getAllDatas(int team_num, int start) {
//		// TODO Auto-generated method stub
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("team_num",team_num);
//		map.put("start",start);
//		return getSqlSession().selectList("selectAllOfTeamMember",map);
//	}

	//팀 가입
	@Override
	public void joinTeamMember(TeamMemberDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("joinOfTeamMember",dto);
	}
	
	//창단자 = 팀장 생성
	@Override
	public void joinTeamMaster(int team_num, String member_id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("team_num",team_num);
		map.put("member_id",member_id);
		getSqlSession().insert("CreateMaster",map);
	}

	//팀 제명
	@Override
	public void outTeamMember(int team_member_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("outOfTeamMember",team_member_num);
	}

	//멤버 권한 변경
	@Override
	public void gradeChangeTeamMember(TeamMemberDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("gradeOfTeamMember",dto);
	}

	//멤버 전체 수
	@Override
	public int getTotalMember(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("totalOfTeamMember",team_num);
	}

	//팀장 구하기
	@Override
	public TeamMemberDto selectMaster(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOfMaster",team_num);
	}

	//팀 멤버 검색
	@Override
	public List<HashMap<String, Object>> getSearchDatas(String option, String keyword, int start) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("option",option);
		map.put("keyword",keyword);
		map.put("start",start);
		
		return getSqlSession().selectList("searchOfTeamMember",map);
	}

	//승인 대기자 리스트
	@Override
	public List<HashMap<String, Object>> getAllDatasApply(int team_num, int start) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("team_num",team_num);
		map.put("start",start);
		return getSqlSession().selectList("selectAllApply",map);
	}

	//자기가 소속된 팀 번호 찾기
	@Override
	public int myTeamNum(String member_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("myTeamNumber",member_id);
	}

	//팀멤버 목록 페이지 접속자(팀장)의 권한을 찾기
	@Override
	public String marsterGrade(String member_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("BeforeTeamMaster",member_id);
	}
	
	//팀장이 권한 양도시 팀장의 권한을 멤버로 내리기
	@Override
	public void masterChange(String beforemaster) {
		// TODO Auto-generated method stub
		getSqlSession().update("ChangeTeamMaster",beforemaster);
	}

	@Override
	public void birthMaster(TeamMemberDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("birthTeamMaster",dto);
	}

	@Override
	public int getTotalCountSearch(String option, String keyword) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("option",option);
		map.put("keyword",keyword);
		return getSqlSession().selectOne("totalSearch",map);
		
	}

	@Override
	public int getHwin(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("teamWinHome",team_num);
	}

	@Override
	public int getAwin(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("teamWinAway",team_num);
	}

	@Override
	public int getHlose(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("teamLoseHome",team_num);
	}

	@Override
	public int getAlose(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("teamLoseAway",team_num);
	}

	@Override
	public int getDraw(int team_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("teamDraw",team_num);
	}

	
	

	

	
	

}

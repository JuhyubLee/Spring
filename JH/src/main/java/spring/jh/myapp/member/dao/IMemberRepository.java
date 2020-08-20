package spring.jh.myapp.member.dao;

import java.util.List;

import spring.jh.myapp.member.model.MemberVO;

public interface IMemberRepository {

	void insertMember(MemberVO mem);
	void insertAuth(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	void updateMem(MemberVO mem);
	void deleteMem(String userId);
	void deleteAu(String userId);
	void updateFile(String userId);
	void deleteFile(String userId);
	List<MemberVO> getMemList(int page);
	void updateEnable(MemberVO mem);
	void updateAuthority(MemberVO mem);
	int getMemListCnt(MemberVO mem);
	boolean checkId(String userId);
}

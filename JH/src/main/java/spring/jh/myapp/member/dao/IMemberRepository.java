package spring.jh.myapp.member.dao;

import spring.jh.myapp.member.model.MemberVO;

public interface IMemberRepository {

	void insertMember(MemberVO mem);
	void insertAuth(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	void updateMem(MemberVO mem);
	void deleteMem(String userId);
	void deleteAu(String userId);
}

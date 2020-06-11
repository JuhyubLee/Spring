package spring.jh.myapp.member.service;

import spring.jh.myapp.member.model.MemberVO;

public interface IMemberService {

	void insertMember(MemberVO member);
	MemberVO getMember(String userId);
	String getPassword(String userId);
}

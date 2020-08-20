package spring.jh.myapp.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.jh.myapp.member.model.MemberVO;

public interface IMemberService {

	void insertMember(MemberVO member);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	void updateMem(@Param("userId") MemberVO mem);
	void deleteMem(String userId);
	void deleteAu(String userId);
	void updateFile(String userId);
	void deleteFile(String userId);
	List<MemberVO> getMemList(int page);
	void updateEnable(MemberVO mem);
	void updateAuthority(@Param("userId") MemberVO mem);
	int getMemListCnt(MemberVO mem);
	boolean checkId(@Param("userId") String userId);
}


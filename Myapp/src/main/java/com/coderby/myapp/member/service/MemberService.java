package com.coderby.myapp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderby.myapp.member.dao.IMemberRepository;
import com.coderby.myapp.member.model.MemberVO;

@Service
public class MemberService implements IMemberService{

	@Autowired
	IMemberRepository memRepository;
	
	@Transactional("txManager")
	public void insertMember(MemberVO mem) {
		memRepository.insertMember(mem);
		memRepository.insertAuth(mem.getUserId());
	}
	
	public MemberVO getMember(String userId) {
		return memRepository.getMember(userId);
	}
	
	public String getPassword(String userId) {
		return memRepository.getPassword(userId);
	}

	@Override
	public List<MemberVO> getMemberList(int page, String word) {
		return memRepository.getMemberList(page, word);
	}

	@Override
	public void updateMember(MemberVO mem) {
		memRepository.updateMember(mem);
	}

	@Override
	public void updateMemberEnable(String userId) {
		memRepository.updateMemberEnable(userId);
	}

	@Override
	public void updateMemberAuth(String auth, String userId) {
		memRepository.updateMemberAuth(auth, userId);
	}

	@Override
	@Transactional("txManager")
	public void deleteMember(String userId) {
		memRepository.deleteAuthor(userId);
		memRepository.deleteMember(userId);
	}

	@Override
	public Integer getMemberCount(String word) {
		return memRepository.getMemberCount(word);
	}
	
}

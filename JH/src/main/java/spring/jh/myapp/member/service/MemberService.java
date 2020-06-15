package spring.jh.myapp.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.jh.myapp.member.dao.IMemberRepository;
import spring.jh.myapp.member.model.MemberVO;

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
}

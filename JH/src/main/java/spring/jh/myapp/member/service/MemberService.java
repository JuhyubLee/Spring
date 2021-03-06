package spring.jh.myapp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.jh.myapp.member.dao.IMemberRepository;
import spring.jh.myapp.member.model.MemberVO;

@Service
public class MemberService implements IMemberService{

	@Autowired
	@Qualifier("IMemberRepository")
	IMemberRepository memRepository;
	
	@Override
	public boolean checkId(String userId) {
		return memRepository.checkId(userId);
	}
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
	public void updateMem(MemberVO mem) {
		memRepository.updateMem(mem);
	}
	
	@Override
	@Transactional("txManager")
	public void deleteMem(String userId) {
		memRepository.deleteFile(userId);
		memRepository.updateFile(userId);
		memRepository.deleteAu(userId);
		memRepository.deleteMem(userId);
	}
	
	@Override
	public void deleteAu(String userId) {
		memRepository.deleteAu(userId);
	}
	
	@Override
	public void updateFile(String userId) {
		memRepository.updateFile(userId);
	}
	
	@Override
	public void deleteFile(String userId) {
		memRepository.deleteFile(userId);
	}
	
	@Override
	public List<MemberVO> getMemList(int page) {
		return memRepository.getMemList(page);
	}
	
	@Override
	public void updateEnable(MemberVO mem) {
		memRepository.updateEnable(mem);
	}
	
	@Override
	public void updateAuthority(MemberVO mem) {
		memRepository.updateAuthority(mem);
	}
	
	@Override
	public int getMemListCnt(MemberVO mem) {
		return memRepository.getMemListCnt(mem);
	}
}
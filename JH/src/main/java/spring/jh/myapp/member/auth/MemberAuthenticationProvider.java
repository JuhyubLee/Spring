package spring.jh.myapp.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import spring.jh.myapp.member.model.MemberVO;
import spring.jh.myapp.member.service.IMemberService;



@Component
public class MemberAuthenticationProvider implements
 AuthenticationProvider{

	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication.getPrincipal()==null) {
			return null;
		}
		if(authentication.getCredentials()==null) {
			return null;
		}
		String userId = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		String dbpw = memberService.getPassword(userId);
		if(dbpw==null) {
			throw new InternalAuthenticationServiceException("아이디가 없습니다.");
		}
		if(!bpe.matches(password, dbpw)) {
			throw new BadCredentialsException("비밀번호가 다릅니다.");
		}
		MemberVO member = memberService.getMember(userId);
		if(!member.isEnabled()) {
			throw new DisabledException("정지당한 계정입니다. 관리자에게 문의하세요.");
		}
		UsernamePasswordAuthenticationToken result =
				new UsernamePasswordAuthenticationToken(member, 
						password, member.getAuthorities());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
}

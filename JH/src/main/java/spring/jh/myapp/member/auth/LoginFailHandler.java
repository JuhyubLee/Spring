package spring.jh.myapp.member.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
			AuthenticationException exception) throws IOException, ServletException {
		request.getSession().setAttribute("message", exception.getMessage());
		response.sendRedirect("login?error");
	}
}

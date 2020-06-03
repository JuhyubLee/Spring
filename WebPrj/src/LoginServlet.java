

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/Login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		if("gctserf".equals(id)&&"0000".equals(pw)) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			Cookie c1 = new Cookie("id",id);
			response.addCookie(c1);
			response.sendRedirect("/DataIndex.jsp");
		}else {
			request.setAttribute("message", "아이디 또는 비밀번호가 잘못되었습니다.");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}


















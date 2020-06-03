

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz.do")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QuizServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String message = null;
		if("gctserf".equals(id) && "0000".equals(pw)) {
			message = "안녕하세요 엄영범님. 환영합니다.";
		}else {
			message = "입력정보가 잘못되었습니다.";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>퀴즈 결과</title></head>");
		out.println("<body>");
		out.println(message);
		out.println("</body>");
		out.println("</html>");
	}

}


















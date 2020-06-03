

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReDi.do")
public class ReDiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReDiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String name = request.getParameter("name");
		if(action.equals("DI")) {
			request.setAttribute("message", "입력하신 이름은 "+name+" 입니다.");
			request.
			getRequestDispatcher("/ReDiResult.jsp").
			forward(request, response);
		}else if(action.equals("RE")) {
			request.setAttribute("message", "입력하신 이름은 "+name+" 입니다.");
			response.sendRedirect("/ReDiResult.jsp");
		}
		
	}

}


























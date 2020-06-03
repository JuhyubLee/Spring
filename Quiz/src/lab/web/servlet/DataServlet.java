package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.vo.DataVO;

@WebServlet("/Data.do")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList<DataVO> list
	= new ArrayList<>();
	
    public DataServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", list);
		request.getRequestDispatcher("/DataList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");;
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(
				request.getParameter("age"));
		DataVO data = new DataVO();
		data.setName(name);
		data.setAge(age);
		data.setTel(tel);
		list.add(data);
		response.sendRedirect("/Quiz/Data.do");
	}

}















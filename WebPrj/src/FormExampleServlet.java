

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormExample.do")
public class FormExampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FormExampleServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String sdate = request.getParameter("birth");
		SimpleDateFormat tool = 
				new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birth = null;
		try {
			birth = tool.parse(sdate);
		}catch(Exception e) {
			
		}
		String[] hobbys = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		int grade = Integer.parseInt(
				request.getParameter("grade"));
		String intro = request.getParameter("intro");
		String area = request.getParameter("area");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>폼 예제 결과</title></head>");
		out.println("<body>");
		out.printf("아이디 : %s<br>", id);
		out.printf("비밀번호 : %s<br>", pw);
		out.printf("생년월일 : %s<br>", birth.toString());
		out.println("취미 : ");
		HashMap<String,String> hobbyMap
		= new HashMap<>();
		hobbyMap.put("1", "철권");
		hobbyMap.put("2", "펜타스톰");
		hobbyMap.put("3", "롤토체스");
		for(String key : hobbys) {
			out.printf("%s ", hobbyMap.get(key));
		}
		out.println("<br>");
		out.printf("성별 : %s<br>", gender.equals("F") ? "여" : "남");
		out.printf("이메일 : %s<br>", email);
		out.printf("연락처 : %s<br>", tel);
		out.printf("학년 : %d<br>", grade);
		out.printf("자기소개 : <pre>%s</pre><br>", intro);
		HashMap<String, String> areaMap
		= new HashMap<>();
		areaMap.put("1", "서울");
		areaMap.put("2", "경기도");
		areaMap.put("3", "경상도");
		areaMap.put("4", "전라도");
		out.printf("지역 : %s<br>", areaMap.get(area));
		out.println("</body>");
		out.println("</html>");
	}

}


















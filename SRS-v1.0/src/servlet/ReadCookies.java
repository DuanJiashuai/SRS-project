package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.ProfessorWithPasswordDao;
import dao.StudentWithPasswordDao;
import dao.dataAccess;
import model.ProfessorWithPassword;
import model.StudentWithPassword;

/**
 * Servlet implementation class ReadCookies
 */
@WebServlet("/ReadCookies")
public class ReadCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadCookies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();

		String pwd = "";
		int role = 0;
		String ssn = "";

		// ��ȡcookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			jo.put("auto", true);
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals("username")) {
					ssn = cookie.getValue();
				} else if (name.equals("role")) {
					role = Integer.parseInt(cookie.getValue());
				} else {
					continue;
				}
			}
			jo.put("username", ssn);
			jo.put("role", role);
			if (role == 1) {
				StudentWithPasswordDao spd = dataAccess.createStudentWithPasswordDao();
				StudentWithPassword sp = spd.getStudentWithPassword(ssn);
				pwd = sp.getPassword();
			} else if (role == 2) {
				ProfessorWithPasswordDao ppd = dataAccess.createProfessorWithPasswordDao();
				ProfessorWithPassword pp = ppd.getProfessorWithPassword(ssn);
				pwd = pp.getPassword();
			} else {
				pwd = "admin";
			}
			jo.put("password", pwd);
		}else{
			jo.put("auto", false);
		}
		out.print(jo.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProfessorWithPasswordDao;
import dao.StudentWithPasswordDao;
import dao.dataAccess;
import model.Professor;
import model.ProfessorWithPassword;
import model.Student;
import model.StudentWithPassword;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String auto = request.getParameter("check");
		String pwd = request.getParameter("password");
		int role = Integer.parseInt(request.getParameter("role"));
		
		
		Student student=null;
		Professor professor=null;
		
		boolean isValidated=false;
		if(role==1){//学生
			StudentWithPasswordDao spd=dataAccess.createStudentWithPasswordDao();
			StudentWithPassword sp=spd.getStudentWithPassword(userName);			
			student=sp.getStudent();
			isValidated=sp.validatePassword(pwd);
		}else if(role==2){//教师
			ProfessorWithPasswordDao ppd=dataAccess.createProfessorWithPasswordDao();
			ProfessorWithPassword pp=ppd.getProfessorWithPassword(userName);
			professor=pp.getProfessor();
			isValidated=pp.validatePassword(pwd);
		}else{//管理员
			isValidated=userName.equals("admin")&&pwd.equals("admin");
		}
		
		if(isValidated){//登录成功
			System.out.println("登录成功");
			HttpSession session = request.getSession();
			session.setAttribute("role", role);
			if(role==1){
				session.setAttribute("Student", student);
			}else if(role==2){
				session.setAttribute("Professor", professor);
			}else{
				session.setAttribute("Admin", 1);
			}
			session.setAttribute("isLogined", 1);
			if (auto != null && auto.equals("on")) {//记住密码
				Cookie user = new Cookie("username", userName);
				//cookie.setMaxAge(7*24*60*60);
				user.setMaxAge(5*60 * 60);
				Cookie roleId=new Cookie("role",String.valueOf(role));
				roleId.setMaxAge(5*60 * 60);
				response.addCookie(user);
				response.addCookie(roleId);
			} else {
				Cookie cookie = new Cookie("username", null);
				cookie.setMaxAge(0);
			}
			response.sendRedirect("SRS.html");
		}else{
			System.out.println("登录失败");
			HttpSession session = request.getSession();
			session = request.getSession();
			session.setAttribute("isLogined", 0);
			response.sendRedirect("login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

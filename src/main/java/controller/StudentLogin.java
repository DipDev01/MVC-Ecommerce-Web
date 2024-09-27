package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.BadRequestException;

import service.StudentDAO;

/**
 * Servlet implementation class StudentLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO dao;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		super.init(config);
		dao=new StudentDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/view/studentLogin.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginForm = request.getParameter("submit");
		if(loginForm!=null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				
				boolean isSuccess=dao.studentLogin(username,password);
				
				if(isSuccess) {
					HttpSession session = request.getSession();
					session.setAttribute("Username", username);
					session.setMaxInactiveInterval(5*60);
					request.getRequestDispatcher("profile").forward(request, response);
				}
				else {
					request.setAttribute("error", "Invalid username or password");
					doGet(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			doGet(request, response);
		}
		
	}

}

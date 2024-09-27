package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import service.StudentDAO;
import utils.PasswordHash;

/**
 * Servlet implementation class StudentRegister
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Register" })
public class StudentRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRegister() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	dao = new StudentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/view/studentRegister.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String userName=request.getParameter("username");
		Date dob=null;
		try {
			dob=new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
					.parse(request.getParameter("birthday")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		long phoneNumber=Long.parseLong(request.getParameter("phoneNumber"));
		String subject = request.getParameter("subject");
		String password= request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		
		
		if(!password.equals(retypePassword)) {
			request.setAttribute("firstName ", firstName);
			request.setAttribute("error", "Password not Matched");
			request.getRequestDispatcher("/WEB-INF/view/studentRegister.jsp").forward(request, response);
		}
		
		Student student = new Student();
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setUsername(userName);
		student.setDob(dob);
		student.setGender(gender);
		student.setEmail(email);
		student.setPhoneNumber(phoneNumber);
		
		student.setPassword(PasswordHash.getPasswordHash(password));
		
		if(dao.isSaveStudent(student)) {
			request.getRequestDispatcher("Login");
		}
		else {
			request.setAttribute("error", "username or email or phone number already existed");
			request.getRequestDispatcher("/WEB-INF/view/studentRegister.jsp").forward(request, response);
		}
		
	}

}

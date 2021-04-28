package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.RegisterModel;
import Service.ServiceClass;
import Service.ServiceInterface;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String fname= request.getParameter("fname");
		String lname=request.getParameter("lname");
		String city=request.getParameter("city");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		
		RegisterModel r= new RegisterModel();
		r.setFname(fname);
		r.setLname(lname);
		r.setCity(city);
		r.setMobile(mobile);
		r.setEmail(email);
		r.setPassword(password);
		
		ServiceInterface si=new ServiceClass();
		try {
			boolean b=si.register(r);
			if(b)
			{
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
				out.println("<center><p style='color:green;'>Registered successfully,Login Now </p></center>");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("register.html");
				rd.include(request, response);
				out.println("<center><p style='color:red;'>Not Registered, Try Again </p></center>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("Error is: "+e);
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

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.EMIModel;
import Service.ServiceClass;
import Service.ServiceInterface;

/**
 * Servlet implementation class CalculationServlet
 */
@WebServlet("/calculate")
public class CalculationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		DecimalFormat decimalFormat = new DecimalFormat("#.###");

		
		int loanamount=Integer.parseInt(request.getParameter("loanamount"));
		
		float interestrate =Float.parseFloat(request.getParameter("interestrate"));
		interestrate= Float.valueOf(decimalFormat.format(interestrate));
		
		int years=Integer.parseInt(request.getParameter("years"));
		int months=Integer.parseInt(request.getParameter("months"));
		
	
		EMIModel emi=new EMIModel();
	
		emi.setLoanamount(loanamount);
		emi.setInterestrate(interestrate);
		emi.setMonths(months);
		emi.setYears(years); 
		
		ServiceInterface si=new ServiceClass();
		try {
			EMIModel em=new EMIModel();
			em=si.calculateEMI(emi);
			RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
			rd.include(request, response);
			
		
				out.println(" <br><div class=\"emidiv\" style=\"background-color:green;color:white;\">\r\n"
						+ "			<h5>Loan Amount   :"+em.getLoanamount()+"</h5><br>\r\n"
						+ "			<h5>Interest Rate :"+em.getInterestrate()+"&nbsp;%</h5><br>\r\n"
						+ "			<h5>Time Period   :"+em.getYears()+"&nbsp;&nbsp;years&nbsp;&nbsp;"+em.getMonths()+"&nbsp;&nbsp;months</h5>\r\n"
						+ "		</div>\r\n"
						+ "		<div class=\"row\">\r\n"
						+ "			<div class=\"col-lg-6\">\r\n"
						+ "				<div class=\"emidiv row \">\r\n"
						+ "					<div class=\"col-sm-5\" style=\"text-align:center;\">\r\n"
						+ "						<h5>Monthly EMI:</h5>\r\n"
						+ "						<h1>"+em.getMonthemi()+"</h1>\r\n"
						+ "					</div>\r\n"
						+ "					<div class=\"col-sm-7\">\r\n"
						+ "						<br><p>Total Interest:&nbsp; "+em.getTotalinterest() +" </p><br>\r\n"
						+ "						<p>Total Amount  :&nbsp; "+em.getTotalmoney()+" </p>	\r\n"
						+ "					</div>	\r\n"
						+ "				</div>\r\n"
						+ "			</div>\r\n"
						+ "			\r\n"
						+ "			<div class=\"col-lg-6\">\r\n"
						+ "				<div class=\"emidiv row \">\r\n"
						+ "					<div class=\"col-sm-5\" style=\"text-align:center;\">\r\n"
						+ "						<h5>Three Month EMI:</h5>\r\n"
						+ "						<h1>"+em.getThreemonthemi()+"</h1>\r\n"
						+ "					</div>\r\n"
						+ "					<div class=\"col-sm-7\">\r\n"
						+ "						<br><p>Total Interest: &nbsp; "+em.getThree_month_interest() +" </p><br>\r\n"
						+ "						<p>Total Amount  : &nbsp; "+em.getThree_month_total()+" </p>	\r\n"
						+ "					</div>	\r\n"
						+ "				</div>\r\n"
						+ "			</div>\r\n"
						+ "\r\n"
						+ "		</div>\r\n"
						+ "		\r\n"
						+ "		<div class=\"row\">\r\n"
						+ "			<div class=\"col-lg-6\">\r\n"
						+ "				<div class=\"emidiv row \">\r\n"
						+ "					<div class=\"col-sm-5\" style=\"text-align:center;\">\r\n"
						+ "						<h5>Six Month EMI:</h5>\r\n"
						+ "						<h1>"+em.getSixmonthemi()+"</h1>\r\n"
						+ "					</div>\r\n"
						+ "					<div class=\"col-sm-7\">\r\n"
						+ "						<br><p>Total Interest: &nbsp; "+em.getSix_month_interest()+" </p><br>\r\n"
						+ "						<p>Total Amount  : &nbsp;"+em.getSix_month_total()+" </p>	\r\n"
						+ "					</div>	\r\n"
						+ "				</div>\r\n"
						+ "			</div>\r\n"
						+ "			\r\n"
						+ "			<div class=\"col-lg-6\">\r\n"
						+ "				<div class=\"emidiv row \">\r\n"
						+ "					<div class=\"col-sm-5\" style=\"text-align:center;\">\r\n"
						+ "						<h5>Yearly EMI:</h5>\r\n"
						+ "						<h1>"+em.getOneyearemi()+"</h1>\r\n"
						+ "					</div>\r\n"
						+ "					<div class=\"col-sm-7\">\r\n"
						+ "						<br><p>Total Interest: &nbsp;"+em.getOne_year_interest()+" </p><br>\r\n"
						+ "						<p>Total Amount  : &nbsp; "+em.getOne_year_total()+" </p>	\r\n"
						+ "					</div>	\r\n"
						+ "				</div>\r\n"
						+ "			</div>\r\n"
						+ "\r\n"
						+ "		</div> ");
	
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

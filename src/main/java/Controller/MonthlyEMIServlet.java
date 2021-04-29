package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.EMIModel;
import Model.MonthModel;
import Model.YearModel;
import Service.ServiceClass;

/**
 * Servlet implementation class MonthlyEMIServlet
 */
@WebServlet("/monthlyemi")
public class MonthlyEMIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthlyEMIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ServiceClass sc= new ServiceClass();
		List<MonthModel> al = new ArrayList<MonthModel>();
		List<YearModel> ay = new ArrayList<YearModel>();
		
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		int loanamount=Integer.parseInt(request.getParameter("loanamount"));
		
		float interestrate =Float.parseFloat(request.getParameter("interestrate"));
		interestrate= Float.valueOf(decimalFormat.format(interestrate));
		
		int years=Integer.parseInt(request.getParameter("years"));
		int months=Integer.parseInt(request.getParameter("months"));
		
		EMIModel emi= new EMIModel();
		emi.setLoanamount(loanamount);
		emi.setInterestrate(interestrate);
		emi.setMonths(months);
		emi.setYears(years); 
		try {
			
			EMIModel em=new EMIModel();
			em=sc.calculateEMI(emi);
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

			
			
			
/*			
			ay= sc.yearInterest(emi);
			
			out.println("<div class=\"container\">");
			out.println("<table class=\"table\">");
			out.println("<thead class=\"thead-dark\">");
				out.println("<tr>");
					out.println("<th scope='col'>Month</th>");
					out.println("<th scope='col'>Principal(A)</th>");
					out.println("<th scope='col'>Interest(B)</th>");
					out.println("<th scope='col'>EMI(A+B)</th>");
					out.println("<th scope='col'>Balance</th>");
					out.println("<th scope='col'>Loan Paid</th>");
					out.println("<th scope='col'>Paid(%)</th>");
				out.println("<tr>");
			out.println("</thead>");
			out.println("<tbody>");	
			for (YearModel ym: ay)
			{ 
				out.println("<tr>");
					out.println("<td>"+ym.getYear()+"</td>");
					out.println("<td>"+ym.getOyp()+"</td>");
					out.println("<td>"+ym.getOyi()+"</td>");
					out.println("<td>"+ym.getOyemi()+"</td>");
					out.println("<td>"+ym.getOybr()+"</td>");
					out.println("<td>"+ym.getOylp()+"</td>");
					out.println("<td>"+ym.getOylpper()+"</td>");
				out.println("</tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
		out.println("</div>");
			
			
			
		*/	
		/*		
			al= sc.monthlyInterest(emi);
			
				out.println("<div class=\"container\">");
				out.println("<table class=\"table\">");
				out.println("<thead class=\"thead-dark\">");
					out.println("<tr>");
						out.println("<th scope='col'>Month</th>");
						out.println("<th scope='col'>Principal(A)</th>");
						out.println("<th scope='col'>Interest(B)</th>");
						out.println("<th scope='col'>EMI(A+B)</th>");
						out.println("<th scope='col'>Balance</th>");
						out.println("<th scope='col'>Loan Paid</th>");
						out.println("<th scope='col'>Paid(%)</th>");
					out.println("<tr>");
				out.println("</thead>");
				out.println("<tbody>");	
				for (MonthModel mm: al)
				{ 
					out.println("<tr>");
						out.println("<td>"+mm.getMonth()+"</td>");
						out.println("<td>"+mm.getPa()+"</td>");
						out.println("<td>"+mm.getIp()+"</td>");
						out.println("<td>"+mm.getE()+"</td>");
						out.println("<td>"+mm.getBr()+"</td>");
						out.println("<td>"+mm.getLp()+"</td>");
						out.println("<td>"+mm.getLpper()+"</td>");
					out.println("</tr>");
				}
				out.println("</tbody>");
				out.println("</table>");
			out.println("</div>");
		
			*/
			
			ay= sc.yearInterest(emi);
			
			out.println("<div class=\"container\">");
			out.println("           <table class=\"table outertable\" style=\"border-collapse:collapse;\">\r\n"
					+ "				    <thead class=\"thead-dark\">\r\n"
					+ "				        <tr>\r\n"
					+ "				           <th scope='col'>Year</th>\r\n"
					+ "				           <th scope='col'>Principle(A)</th>\r\n"
					+ "				           <th scope='col'>Interest(B)</th>\r\n"
					+ "				           <th scope='col'>EMI(A+B)</th>\r\n"
					+ "				           <th scope='col'>Balance</th>\r\n"
					+ "				           <th scope='col'>Loan Paid</th>\r\n"
					+ "				           <th scope='col'>Paid(%)</th>\r\n"
					+ "				        </tr>\r\n"
					+ "				    </thead>\r\n"
					+ "				    <tbody>\r\n"
					+ "                 ");		
									int m=0;//months
									int index=0;
									int count=0;
									for (YearModel ym: ay)
									{ 
										index=0;
										count=0;
					
					out.println("		    <tr colspan=\"6\" data-toggle=\"collapse\" data-target=\"#"+ym.getYear()+"\" class=\"accordion-toggle\">\r\n"
					+ "				            <td>"+ym.getYear()+"</td>\r\n"
					+ "				             <td>"+ym.getOyp()+"</td>\r\n"
					+ "				              <td>"+ym.getOyi()+"</td>\r\n"
					+ "				               <td>"+ym.getOyemi()+"</td>\r\n"
					+ "				                <td>"+ym.getOybr()+"</td>\r\n"
					+ "				                 <td>"+ym.getOylp()+"</td>\r\n"
					+ "				                  <td>"+ym.getOylpper()+"</td>\r\n"
					+ "				        </tr>\r\n"
					+ "				        <tr class=\"p\">\r\n"
					+ "				            <td colspan=\"12\" class=\"hiddenRow\">\r\n"
					+ "					            <div class=\"accordian-body collapse\" style=\"margin:0px; padding:0px;\" id=\""+ym.getYear()+"\">\r\n"
					+ "					            	 \r\n"
					+ "                 ");	
					
					
														al= sc.monthlyInterest(emi);
					out.println("		            	 <table class=\"table innertable\" style=\"margin:0px; padding:0px; border-collapse:collapse; \">\r\n"
					+ "									   \r\n"
					+ "									    <tbody>\r\n"
					+ "                 ");
														for (MonthModel mm: al)
														{ 
															count++;
															
															if(count<=m)
															{
																continue;
															}
															index++;
															
					out.println("						    		<tr colspan=\"12\" >\r\n"
					+ "									            <td>"+index+"</td>\r\n"     //+mm.getMonth()+
					+ "									             <td>"+mm.getPa()+"</td>\r\n"
					+ "									              <td>"+mm.getIp()+"</td>\r\n"
					+ "									               <td>"+mm.getE()+"</td>\r\n"
					+ "									                <td>"+mm.getBr()+"</td>\r\n"
					+ "									                 <td>"+mm.getLp()+"</td>\r\n"
					+ "									                  <td>"+mm.getLpper()+"</td>\r\n"
					+ "									        </tr>\r\n"
					+ "                 ");	
					
															m++;
															if(m%12==0)
															{
																break;
															}
														}
					
					out.println("			            			</tbody>\r\n"
					+ "				            		</table>\r\n"
					+ "					            	 \r\n"
					+ "					        	</div> \r\n"
					+ "				        	</td> \r\n"
					+ "				        </tr>\r\n"
					+ "			\r\n"
					+ "                 ");		
					
					}
									
					out.println("		</tbody>\r\n"
					+ "				</table>\r\n"
					+ "                 ");			
			
					out.println("</div>");
			
			
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

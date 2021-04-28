package Repository;

import java.sql.*;

import Model.EMIModel;
import Model.LoginModel;
import Model.RegisterModel;

public class RepoClass implements RepoInterface {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	
	public RepoClass() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/emicalculator","root","root");
	}

	@Override
	public boolean register(RegisterModel rm) throws SQLException,ClassNotFoundException{

		stmt=conn.prepareStatement("insert into users values('0',?,?,?,?,?,?);");
		stmt.setString(1,rm.getFname());
		stmt.setString(2, rm.getLname());
		stmt.setString(3, rm.getCity());
		stmt.setString(4, rm.getMobile());
		stmt.setString(5, rm.getEmail());
		stmt.setString(6, rm.getPassword());
		
		int value=stmt.executeUpdate();
		if(value>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean login(LoginModel lm) throws SQLException, ClassNotFoundException {

		stmt=conn.prepareStatement("select * from users where email=? && password=?;");
		stmt.setString(1, lm.getEmail());
		stmt.setString(2, lm.getPassword());
		rs=stmt.executeQuery();
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean saveEMI(EMIModel emi) throws SQLException, ClassNotFoundException {

		stmt=conn.prepareStatement("insert into emi values('0',?,?,?,?,?,?,?,?,?,?);");
		
		stmt.setInt(1,emi.getLoanamount());
		stmt.setFloat(2,emi.getInterestrate());
		stmt.setInt(3,emi.getYears());
		stmt.setInt(4,emi.getMonths());
		
		stmt.setFloat(5,emi.getTotalmoney());
		stmt.setFloat(6,emi.getTotalinterest());
		
		stmt.setFloat(7,emi.getMonthemi());
		stmt.setFloat(8,emi.getThreemonthemi());
		stmt.setFloat(9,emi.getSixmonthemi());
		stmt.setFloat(10,emi.getOneyearemi());
		
		int value=stmt.executeUpdate();
		if(value>0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	
}

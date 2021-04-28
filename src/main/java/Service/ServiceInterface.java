package Service;

import java.sql.SQLException;
import java.util.List;

import Model.EMIModel;
import Model.LoginModel;
import Model.MonthModel;
import Model.RegisterModel;
import Model.YearModel;

public interface ServiceInterface {

	public abstract boolean register(RegisterModel rm) throws SQLException,ClassNotFoundException;
	public abstract boolean login(LoginModel lm) throws SQLException,ClassNotFoundException;
	
	public abstract EMIModel calculateEMI(EMIModel emi) throws SQLException,ClassNotFoundException;
	
	public abstract List<MonthModel> monthlyInterest(EMIModel emi) throws SQLException, ClassNotFoundException;
	public abstract List<YearModel> yearInterest(EMIModel emi) throws SQLException, ClassNotFoundException;
	
}

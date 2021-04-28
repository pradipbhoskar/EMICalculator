package Repository;

import java.sql.SQLException;

import Model.EMIModel;
import Model.LoginModel;
import Model.RegisterModel;

public interface RepoInterface {

	public abstract boolean register(RegisterModel rm) throws SQLException,ClassNotFoundException;
	public abstract boolean login(LoginModel lm) throws SQLException,ClassNotFoundException;
	
	public abstract boolean saveEMI(EMIModel emi) throws SQLException,ClassNotFoundException;
	
	
}

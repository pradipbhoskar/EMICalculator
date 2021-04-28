package Service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Model.EMIModel;
import Model.LoginModel;
import Model.MonthModel;
import Model.RegisterModel;
import Model.YearModel;
import Repository.RepoClass;
import Repository.RepoInterface;

public class ServiceClass implements ServiceInterface{

	RepoInterface ri;
	@Override
	public boolean register(RegisterModel rm) throws SQLException, ClassNotFoundException {

		ri=new RepoClass();
		return ri.register(rm);
	}
	@Override
	public boolean login(LoginModel lm) throws SQLException, ClassNotFoundException {

		ri= new RepoClass();
		return ri.login(lm);
	}
	
	
	@Override
	public EMIModel calculateEMI(EMIModel emi) throws SQLException, ClassNotFoundException {
		
		DecimalFormat decimalFormat = new DecimalFormat("#.");
		float rate=emi.getInterestrate();
		float r=(rate/12)/100;  //rate of interest calculated on monthly basis
		int n=emi.getYears()*12 +emi.getMonths();  //duration in number of months
		int p=emi.getLoanamount();           //Principal Loan Amount
		
		//  e=  (p*r*(1+r)^n)/((1+r)^n-1);
		
		float e=(float) ((p*r*Math.pow(1+r, n)) / (Math.pow(1+r, n)-1));    
		
		float totalmoney=e*n;
		float totalinterest=totalmoney-emi.getLoanamount();
		
		//3 months
		float r3=(emi.getInterestrate()/4)/100;  
		float n3=n/3;
		//float e3=(float) ((p*r3*Math.pow(1+r3, n3)) / (Math.pow(1+r3, n3)-1));
		float e3=e*3;
		float total3=e3*n/3;
		float interest3=total3-emi.getLoanamount();
		
		//6 months
		float r6=(emi.getInterestrate()/2)/100;
		float n6=n/6;
		//float e6=(float) ((p*r6*Math.pow(1+r6, n6)) / (Math.pow(1+r6, n6)-1));
		float e6=e*6;
		float total6=e6*n/6;
		float interest6=total6-emi.getLoanamount();

		//1 year
		float r12=(emi.getInterestrate())/100;  
		float n12=n/12;
		//float e12=(float) ((p*r12*Math.pow(1+r12, n12)) / (Math.pow(1+r12, n12)-1));\
		float e12=e*12;
		float total12=e12*n/12;
		float interest12=total12-emi.getLoanamount();
		
		
		totalmoney= Float.valueOf(decimalFormat.format(totalmoney));
		totalinterest= Float.valueOf(decimalFormat.format(totalinterest));
		total3= Float.valueOf(decimalFormat.format(total3));
		interest3= Float.valueOf(decimalFormat.format(interest3));
		total6= Float.valueOf(decimalFormat.format(total6));
		interest6= Float.valueOf(decimalFormat.format(interest6));
		total12= Float.valueOf(decimalFormat.format(total12));
		interest12= Float.valueOf(decimalFormat.format(interest12));
		
		e= Float.valueOf(decimalFormat.format(e));
		e3= Float.valueOf(decimalFormat.format(e3));
		e6= Float.valueOf(decimalFormat.format(e6));
		e12= Float.valueOf(decimalFormat.format(e12));
		
		emi.setTotalmoney(totalmoney);
		emi.setTotalinterest(totalinterest);
		emi.setMonthemi(e);
		emi.setThreemonthemi(e3);
		emi.setSixmonthemi(e6);
		emi.setOneyearemi(e12);
		
		emi.setThree_month_total(total3);
		emi.setSix_month_total(total6);
		emi.setOne_year_total(total12);
		
		emi.setThree_month_interest(interest3);
		emi.setSix_month_interest(interest6);
		emi.setOne_year_interest(interest12);
		
		ri=new RepoClass();
		ri.saveEMI(emi);
		return emi;
	}
	@Override
	public List<MonthModel> monthlyInterest(EMIModel emi) throws SQLException, ClassNotFoundException {

		DecimalFormat decimalFormat = new DecimalFormat("#.");
		
		float rate=emi.getInterestrate();    //Actual Interest
		float r=(rate/12)/100;  //rate of interest calculated on monthly basis
		int n=emi.getYears()*12 +emi.getMonths();  //duration in number of months
		int p=emi.getLoanamount();           //Principal Loan Amount
		float pa;  //principal amount paid for month
		float ip; //interest paid for month
		float br;  //balance remaining to paid
		float lp;  //loan amount paid upto month
		float lpper;  //loan paid percentage
		
		
		
		float e=(float) ((p*r*Math.pow(1+r, n)) / (Math.pow(1+r, n)-1));
		br=p;
		lp=0;
		int count=0;
		List<MonthModel> al=new ArrayList();
		while(n>0)
		{	
			ip=0;
			pa=0;
			count++;
			
			ip=(br*rate)/(100*12);
			pa=e-ip;
			lp=lp+pa;
			lpper=(lp/p)*100;
			br=br-pa;
			
			pa= Float.valueOf(decimalFormat.format(pa));
			ip= Float.valueOf(decimalFormat.format(ip));
			e= Float.valueOf(decimalFormat.format(e));
			br= Float.valueOf(decimalFormat.format(br));
			lp= Float.valueOf(decimalFormat.format(lp));
			lpper= Float.valueOf(new DecimalFormat("#.##").format(lpper));

			
			MonthModel m= new MonthModel();
			m.setMonth(count);
			m.setPa(pa);
			m.setIp(ip);
			m.setE(e);
			m.setBr(br);
			m.setLp(lp);
			m.setLpper(lpper);
			
			al.add(m);
			n--;
		}
		
		return al;
	}

	
	public List<YearModel> yearInterest(EMIModel emi) throws SQLException, ClassNotFoundException {

		DecimalFormat decimalFormat = new DecimalFormat("#.");
		
		float rate=emi.getInterestrate();    //Actual Interest
		float r=(rate/12)/100;  //rate of interest calculated on monthly basis
		int n=emi.getYears()*12 +emi.getMonths();  //duration in number of year
		int p=emi.getLoanamount();           //Principal Loan Amount
		float pa;  //principal amount paid for year
		float ip; //interest paid for year
		float br;  //balance remaining to paid
		float lp;  //loan amount paid upto year
		float lpper;  //loan paid percentage

		float e=(float) ((p*r*Math.pow(1+r, n)) / (Math.pow(1+r, n)-1));
		float oyemi=e*12;
		float oybr=p;
		br=p;
		lp=0;
		int count=0;
		int years=0;
		float ti;  //totalinterest
		float oyi=0;  //one year interest
		float oyp=0;  //one year principle paid
		float oylp=0;
		float oylpper=0;
		List<YearModel> al=new ArrayList();
		while(n>0)
		{	
			ip=0;
			pa=0;
			count++;
			
			
			ip=(br*rate)/(100*12);
			pa=e-ip;
			lp=lp+pa;
			lpper=(lp/p)*100;
			br=br-pa;
			
			
			oyi=oyi+ip;
			oyp=oyemi-oyi;
			/*
			pa= Float.valueOf(decimalFormat.format(pa));
			ip= Float.valueOf(decimalFormat.format(ip));
			e= Float.valueOf(decimalFormat.format(e));
			br= Float.valueOf(decimalFormat.format(br));
			lp= Float.valueOf(decimalFormat.format(lp));
			lpper= Float.valueOf(new DecimalFormat("#.##").format(lpper));

*/
			n--;
			
		
			if(count==12 || n==0 )
			{
				years++;
			
				oybr=oybr-oyp;
				oylp=oylp+oyp;
				oylpper=(oylp/p)*100;
				
				
				oyp= Float.valueOf(decimalFormat.format(oyp));
				oyi= Float.valueOf(decimalFormat.format(oyi));
				oyemi= Float.valueOf(decimalFormat.format(oyemi));
				oybr= Float.valueOf(decimalFormat.format(oybr));
				oylp= Float.valueOf(decimalFormat.format(oylp));
				oylpper= Float.valueOf(new DecimalFormat("#.##").format(oylpper));
				
				YearModel y= new YearModel();
				y.setOyp(oyp);
				y.setYear(years);
				y.setOyi(oyi);
				y.setOyemi(oyemi);
				y.setOybr(oybr);
				y.setOylp(oylp);
				y.setOylpper(oylpper);
				al.add(y);
				
				
				count=0;
				oyp=0;
				oyi=0;
			}
		}
		
		return al;
	}

}

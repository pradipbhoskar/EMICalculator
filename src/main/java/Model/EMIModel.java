package Model;

public class EMIModel {

	private int id;
	private int loanamount;
	private float interestrate;
	private int years;
	private int months;

	private float totalmoney;
	private float totalinterest;

	private float monthemi;
	private float threemonthemi;
	private float sixmonthemi;
	private float oneyearemi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(int loanamount) {
		this.loanamount = loanamount;
	}
	public float getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(float interestrate) {
		this.interestrate = interestrate;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public float getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(float totalmoney) {
		this.totalmoney = totalmoney;
	}
	public float getTotalinterest() {
		return totalinterest;
	}
	public void setTotalinterest(float totalinterest) {
		this.totalinterest = totalinterest;
	}
	public float getMonthemi() {
		return monthemi;
	}
	public void setMonthemi(float monthemi) {
		this.monthemi = monthemi;
	}
	public float getThreemonthemi() {
		return threemonthemi;
	}
	public void setThreemonthemi(float threemonthemi) {
		this.threemonthemi = threemonthemi;
	}
	public float getSixmonthemi() {
		return sixmonthemi;
	}
	public void setSixmonthemi(float sixmonthemi) {
		this.sixmonthemi = sixmonthemi;
	}
	public float getOneyearemi() {
		return oneyearemi;
	}
	public void setOneyearemi(float oneyearemi) {
		this.oneyearemi = oneyearemi;
	}

	
	private float three_month_interest;
	public float getThree_month_interest() {
		return three_month_interest;
	}
	public void setThree_month_interest(float three_month_interest) {
		this.three_month_interest = three_month_interest;
	}
	public float getThree_month_total() {
		return three_month_total;
	}
	public void setThree_month_total(float three_month_total) {
		this.three_month_total = three_month_total;
	}
	public float getSix_month_interest() {
		return six_month_interest;
	}
	public void setSix_month_interest(float six_month_interest) {
		this.six_month_interest = six_month_interest;
	}
	public float getSix_month_total() {
		return six_month_total;
	}
	public void setSix_month_total(float six_month_total) {
		this.six_month_total = six_month_total;
	}
	public float getOne_year_interest() {
		return one_year_interest;
	}
	public void setOne_year_interest(float one_year_interest) {
		this.one_year_interest = one_year_interest;
	}
	public float getOne_year_total() {
		return one_year_total;
	}
	public void setOne_year_total(float one_year_total) {
		this.one_year_total = one_year_total;
	}


	private float three_month_total;
	private float six_month_interest;
	private float six_month_total;
	private float one_year_interest;
	private float one_year_total;
	
	
}

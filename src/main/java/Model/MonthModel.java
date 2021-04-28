package Model;

public class MonthModel {
	
	private int month; //no of months
	private float pa;   //principle amount paid
	private float ip;   //interest paid
	private float e;    //emi
	private float br;   //balance ramaining
	private float lp;    //loan paid
	private float lpper;  //loan paid percentage
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public float getPa() {
		return pa;
	}
	public void setPa(float pa) {
		this.pa = pa;
	}
	public float getIp() {
		return ip;
	}
	public void setIp(float ip) {
		this.ip = ip;
	}
	public float getE() {
		return e;
	}
	public void setE(float e) {
		this.e = e;
	}
	public float getBr() {
		return br;
	}
	public void setBr(float br) {
		this.br = br;
	}
	public float getLp() {
		return lp;
	}
	public void setLp(float lp) {
		this.lp = lp;
	}
	public float getLpper() {
		return lpper;
	}
	public void setLpper(float lpper) {
		this.lpper = lpper;
	}
}

package com.pennanttech.Team2.Login;

import java.util.List;

import com.pennanttech.Team2.Empr.Job_Tbl;

public interface LoginDAO {
	public int login(UserDetailsModel e);
	public int login2(EmprDetailsModel e1);
	public int validuser(String b1, String c1);
	public int validempr(String b1, String c1);
	public int cmpnyid(String b1); 
	public int empid(String b1);
	public int Emprmailcheck(String b1);
	public void EmprPwdChange(String mail,String pwd);
	public int Usermailcheck(String b1);
	public void UserPwdChange(String mail,String pwd);
}

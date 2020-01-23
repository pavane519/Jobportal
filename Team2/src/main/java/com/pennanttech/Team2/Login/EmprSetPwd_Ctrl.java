package com.pennanttech.Team2.Login;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EmprSetPwd_Ctrl extends Window{
		LoginDAO db1;
		public Component login;
		public void onSubmit() 
		{
		
		Window win = (Window)this.getFellow("set");
		String mail = (String) win.getAttribute("email");
		System.out.println(mail);
		String pass  =  ((Textbox)this.getFellow("pwd1")).getValue();
		String cpass =  ((Textbox)this.getFellow("pwd2")).getValue();
		System.out.println(pass);
		System.out.println(cpass);
		
		if(pass.equals(cpass)) 		
		{
			ApplicationContext ctx =WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			db1=(LoginDAO)ctx.getBean("Login");	
			db1.EmprPwdChange(mail, ((Textbox)this.getFellow("pwd1")).getValue());
			showNotify("Password Sucessfully changed","info",login);
			Executions.sendRedirect("EmprLogin.zul");
		}
		else 
		{
			showNotify("Password and Confirm Password does not match","error",login); 
		}
			
		
		
		
		
	}
	private void showNotify(String msg,String type, Component ref)
	{
	    Clients.showNotification(msg, type, ref, "middle_center", 2000);
	}
	
}

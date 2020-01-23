package com.pennanttech.Team2.Empr;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.pennanttech.Team2.Login.EmprDetailsModel;
import com.pennanttech.Team2.Session.AuthenticationServiceEmpr;
import com.pennanttech.Team2.Session.AuthenticationServiceImplEmpr;
import com.pennanttech.Team2.User.LocationModel;
import com.pennanttech.Team2.User.UserPagesDAO;
import com.pennanttech.Team2.Empr.*;


public class EmprPostNewJob_Ctrl extends Div

{
	private static Logger logger = Logger.getLogger(EmprPostNewJob_Ctrl.class);
	EmprDAO db1;
	private Component click;
	protected List LocList;
	protected List TitleList;
	protected UserPagesDAO UDAO;
	AuthenticationServiceEmpr as= new AuthenticationServiceImplEmpr();
	EmprDetailsModel e=new EmprDetailsModel();
	 public void onCreate() throws Exception {
		 logger.info("Entering");
			ApplicationContext ctx =WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			UDAO = (UserPagesDAO)ctx.getBean("UserDAO");
				//fDAO =(FormDAO)ctx.getBean("fDAO");;
			TitleList = UDAO.jobsTitle();
			LocList = UDAO.Locations();
			Combobox com1= (Combobox)this.getFellow("Job_Role");
			Combobox com2 = (Combobox)this.getFellow("Job_Location");
			for (Iterator it = TitleList.iterator(); it.hasNext();) 
				{
				Job_Tbl m = (Job_Tbl) it.next();
						//	System.out.println(m.getJob_Title());
					com1.appendItem(m.getJob_Role());	
		         }
	        for (Iterator it = LocList.iterator(); it.hasNext();) 
	        	{
	        		LocationModel lm = (LocationModel) it.next();
	        		com2.appendItem(lm.getLocation());	
	        			//System.out.print(lm.getLocation());
	        	}

	 }
	
	
	public void verifyJob() 
		{
		 e=as.getLoginCredential();
			logger.info("enter");
			ApplicationContext ctx =WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			db1=(EmprDAO)ctx.getBean("Empr");			
			System.out.println("enter");
			
			
				String j1=((Combobox)this.getFellow("Job_Role")).getValue();
			Textbox j2=(Textbox)this.getFellow("Job_Description");
				String j3=j2.getValue();
				System.out.println(j3);
			Decimalbox j4=(Decimalbox)this.getFellow("Salary");
				BigDecimal j5=j4.getValue();
			Intbox j6=(Intbox)this.getFellow("Experience");
				int j7=j6.getValue();
			Intbox j8=(Intbox)this.getFellow("No_of_Openings");
				int j9=j8.getValue();
			Textbox j10=(Textbox)this.getFellow("Minimum_Qualification");
				String j11=j10.getValue();
			Datebox j12=(Datebox)this.getFellow("last_Date");
				Date j13=j12.getValue(); 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate= formatter.format(j13);
			
				java.sql.Date jdate=java.sql.Date.valueOf(strDate); 
			
				Combobox com1= (Combobox)this.getFellow("Job_Location");
				
				String j15=com1.getValue();
					/*
					 * Textbox j16=(Textbox)this.getFellow("Address"); String j17=j16.getValue();
					 */
			Textbox j18=(Textbox)this.getFellow("Skills");
				String j19=j18.getValue();
			Textbox j20=(Textbox)this.getFellow("Venue");
				String j21=j20.getValue();
			Textbox j22=(Textbox)this.getFellow("Rounds");
				String j23=j22.getValue();
				
			Datebox j24=(Datebox)this.getFellow("I_Date");
				Date j25=j24.getValue(); 
				System.out.println("..");
				System.out.println(j25);
				System.out.println("..");
				SimpleDateFormat formatterr = new SimpleDateFormat("dd-MM-yy '-' HH:mm");
				String irDate= formatterr.format(j25);
				
				System.out.println("..");
				System.out.println(irDate);
				System.out.println("..");
	/*
	 * java.sql.Date idate=java.sql.Date.valueOf(irDate); System.out.println(idate);
	 */
				
				
				Job_Tbl jt=new Job_Tbl();
				jt.setCompany_Id(e.getCompany_Id());
			 jt.setJob_Role(j1); 
			 jt.setJob_Description(j3); 
			 logger.info(j3);
			 jt.setSalary(j5);
			 logger.info(j5);
			 jt.setExperience(j7);
			 jt.setNo_of_Openings(j9);
			 jt.setMinimum_Qualification(j11);
			 jt.setLast_Date(jdate);
			 jt.setJob_Location(j15); 
			 	/* jt.setAddress(j17);logger.info(j7); */
			 jt.setSkills(j19); 
			 jt.setVenue(j21);
			 jt.setRounds(j23);
			 jt.setI_Date(irDate);
			 
			 
			 db1.newjob(jt);	
			 
			 Messagebox.show("You have Successfully Posted New Job");
			 Executions.sendRedirect("EmprHome.zul");
			 
			 logger.info("end");
		}

}

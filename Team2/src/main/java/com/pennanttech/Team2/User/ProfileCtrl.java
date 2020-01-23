package com.pennanttech.Team2.User;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.pennanttech.Team2.Login.UserDetailsModel;
import com.pennanttech.Team2.Session.AuthenticationService;
import com.pennanttech.Team2.Session.AuthenticationServiceImpl;
import com.pennanttech.Team2.Session.AuthenticationServiceImplEmpr;
public class ProfileCtrl extends Window
	{
		UserPagesDAO db1;
		List Profile;
		AuthenticationService as= new AuthenticationServiceImpl();
		UserDetailsModel e= new UserDetailsModel();
		
		
		
		public void onCreate() 
			{
			
			 	e=as.getLoginCredential();
			 	
				ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
				db1=(UserPagesDAO)ctx.getBean("UserDAO");
				int x= e.getEmp_Id();
				Profile = db1.Profile(x);
				for (Iterator it = Profile.iterator(); it.hasNext();) 
					{
						UserdetailsModel m = (UserdetailsModel) it.next();						
						Label lb1 = (Label)this.getFellow("name");
							lb1.setValue(m.getName());
								
						 Label lb2= (Label)this.getFellow("DOB"); 
							Date dt=(Date) m.getDate_Of_Birth();
							lb2.setValue(dt.toString());
							System.out.println(lb2);
								 
						 Label lb10=(Label)this.getFellow("fresher");
						 	Integer in1 = (int) m.getNo_of_Years();
						 	lb10.setValue(in1.toString());
						 	System.out.println(lb10);
										/*
										 * if(No_of_Years==Null) {
										 * 
										 * }
										 */
						Label lb3=(Label)this.getFellow("Ed");
							lb3.setValue(m.getHighest_Qualification());
						
						Label lb4=(Label)this.getFellow("skills");
							lb4.setValue(m.getSkills());
							Label lb6=(Label)this.getFellow("Resume");
							System.out.println(m.getResume());
							System.out.println("RRRRRRR");
							
							if(m.getResume() == null) {
								lb6.setValue("Upload");
							}else
								lb6.setValue("Update");
						
						Label lb7=(Label)this.getFellow("Contactdetails");
							lb7.setValue("Contact Details");
						
						Label lb8=(Label)this.getFellow("email");
							lb8.setValue(m.getEmail_Id());
			
							Label lb9=(Label)this.getFellow("number");
							  
							 long in = (long) m.getMobile_Number();
							 
					 
							 lb9.setValue(String.valueOf(in));
				 
					}
		
			}
		
		
		public void popup() {

			 Window window = (Window)Executions.createComponents("EditProfilepopup.zul", null, null);
			 window.setClosable(true);
			 window.setTitle("Title");
			 window.setBorder(true);
			 window.doModal();	 
			 
			 
			 
			 
			 
		}
public void Upload() {
			e=as.getLoginCredential();
			 
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
					db1=(UserPagesDAO)ctx.getBean("UserDAO");
					
			        EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
			            public void onEvent(UploadEvent event) throws Exception {
			            	UserdetailsModel um = new UserdetailsModel();
			            	Media m = event.getMedia();
			            	um.setResume(m.getByteData());
			            	um.setEmp_id(e.getEmp_Id());
			            	db1.ResumeChange(um);
			            	
						       
			            }
			        };
			      
			        Fileupload.get(el);
			        Label lb6=(Label)this.getFellow("Resume");
					 lb6.setValue("Update");	
			    
					
			       		    
		}

	}
		
	
	

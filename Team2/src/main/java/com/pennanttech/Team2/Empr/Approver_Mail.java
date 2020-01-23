package com.pennanttech.Team2.Empr;
import javax.mail.*;
import javax.activation.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.*;





public class Approver_Mail {

	
	
public void main(String email,List Interviewdetails) {
	
	final String uname="knowwhere2@gmail.com";
	final String pwd="team2vizag";
	
	Properties props= new Properties();
	props.put("mail.smtp.auth","true");
	props.put("mail.smtp.starttls.enable","true");
	props.put("mail.smtp.ssl.trust","smtp.gmail.com");
	props.put("mail.smtp.host","smtp.gmail.com");
	props.put("mail.smtp.port","587");
	int i=0;
	Session ss = Session.getInstance(props,new javax.mail.Authenticator(){
	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(uname,pwd);
	}
	});
	
	  try
      { 
         String s = new String();
         s= email ;
         for(Iterator it=Interviewdetails.iterator() ; it.hasNext();) {
        		Job_Tbl  em =  (Job_Tbl) it.next(); 
        		System.out.println(em.getVenue());
        		System.out.println(em.getI_Date());
        		System.out.println(em.getRounds());
        		
       
                  Message message = new MimeMessage(ss);
                  message.setFrom(new InternetAddress(uname));
                  message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
                  message.setSubject(" Shortlisted for the Job Opportunity |"+em.getJob_Role());
                  MimeMultipart multipart= new  MimeMultipart("related") ;
                  BodyPart messageBodyPart = new MimeBodyPart();
                  String bdy = "<html>\r\n" + 
                  		"\r\n" + 
                  		"<body>\r\n" + 
                  		"\r\n" + 
                  		"<img src=\"https://i.ibb.co/c36px6R/LOGO.jpg\" border=\"0\" style=\"width: 150px;\">\r\n" + 
                  		"<p>Congratulations </p>\r\n" + 
                  		"\r\n" + 
                  		"<p>Your resume has been shortlisted for the Job Oppurtunity with "+em.getCompany_Name()+" for the Profile of "+em.getJob_Role()+".</p>\r\n" + 
                  		"\r\n" + 
                  		"<h style=\"font-weight: bold;\r\n" + 
                  		"    font-size: 20px;\r\n" + 
                  		"    text-color: red;\r\n" + 
                  		"    color: #f91c45;\">Job Description:</h>\r\n" + 
                  		"<p>"+em.getJob_Description()+" </p>\r\n" + 
                  		"\r\n" + 
                  		"<p style=\"font-weight: bold;\r\n" + 
                  		"    font-size: 15px;\r\n" + 
                  		"    \r\n" + 
                  		"    color: #34445a;\"> Please find the Interview Details below:</p>\r\n" + 
                  		"\r\n" + 
                  		"<p>Venue:"+em.getVenue()+"</p>\r\n" + 
                  		"<p>Interview Date:"+em.getI_Date()+"</p>\r\n" + 
                  		"<p>Rounds:"+em.getRounds()+"</p>\r\n" + 
                  		"\r\n" + 
                  		"<p style=\"font-weight: bold;\r\n" + 
                  		"    font-size: 20px;\r\n" + 
                  		"    text-color: red;\r\n" + 
                  		"    color: #f91c45;\">\r\n" + 
                  		"Contact Details:</p>\r\n" + 
                  		"<p>\r\n" + 
                  		"Email: "+em.getJob_Location()+"<p>\r\n" + 
                  		"<p>\r\n" + 
                  		"Phone number:"+em.getSalary()+"\r\n" + 
                  		"</p>\r\n" + 
                  		"</body>\r\n" + 
                  		"</html>";
                  messageBodyPart.setContent(bdy,"text/html");
                  multipart.addBodyPart(messageBodyPart);
                  message.setContent(multipart);
                  Transport.send(message);
          }
         }
    
         
      
     
      catch(Exception e)
  {
	  System.out.println(e);
  }
	
	

	  

}}


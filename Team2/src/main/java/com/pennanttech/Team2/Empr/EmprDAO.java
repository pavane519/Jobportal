package com.pennanttech.Team2.Empr;

import java.util.List;

public interface EmprDAO 
	{
		public List EmprJob_Data(int jobid);
		public int emprreg(Empr_Model m);
		public int newjob(Job_Tbl jt);
		public List ApplicantsList(int jobid);
		public List Profile(int profileid) ;
		public String Approve(int empid,int job_id) ;
		public int applicantcount(int Job_Id);
		public int Reject(int empid);
		public void companyupdate(Empr_Model em);
		public List companyProfile(int Empr_Model);
		public int valid(Job_Tbl jt);
		public List interviewdetails(int job_id);
		
		
	}

package com.pennanttech.Team2.User;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

public class Navigation_Ctrl extends SelectorComposer<Component> {
	
	
	@Wire
	public Include x;
	
	
	@Listen("onClick=#home")
	public void home() {
		x.setSrc("UserHome.zul");
		
	}
	@Listen("onClick=#profile")
	public void profile() {
		
		x.setSrc("Userprof.zul");
		
    }
	@Listen("onClick=#applied")
	 public void Applied() {
		
		x.setSrc("Appliedpage.zul");
	}
	
	

}

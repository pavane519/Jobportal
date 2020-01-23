package com.pennanttech.Team2.Empr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
 
public class pdf extends Window
{
   public void submit()
   {
	   byte[] a;
	   Document resume = new Document();
	   Vbox vb = (Vbox)this.getFellow("vb");
	   Vbox vb1 = (Vbox)this.getFellow("vb1");
	   ByteArrayOutputStream out = new ByteArrayOutputStream();
  try
      {
    	  PdfWriter.getInstance(resume, out);
          resume.open();
          LineSeparator line;
          line = new LineSeparator();
          Font f = new Font();
          resume.add( Chunk.NEWLINE );
          String space = "";
          Font font2 = new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD | Font.UNDERLINE);
          resume.add(new Paragraph("RESUME", font2)); 
          Paragraph name = new Paragraph("Name");
          name.setAlignment(Paragraph.ALIGN_LEFT);
          resume.add( name );
          Paragraph email = new Paragraph("Email");
          email.setAlignment(Paragraph.ALIGN_RIGHT);
          resume.add(email );
          Paragraph contact = new Paragraph("ContactNumber");
          contact.setAlignment(Paragraph.ALIGN_RIGHT);
          resume.add(contact);  
          resume.add(Chunk.NEWLINE);
          resume.add(line);
          resume.add(Chunk.NEWLINE);
          Font font1 = new Font(Font.FontFamily.HELVETICA  , 13, Font.BOLD | Font.UNDERLINE);
          resume.add(new Chunk("Qulaification ", font1));
          resume.add(Chunk.NEWLINE);
         
          resume.add(Chunk.NEWLINE);
          Font font3;
          font3 = new Font(Font.FontFamily.HELVETICA  , 13, Font.BOLD | Font.UNDERLINE);
          resume.add(new Chunk("Graduation and PostGraduation ", font1));
          //resume.add(Chunk.NEWLINE);
  		
  		for(Component div : vb.getChildren()) {
  			ArrayList<InputElement> Array = new ArrayList<InputElement>();	
  				Array = makearray(div, Array); 
  				Iterator<InputElement> it =  Array.iterator();
  				Font font4 = new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD);
  				 resume.add(new Paragraph((String) ((InputElement)it.next()).getRawValue(),font4));
  				 resume.add(new Paragraph((String) ((InputElement)it.next()).getRawValue()));
  				 resume.add(new Paragraph((String) ((InputElement)it.next()).getRawValue()+"."));
  				 resume.add(new Paragraph((String) ((InputElement)it.next()).getRawValue()+","));
  				 resume.add(new Paragraph((String) ((InputElement)it.next()).getRawValue()));
  				 resume.add(new Paragraph("Completed with an aggregate of" +(String) ((InputElement)it.next()).getRawValue()));
  				 Date d = (Date) ((InputElement)it.next()).getRawValue();
  				resume.add(new Paragraph("Grauated On"+d.toString()));
  				
  				resume.add(Chunk.NEWLINE);
  				 
   }
          Font font4;
          font4 = new Font(Font.FontFamily.HELVETICA  , 13, Font.BOLD | Font.UNDERLINE);
          resume.add(new Chunk("Skills", font1));
          resume.add(Chunk.NEWLINE);
           resume.add(Chunk.NEWLINE);
           for(Component div : vb1.getChildren()) {
     			ArrayList<InputElement> Array = new ArrayList<InputElement>();	
     				Array = makearray(div, Array); 
     				Iterator<InputElement> it =  Array.iterator();
     				 resume.add(new Paragraph((String) ((InputElement)it.next()).getRawValue() +"-" + (String) ((InputElement)it.next()).getRawValue()));
     				}
        
          Font font5;
          font5 = new Font(Font.FontFamily.HELVETICA  , 13, Font.BOLD | Font.UNDERLINE);
          resume.add(new Chunk("Projects", font1));
          resume.add(Chunk.NEWLINE);
          Paragraph name2 = new Paragraph("ProjectName1");
          name2.setIndentationLeft(50);
          resume.add(name2);
          resume.add(Chunk.NEWLINE);
          Paragraph name3 = new Paragraph("projectDescription1");
          name3.setIndentationLeft(50);
          resume.add(name3);
          resume.add(Chunk.NEWLINE);
          Paragraph name4 = new Paragraph("ProjectName2");
          name4.setIndentationLeft(50);
          resume.add(name4);
          resume.add(Chunk.NEWLINE);
          Paragraph name5 = new Paragraph("projectDescription2");
          name5.setIndentationLeft(50);
          resume.add(name5);
          resume.add(Chunk.NEWLINE);
          Paragraph name6 = new Paragraph("ProjectName3");
          name6.setIndentationLeft(50);
          resume.add(name6);
          Paragraph name7 = new Paragraph("projectDescription3");
          name7.setIndentationLeft(50);
          resume.add(name7);
          resume.add(Chunk.NEWLINE);
          Paragraph name8 = new Paragraph("ProjectName4");
          name8.setIndentationLeft(50);
          resume.add(name8);
          Paragraph name9 = new Paragraph("projectDescription4");
          name9.setIndentationLeft(50);
          resume.add(name9);
          resume.close();
          Filedownload.save(out.toByteArray(), null, "someName.pdf");
      } catch (DocumentException e)
      {
         e.printStackTrace();
      }
   }
  
  public ArrayList<InputElement> makearray(Component c,ArrayList<InputElement> array) {
		for(Component cp : c.getChildren())
		{
			 if(cp instanceof InputElement) {
				 array.add((InputElement) cp);
			 }			 
			 makearray(cp, array);
			 }
		return array;
  }
   private void clearAllFields(Component c) {
		
		if (c == null || c.getChildren() == null)
			return;
		
		for(Component child : c.getChildren()) {
			if (child instanceof InputElement) {
				((InputElement)child).setRawValue(null);
				((InputElement)child).clearErrorMessage();
				((InputElement)child).setParent(c);
		
			}
			clearAllFields(child);
		}
	}
   public void add(String Parent ) {
	   Vbox vb = (Vbox)this.getFellow(Parent);
	   Div d = (Div) (vb.getFirstChild()).clone();
			clearAllFields(d);
			vb.appendChild(d);
		}
		
		public void remove(String Parent) {
			Vbox vb = (Vbox)this.getFellow(Parent);	
			vb.removeChild(vb.getLastChild());
			
			
			
		}
}
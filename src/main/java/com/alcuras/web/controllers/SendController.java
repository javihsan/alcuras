package com.alcuras.web.controllers;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/*send*")
public class SendController {

	private static final Logger LOG = Logger.getLogger(SendController.class.getSimpleName());
	    
	private Map viewMap;
	private String view;
	private String path;
	private String sender;
	private String recipient;
	private String recipientCC;
	private String subject;
	 
	@RequestMapping("")
	private ModelAndView inicioInit(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {


		String path = arg0.getRequestURI().toLowerCase();
		
		Set setKeys = getViewMap().keySet();
		for (Object key : setKeys) {
			if (path.indexOf((String)key)!=-1){
				setView((String)getViewMap().get(key));
				setPath((String)key);
				//setType(Long.valueOf((String)getTypeMap().get(key)));
				break;
			}
		}
		
		return sendMail(arg0,arg1);
	}
	

	private ModelAndView sendMail(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		try {
			String infName = arg0.getParameter("name");
			String infSurname = arg0.getParameter("surname");
			String infEmail = arg0.getParameter("email");
			String infTelf = arg0.getParameter("telf");
			String infComment = arg0.getParameter("comment");
			
			if (infName!=null){
				Properties props = new Properties();
				Session mailSession = Session.getDefaultInstance(props, null);    
				MimeMessage msg = new MimeMessage(mailSession);
				msg.setFrom(new InternetAddress(getSender()));
				msg.addRecipient(Message.RecipientType.TO,
				                 new InternetAddress(getRecipient()));
				if (getRecipientCC()!= null){
					msg.addRecipient(Message.RecipientType.CC,
		                 new InternetAddress(getRecipientCC()));
				}			
				String subjectCom = getSubject()+ infName + ", " + infSurname;
				msg.setSubject(subjectCom, "UTF-8");
	
				StringBuffer text = new StringBuffer(subjectCom+"<br>");
				text.append("Email: "+infEmail+"<br>");
				text.append("Telf.: "+infTelf+"<br>");
				if (infComment!=null && infComment.length()>0){
					text.append("Comment: "+infComment+"<br>");
				}
				
				msg.setContent(text.toString(), "text/html");
				
				Transport.send(msg);
				
				LOG.info("SendController mail enviado : "+msg.toString());
			}
		
			ModelAndView mav = new ModelAndView(getView());
			mav.addObject("path", getPath());
			
			return mav;
			
		} catch (Exception e) {
			arg1.getWriter().println(e.getMessage()+" : "+getSender());       
		}
	        
		return null;
	}
	
	public Map getViewMap() {
		return viewMap;
	}

	public void setViewMap(Map viewMap) {
		this.viewMap = viewMap;
	}

	
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getRecipient() {
		return recipient;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientCC() {
		return recipientCC;
	}


	public void setRecipientCC(String recipientCC) {
		this.recipientCC = recipientCC;
	}
	
	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}

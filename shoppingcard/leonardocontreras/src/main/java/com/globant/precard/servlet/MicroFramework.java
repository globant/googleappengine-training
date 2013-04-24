package com.globant.precard.servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class MicroFramework {
	
	private static Logger logger = Logger.getLogger(MicroFramework.class
			.getCanonicalName());
	
	private static String fromAddress = "leonardo.contreras@globant.com";

	public static String[] getPathTokens(HttpServletRequest req) {
		String servletBaseString = req.getServletPath().concat("/");
		String cashierPath = req.getRequestURI().replaceAll(servletBaseString,
				"");
		return cashierPath.split("/");

	}
	
	public static void sendMail(String toAddress, String subject, String msgBody)
			throws IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromAddress));
			InternetAddress to = new InternetAddress(toAddress);
			msg.addRecipient(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setText(msgBody);
			Transport.send(msg, new InternetAddress[] { to });
		} catch (AddressException addressException) {
			logger.log(Level.SEVERE, "Address Exception , mail could not be sent",
					addressException);
		} catch (MessagingException messageException) {
			logger.log(Level.SEVERE,
					"Messaging Exception , mail could not be sent",
					messageException);
		}
	}


}

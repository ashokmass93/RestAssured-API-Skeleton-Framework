package com.projectname.files;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.apache.commons.io.IOUtils;

public class MailContentReader {
	
	public static String finallink;
	
	public static String  getmail(String subject,String reg) throws Exception
	{
		
		Store store;
		String theString = null;
		boolean flag = false;
		try {
			Date  mydate = new Date();
			SearchTerm st = new ReceivedDateTerm(ComparisonTerm.EQ,mydate); 
			store = connectmail();
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_WRITE);
//			Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flag.SEEN), false));
			Message[] messages = emailFolder.search(st);
			int s = messages.length;
			boolean mailflag=true;
			for (int i = s; i > 1; i--) {
				Message message = messages[i - 1];
				System.out.println("Mail Found :" + message.getSubject());
				if (message.getSubject().contains(subject)) {
					mailflag=false;
					System.out.println("Mail Found :" + message.getSubject());
					Object mp = (Object) message.getContent();
					if (mp instanceof MimeMultipart) {
						MimeMultipart mpp = (MimeMultipart) mp;
						for (int count = 0; count < mpp.getCount(); count++) {
							MimeBodyPart bp = (MimeBodyPart) mpp.getBodyPart(count);
						InputStream fileNme = bp.getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(fileNme, writer, "UTF-8");
						theString = writer.toString();
					}
				} else if (mp instanceof Multipart) {
					Multipart mpp = (Multipart) mp;
					for (int count = 0; count < mpp.getCount(); count++) {
						MimeBodyPart bp = (MimeBodyPart) mpp.getBodyPart(count);
						InputStream fileNme = bp.getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(fileNme, writer, "UTF-8");
						theString = writer.toString();
					}
				} else if (mp instanceof String) {
					theString = (String) message.getContent();
				}
				System.out.println(theString);
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(theString);
				if (matcher.find()) {
					finallink = matcher.group(1);
				}
				System.out.println("Key Found : "+finallink);
				  message.setFlag(Flags.Flag.DELETED, true);
					emailFolder.expunge();
					emailFolder.close(true);
				flag = true;
				break;
			}		
				
		}
			if(mailflag)
			{
				System.out.println("Key not Found ");
				finallink=null;
			}
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception(subject + " Not Found");
	}
		
	store.close();
	return finallink;
	
	}
	
	public static Store connectmail() throws Exception {
		Store store;
		String hostname = "pop.gmail.com";
		try {
			Properties properties = new Properties();
			Session session = Session.getDefaultInstance(properties);
			store = session.getStore("imaps");
			
			store.connect("pop.gmail.com", "sathesh.murugesan@tnqsoftware.co.in", "LilPIZI0ke");
		
			return store;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Connection Problem");
		}
	}
	
	
	

}

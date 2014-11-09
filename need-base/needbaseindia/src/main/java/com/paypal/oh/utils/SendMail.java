package com.paypal.oh.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.paypal.oh.dos.FoundReport;
import com.paypal.oh.dos.LostReport;
import com.paypal.oh.notification.NotificationRecord;

/** #########################################
 * Created by ROCKSTARS of Compliance!!!! 
    #########################################
  */
public class SendMail {

	public static void EmailNotifier(String toEmail, NotificationRecord records) {

		LostReport lostReport = records.getLostReport();
		List<FoundReport> foundReports = records.getFoundReports();
		final String username = "missingchildinfoindia@gmail.com";
		final String password = "paypal@12345";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject("Child Match Alert !");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart
					.setText("Please contact us if you find any match for "
							+ lostReport.getSubjectFirstName()
							+ " in the attached file");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			String filename = "C:\\workspace\\Hack\\JavaMail\\src\\ListOfProbableMatch.txt";

			PrintWriter writer = null;
			try {
				writer = new PrintWriter(filename);
				writer.println("Subject Found City" + "\t"
						+ "Subject Photo URL" + "\t" + "Reporter Email ID"
						+ "\t" + "Reporter Phone Number");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			for (FoundReport report : foundReports) {

				try {
					writer.println(report.getSubjectFoundCity() + "\t"
							+ report.getSubjectPhotoURL() + "\t"
							+ report.getReporterEmail() + "\t"
							+ report.getReporterPhoneNumber());
					writer.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			writer.close();

			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Mail sent succesfully!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
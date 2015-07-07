package cn.lee.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	private static final String SMTP_163 = "smtp.163.com";

	/**
	 * 从163邮箱向外发邮件
	 */
	public static boolean sendEmailFrom163(String from, String password, String to, String title, String content) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_163);
			props.put("mail.smtp.auth", "true");

			Session mailSession = Session.getDefaultInstance(props);

			mailSession.setDebug(true);
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(title);
			message.setText(content);
			message.saveChanges();

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(SMTP_163, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

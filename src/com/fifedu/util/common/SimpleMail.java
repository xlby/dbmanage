package com.fifedu.util.common;

import javax.mail.Session;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Store;

/**
 * 邮件工具类
 * @author liu10
 *
 */
public class SimpleMail {
	private String host;
	private Properties props;
	private Session session;
	private Message msg;
	private Transport transport;
	
	public SimpleMail(String host,String protocol){
		this.host = host;
		this.props = new Properties();
		this.props.setProperty("mail.debug", "true");
		this.props.setProperty("mail.smtp.auth", "true");
		this.props.setProperty("mail.host", host);
		this.props.setProperty("mail.transport.protocol", protocol);
	}
	/**
	 * 发送邮件
	 * @param subject 主题
	 * @param content 内容
	 * @param from 发件人
	 * @param to 收件人
	 * @param username 用户名
	 * @param pwd 密码
	 * @throws MessagingException
	 */
	public void send(String subject, String content, 
			String from,String to,String username, String pwd) throws MessagingException{
		this.session = Session.getInstance(this.props);
		this.msg = new MimeMessage(session);
		this.msg.setSubject(subject);
//		this.msg.setText(content);
		Multipart multi = new MimeMultipart();
		BodyPart part = new MimeBodyPart();
		part.setText(content);
		multi.addBodyPart(part);
		this.msg.setContent(multi,"text/html;charset=utf8");
		this.msg.setFrom(new InternetAddress(from));
		
		this.transport = this.session.getTransport();
//		this.transport.connect(username,pwd);
		this.transport.connect(this.host,username,pwd);
		this.transport.sendMessage(this.msg, new Address[] {new InternetAddress(to)});
	}
}

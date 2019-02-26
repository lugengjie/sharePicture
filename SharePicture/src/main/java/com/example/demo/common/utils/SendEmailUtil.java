package com.example.demo.common.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件工具类
 * 
 * @author Administrator
 *
 */
public class SendEmailUtil
{
	public static void sendHtmlMail(String receiver, String subject, String content)
	{
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		MimeMessage message = jms.createMimeMessage();
		jms.setHost("smtp.qq.com");
		jms.setUsername("1923808485@qq.com");
		jms.setPassword("yqffseanhtfodgfg");
		try
		{
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(jms.getUsername());
			helper.setTo(receiver);
			helper.setSubject(subject);
			helper.setText(content, true);
			jms.send(message);
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}

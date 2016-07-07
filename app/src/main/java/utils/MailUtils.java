package utils;



import android.util.Log;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * 邮件工具   邮件设置，发送，接收
 * Created by Administrator on 2016/7/6.
 */
public class MailUtils {
    private String username;
    private String password;


    /**
     * 发送邮件
     *
     * */
    public synchronized boolean sendMail(String postAddress,String subject,String content,Multipart multipart){
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.163.com");
            props.put("mail.smtp.user", getUsername());
            props.put("mail.smtp.password", getPassword());
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            MAuth authenticator=new MAuth(getUsername(),getPassword());
            Session session=Session.getDefaultInstance(props,authenticator);
            MimeMessage message=setMessage(session,postAddress,subject,new InternetAddress(username),multipart);
            Transport transport=session.getTransport("smtp");
            transport.connect("smtp.163.com",getUsername(),getPassword());
            Log.i("check", "connected");
            transport.send(message,message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 组装邮件内容
     * @param multipart
     * @param postAddress
     * @param senderAddress
     * @param session
     * @param subject
     * @return message
     * */
    private MimeMessage setMessage(Session session,String  postAddress,String subject, Address senderAddress, Multipart multipart) throws AddressException {
        Address post=new InternetAddress(postAddress);
        MimeMessage message=new MimeMessage(session);

        try {
            DataHandler handler = new DataHandler(new ByteArrayDataSource("".getBytes(), "text/plain"));
            message.setSender(senderAddress);
            message.setDataHandler(handler);
            message.setSubject(subject);
            message.setSentDate(new Date());
            if (multipart==null)
                multipart=new MimeMultipart();
            message.setContent(multipart);
            message.setFrom();
            message.addRecipient(Message.RecipientType.TO,post);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
/***用户授权类*/
class MAuth extends Authenticator{
    private String username;
    private String password;
    public MAuth (String username,String password){
        this.username=username;
        this.password=password;
    }
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
    }
}


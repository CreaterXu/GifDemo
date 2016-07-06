package utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    public synchronized boolean sendMail(Address postAddress, Multipart multipart){
        try {
            Properties properties=new Properties();
            MAuth authenticator=new MAuth(username,password);
            Session session=Session.getDefaultInstance(properties,authenticator);
            Message message=setMessage(session,postAddress,new InternetAddress(username),multipart);
            Transport.send(message);
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
     * @return
     * */
    private Message setMessage(Session session,Address postAddress, Address senderAddress, Multipart multipart){
        MimeMessage message=new MimeMessage(session);
        try {
            message.setSubject("xxx");
            message.setSentDate(new Date());
            if (multipart!=null)
            message.setContent(multipart);
            message.setFrom(senderAddress);
            message.addRecipient(Message.RecipientType.TO,postAddress);
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


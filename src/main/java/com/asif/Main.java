package com.asif;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println("Preparing to send message...");
        String message = dotenv.get("message");
        String subject = dotenv.get("subject");
        String to = dotenv.get("to");
        String from = dotenv.get("from");
        String pass = dotenv.get("pass");
        System.out.println(from + pass);
        sendEmail(message, subject, to, from, pass);
    }

    // this is responsible to send email.
    private static void sendEmail(String message, String subject, String to, String from, String pass) {
        String host = "smtp.gmail.com";

        // get the system properties
        Properties properties = System.getProperties();
        System.out.println(properties);

        // setting important info to properties
        // host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        session.setDebug(true);
        // compose the message
        MimeMessage m =new MimeMessage(session);

        try{
            // from addr
            m.setFrom(from);
            // to addr
            m.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            // email subject
            m.setSubject(subject);
            // message text
            m.setText(message);
            // send the email using transport class
            Transport.send(m);

            System.out.println("message sent successfully...");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
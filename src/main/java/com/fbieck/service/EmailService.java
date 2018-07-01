package com.fbieck.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class EmailService {

    private final String URL_LOCAL = "localhost:8080", URL_GAE = "www.finestfitness.app";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("manuspeeders@googlemail.com");
        mailSender.setPassword("lzangtdcndgotpzj");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendMail(String from, String to, String subject, String body){

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        logger.info("Sending simple mail to "+to);

        getJavaMailSender().send(mail);

        logger.info("Simple mail sent!");
    }

    private void sendMimeMail(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();
        mimeMessage.setSubject(subject);

        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom("info@finestfitness.app", "Finest Fitness");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body, true);
        mimeMessageHelper.setTo(to);

        logger.info("Sending mime mail to "+to);

        getJavaMailSender().send(mimeMessage);

        logger.info("Mime mail sent!");
    }

    private String getBootstrapHeader(){
        String header = "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>";
        header += "<script src='https://code.jquery.com/jquery-3.2.1.slim.min.js' integrity='sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN' crossorigin='anonymous'></script>";
        header += "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js' integrity='sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q' crossorigin='anonymous'></script>";
        header += "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js' integrity='sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl' crossorigin='anonymous'></script>";
        return header;
    }

    private String getURL(){
        return URL_GAE;
    }

    public void sendRegistrationMail(String to, String UUID){
        String htmlMessage = "<html>";
        htmlMessage += "<head>";

        htmlMessage += getBootstrapHeader();

        htmlMessage += "<meta charset='utf-8'>\n";
        htmlMessage += "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>";

        htmlMessage += "</head>";

        htmlMessage += "<body>";

        htmlMessage += "<div class='container jumbotron'>";
        htmlMessage += "<h1>Registrierung abschließen</h1>";
        htmlMessage += "</div>";

        htmlMessage += "<br><hr><br>";

        htmlMessage += "<div class='container'>";
        htmlMessage += "<a href='http://"+getURL()+"/confirm?UUID="+UUID+"'>Registrierung bestätigen</a>";
        htmlMessage += "</div>";

        htmlMessage += "</body>";

        htmlMessage += "</html>";
        try {
            sendMimeMail(to, "Registrierung abschließen", htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendConfirmationMail(String to){
        String htmlMessage = "<html>";
        htmlMessage += "<head>";

        htmlMessage += getBootstrapHeader();

        htmlMessage += "<meta charset='utf-8'>\n";
        htmlMessage += "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>";

        htmlMessage += "</head>";

        htmlMessage += "<body>";

        htmlMessage += "<div class='container jumbotron'>";
        htmlMessage += "<h1>Vielen Dank für Ihre Registrierung!</h1>";
        htmlMessage += "</div>";

        htmlMessage += "<br><hr><br>";

        htmlMessage += "</body>";

        htmlMessage += "</html>";
        try {
            sendMimeMail(to, "Registrierung", htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendPasswordResetMail(String to, String UUID){
        String htmlMessage = "<html>";
        htmlMessage += "<head>";

        htmlMessage += getBootstrapHeader();

        htmlMessage += "<meta charset='utf-8'>\n";
        htmlMessage += "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>";

        htmlMessage += "</head>";

        htmlMessage += "<body>";

        htmlMessage += "<div class='container jumbotron'>";
        htmlMessage += "<h1>Passwort zurücksetzen</h1>";
        htmlMessage += "</div>";

        htmlMessage += "<br><hr><br>";

        htmlMessage += "<div class='container'>";
        htmlMessage += "<a href='http://"+getURL()+"/passwordreset?UUID="+UUID+"'>Passwort zurücksetzen</a>";
        htmlMessage += "</div>";

        htmlMessage += "</body>";

        htmlMessage += "</html>";
        try {
            sendMimeMail(to, "Passwort zurücksetzen", htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendPasswordResetedMail(String to){
        String htmlMessage = "<html>";
        htmlMessage += "<head>";

        htmlMessage += getBootstrapHeader();

        htmlMessage += "<meta charset='utf-8'>\n";
        htmlMessage += "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>";

        htmlMessage += "</head>";

        htmlMessage += "<body>";

        htmlMessage += "<div class='container jumbotron'>";
        htmlMessage += "<h1>Passwort erfolgreich zurückgesetzt!</h1>";
        htmlMessage += "</div>";

        htmlMessage += "<br><hr><br>";

        htmlMessage += "</body>";

        htmlMessage += "</html>";
        try {
            sendMimeMail(to, "Passwort zurückgesetzt", htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

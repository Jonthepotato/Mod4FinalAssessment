package Supermarket.SupermarketServer.Controllers;

import java.util.Properties;

import javax.validation.ValidationException;

import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Supermarket.SupermarketServer.EmailConfig;
import Supermarket.SupermarketServer.Models.FeedBack;

@RestController
@RequestMapping(path="/api/feedback" ,consumes=MediaType.APPLICATION_JSON_VALUE)
public class FeedbackRestController {

    private EmailConfig emailConfig;

    public FeedbackRestController(EmailConfig emailConfig){
        this.emailConfig=emailConfig;
    }


    @PostMapping
    public void sendFeedback(@RequestBody FeedBack feedback, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }
        //Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        //Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("jonathanchua93@gmail.com");
        mailMessage.setSubject("Feedback from " +feedback.getName() +" " + feedback.getSubject());
        mailMessage.setText(feedback.getFeedback());

        //Send mail
        mailSender.send(mailMessage);
        }

    }

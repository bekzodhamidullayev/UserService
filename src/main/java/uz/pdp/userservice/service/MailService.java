package uz.pdp.userservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.MailDto;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(mailDto.getReceivers().toArray(new String[0]));
        message.setText(mailDto.getMessage());
        message.setCc("nodirbec23@gmail.com");
        javaMailSender.send(message);
    }
}

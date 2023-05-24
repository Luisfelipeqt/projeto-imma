package br.com.projectcedro.backend.projectcedro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;



    public void sendSimpleEmail(String destinatario, String assunto, String conteudo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("desenvolvimento@kanawaga.com.br");
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(conteudo);

        javaMailSender.send(message);
    }
}

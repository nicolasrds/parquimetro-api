package com.fiap.parquimetro.dominio.util;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class EmailUtil {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private Environment env;

    public String emailTemplate(String nome, int duracao, String templateName) {
        Context context = new Context();
        context.setVariable("nome", nome);
        context.setVariable("duracao", duracao);
        return templateEngine.process(templateName, context);
    }


    public void enviarEmail(String destinatario, String nome, int duracao, String templateName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            messageHelper.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
            messageHelper.setTo(destinatario);
            messageHelper.setSubject("Lembrete de Estacionamento");
            String html = emailTemplate(nome, duracao, templateName);
            messageHelper.setText(html, true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            log.error("Erro ao enviar email: " + e.getMessage());
        }
    }
}

package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.times;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("mac.ziel@gmail.com", "Test", "Test message", "test@cc.com");
        MimeMessagePreparator mimeMessagePreparator = simpleEmailService.createMimeMessage(mail, EmailType.TRELLO_CARD_MAIL);
        //when

        //when
        simpleEmailService.send(mail, EmailType.TRELLO_CARD_MAIL);

        //then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));

    }

    @Test
    public void shouldSendEmailWithoutCc() {
        //given
        Mail mail = new Mail("test@test.com", "test2@test.com", "test", "to jest test");
        MimeMessagePreparator mimeMessagePreparator = simpleEmailService.createMimeMessage(mail, EmailType.TRELLO_CARD_MAIL);
        //when
        simpleEmailService.send(mail, EmailType.TRELLO_CARD_MAIL);

        //then
        verify(javaMailSender, times(1)).send(refEq(mimeMessagePreparator));
    }

}
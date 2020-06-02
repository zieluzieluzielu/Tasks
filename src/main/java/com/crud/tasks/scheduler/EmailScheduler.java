package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.EmailType;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(fixedDelay = 10000)
    //@Scheduled(cron = "0 0 10 * * *") //moje
    //@Scheduled(cron = "0 */5 * * * *" )
    private void sendInformationEmail() {
        long size = taskRepository.count();
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "Currently in database you got: " + size + (size == 1 ? " task" : " tasks")), EmailType.SCHEDULED_MAIL);
    }


}
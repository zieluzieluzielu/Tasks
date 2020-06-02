package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${admin.mail}")
    public String adminMail;

    @Value("${admin.name}")
    public String adminName;

}

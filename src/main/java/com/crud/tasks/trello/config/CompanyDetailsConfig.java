package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyDetailsConfig {

    @Value("${info.company.name}")
    public String companyName;

    @Value("${info.company.goal}")
    public String companyGoal;

    @Value("${info.company.phone}")
    public String companyPhone;

    @Value("${info.company.email}")
    public String companyMail;
}
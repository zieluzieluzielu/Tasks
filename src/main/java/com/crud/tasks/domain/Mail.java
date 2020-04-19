package com.crud.tasks.domain;

import lombok.Getter;

@Getter
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String toCc;

    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    public Mail(String mailTo, String subject, String message,String toCc) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.toCc = toCc;
    }
}

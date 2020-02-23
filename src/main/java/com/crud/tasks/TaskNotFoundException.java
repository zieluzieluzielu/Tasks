package com.crud.tasks;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(final String message) {
    super(message);
}
}

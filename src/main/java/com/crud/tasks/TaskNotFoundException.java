package com.crud.tasks;

import com.crud.tasks.domain.Task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(final String message) {
    super(message);
}
}

package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    DbService dbService;
    @Mock
    TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        List<Task> tasksList = new ArrayList<>();
        Task task1 = new Task();
        tasksList.add(task1);

        when(repository.findAll()).thenReturn(tasksList);

        //When
        List<Task> allTasks = dbService.getAllTasks();

        //Then
        assertEquals(1, allTasks.size());
    }



    @Test
    public void saveTask() {
        //Given
        Task task1 = new Task(1L, "title", "saved content");

        when(repository.save(task1)).thenReturn(task1);

        //When
        Task savedTask = dbService.saveTask(task1);

        //Then
        assertEquals("saved content", savedTask.getContent());


    }


    @Test
    public void getTask() {
        //Given
        Task task1 = new Task(1L, "title", "content");

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(task1));

        //When
        Optional<Task> getTask = dbService.getTask(1L);

        //Then
        assertTrue(getTask.isPresent());
        assertEquals("content", getTask.get().getContent());

    }
}
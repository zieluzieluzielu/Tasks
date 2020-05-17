package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"title","content");

        //When
        Task task = taskMapper.mapToTask(taskDto);


        //Then
        assertEquals("title", task.getTitle());
        assertEquals(Long.valueOf(1L), task.getId());
        assertEquals("content", task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"title","content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);


        //Then
        assertEquals("title", taskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L,"title","content");
        Task task2 = new Task(2L,"title2","content2");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(2,taskDtoList.size());
        assertEquals("content",taskDtoList.get(0).getContent());
        assertEquals("title2",taskDtoList.get(1).getTitle());
        assertEquals(Long.valueOf(2L),taskDtoList.get(1).getId());
    }


}

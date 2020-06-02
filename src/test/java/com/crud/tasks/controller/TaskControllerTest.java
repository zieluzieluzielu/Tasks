package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTaskList() throws Exception {
        //Given
        List<TaskDto> tasksDto = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L, "title1", "content1");
        TaskDto taskDto2 = new TaskDto(2L, "title2", "content2");
        tasksDto.add(taskDto1);
        tasksDto.add(taskDto2);

        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.any())).thenReturn(tasksDto);
        //when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(tasksDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(taskDto1.getId().intValue())))
                .andExpect(jsonPath("$[1].title", is("title2")))
                .andExpect(jsonPath("$[0].content", is("content1")));
    }


    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "title1", "content1");
        Task task1 = new Task(1L, "title1", "content1");
        Optional<Task> task21 = Optional.of(task1);

        when(service.getTask(ArgumentMatchers.any())).thenReturn(task21);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any())).thenReturn(taskDto1);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(taskDto1.getId().intValue())))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.content", is("content1")));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task1 = new Task(1L, "title1", "content1");

        Optional<Task> task21 = Optional.of(task1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(service.getTask(ArgumentMatchers.any())).thenReturn(task21);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(taskDto.getId().intValue())))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.content", is("content1")));
    }


}
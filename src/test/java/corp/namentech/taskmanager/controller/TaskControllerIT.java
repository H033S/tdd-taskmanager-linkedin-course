package corp.namentech.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import corp.namentech.taskmanager.model.Task;
import corp.namentech.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerIT {

    @MockitoBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAllTasks() throws Exception {

        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "To Do"),
                new Task(2L, "Task 2", "Done")
        );
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"));

    }

    @Test
    void testAddTask() throws Exception {

        Task task = new Task(1L, "Task 1", "To do");
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"));
    }

    @Test
    void testAddTask_shouldExceptIfInvalidInput() throws Exception {

        Task task = new Task(1L, "", "");
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task))
                )
                .andExpect(status().isBadRequest());
    }

}

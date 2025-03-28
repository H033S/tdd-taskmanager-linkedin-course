package corp.namentech.taskmanager.service;


import corp.namentech.taskmanager.TaskNotFoundException;
import corp.namentech.taskmanager.model.Task;
import corp.namentech.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testUpdateTaskStatus() {

        Task task = new Task(1L, "Existing Task", "To do");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTaskStatus(1L, "In Progress");

        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getStatus()).isEqualTo("In Progress");
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetTaskById_TaskNotFound() {

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getTaskById(1L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task was not found");

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTaskById() {

        Task task = new Task(1L, "Task 1", "To Do");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task taskReturned = taskService.getTaskById(1L);

        assertThat(taskReturned).isNotNull();
        assertThat(taskReturned.getId()).isEqualTo(1L);

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {

        List<Task> tasks = Arrays.asList(
                new Task("Task 1", "To do"),
                new Task("Task 2", "In Progress")
        );
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> retrievedTasks = taskService.getAllTasks();

        assertThat(retrievedTasks).isNotNull();
        assertThat(retrievedTasks).hasSize(2);

        verify(taskRepository, times(1)).findAll();
    }


    @Test
    void testCreateTask() {

        Task task = new Task(1L, "Task 1", "To do");
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertThat(createdTask).isNotNull();
        assertThat(createdTask.getTitle()).isEqualTo("Task 1");

        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask() {

        Task existingTask = new Task(1L, "Task 1", "To Do");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).delete(existingTask);
    }

    @Test
    void testDeleteTask_TaskNotFoundException() {

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.deleteTask(1L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task was not found");

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateTask(){

        Task existingTask = new Task(1L, "Task 1", "To Do");
        Task updatedTask = new Task(1L, "Task 2", "Done");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        taskService.updateTask(1L, updatedTask);

        assertThat(existingTask).isNotNull();
        assertThat(existingTask.getTitle()).isEqualTo("Task 2");
        assertThat(existingTask.getStatus()).isEqualTo("Done");

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(existingTask);
    }
}

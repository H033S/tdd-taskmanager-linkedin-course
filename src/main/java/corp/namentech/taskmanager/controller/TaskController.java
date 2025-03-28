package corp.namentech.taskmanager.controller;

import corp.namentech.taskmanager.model.Task;
import corp.namentech.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getAllTasks() {

        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/task")
    ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {

        return ResponseEntity.ok(taskService.createTask(task));
    }
}

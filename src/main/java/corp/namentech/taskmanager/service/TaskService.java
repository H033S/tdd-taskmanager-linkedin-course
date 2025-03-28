package corp.namentech.taskmanager.service;

import corp.namentech.taskmanager.TaskNotFoundException;
import corp.namentech.taskmanager.model.Task;
import corp.namentech.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    Task updateTaskStatus(long id, String status) {

        Task task = getTaskById(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task was not found"));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        final Task task = optionalTask.orElseThrow(
                () -> new TaskNotFoundException("Task was not found"));
        taskRepository.delete(task);
    }

    public void updateTask(Long id, Task updatedTask) {

        final Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task was not found"));

        task.setTitle(updatedTask.getTitle());
        task.setStatus(updatedTask.getStatus());

        taskRepository.save(task);
    }
}

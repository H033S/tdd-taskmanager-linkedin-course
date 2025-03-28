package corp.namentech.taskmanager.repository;

import corp.namentech.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryIT {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void saveTask(){

        Task task = new Task("Title of the task","To do");

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getTitle()).isEqualTo("Title of the task");
    }

    @Test
    void testDeleteTask(){

        Task task = new Task("Task to delete","Done");
        taskRepository.save(task);

        taskRepository.delete(task);

        Optional<Task> deletedTask = taskRepository.findById(task.getId());
        assertThat(deletedTask.isPresent()).isFalse();
    }

    @Test
    void testFindAllTasks(){

        Task task1 = new Task("Task 1", "To do");
        Task task2 = new Task("Task 2", "Done");
        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();

        assertThat(tasks).hasSize(2);
    }

    @Test
    void testFindById(){

        Task task = new Task("Task" , "To do");
        taskRepository.save(task);

        Optional<Task> optionalTask = taskRepository.findById(task.getId());

        assertThat(optionalTask.isEmpty()).isFalse();
        assertThat(optionalTask.get().getTitle()).isEqualTo("Task");
    }

    @Test
    void testUpdateTask(){

         Task task = new Task("Updating a task", "To Do");
         taskRepository.save(task);

         task.setStatus("Done");
         taskRepository.save(task);
         Optional<Task> optionalTask = taskRepository.findById(task.getId());

         assertThat(optionalTask.isEmpty()).isFalse();
         assertThat(optionalTask.get().getStatus()).isEqualTo("Done");
    }
}

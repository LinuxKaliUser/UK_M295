package ch.ilv.zufallsgenerator.service;

import ch.ilv.zufallsgenerator.base.MessageResponse;
import ch.ilv.zufallsgenerator.model.Task;
import ch.ilv.zufallsgenerator.repo.TaskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final RandomGenerator randomGenerator;

    public TaskService(TaskRepo taskRepo, RandomGenerator randomGenerator) {
        this.taskRepo = taskRepo;
        this.randomGenerator = randomGenerator;
    }

    public List<Task> saveAllTasks(List<Task> tasks) {
        return taskRepo.saveAll(tasks);
    }

    public Task saveTask(Task Task) {
        return taskRepo.save(Task);
    }

    public Task getTask(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Task> getAllTasksRandom() {
        List<Task> tasks = taskRepo.findAll();
        return randomGenerator.getRandomTasksList(tasks, taskRepo);
    }
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return tasks;
    }

    public String getTestTaskName() {
        Task Task = new Task();
        Task.setDesignation("TestTätigkeit");
        saveTask(Task);
        return Task.getDesignation();
    }

    public Task updateTask(Task task, Long id) {
        return taskRepo.findById(id)
                .map(TaskOrig -> {
                    TaskOrig.setId(task.getId());
                    TaskOrig.setDesignation(task.getDesignation());
                    TaskOrig.setRemarks(task.getRemarks());
                    return taskRepo.save(TaskOrig);
                })
                .orElseGet(() -> taskRepo.save(task));
    }

    public MessageResponse deleteTask(Long id) {
        taskRepo.deleteById(id);
        return new MessageResponse( "Task" + id+" deleted");

    }

    public String deleteAllTasks(List<Task> Tasks) {
        try {
            taskRepo.deleteAll(Tasks);
            return "Deleted all Tasks";
        } catch (Throwable throwable) {
            return "Didn't delete all Tasks";
        }

    }
}

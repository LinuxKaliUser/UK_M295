package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.model.Task;
import zufallsgenerator.model.Task;
import zufallsgenerator.repo.TaskRepo;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }
    public String saveAllTasks(List<Task> tasks) {
        try {
            taskRepo.saveAll(tasks);
            return "Saved allTätigkeit!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    public String saveTask(Task Task){
        taskRepo.save(Task);
        return "%s saved!".formatted(Task.getDesignation());
    }
    public String getTaskById(Long id){
       Task Task = taskRepo.findById(id).get();
        return Task.getDesignation();
    }
    public String getAllTasks(){
        List<Task> tasks = taskRepo.findAll();
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.getDesignation());
            result.append(", ");
        }
        return result.toString();
    }
    public String getTestTaskName(){
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

    public String deleteTask(Long id) {
        Task Task = taskRepo.findById(id).get();
        try {
            taskRepo.deleteById(id);
            return "Deleted"+Task.getDesignation();
        }catch(Throwable throwable) {
            return "Didn't delete "+Task.getDesignation();
        }

    }

    public String deleteAllTasks(List<Task> Tasks) {
        try {
            taskRepo.deleteAll(Tasks);
            return "Deleted all Tasks";
        }catch(Throwable throwable) {
            return "Didn't delete all Tasks" ;
        }

    }
}

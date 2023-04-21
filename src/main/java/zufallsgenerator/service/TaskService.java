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
    public List<Task> saveAllTasks(List<Task> tasks) {
        return taskRepo.saveAll(tasks);
    }
    public Task saveTask(Task Task){
        return taskRepo.save(Task);
    }
    public Task getTaskById(Long id){
        return taskRepo.findById(id).get();
    }
    public List<Task> getAllTasks(){
        return taskRepo.findAll();
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

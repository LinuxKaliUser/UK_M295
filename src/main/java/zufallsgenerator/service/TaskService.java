package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.model.Task;
import zufallsgenerator.repo.TaskRepo;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final RandomGenerator randomGenerator;

    public TaskService(TaskRepo taskRepo, RandomGenerator randomGenerator){
        this.taskRepo = taskRepo;
        this.randomGenerator = randomGenerator;
    }
    public List<Task> saveAllTasks(List<Task> tasks) {
        return taskRepo.saveAll(tasks);
    }
    public Task saveTask(Task Task){
        return taskRepo.save(Task);
    }
    public Task getTask(){
        List<Task> tasks = taskRepo.findAll();
        return  randomGenerator.getRandomTask(tasks,taskRepo);
    }
    public List<Task> getAllTasks(){
        List<Task> tasks = taskRepo.findAll();
        return  randomGenerator.getRandomTasksList(tasks, taskRepo);
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

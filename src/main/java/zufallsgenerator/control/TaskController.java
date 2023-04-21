package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Task;
import zufallsgenerator.model.Task;
import zufallsgenerator.service.TaskService;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping("/Task/{id}")
    //@RolesAllowed(Roles.Read)
    public String getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @GetMapping("/Task")
    //@RolesAllowed(Roles.Read)
    public String getTaskById(){
        return taskService.getAllTasks();
    }

    @GetMapping("/TestTask")
    public String getTaskName(){
        return taskService.getTestTaskName();
    }

    @PutMapping("/Task/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id){
        Task updatedTask=taskService.updateTask(task,id);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    
    @PostMapping("/Task")
    public String saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
    @PostMapping("/Task")
    public String saveTasks(@RequestBody List<Task> tasks){
        return  taskService.saveAllTasks(tasks);
    }
   
    @DeleteMapping("/Task/{id}")
    public  String deleteTask(@PathVariable Long id){
        return  taskService.deleteTask(id);
    }
    @DeleteMapping("/Task")
    public  String deleteAllTasks(@RequestBody List<Task> tasks){
        return  taskService.deleteAllTasks(tasks);
    }
}

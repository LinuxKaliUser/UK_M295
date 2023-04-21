package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Task;
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
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task taskById = taskService.getTaskById(id);
        return new ResponseEntity<>(taskById, HttpStatus.OK);
    }
    @GetMapping("/Task")
    //@RolesAllowed(Roles.Read)
    public ResponseEntity<List<Task>> getTaskById(){
        List<Task> allTasks = taskService.getAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
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
    public ResponseEntity<Task> saveTask(@RequestBody Task task){
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }
    @PostMapping("/Tasks")
    public ResponseEntity<List<Task>> saveTasks(@RequestBody List<Task> tasks){
        List<Task> savedAllTasks = taskService.saveAllTasks(tasks);
        return new ResponseEntity<>(savedAllTasks, HttpStatus.OK);
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

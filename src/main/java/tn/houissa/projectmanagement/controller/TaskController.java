package tn.houissa.projectmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.houissa.projectmanagement.entities.Task;
import tn.houissa.projectmanagement.services.taskservice.ITaskService;

@Controller
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskService iTaskService;

    @PostMapping("/add/{userId}")
    @ResponseBody
    public ResponseEntity<?> addTask (@RequestBody Task task,@PathVariable int userId){
        Task createdTask= iTaskService.addTask(task,userId);
        if(createdTask==null) return new ResponseEntity<>("Couldn't add task", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(createdTask, HttpStatusCode.valueOf(201));

    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<?> getTasks (){

        return new ResponseEntity<>(iTaskService.getTasks(), HttpStatusCode.valueOf(201))
     ;
    }
    @GetMapping("/{idTask}")
    @ResponseBody
    public ResponseEntity<?> getOneTask(@PathVariable int idTask){
        Task taskRetreived= iTaskService.getTask(idTask);
        if(taskRetreived==null) return new ResponseEntity<>("Couldn't find task", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(taskRetreived, HttpStatusCode.valueOf(200));

    }

    @DeleteMapping("delete/{idTask}")
    @ResponseBody
    public ResponseEntity<?> deleteTask(@PathVariable int idTask) {
        if(iTaskService.deleteTask(idTask)) return new ResponseEntity<>("Task Deleted",HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>("Cannot delete task",HttpStatusCode.valueOf(404));
    }

    @PutMapping("update")
    @ResponseBody
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        Task updatedTask= iTaskService.updateTask(task);
        if(updatedTask==null) return new ResponseEntity<>("Couldn't update task", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(updatedTask, HttpStatusCode.valueOf(200));
    }

}

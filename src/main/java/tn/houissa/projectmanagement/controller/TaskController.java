package tn.houissa.projectmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.houissa.projectmanagement.entities.Task;
import tn.houissa.projectmanagement.services.task.TaskService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add/{userId}")
    @ResponseBody
    public Task addTask (@RequestBody Task task,@PathVariable int userId){

        return taskService.addTask(task,userId);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Task> getTasks (){
        return taskService.getTasks();
    }


    @DeleteMapping("delete/{idTask}")
    @ResponseBody
    public String deleteTask(@PathVariable int idTask) {
        taskService.deleteTask(idTask);
        return "Task deleted";
    }

    @PutMapping("update")
    @ResponseBody
    public Task updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

}

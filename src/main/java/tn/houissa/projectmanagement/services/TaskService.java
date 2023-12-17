package tn.houissa.projectmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.Task;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.TaskRepository;
import tn.houissa.projectmanagement.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class TaskService  {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;


    public Task addTask(Task task,int userId) {
        User user =userRepository.findById(userId).orElse(null);
        if (validTask(task)&& user!=null){
            task.setUser(user);
            return taskRepository.save(task);
        }
        return null;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Task task) {
        Task tasktoUpdate = taskRepository.findById(task.getTask_id()).orElse(null);

        if (validTask(task) && tasktoUpdate != null){
            return taskRepository.save(task);

        }
        return null;
    }

    public boolean deleteTask(int idTask) {
        Task task =taskRepository.findById( idTask).orElse(null) ;
        if(task!=null){
            taskRepository.delete(task);
            return true;
        }
        return false;
    }

    public Task getTask(int idTask) {
        return taskRepository.findById(idTask).orElse(null);
    }

    private boolean validTask(Task task) {
        return task != null && task.getTitle().isEmpty() && task.getDescription().isEmpty() && task.getDueDate().compareTo(new Date())<0 && task.getStatus().name().isEmpty();
    }
}

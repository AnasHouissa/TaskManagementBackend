package tn.houissa.projectmanagement.services.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.Task;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.TaskRepository;
import tn.houissa.projectmanagement.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Task addTask(Task task,int userId) {
        if (validTask(task)){
            task.setUser(userRepository.findById(userId).orElse(null));
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        if (validTask(task)){
            return taskRepository.save(task);

        }
        return null;
    }

    @Override
    public boolean deleteTask(int idTask) {
        Task task =taskRepository.findById( idTask).orElse(null) ;
        if(task!=null){
            taskRepository.delete(task);
            return true;
        }
        return false;
    }

    @Override
    public Task getTask(int idTask) {
        return taskRepository.findById(idTask).orElse(null);
    }

    private boolean validTask(Task task) {
        return task != null && task.getTitle().isEmpty() && task.getDescription().isEmpty() && task.getDueDate().compareTo(new Date())<0 && task.getStatus().name().isEmpty();
    }
}

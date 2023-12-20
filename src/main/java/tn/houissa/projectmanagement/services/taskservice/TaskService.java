package tn.houissa.projectmanagement.services.taskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.Task;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.ITaskRepository;
import tn.houissa.projectmanagement.repositories.IUserRepository;

import java.util.Date;
import java.util.List;

@Service
public class TaskService  implements  ITaskService{

    final
    ITaskRepository ITaskRepository;
    final
    IUserRepository IUserRepository;

    public TaskService(ITaskRepository ITaskRepository, IUserRepository IUserRepository) {
        this.ITaskRepository = ITaskRepository;
        this.IUserRepository = IUserRepository;
    }


    @Override
    public Task addTask(Task task, int userId) {
        User user = IUserRepository.findById(userId).orElse(null);
        if (validTask(task)&& user!=null){
            task.setUser(user);
            return ITaskRepository.save(task);
        }
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return ITaskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        Task tasktoUpdate = ITaskRepository.findById(task.getTask_id()).orElse(null);

        if (validTask(task) && tasktoUpdate != null){
            return ITaskRepository.save(task);

        }
        return null;
    }

    @Override
    public boolean deleteTask(int idTask) {
        Task task = ITaskRepository.findById( idTask).orElse(null) ;
        if(task!=null){
            ITaskRepository.delete(task);
            return true;
        }
        return false;
    }

    @Override
    public Task getTask(int idTask) {
        return ITaskRepository.findById(idTask).orElse(null);
    }

    private boolean validTask(Task task) {

        return task != null && !task.getPriority().name().isEmpty() && !task.getTitle().isEmpty() && !task.getDescription().isEmpty() && task.getDueDate().compareTo(new Date())>=0 && task.getStartTime().compareTo(task.getEndTime())<0 && !task.getStatus().name().isEmpty();
    }
}

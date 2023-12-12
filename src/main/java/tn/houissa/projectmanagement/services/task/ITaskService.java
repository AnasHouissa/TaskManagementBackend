package tn.houissa.projectmanagement.services.task;

import tn.houissa.projectmanagement.entities.Task;
import tn.houissa.projectmanagement.entities.User;

import java.util.List;

public interface ITaskService {

    public Task addTask(Task task,int userId);

    public List<Task> getTasks();

    public Task updateTask(Task task);

    public boolean deleteTask(int idTask);

    public Task getTask(int idTask);
}

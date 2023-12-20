package tn.houissa.projectmanagement.services.taskservice;

import tn.houissa.projectmanagement.entities.Task;

import java.util.List;

public interface ITaskService {

     Task addTask(Task task, int userId);
     List<Task> getTasks();

     Task updateTask(Task task) ;

     boolean deleteTask(int idTask);

     Task getTask(int idTask);


}

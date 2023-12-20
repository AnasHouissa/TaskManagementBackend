package tn.houissa.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.houissa.projectmanagement.entities.Task;

public interface ITaskRepository extends JpaRepository<Task,Integer> {
}

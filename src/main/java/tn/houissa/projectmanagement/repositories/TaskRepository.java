package tn.houissa.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.houissa.projectmanagement.entities.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}

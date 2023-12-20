package tn.houissa.projectmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.houissa.projectmanagement.entities.User;

public interface IUserRepository extends JpaRepository<User,Integer> {
}
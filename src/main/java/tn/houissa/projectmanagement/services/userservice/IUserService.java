package tn.houissa.projectmanagement.services.userservice;

import tn.houissa.projectmanagement.entities.User;

import java.util.List;

public interface IUserService {

     User addUser(User user);

     List<User> getUsers();

     User updateUser(User user);

     boolean deleteUser(int idUser);

     User getUser(int idUser);
}

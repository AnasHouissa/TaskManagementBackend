package tn.houissa.projectmanagement.services.userservice;

import tn.houissa.projectmanagement.entities.User;

import java.util.List;

public interface IUserService {

     Boolean register(User user);
     String login(String email,String pwd);

     List<User> getUsers();

     User updateUser(User user);

     boolean deleteUser(int idUser);

     User getUser(int idUser);

     User getUserByEmail(String email);
}

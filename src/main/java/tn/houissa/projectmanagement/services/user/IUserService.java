package tn.houissa.projectmanagement.services.user;

import tn.houissa.projectmanagement.entities.User;

import java.util.List;

public interface IUserService {

    public User addUser(User user);

    public List<User> getUsers();

    public User updateUser(User user);

    public boolean deleteUser(int idUser);

    public User getUser(int idUser);
}

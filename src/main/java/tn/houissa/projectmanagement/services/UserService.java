package tn.houissa.projectmanagement.services;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {

        if (validUser(user)){
            return userRepository.save(user);
        }
        return null;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User usertoUpdate = userRepository.findById(user.getUser_id()).orElse(null);
        if (validUser(user) && usertoUpdate != null){
            return  userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(int idUser) {
        User user =userRepository.findById( idUser).orElse(null) ;
        if(user!=null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public User getUser(int idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    private boolean validUser(User user) {
        return user != null && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty() && !user.getUsername().isEmpty();
    }
}

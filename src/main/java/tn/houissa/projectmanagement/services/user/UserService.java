package tn.houissa.projectmanagement.services.user;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {

        if (validUser(user)){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if (validUser(user)){
            return  userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean deleteUser(int idUser) {
        User user =userRepository.findById( idUser).orElse(null) ;
        if(user!=null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(int idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    private boolean validUser(User user) {
        return user != null && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty() && !user.getUsername().isEmpty();
    }
}

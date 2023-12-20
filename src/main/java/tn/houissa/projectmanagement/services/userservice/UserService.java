package tn.houissa.projectmanagement.services.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.IUserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService implements  IUserService{

    @Autowired
    IUserRepository IUserRepository;

    @Override
    public User addUser(User user) {

        if (validUser(user)){
            return IUserRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return IUserRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User usertoUpdate = IUserRepository.findById(user.getUser_id()).orElse(null);
        if (validUser(user) && usertoUpdate != null){
            return  IUserRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean deleteUser(int idUser) {
        User user = IUserRepository.findById( idUser).orElse(null) ;
        if(user!=null){
            IUserRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(int idUser) {
        return IUserRepository.findById(idUser).orElse(null);
    }

    private boolean validUser(User user) {
        return user != null && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty() && !user.getUsername().isEmpty();
    }
}

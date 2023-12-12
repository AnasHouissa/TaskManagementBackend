package tn.houissa.projectmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.services.user.UserService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addUser (@RequestBody User user){
        User createdUser= userService.addUser(user);
        if(createdUser==null) return new ResponseEntity<>("Couldn't add user", HttpStatusCode.valueOf(400));
        return new ResponseEntity<>(createdUser, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<User> getUsers (){
        return userService.getUsers();
    }


    @DeleteMapping("delete/{idUser}")
    @ResponseBody
    public String deleteUser(@PathVariable int idUser) {
        userService.deleteUser(idUser);
        return "User deleted";
    }

    @PutMapping("update")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}

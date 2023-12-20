package tn.houissa.projectmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.services.userservice.IUserService;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addUser (@RequestBody User user){
        User createdUser= iUserService.addUser(user);
        if(createdUser==null) return new ResponseEntity<>("Couldn't add user", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(createdUser, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<?> getUsers (){
        return new ResponseEntity<>(iUserService.getUsers(),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{idUser}")
    @ResponseBody
    public ResponseEntity<?> getOneUser(@PathVariable int idUser){
        User userRetrieved= iUserService.getUser(idUser);
        if(userRetrieved==null) return new ResponseEntity<>("Couldn't find user", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(userRetrieved, HttpStatusCode.valueOf(200));

    }


    @DeleteMapping("delete/{idUser}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable int idUser) {
        if(iUserService.deleteUser(idUser)) return new ResponseEntity<>("User Deleted",HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>("Cannot delete user",HttpStatusCode.valueOf(404));
    }

    @PutMapping("update")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser= iUserService.updateUser(user);
        if(updatedUser==null) return new ResponseEntity<>("Couldn't update user", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(updatedUser, HttpStatusCode.valueOf(200));
    }
}

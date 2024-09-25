package hemangs.own.project.controller;

import hemangs.own.project.model.User;
import hemangs.own.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "test successfull.";
    }

    @GetMapping("/AllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/GetUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/CreateUser")
    public String createUser(@RequestBody User user) { //need to change it to optional at some point.
        boolean save = userService.createUser(user);
        if(save)
                return "Profile Created";
        return "Email Already Exists!";
    }

    @PostMapping("/Login")
    public String loginUser(@RequestBody User user) { //need to change it to optional at some point.
        boolean validateUser = userService.loginCheck(user);
        if(validateUser == true) {
            return "correct Creds!!";
        }
        return "Incorrect Creds.";
    }

    @PutMapping("/UpdateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/DeleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/DeleteAllUser")
    public ResponseEntity<Void> deleteAllUser(@PathVariable String id) {
        userService.deleteAllUser();
        return ResponseEntity.noContent().build();
    }
}

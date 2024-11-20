package hemangs.own.project.controller;

import hemangs.own.project.model.User;
import hemangs.own.project.service.UserService;
import hemangs.own.project.service.WebsitesScraping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WebsitesScraping websitesScraping;
    @Autowired
    public UserController(WebsitesScraping websitesScraping) {
        this.websitesScraping = websitesScraping;
    }


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
        boolean save = userService.doesEmailExist(user);
        if(!save){
            userService.createUser(user);
            return "Profile Created";
        }
        return "Email Already Exists!";
    }

    @PostMapping("/Login")
    public ResponseEntity<String> loginUser(@RequestBody User user) { //need to change it to optional at some point.
        boolean correctEmail = userService.doesEmailExist(user);
        boolean correctPassword = userService.checkPassword(user);
        if(correctEmail && correctPassword){
            String loginResponse = userService.login();
            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
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
    public ResponseEntity<Void> deleteAllUser() {
        userService.deleteAllUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ScrapeWebsiteTest")
    public ResponseEntity<String> ScrapeWebsiteTest(@RequestParam String url) throws IOException {
        System.out.println("made itto webpage controller!! and URL is : "+url);
        return ResponseEntity.ok(websitesScraping.scrapeLeetCodeWebPage(url));
    }

    @GetMapping("/ScrapeUsername")
    public ResponseEntity<String> ScrapeUsername(@RequestParam String url) throws IOException {
        System.out.println("got into username controller!! and URL is : "+url);
        return ResponseEntity.ok(websitesScraping.getUserNameFromLeetcode(url));
    }
}
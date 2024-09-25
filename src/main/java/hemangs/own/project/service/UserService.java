package hemangs.own.project.service;

import hemangs.own.project.model.User;
import hemangs.own.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private LoginPageService loginPageService;
    private Optional<User> user;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String email) {
        return userRepository.findById(email);
    }

    public boolean validateEmail(String userEmail) {

        user = userRepository.findById(userEmail);
        if(user.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean createUser(User user) {
        String userEmail = user.getEmail();
        if(!validateEmail(userEmail) ) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean loginCheck(User user){
        String userEmail = user.getEmail();
        if(validateEmail(userEmail) && loginPageService.validatePassword(user)) {
            return true;
        }
        return false;
    }

    public User updateUser(String email, User userDetails) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found with id " + email));
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }
}

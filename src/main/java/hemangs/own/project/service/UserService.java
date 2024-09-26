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

    public boolean doesEmailExist(User user){
        String email = user.getEmail();
        Optional<User> existingUser = userRepository.findById(email);

        if(existingUser.isEmpty())
                return false; //No user does not exist
        return true; //yes this user exist
    }

    public boolean checkPassword(User user){
        String inputEmail = user.getEmail();
        Optional<User> optionalExistingUser = userRepository.findById(inputEmail);
        String inputPassword = user.getPassword();

        if(optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            String existingPassword = existingUser.getPassword();

            return existingPassword.equals(inputPassword); //password is currect
        }

        else return false;
    }

    public String login(){
        return "login successful!";
    }

    public void createUser(User user){
        userRepository.save(user);
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

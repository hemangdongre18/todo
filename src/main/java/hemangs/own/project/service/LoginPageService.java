package hemangs.own.project.service;

import hemangs.own.project.model.User;
import hemangs.own.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginPageService {

    @Autowired
    private UserRepository userRepository;
    Optional<User> user = Optional.of(new User());


    public boolean validatePassword(User user){
        String email = user.getEmail();
        String password = user.getPassword();

        User validUser = userRepository.findById(email).orElse(null);
        if(validUser.getPassword().equals(password))
            return true;
        return false;
    }
}

package hemangs.own.project.defaultData;

import hemangs.own.project.model.User;
import hemangs.own.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0) {
            User user = new User();
            user.setEmail("example1@gmail.com");
            user.setPassword("example1");
            userRepository.save(user);

            User user1 = new User();
            user1.setPassword("example2");
            user1.setEmail("example2@gmail.com");
            userRepository.save(user1);

            User user2 = new User();
            user2.setPassword("example3");
            user2.setEmail("example3@gmail.com");
            userRepository.save(user2);


            System.out.println("Default Users have been added!");
        }
    }
}

package hemangs.own.project; // Correct package declaration

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "hemangs.own.project.repository") // Ensure this matches the correct package name
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

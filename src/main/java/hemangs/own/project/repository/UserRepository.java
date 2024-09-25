package hemangs.own.project.repository;

import hemangs.own.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Additional query methods can be defined here if needed
}

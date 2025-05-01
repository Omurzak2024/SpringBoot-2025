package peaksoft.springboot2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.springboot2025.entity.User;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findUserByEmail(String email);
    @Query("select u from User u where u.email=:email")
    User findUserWithEmail(String email);

}

package java16.repo;

import java16.entitys.User;
import java16.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User  findByUserId(Long userId){
        return findById(userId).orElseThrow(()->new NotFoundException("not found with User Id : "+userId));
    }

    @Query("select count(u) > 0 from User u where u.email = :adminEmail")
    boolean existsByEmail(String adminEmail);
}

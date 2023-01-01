package m.Model.Repository;

import m.Model.Entity.UserMana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserMana,Integer> {
UserMana findByUserName(String userName);
boolean existsByUserName(String userName);
boolean existsByEmail(String email);
}

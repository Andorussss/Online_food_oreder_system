package kz.foodorder.delivery.repositories;

import jakarta.transaction.Transactional;
import kz.foodorder.delivery.entities.Order;
import kz.foodorder.delivery.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}

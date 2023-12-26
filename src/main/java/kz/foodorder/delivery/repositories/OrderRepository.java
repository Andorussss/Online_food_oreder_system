package kz.foodorder.delivery.repositories;

import jakarta.transaction.Transactional;
import kz.foodorder.delivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser_UserId(Long id);

    List<Order> findAllByDish_Id(Long id);
}

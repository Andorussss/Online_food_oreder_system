package kz.foodorder.delivery.repositories;

import jakarta.transaction.Transactional;
import kz.foodorder.delivery.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DishRepository extends JpaRepository<Dish,Long> {
}

package kz.foodorder.delivery.repositories;

import jakarta.transaction.Transactional;
import kz.foodorder.delivery.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Long> {
}

package example.repo;

import example.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends CrudRepository<OrderEntity, Long> {
}

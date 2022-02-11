package example.service;

import example.mapper.OrderEntityMapper;
import example.model.Order;
import example.model.entity.OrderEntity;
import example.repo.OrderRepo;
import org.hibernate.StaleObjectStateException;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    private final OrderEntityMapper mapper = Mappers.getMapper(OrderEntityMapper.class);
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order create(Order order) {
        OrderEntity entity = orderRepo.save(mapper.toEntity(order));
        return mapper.fromEntity(entity);
    }

    public Optional<Order> update(Order order) {
        if( !orderRepo.existsById(order.getId())) {
            return Optional.empty();
        }

        OrderEntity entity = null;
        try {
            entity = orderRepo.save(mapper.toEntity(order));
        } catch (StaleObjectStateException e) {

        }
        return Optional.ofNullable(mapper.fromEntity(entity));
    }

    public Optional<Order> get(Long id) {
        return orderRepo.findById(id)
                .map(mapper::fromEntity)
                .or(()-> Optional.empty());
    }

    public List<Order> list() {
        Iterable<OrderEntity> entities = orderRepo.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }
}

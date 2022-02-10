package example.mapper;

import example.model.Order;
import example.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderEntityMapper {
    Order fromEntity (OrderEntity source);
    OrderEntity toEntity(Order destination);
    void updateEntity(Order source, @MappingTarget OrderEntity destination);
}

package example.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Data
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;
    private String num;
}

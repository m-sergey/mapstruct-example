package example.model;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String num;
    private Long version;
}

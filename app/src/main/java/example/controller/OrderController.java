package example.controller;

import example.model.Order;
import example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> list() {
        return ResponseEntity.ok(orderService.list());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> create(@RequestBody Order newOrder) {
        return ResponseEntity.ok(orderService.create(newOrder));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> update(@PathParam("id") Long id, @RequestBody Order order) {
        Optional<Order> result = orderService.update(order);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> get(@PathParam("id") Long id) {
        Optional<Order> result = orderService.get(id);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

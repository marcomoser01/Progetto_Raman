package eu.fbk.dslab.af.order.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.fbk.dslab.af.order.domain.Order;
import eu.fbk.dslab.af.order.service.OrderService;


@RestController
@RequestMapping("/api")
public class OrderController {
    
    @Autowired
    private OrderService service;

    @GetMapping("/orders/{userId}")
    public @ResponseBody Page<Order> getOrders(@PathVariable String userId, Pageable page) {
        return service.getOrders(userId, page);
    }

    @GetMapping("/orders/{userId}/{orderId}")
    public @ResponseBody Order getOrder(@PathVariable String userId, @PathVariable String orderId) {
        return service.getOrder(userId, orderId);
    }

    @PostMapping("/orders/{userId}/{productId}/{quantity}")
    public @ResponseBody Order createOrder(@PathVariable String userId, @PathVariable String productId, @PathVariable Integer quantity) {
        return service.createOrder(userId, productId, quantity);
    }
}

package eu.fbk.dslab.af.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import eu.fbk.dslab.af.order.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findByUserId(String userId, Pageable page);
    
}

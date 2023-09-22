package eu.fbk.dslab.af.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eu.fbk.dslab.af.order.domain.Order;
import eu.fbk.dslab.af.order.domain.ProductObject;
import eu.fbk.dslab.af.order.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OpenFeignCatalogService catalogService;
    // private CatalogService catalogService;

    public Page<Order> getOrders(String userId, Pageable page) {
        return repository.findByUserId(userId, page);
    }

    public Order getOrder(String userId, String orderId) {
        return repository.findById(orderId).orElse(null);
    }

    public Order createOrder(String userId, String productId, Integer quantity) {
        ProductObject productFromCatalog = catalogService.getProductFromCatalog(productId);
        if (productFromCatalog == null) return null;

        catalogService.changeProductAvailability(productId, quantity);

        Order order = new Order();
        order.setProductDescription(productFromCatalog.getDescription());
        order.setProductTitle(productFromCatalog.getTitle());
        order.setPrice(productFromCatalog.getPrice());
        order.setQuantity(quantity);
        order.setTotal(order.getPrice() * order.getQuantity());
        order.setUserId(userId);
        order = repository.save(order);
        return order;
    }
}

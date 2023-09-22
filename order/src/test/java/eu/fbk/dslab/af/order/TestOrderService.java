package eu.fbk.dslab.af.order;

import org.apache.commons.math.stat.descriptive.summary.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import eu.fbk.dslab.af.order.domain.Order;
import eu.fbk.dslab.af.order.domain.ProductObject;
import eu.fbk.dslab.af.order.repository.OrderRepository;
import eu.fbk.dslab.af.order.service.OpenFeignCatalogService;
import eu.fbk.dslab.af.order.service.OrderService;

@SpringBootTest
public class TestOrderService {
    
    @Autowired
    private OrderRepository repo;
    @Autowired
    private OrderService service;
    @MockBean
    private OpenFeignCatalogService catalogService;

    @BeforeEach
	public void setUp() {
		repo.deleteAll();
        ProductObject test = new ProductObject();
        test.setCategory("category");
        test.setDescription("desc");
        test.setTitle("title");
        test.setPrice(100.0);
		Mockito.when(catalogService.getProductFromCatalog("123")).thenReturn(test);
	}

    @Test
    public void test() {
        Order order = service.createOrder("1", "123", 1);
        Assert.notNull(order, "empty message");
    }
    
}

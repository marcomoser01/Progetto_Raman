package eu.fbk.dslab.af.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class TestFilter implements GlobalFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.err.println("pre filter");
        exchange.getAttributes().putIfAbsent("startTime", System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // POST FILTER LOGIC
            System.err.println("post filter: " + (System.currentTimeMillis() - (Long)exchange.getAttribute("startTime")));
         }));
    }
    
}

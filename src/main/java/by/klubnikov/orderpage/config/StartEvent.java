package by.klubnikov.orderpage.config;

import by.klubnikov.orderpage.entity.Order;
import by.klubnikov.orderpage.entity.OrderStatus;
import by.klubnikov.orderpage.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartEvent {

    private final OrderService service;

    @EventListener(ApplicationReadyEvent.class)
    public void startApp(){
        Order order = new Order();
        order.setPrice(26.99);
        service.save(order);

        Order order1 = new Order();
        order1.setPrice(32.45);
        service.save(order1);

        Order order2 = new Order();
        order2.setPrice(250.64);
        service.save(order2);
    }

}

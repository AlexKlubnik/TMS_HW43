package by.klubnikov.orderpage.service;

import by.klubnikov.orderpage.entity.Order;
import by.klubnikov.orderpage.entity.OrderStatus;
import by.klubnikov.orderpage.repository.CrudRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CrudRepo<Order, Long> repo;


    public Order save(Order entity) {
        return repo.save(entity);
    }


    public Optional<Order> findById(Long id) {
        return repo.findById(id);
    }


    public List<Order> findAll() {
        return repo.findAll();
    }


    public void delete(Long id) {
        repo.delete(id);
    }

    public OrderStatus getOrderStatus(LocalDateTime creationTime, LocalDateTime requestTime) {
        OrderStatus status = null;
        long timeDiff = ChronoUnit.SECONDS.between(requestTime, creationTime);
        if (timeDiff <= 60) {
            status = OrderStatus.PAID;
        } else if (timeDiff > 60 && timeDiff <= 300) {
            status = OrderStatus.SEND;
        } else if (timeDiff > 300 && timeDiff <= 3600) {
            status = OrderStatus.INDELIVERY;
        } else if (timeDiff >= 3600) {
            status = OrderStatus.RECEIVED;
        }
        return status;
    }
}

package by.klubnikov.orderpage.repository;

import by.klubnikov.orderpage.entity.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepo implements CrudRepo<Order, Long> {

    private final SessionFactory factory;

    @Override
    public Order save(Order entity) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (entity.getId() == null) {
                session.persist(entity);
            } else {
                session.merge(entity);
            }
            transaction.commit();
        }
        return entity;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order order = null;
        try (Session session = factory.openSession()) {
            order = session.find(Order.class, id);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders;
        try (Session session = factory.openSession()) {
            orders = session
                    .createQuery("from Order", Order.class)
                    .getResultList();
        }
        return orders;
    }

    @Override
    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            findById(id)
                    .ifPresent(order -> session.remove(order));
            transaction.commit();
        }
    }
}

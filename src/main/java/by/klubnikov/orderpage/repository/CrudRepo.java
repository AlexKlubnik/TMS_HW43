package by.klubnikov.orderpage.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepo<T, D> {

    T save(T entity);

    Optional<T> findById(D id);

    List<T> findAll();

    void delete(D id);
}

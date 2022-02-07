package by.library.yurueu.repository;

import by.library.yurueu.entity.Order;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface OrderRepository {
    Order findById(Long id) throws RepositoryException;

    List<Order> findAll() throws RepositoryException;

    Order add(Order order) throws RepositoryException;

    boolean update(Order order) throws RepositoryException;

    boolean delete(Long id) throws RepositoryException;
}

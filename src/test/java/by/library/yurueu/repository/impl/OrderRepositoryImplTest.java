package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Order;
import by.library.yurueu.entity.OrderStatus;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import by.library.yurueu.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;


class OrderRepositoryImplTest extends BaseRepositoryTest {

    private final OrderRepository orderRepository;

    public OrderRepositoryImplTest() {
        orderRepository = new OrderRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstOrderInDB() throws RepositoryException {
        //given
        Order expected = findOrderForFindById();

        //when
        Order actual = orderRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllOrders() throws RepositoryException {
        //given
        List<Order> expected = findOrdersForFindAll();

        //when
        List<Order> actual = orderRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedOrder() throws RepositoryException {
        //given
        Order expected = new Order(OrderStatus.NEW, LocalDate.of(1999, 7, 6), LocalDate.of(1988, 5, 6), 223, 4L);
        //when
        Order actual = orderRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateOrder() throws RepositoryException {
        //given
        Order order = new Order(2L, OrderStatus.NEW, LocalDate.of(1998, 6, 6), LocalDate.of(1998, 6, 6), 243, 1L);

        // when
        boolean isUpdated = orderRepository.update(order);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteOrder() throws RepositoryException {
        //given
        Long orderId = 1L;

        // when
        boolean isDeleted = orderRepository.delete(orderId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
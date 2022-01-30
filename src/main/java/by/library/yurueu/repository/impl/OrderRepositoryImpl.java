package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Order;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.OrderRepository;

import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;

public class OrderRepositoryImpl implements OrderRepository {
    private static final String ID_COLUMN = "id";
    private static final String ORDER_STATUS_COLUMN = "order_status";
    private static final String START_DATE_COLUMN = "start_date";
    private static final String END_DATE_COLUMN = "end_date";
    private static final String PRICE_COLUMN = "price";
    private static final String USER_ID_COLUMN = "user_id";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM orders WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM orders";
    private static final String INSERT_QUERY =
            "INSERT INTO orders (order_status, start_date, end_date, price, user_id) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE orders SET order_status=?, start_date=?, end_date=?, price=?, user_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE id=?";



    private final DataSource dataSource;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order findById(Long id) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Order was not found[" + ex.getMessage() + "]");
        }
    }

    private Order construct(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(ID_COLUMN));
        order.setOrderStatus(resultSet.getString(ORDER_STATUS_COLUMN));
        order.setStartDate(resultSet.getDate(START_DATE_COLUMN).toLocalDate());
        order.setEndDate(resultSet.getDate(END_DATE_COLUMN).toLocalDate());
        order.setPrice(resultSet.getInt(PRICE_COLUMN));
        order.setUserId(resultSet.getLong(USER_ID_COLUMN));
        return order;
    }

    @Override
    public List<Order> findAll() throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)
        ) {
            List<Order> orders = new ArrayList<>();
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    orders.add(construct(resultSet));
                }
            }
            return orders;
        } catch (Exception ex) {
            throw new RepositoryException("Orders were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public Order add(Order order) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            settingPreparedStatement(preparedStatement, order);
            int value = preparedStatement.executeUpdate();

            if (value == 1) {
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        order.setId(resultSet.getLong(1));
                    }
                }
            }
            return order;
        } catch (Exception ex) {
            throw new RepositoryException("Order was not added[" + ex.getMessage() + "]");
        }
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setString(1, order.getOrderStatus());
        preparedStatement.setDate(2, Date.valueOf(order.getStartDate()));
        preparedStatement.setDate(3, Date.valueOf(order.getEndDate()));
        preparedStatement.setInt(4, order.getPrice());
        preparedStatement.setLong(5, order.getUserId());
    }

    @Override
    public boolean update(Order order) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            settingPreparedStatement(preparedStatement, order);
            preparedStatement.setLong(6, order.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("Order was not added[" + ex.getMessage() + "]");
        }
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        return false;
    }
}

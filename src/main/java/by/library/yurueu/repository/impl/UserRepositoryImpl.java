package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.User;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final String ID_COLUMN = "id";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String LAST_NAME_COLUMN = "last_name";
    private static final String PASSPORT_COLUMN = "passport";
    private static final String EMAIL_COLUMN = "email";
    private static final String ADDRESS_COLUMN = "address";
    private static final String BIRTH_DATE_COLUMN = "birth_date";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    private static final String INSERT_QUERY =
            "INSERT INTO users (first_name, last_name, passport, email, address, birth_date) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE users SET first_name=?, last_name=?, passport=?, email=?, address=?, birth_date=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";

    private static final String SELECT_ORDERS_BY_USER_ID_QUERY = "SELECT id FROM orders WHERE user_id=?";
    private static final String DELETE_ROLE_LINKS_QUERY = "DELETE FROM user_role_links WHERE user_id=?";
    private static final String DELETE_ORDERS_QUERY = "DELETE FROM orders WHERE user_id=?";
    private static final String DELETE_ORDER_LINKS_QUERY = "DELETE FROM order_book_copy_links WHERE order_id=?";
    private static final String DELETE_BOOK_DAMAGE_QUERY = "DELETE FROM book_damage WHERE order_id=?";

    private final DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("User was not found[" + ex.getMessage() + "]");
        }
    }

    private User construct(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID_COLUMN));
        user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN));
        user.setLastName(resultSet.getString(LAST_NAME_COLUMN));
        user.setPassportNumber(resultSet.getString(PASSPORT_COLUMN));
        user.setEmail(resultSet.getString(EMAIL_COLUMN));
        user.setAddress(resultSet.getString(ADDRESS_COLUMN));
        user.setBirthDate(resultSet.getDate(BIRTH_DATE_COLUMN).toLocalDate());
        return user;
    }

    @Override
    public List<User> findAll() throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(construct(resultSet));
                }
                return users;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Users were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public User add(User user) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            settingPreparedStatement(preparedStatement, user);
            int value = preparedStatement.executeUpdate();

            if (value == 1) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        user.setId(resultSet.getLong(1));
                    }
                }
            }
            return user;
        } catch (Exception ex) {
            throw new RepositoryException("User was not added [" + ex.getMessage() + "]");
        }
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getPassportNumber());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setDate(6, Date.valueOf(user.getBirthDate()));
    }

    @Override
    public boolean update(User user) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            settingPreparedStatement(preparedStatement, user);
            preparedStatement.setLong(7, user.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("User was not updated [" + ex.getMessage() + "]");
        }
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try {
                connection.setAutoCommit(false);
                deleteUserRoleLinks(connection, id);
                deleteBookDamage(connection, id);
                deleteUserOrders(connection, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            throw new RepositoryException("User was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }

    private void deleteUserRoleLinks(Connection connection, Long userId) throws SQLException {
        deleteLinks(connection, userId, DELETE_ROLE_LINKS_QUERY);
    }

    private void deleteLinks(Connection connection, Long id, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private void deleteUserOrders(Connection connection, Long userId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID_QUERY)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Long> ordersId = new ArrayList<>();
                while (resultSet.next()) {
                    ordersId.add(resultSet.getLong(1));
                }
                deleteOrdersLinks(connection, ordersId);
                deleteOrders(connection, userId);
            }
        }
    }

    private void deleteOrders(Connection connection, Long userId) throws SQLException {
        deleteLinks(connection, userId, DELETE_ORDERS_QUERY);
    }

    private void deleteOrdersLinks(Connection connection, List<Long> orders) throws SQLException {
        for (Long orderId : orders) {
            deleteLinks(connection, orderId, DELETE_ORDER_LINKS_QUERY);
        }
    }

    private void deleteBookDamage(Connection connection, Long orderId) throws SQLException {
        deleteLinks(connection, orderId, DELETE_BOOK_DAMAGE_QUERY);
    }
}
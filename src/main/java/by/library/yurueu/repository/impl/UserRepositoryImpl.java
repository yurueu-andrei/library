package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.User;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static by.library.yurueu.repository.Property.*;

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
                "UPDATE users SET first_name = ?, last_name = ?, passport = ?, email = ?, address = ?, birth_date = ? WHERE (id = ?)";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE (id = ?)";

    @Override
    public User findById(Long id) throws RepositoryException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong(ID_COLUMN));
                    user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN));
                    user.setLastName(resultSet.getString(LAST_NAME_COLUMN));
                    user.setPassportNumber(resultSet.getString(PASSPORT_COLUMN));
                    user.setEmail(resultSet.getString(EMAIL_COLUMN));
                    user.setAddress(resultSet.getString(ADDRESS_COLUMN));
                    user.setBirthDate(resultSet.getDate(BIRTH_DATE_COLUMN).toLocalDate());
                }
                return user;
            }
        } catch (Exception ex) {
            throw new RepositoryException("User was not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public List<User> findAll() throws RepositoryException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                User user = null;
                List<User> allUsers = new ArrayList<>();
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong(ID_COLUMN));
                    user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN));
                    user.setBirthDate(resultSet.getDate(BIRTH_DATE_COLUMN).toLocalDate());
                    allUsers.add(user);
                }
                return allUsers;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all users[" + ex.getMessage() + "]");
        }
    }

    @Override
    public User add(User user) throws RepositoryException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassportNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setDate(6, Date.valueOf(user.getBirthDate()));

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

    @Override
    public boolean update(User user) throws RepositoryException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassportNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setDate(6, Date.valueOf(user.getBirthDate()));
            preparedStatement.setLong(7, user.getId());

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            throw new RepositoryException("User was not updated [" + ex.getMessage() + "]");
        }
        return true;
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        ) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            throw new RepositoryException("User was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }
}

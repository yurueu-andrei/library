package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.BookDamage;
import by.library.yurueu.entity.User;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BookDamageRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookDamageRepositoryImpl implements BookDamageRepository {
    private static final String ID_COLUMN = "id";
    private static final String IMAGE_PATH_COLUMN = "image_path";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String ORDER_ID_COLUMN = "order_id";
    private static final String BOOK_COPY_ID_COLUMN = "book_copy_id";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM book_damage WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM book_damage";
    private static final String INSERT_QUERY =
            "INSERT INTO book_damage (image_path, user_id, order_id, book_copy_id) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE book_damage SET image_path=?, user_id=?, order_id=?, book_copy_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM book_damage WHERE id=?";

    private final DataSource dataSource;

    public BookDamageRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BookDamage findById(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Book damage was not found[" + ex.getMessage() + "]");
        }
    }

    private BookDamage construct(ResultSet resultSet) throws SQLException {
        BookDamage bookDamage = new BookDamage();
        bookDamage.setId(resultSet.getLong(ID_COLUMN));
        bookDamage.setImagePath(resultSet.getString(IMAGE_PATH_COLUMN));
        bookDamage.setUserId(resultSet.getLong(USER_ID_COLUMN));
        bookDamage.setOrderId(resultSet.getLong(ORDER_ID_COLUMN));
        bookDamage.setBookCopyId(resultSet.getLong(BOOK_COPY_ID_COLUMN));
        return bookDamage;
    }

    @Override
    public List<BookDamage> findAll() throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<BookDamage> bookDamages = new ArrayList<>();
                while (resultSet.next()) {
                    bookDamages.add(construct(resultSet));
                }
                return bookDamages;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Book damages were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public BookDamage add(BookDamage bookDamage) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            settingPreparedStatement(preparedStatement, bookDamage);
            int value = preparedStatement.executeUpdate();

            if (value == 1) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        bookDamage.setId(resultSet.getLong(1));
                    }
                }
            }
            return bookDamage;
        } catch (Exception ex) {
            throw new RepositoryException("Book damage was not added [" + ex.getMessage() + "]");
        }
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, BookDamage bookDamage) throws SQLException {
        preparedStatement.setString(1, bookDamage.getImagePath());
        preparedStatement.setLong(2, bookDamage.getUserId());
        preparedStatement.setLong(3, bookDamage.getOrderId());
        preparedStatement.setLong(4, bookDamage.getBookCopyId());
    }

    @Override
    public boolean update(BookDamage bookDamage) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            settingPreparedStatement(preparedStatement, bookDamage);
            preparedStatement.setLong(5, bookDamage.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("Book damage was not updated [" + ex.getMessage() + "]");
        }
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            throw new RepositoryException("Book damage was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }
}

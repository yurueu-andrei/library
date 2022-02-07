package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.BookCopy;
import by.library.yurueu.entity.BookCopyStatus;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BookCopyRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookCopyRepositoryImpl implements BookCopyRepository {
    private static final String ID_COLUMN = "id";
    private static final String BOOK_COPY_STATUS_COLUMN = "book_copy_status";
    private static final String REGISTRATION_DATE_COLUMN = "registration_date";
    private static final String PRICE_COLUMN = "price";
    private static final String PRICE_PER_DAY_COLUMN = "price_per_day";
    private static final String BOOK_ID_COLUMN = "book_id";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM book_copies WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM book_copies";
    private static final String INSERT_QUERY =
            "INSERT INTO book_copies (book_copy_status, registration_date, price, price_per_day, book_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE book_copies SET book_copy_status=?, registration_date=?, price=?, price_per_day=?, book_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM book_copies WHERE id=?";

    private static final String DELETE_ORDER_BOOK_COPY_LINKS_QUERY = "DELETE FROM order_book_copy_links WHERE book_copy_id=?";
    private static final String DELETE_BOOK_DAMAGE_QUERY = "DELETE FROM book_damage WHERE book_copy_id=?";

    private final DataSource dataSource;

    public BookCopyRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BookCopy findById(Long id) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next() ? construct(resultSet) : null;
            }

        } catch (Exception ex) {
            throw new RepositoryException("Book copy was not found[" + ex.getMessage() + "]");
        }
    }

    private BookCopy construct(ResultSet resultSet) throws SQLException {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(resultSet.getLong(ID_COLUMN));
        bookCopy.setStatus(BookCopyStatus.valueOf(resultSet.getString(BOOK_COPY_STATUS_COLUMN)));
        bookCopy.setRegistrationDate(resultSet.getDate(REGISTRATION_DATE_COLUMN).toLocalDate());
        bookCopy.setPrice(resultSet.getInt(PRICE_COLUMN));
        bookCopy.setPricePerDay(resultSet.getInt(PRICE_PER_DAY_COLUMN));
        bookCopy.setBookId(resultSet.getLong(BOOK_ID_COLUMN));
        return bookCopy;
    }

    @Override
    public List<BookCopy> findAll() throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<BookCopy> bookCopies = new ArrayList<>();
                while (resultSet.next()) {
                    bookCopies.add(construct(resultSet));
                }
                return bookCopies;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Book copies were not found[" + ex.getMessage() + "]");
        }
    }
    @Override
    public BookCopy add(BookCopy bookCopy) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            settingPreparedStatement(preparedStatement, bookCopy);
            int value = preparedStatement.executeUpdate();

            if (value == 1){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        bookCopy.setId(resultSet.getLong(1));
                    }
                }
            }
            return bookCopy;
        } catch (Exception ex) {
            throw new RepositoryException("Book copy was not added[" + ex.getMessage() + "]");
        }
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, BookCopy bookCopy) throws SQLException {
        preparedStatement.setString(1, bookCopy.getStatus().toString());
        preparedStatement.setDate(2,Date.valueOf(bookCopy.getRegistrationDate()));
        preparedStatement.setInt(3, bookCopy.getPrice());
        preparedStatement.setInt(4, bookCopy.getPricePerDay());
        preparedStatement.setLong(5, bookCopy.getBookId());
    }

    @Override
    public boolean update(BookCopy bookCopy) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            settingPreparedStatement(preparedStatement, bookCopy);
            preparedStatement.setLong(6,bookCopy.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("Book copy was not updated[" + ex.getMessage() + "]");
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
                deleteOrderBookCopyLinks(connection, id);
                deleteBookDamage(connection, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            throw new RepositoryException("Book copy was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }

    private void deleteLinks(Connection connection, Long id, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private void deleteOrderBookCopyLinks(Connection connection, Long bookCopyId) throws SQLException {
        deleteLinks(connection, bookCopyId, DELETE_ORDER_BOOK_COPY_LINKS_QUERY);
    }

    private void deleteBookDamage(Connection connection, Long bookCopyId) throws SQLException {
        deleteLinks(connection, bookCopyId, DELETE_BOOK_DAMAGE_QUERY);
    }
}

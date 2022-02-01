package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Book;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BookRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookRepositoryImpl implements BookRepository {
    private static final String ID_COLUMN = "id";
    private static final String TITLE_COLUMN = "title";
    private static final String PAGES_COLUMN = "pages";
    private static final String IMAGE_PATH_COLUMN = "image_path";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM books WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM books";
    private static final String INSERT_QUERY =
            "INSERT INTO books (title, pages, image_path) VALUES (?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE books SET title = ?, pages = ?, image_path = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM books WHERE id = ?";

    private static final String SELECT_BOOK_COPIES_BY_BOOK_ID_QUERY = "SELECT id FROM book_copies WHERE book_id=?";
    private static final String DELETE_BOOK_GENRE_LINKS_QUERY = "DELETE FROM book_genre_links WHERE book_id=?";
    private static final String DELETE_BOOK_AUTHOR_LINKS_QUERY = "DELETE FROM book_author_links WHERE book_id=?";
    private static final String DELETE_BOOK_COPIES_QUERY = "DELETE FROM book_copies WHERE book_id=?";
    private static final String DELETE_BOOK_DAMAGE_QUERY = "DELETE FROM book_damage WHERE book_copy_id=?";
    private static final String DELETE_ORDER_BOOK_COPY_LINKS_QUERY = "DELETE FROM order_book_copy_links WHERE book_copy_id=?";

    private final DataSource dataSource;

    public BookRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Book findById(Long id) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("User was not found[" + ex.getMessage() + "]");
        }
    }

    private Book construct(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong(ID_COLUMN));
        book.setTitle(resultSet.getString(TITLE_COLUMN));
        book.setPagesNumber(resultSet.getInt(PAGES_COLUMN));
        book.setImagePath(resultSet.getString(IMAGE_PATH_COLUMN));
        return book;
    }

    @Override
    public List<Book> findAll() throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<Book> books = new ArrayList<>();
                while(resultSet.next()){
                    books.add(construct(resultSet));
                }
                return books;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Books were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public Book add(Book book) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            settingPreparedStatement(preparedStatement,book);
            int value = preparedStatement.executeUpdate();

            if (value == 1){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys();){
                    if (resultSet.next()){
                        book.setId(resultSet.getLong(ID_COLUMN));
                    }
                }
            }
        } catch (Exception ex) {
            throw new RepositoryException("Book was not added[" + ex.getMessage() + "]");
        }
        return book;
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, Book book) throws SQLException {
        preparedStatement.setString(1,book.getTitle());
        preparedStatement.setInt(2,book.getPagesNumber());
        preparedStatement.setString(3,book.getImagePath());
    }

    @Override
    public boolean update(Book book) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            settingPreparedStatement(preparedStatement,book);
            preparedStatement.setLong(4,book.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("Book was not updated[" + ex.getMessage() + "]");
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
                deleteBookGenreLinks(connection, id);
                deleteBookAuthorLinks(connection, id);
                deleteBooksBookCopies(connection, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            throw new RepositoryException("Order was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }

    private void deleteLinks(Connection connection, Long id, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private void deleteBookAuthorLinks(Connection connection, Long bookId) throws SQLException {
        deleteLinks(connection, bookId, DELETE_BOOK_AUTHOR_LINKS_QUERY);
    }

    private void deleteBookGenreLinks(Connection connection, Long bookId) throws SQLException {
        deleteLinks(connection, bookId, DELETE_BOOK_GENRE_LINKS_QUERY);
    }

    private void deleteBooksBookCopies(Connection connection, Long bookId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_COPIES_BY_BOOK_ID_QUERY)) {
            preparedStatement.setLong(1, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Long> bookCopiesId = new ArrayList<>();
                while (resultSet.next()) {
                    bookCopiesId.add(resultSet.getLong(1));
                }
                deleteBookDamage(connection, bookCopiesId);
                deleteOrderBookCopyLinks(connection, bookCopiesId);
                deleteBookCopies(connection, bookId);
            }
        }
    }

    private void deleteBookDamage(Connection connection, List<Long> bookCopiesId) throws SQLException {
        for (Long bookId : bookCopiesId) {
            deleteLinks(connection, bookId, DELETE_BOOK_DAMAGE_QUERY);
        }
    }

    private void deleteOrderBookCopyLinks(Connection connection, List<Long> bookCopiesId) throws SQLException {
        for (Long bookId : bookCopiesId) {
            deleteLinks(connection, bookId, DELETE_ORDER_BOOK_COPY_LINKS_QUERY);
        }
    }

    private void deleteBookCopies(Connection connection, Long bookId) throws SQLException {
        deleteLinks(connection, bookId, DELETE_BOOK_COPIES_QUERY);
    }

}
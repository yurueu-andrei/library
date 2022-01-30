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
        return false;
    }
}

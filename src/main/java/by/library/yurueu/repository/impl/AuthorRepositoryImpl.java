package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Author;
import by.library.yurueu.entity.Book;
import by.library.yurueu.entity.Order;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;

import static java.lang.String.valueOf;


public class AuthorRepositoryImpl implements AuthorRepository {
    private static final String ID_COLUMN = "id";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String LAST_NAME_COLUMN = "last_name";
    private static final String BIRTH_DATE_COLUMN = "birth_date";
    private static final String IMAGE_PATH_COLUMN = "image_path";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM authors WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM authors";
    private static final String INSERT_QUERY =
            "INSERT INTO authors (first_name, last_name, birth_date, image_path) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE authors SET first_name = ?, last_name = ?, birth_date = ?, image_path = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id = ?";

    private static final String DELETE_BOOK_AUTHOR_LINKS_QUERY = "DELETE FROM book_author_links WHERE author_id=?";

    private final DataSource dataSource;

    public AuthorRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author findById(Long id) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)
        ) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Author was not found[" + ex.getMessage() + "]");
        }
    }

    private Author construct(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong(ID_COLUMN));
        author.setFirstName(resultSet.getString(FIRST_NAME_COLUMN));
        author.setLastName(resultSet.getString(LAST_NAME_COLUMN));
        author.setBirthDate(resultSet.getDate(BIRTH_DATE_COLUMN).toLocalDate());
        author.setImagePath(resultSet.getString(IMAGE_PATH_COLUMN));
        return author;
    }

    @Override
    public List<Author> findAll() throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)
        ) {
            List<Author> authors = new ArrayList<>();
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    authors.add(construct(resultSet));
                }
            }
            return authors;
        } catch (Exception ex) {
            throw new RepositoryException("Authors were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public Author add(Author author) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){
            settingPreparedStatement(preparedStatement, author);

            int value = preparedStatement.executeUpdate();
            if (value == 1){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        author.setId(resultSet.getLong(1));
                    }
                }
            }
            return author;
        } catch (Exception ex) {
            throw new RepositoryException("Author was not added[" + ex.getMessage() + "]");
        }
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, Author author) throws SQLException {
        preparedStatement.setString(1, author.getFirstName());
        preparedStatement.setString(2,author.getLastName());
        preparedStatement.setDate(3, Date.valueOf(author.getBirthDate()));
        preparedStatement.setString(4, author.getImagePath());
    }

    @Override
    public boolean update(Author author) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            settingPreparedStatement(preparedStatement, author);
            preparedStatement.setLong(5, author.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("Author was not updated[" + ex.getMessage() + "]");
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
                deleteBookAuthorLinks(connection, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            throw new RepositoryException("Genre was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }

    private void deleteLinks(Connection connection, Long id, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private void deleteBookAuthorLinks(Connection connection, Long authorId) throws SQLException {
        deleteLinks(connection, authorId, DELETE_BOOK_AUTHOR_LINKS_QUERY);
    }

}
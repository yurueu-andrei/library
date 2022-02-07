package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Genre;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.GenreRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryImpl implements GenreRepository {
    private static final String ID_COLUMN = "id";
    private static final String GENRE_NAME_COLUMN = "genre_name";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM genres WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM genres";
    private static final String INSERT_QUERY =
            "INSERT INTO genres (genre_name) VALUES (?)";
    private static final String UPDATE_QUERY =
            "UPDATE genres SET genre_name=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM genres WHERE id=?";

    private static final String DELETE_BOOK_GENRE_LINKS_QUERY = "DELETE FROM book_genre_links WHERE genre_id=?";

    private final DataSource dataSource;

    public GenreRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Genre findById(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Genre was not found[" + ex.getMessage() + "]");
        }
    }

    private Genre construct(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong(ID_COLUMN));
        genre.setGenreName(resultSet.getString(GENRE_NAME_COLUMN));
        return genre;
    }

    @Override
    public List<Genre> findAll() throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            List<Genre> genres = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    genres.add(construct(resultSet));
                }
                return genres;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Genres were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public Genre add(Genre genre) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, genre.getGenreName());
            int value = preparedStatement.executeUpdate();
            if (value == 1) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        genre.setId(resultSet.getLong(ID_COLUMN));
                    }
                }
            }
            return genre;
        } catch (Exception ex) {
            throw new RepositoryException("Genre was not added[" + ex.getMessage() + "]");
        }
    }

    @Override
    public boolean update(Genre genre) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            preparedStatement.setString(1, genre.getGenreName());
            preparedStatement.setLong(2, genre.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RepositoryException("Genre was not updated[" + ex.getMessage() + "]");
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

    private void deleteBookGenreLinks(Connection connection, Long genreId) throws SQLException {
        deleteLinks(connection, genreId, DELETE_BOOK_GENRE_LINKS_QUERY);
    }
}

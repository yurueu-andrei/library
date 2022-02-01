package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Role;
import by.library.yurueu.entity.User;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.RoleRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    private static final String ID_COLUMN = "id";
    private static final String ROLE_NAME_COLUMN = "role_name";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM roles WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM roles";
    private static final String INSERT_QUERY =
            "INSERT INTO roles (role_name) VALUES (?)";
    private static final String UPDATE_QUERY =
            "UPDATE roles SET role_name=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM roles WHERE id=?";

    private static final String DELETE_ROLE_LINKS_QUERY = "DELETE FROM user_role_links WHERE role_id=?";

    private final DataSource dataSource;

    public RoleRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Role findById(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? construct(resultSet) : null;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Role was not found[" + ex.getMessage() + "]");
        }
    }

    private Role construct(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(ID_COLUMN));
        role.setRoleName(resultSet.getString(ROLE_NAME_COLUMN));
        return role;
    }

    @Override
    public List<Role> findAll() throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Role> roles = new ArrayList<>();
                while (resultSet.next()) {
                    roles.add(construct(resultSet));
                }
                return roles;
            }
        } catch (Exception ex) {
            throw new RepositoryException("Roles were not found[" + ex.getMessage() + "]");
        }
    }

    @Override
    public Role add(Role role) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            settingPreparedStatement(preparedStatement, role);
            int value = preparedStatement.executeUpdate();

            if (value == 1) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        role.setId(resultSet.getLong(1));
                    }
                }
            }
            return role;
        } catch (Exception ex) {
            throw new RepositoryException("Role was not added[" + ex.getMessage() + "]");
        }
    }

    private void settingPreparedStatement(PreparedStatement preparedStatement, Role role) throws SQLException {
        preparedStatement.setString(1, role.getRoleName());
    }

    @Override
    public boolean update(Role role) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            settingPreparedStatement(preparedStatement, role);
            preparedStatement.setLong(2, role.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new RepositoryException("Role was not updated[" + ex.getMessage() + "]");
        }
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)
        ) {
            preparedStatement.setLong(1,id);
            try{
                connection.setAutoCommit(false);
                deleteUserRoleLinks(connection, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            throw new RepositoryException("Role was not deleted [" + ex.getMessage() + "]");
        }
        return true;
    }
    private void deleteUserRoleLinks(Connection connection, Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_LINKS_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}

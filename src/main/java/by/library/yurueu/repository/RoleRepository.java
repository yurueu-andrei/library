package by.library.yurueu.repository;

import by.library.yurueu.entity.Role;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface RoleRepository {
    Role findById(Long id) throws RepositoryException;

    List<Role> findAll() throws RepositoryException;

    Role add(Role role) throws RepositoryException;

    boolean update(Role role) throws RepositoryException;

    boolean delete(Long id) throws RepositoryException;
}

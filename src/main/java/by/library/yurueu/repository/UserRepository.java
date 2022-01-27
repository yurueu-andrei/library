package by.library.yurueu.repository;

import by.library.yurueu.entity.User;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface UserRepository {
    User findById(Long id) throws RepositoryException;
    List<User> findAll() throws RepositoryException;
    User add(User user) throws RepositoryException;
    boolean update(User user) throws RepositoryException;
    boolean delete(Long id) throws RepositoryException;
}
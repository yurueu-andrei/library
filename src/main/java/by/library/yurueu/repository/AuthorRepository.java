package by.library.yurueu.repository;

import by.library.yurueu.entity.Author;
import by.library.yurueu.entity.Book;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface AuthorRepository {
    Author findById(Long id) throws RepositoryException;

    List<Author> findAll() throws RepositoryException;

    Author add(Author author) throws RepositoryException;

    boolean update(Author author) throws RepositoryException;

    boolean delete(Long id) throws RepositoryException;
}

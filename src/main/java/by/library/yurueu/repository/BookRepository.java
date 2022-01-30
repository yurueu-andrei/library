package by.library.yurueu.repository;

import by.library.yurueu.entity.Book;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;


public interface BookRepository {
    Book findById(Long id) throws RepositoryException;
    List<Book> findAll() throws RepositoryException;
    Book add(Book book) throws RepositoryException;
    boolean update(Book book) throws RepositoryException;
    boolean delete(Long id) throws RepositoryException;
}
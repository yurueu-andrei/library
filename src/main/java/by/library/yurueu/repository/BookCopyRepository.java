package by.library.yurueu.repository;

import by.library.yurueu.entity.BookCopy;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface BookCopyRepository {
    BookCopy findById(Long id) throws RepositoryException;

    List<BookCopy> findAll() throws RepositoryException;

    BookCopy add(BookCopy bookCopy) throws RepositoryException;

    boolean update(BookCopy bookCopy) throws RepositoryException;

    boolean delete(Long id) throws RepositoryException;
}

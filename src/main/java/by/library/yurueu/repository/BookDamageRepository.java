package by.library.yurueu.repository;

import by.library.yurueu.entity.BookDamage;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface BookDamageRepository {
    BookDamage findById(Long id) throws RepositoryException;

    List<BookDamage> findAll() throws RepositoryException;

    BookDamage add(BookDamage bookDamage) throws RepositoryException;

    boolean update(BookDamage bookDamage) throws RepositoryException;

    boolean delete(Long id) throws RepositoryException;
}

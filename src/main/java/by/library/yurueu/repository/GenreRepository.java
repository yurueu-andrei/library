package by.library.yurueu.repository;

import by.library.yurueu.entity.Genre;
import by.library.yurueu.exception.RepositoryException;

import java.util.List;

public interface GenreRepository {
    Genre findById(Long id) throws RepositoryException;

    List<Genre> findAll() throws RepositoryException;

    Genre add(Genre genre) throws RepositoryException;

    boolean update(Genre genre) throws RepositoryException;

    boolean delete(Long id) throws RepositoryException;
}
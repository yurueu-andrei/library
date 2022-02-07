package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Genre;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import by.library.yurueu.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class GenreRepositoryImplTest extends BaseRepositoryTest {

    private final GenreRepository genreRepository;

    public GenreRepositoryImplTest() {
        genreRepository = new GenreRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstGenreInDB() throws RepositoryException {
        //given
        Genre expected = findGenreForFindById();

        //when
        Genre actual = genreRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllGenres() throws RepositoryException {
        //given
        List<Genre> expected = findGenresForFindAll();

        //when
        List<Genre> actual = genreRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedGenre() throws RepositoryException {
        //given
        Genre expected = new Genre("tale");
        //when
        Genre actual = genreRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateGenre() throws RepositoryException {
        //given
        Genre genre = new Genre(2L, "tale");

        // when
        boolean isUpdated = genreRepository.update(genre);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteGenre() throws RepositoryException {
        //given
        Long genreId = 1L;

        // when
        boolean isDeleted = genreRepository.delete(genreId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
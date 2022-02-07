package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Author;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.AuthorRepository;
import by.library.yurueu.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class AuthorRepositoryImplTest extends BaseRepositoryTest {

    private final AuthorRepository authorRepository;

    public AuthorRepositoryImplTest() {
        authorRepository = new AuthorRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstAuthorInDB() throws RepositoryException {
        //given
        Author expected = findAuthorForFindById();

        //when
        Author actual = authorRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllAuthor() throws RepositoryException {
        //given
        List<Author> expected = findAuthorsForFindAll();

        //when
        List<Author> actual = authorRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedAuthor() throws RepositoryException {
        //given
        Author expected = new Author("esdf", "afssd", LocalDate.of(1999, 8, 8), "imagepath");
        //when
        Author actual = authorRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateAuthor() throws RepositoryException {
        //given
        Author author = new Author(2L, "esdf", "afssd", LocalDate.of(1999, 8, 8), "imagepath");

        // when
        boolean isUpdated = authorRepository.update(author);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteAuthor() throws RepositoryException {
        //given
        Long authorId = 1L;

        // when
        boolean isDeleted = authorRepository.delete(authorId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Author;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class AuthorRepositoryImplTest extends BaseRepositoryTest {
    private final AuthorRepositoryImpl authorRepository;

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
        Author expected = Author.builder().id(6L).firstName("Mikhail").lastName("Lermontov").birthDate(LocalDate.of(1999, 8, 8)).imagePath("image path").build();
        Author actual = Author.builder().firstName("Mikhail").lastName("Lermontov").birthDate(LocalDate.of(1999, 8, 8)).imagePath("image path").build();

        //when
        actual = authorRepository.add(actual);

        //then
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, authorRepository.findById(expected.getId()));
    }

    @Test
    void updateTest_shouldUpdateAuthor() throws RepositoryException {
        //given
        Author author = Author.builder().id(2L).firstName("Mikhail").lastName("Lermontov").birthDate(LocalDate.of(1998, 8, 8)).imagePath("image path").build();

        // when
        boolean isUpdated = authorRepository.update(author);

        //then
        Assertions.assertTrue(isUpdated);
        Assertions.assertEquals(author, authorRepository.findById(author.getId()));
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
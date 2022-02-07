package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.BookDamage;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import by.library.yurueu.repository.BookDamageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BookDamageRepositoryImplTest extends BaseRepositoryTest {

    private final BookDamageRepository bookDamageRepository;

    public BookDamageRepositoryImplTest() {
        bookDamageRepository = new BookDamageRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstBookDamageInDB() throws RepositoryException {
        //given
        BookDamage expected = findBookDamageForFindById();

        //when
        BookDamage actual = bookDamageRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllBookDamage() throws RepositoryException {
        //given
        List<BookDamage> expected = findBookDamageForFindAll();

        //when
        List<BookDamage> actual = bookDamageRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedBookDamage() throws RepositoryException {
        //given
        BookDamage expected = new BookDamage("imagepath", 1L,2L,3L);
        //when
        BookDamage actual = bookDamageRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateBookDamage() throws RepositoryException {
        //given
        BookDamage bookDamage = new BookDamage(2L, "imagepath", 2L,2L,2L);

        // when
        boolean isUpdated = bookDamageRepository.update(bookDamage);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteBookDamage() throws RepositoryException {
        //given
        Long bookDamageId = 1L;

        // when
        boolean isDeleted = bookDamageRepository.delete(bookDamageId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
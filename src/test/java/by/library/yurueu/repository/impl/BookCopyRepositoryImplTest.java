package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.BookCopy;
import by.library.yurueu.entity.BookCopyStatus;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import by.library.yurueu.repository.BookCopyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class BookCopyRepositoryImplTest extends BaseRepositoryTest {

    private final BookCopyRepository bookCopyRepository;

    public BookCopyRepositoryImplTest() {
        bookCopyRepository = new BookCopyRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstBookCopyInDB() throws RepositoryException {
        //given
        BookCopy expected = findBookCopyForFindById();

        //when
        BookCopy actual = bookCopyRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllBookCopy() throws RepositoryException {
        //given
        List<BookCopy> expected = findBookCopiesForFindAll();

        //when
        List<BookCopy> actual = bookCopyRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedBookCopy() throws RepositoryException {
        //given
        BookCopy expected = new BookCopy(BookCopyStatus.AVAILABLE, LocalDate.of(2000,1,1), 70, 13, 2L);
        //when
        BookCopy actual = bookCopyRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateBookCopy() throws RepositoryException {
        //given
        BookCopy bookCopy = new BookCopy(2L, BookCopyStatus.AVAILABLE, LocalDate.of(2000,1,1), 70, 13, 2L);

        // when
        boolean isUpdated = bookCopyRepository.update(bookCopy);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteBookCopy() throws RepositoryException {
        //given
        Long bookCopyId = 1L;

        // when
        boolean isDeleted = bookCopyRepository.delete(bookCopyId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
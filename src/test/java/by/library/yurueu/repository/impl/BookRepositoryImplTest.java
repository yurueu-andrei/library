package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Book;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import by.library.yurueu.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BookRepositoryImplTest extends BaseRepositoryTest {

    private final BookRepository bookRepository;

    public BookRepositoryImplTest() {
        bookRepository = new BookRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstBookInDB() throws RepositoryException {
        //given
        Book expected = findBookForFindById();

        //when
        Book actual = bookRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllBooks() throws RepositoryException {
        //given
        List<Book> expected = findBooksForFindAll();

        //when
        List<Book> actual = bookRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedBook() throws RepositoryException {
        //given
        Book expected = new Book("asd", 12, "imagepath");
        //when
        Book actual = bookRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateBook() throws RepositoryException {
        //given
        Book genre = new Book(2L, "asd", 12, "imagepath");

        // when
        boolean isUpdated = bookRepository.update(genre);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteBook() throws RepositoryException {
        //given
        Long bookId = 1L;

        // when
        boolean isDeleted = bookRepository.delete(bookId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
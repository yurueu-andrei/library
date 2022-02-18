package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Book;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BookRepositoryImplTest extends BaseRepositoryTest {

    private final BookRepositoryImpl bookRepository;

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
        Book expected = Book.builder().title("asd").pagesNumber(12).imagePath("imagepath").build();

        //when
        Book actual = bookRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateBook() throws RepositoryException {
        //given
        Book book = Book.builder().id(2L).title("asd").pagesNumber(12).imagePath("imagepath").build();

        // when
        boolean isUpdated = bookRepository.update(book);

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
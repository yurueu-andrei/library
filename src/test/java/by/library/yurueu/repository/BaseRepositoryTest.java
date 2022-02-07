package by.library.yurueu.repository;

import by.library.yurueu.entity.*;
import by.library.yurueu.service.FlywayService;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.library.yurueu.property.Property.*;

public abstract class BaseRepositoryTest {
    private final DataSource dataSource;
    private final FlywayService flywayService;

    private List<User> users;
    private List<Role> roles;
    private List<Order> orders;
    private List<Genre> genres;
    private List<Book> books;
    private List<BookDamage> bookDamage;
    private List<BookCopy> bookCopies;
    private List<Author> authors;

    public BaseRepositoryTest() {
        dataSource = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);
        flywayService = new FlywayService(H2_URL, H2_USER, H2_PASSWORD, MIGRATION_LOCATION);
        fillUsers();
        fillRoles();
        fillOrders();
        fillGenres();
        fillBooks();
        fillBookDamage();
        fillBookCopies();
        fillAuthors();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @BeforeEach
    public void migrate() {
        flywayService.migrate();
    }

    @AfterEach
    public void clean() {
        flywayService.clean();
    }

    private void fillUsers() {
        users = new ArrayList<>() {{
            add(new User(1L,"vlad", "kaliaha", "1111", "email1", "address1", LocalDate.of(2005, 6, 6)));
            add(new User(2L,"andrei", "yurueu", "2222", "email2", "address2", LocalDate.of(2001, 6, 6)));
            add(new User(3L,"yaroslav", "vasilevski", "3333", "email3", "address3", LocalDate.of(1998, 6, 6)));
            add(new User(4L,"nastya", "yurkova", "4444", "email4", "address4", LocalDate.of(1999, 6, 6)));
            add(new User(5L,"alexander", "kuprijanenko", "5555", "email5", "address5", LocalDate.of(1996, 6, 6)));
        }};
    }

    public User findUserForFindById() {
        return users.get(0);
    }

    public List<User> findUsersForFindAll() {
        return users;
    }

    private void fillRoles() {
        roles = new ArrayList<>() {{
            add(new Role(1L, "admin"));
            add(new Role(2L, "user"));
        }};
    }

    public Role findRoleForFindById() {
        return roles.get(0);
    }

    public List<Role> findRolesForFindAll() {
        return roles;
    }

    private void fillOrders() {
        orders = new ArrayList<>() {{
            add(new Order(1L, OrderStatus.NEW, LocalDate.of(1998,6,6), LocalDate.of(1998,6,6), 243,1L));
            add(new Order(2L, OrderStatus.NEW, LocalDate.of(1998,6,6), LocalDate.of(1998,6,6), 21, 1L));
            add(new Order(3L, OrderStatus.NEW, LocalDate.of(1998,6,6), LocalDate.of(1998,6,6), 253,1L));
            add(new Order(4L, OrderStatus.NEW, LocalDate.of(1998,6,6), LocalDate.of(1998,6,6), 273,3L));
            add(new Order(5L, OrderStatus.NEW, LocalDate.of(1998,6,6), LocalDate.of(1998,6,6), 238,4L));
        }};
    }

    public Order findOrderForFindById() {
        return orders.get(0);
    }

    public List<Order> findOrdersForFindAll() {
        return orders;
    }

    private void fillGenres() {
        genres = new ArrayList<>() {{
            add(new Genre(1L, "novel"));
            add(new Genre(2L, "novel in verse"));
            add(new Genre(3L, "epic novel"));
            add(new Genre(4L, "story"));
        }};
    }

    public Genre findGenreForFindById() {
        return genres.get(0);
    }

    public List<Genre> findGenresForFindAll() {
        return genres;
    }

    private void fillBooks() {
        books = new ArrayList<>() {{
            add(new Book(1L,"War and peace", 1365, "imagepath"));
            add(new Book(2L,"The Master and Margarita", 638, "imagepath"));
            add(new Book(3L,"Idiot", 496, "imagepath"));
            add(new Book(4L,"The old man and the sea", 153, "imagepath"));
            add(new Book(5L,"Eugene Onegin", 462, "imagepath"));
        }};
    }

    public Book findBookForFindById() {
        return books.get(0);
    }

    public List<Book> findBooksForFindAll() {
        return books;
    }

    private void fillBookDamage() {
        bookDamage = new ArrayList<>() {{
            add(new BookDamage(1L,"imagepath", 1L, 1L, 3L));
            add(new BookDamage(2L,"imagepath", 1L, 1L, 2L));
            add(new BookDamage(3L,"imagepath", 3L, 4L, 1L));
            add(new BookDamage(4L,"imagepath", 4L, 5L, 5L));
        }};
    }

    public BookDamage findBookDamageForFindById() {
        return bookDamage.get(0);
    }

    public List<BookDamage> findBookDamageForFindAll() {
        return bookDamage;
    }

    private void fillBookCopies() {
        bookCopies = new ArrayList<>() {{
            add(new BookCopy(1L,BookCopyStatus.AVAILABLE, LocalDate.of(2019,3,1), 1365, 150, 1L));
            add(new BookCopy(2L,BookCopyStatus.AVAILABLE, LocalDate.of(2020,6,1), 1638, 210, 2L));
            add(new BookCopy(3L,BookCopyStatus.AVAILABLE, LocalDate.of(2021,8,4), 2496, 225, 2L));
            add(new BookCopy(4L,BookCopyStatus.AVAILABLE, LocalDate.of(2017,10,10), 937, 128, 5L));
            add(new BookCopy(5L,BookCopyStatus.AVAILABLE, LocalDate.of(2020,6,2), 1007, 311, 3L));
        }};
    }

    public BookCopy findBookCopyForFindById() {
        return bookCopies.get(0);
    }

    public List<BookCopy> findBookCopiesForFindAll() {
        return bookCopies;
    }

    private void fillAuthors() {
        authors = new ArrayList<>() {{
            add(new Author(1L,"Lev", "Tolstoy", LocalDate.of(1879,4,4), "imagepath"));
            add(new Author(2L,"Ernest", "Hemingway", LocalDate.of(1903,7,7), "imagepath"));
            add(new Author(3L,"Mikhail", "Bulgakov", LocalDate.of(1885,10,10), "imagepath"));
            add(new Author(4L,"Alexander", "Pushkin", LocalDate.of(1852,2,2), "imagepath"));
            add(new Author(5L,"Fedor", "Dostoevski", LocalDate.of(1845,1,1), "imagepath"));
        }};
    }

    public Author findAuthorForFindById() {
        return authors.get(0);
    }

    public List<Author> findAuthorsForFindAll() {
        return authors;
    }

}
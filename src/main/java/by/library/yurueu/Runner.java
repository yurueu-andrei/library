package by.library.yurueu;

import by.library.yurueu.entity.User;
import by.library.yurueu.repository.UserRepository;
import by.library.yurueu.repository.impl.UserRepositoryImpl;
import by.library.yurueu.service.FlywayService;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static by.library.yurueu.service.Property.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        DataSource dataSource = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        UserRepository userRepository = new UserRepositoryImpl(dataSource);

        System.out.println(userRepository.findById(1L));
        userRepository.delete(1L);
        System.out.println(userRepository.findById(1L));

//        User user = new User(21L,"Andrei", "Yurueu", "31656541", "andreyyurueu1@mail.ru", "Vitebskaja 45/3", LocalDate.of(2003,04,01));
//

    }
}
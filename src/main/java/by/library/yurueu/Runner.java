package by.library.yurueu;

import by.library.yurueu.entity.User;
import by.library.yurueu.repository.UserRepository;
import by.library.yurueu.repository.impl.UserRepositoryImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception {
        User user = new User(21L,"Andrei", "Yurueu", "31656541", "andreyyurueu1@mail.ru", "Vitebskaja 45/3", LocalDate.of(2003,04,01));
        UserRepository userRepository = new UserRepositoryImpl();

    }

    private static void toLocalDate() {
        Date date = new Date(3454584332231L);
        LocalDate localDate = date.toLocalDate();//из даты в локал дэйт
        Date fromLocalDate = Date.valueOf(localDate);
        System.out.println(date);
        System.out.println(localDate);
        System.out.println(fromLocalDate);
    }
}
package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.User;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class UserRepositoryImplTest extends BaseRepositoryTest {
    private final UserRepositoryImpl userRepository;

    public UserRepositoryImplTest() {
        userRepository = new UserRepositoryImpl(getDataSource());
    }

    @Test
    public void findByIdTest_shouldReturnTheFirstUserInDB() throws RepositoryException {
        //given
        User expected = findUserForFindById();

        //when
        User actual = userRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllUsers() throws RepositoryException {
        //given
        List<User> expected = findUsersForFindAll();

        //when
        List<User> actual = userRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedUser() throws RepositoryException {
        //given
        User expected = User.builder().firstName("sergei").lastName("take").passportNumber("1645").email("email235").address("address123").birthDate(LocalDate.of(2002, 5, 5)).build();

        //when
        User actual = userRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateUser() throws RepositoryException {
        //given
        User user = User.builder().id(2L).firstName("sergei").lastName("take").passportNumber("1645").email("email235").address("address123").birthDate(LocalDate.of(2002, 5, 5)).build();

        // when
        boolean isUpdated = userRepository.update(user);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    public void deleteTest_shouldDeleteUser() throws RepositoryException {
        //given
        Long userId = 1L;

        // when
        boolean isDeleted = userRepository.delete(userId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
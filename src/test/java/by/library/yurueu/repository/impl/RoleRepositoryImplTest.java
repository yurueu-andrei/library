package by.library.yurueu.repository.impl;

import by.library.yurueu.entity.Role;
import by.library.yurueu.exception.RepositoryException;
import by.library.yurueu.repository.BaseRepositoryTest;
import by.library.yurueu.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RoleRepositoryImplTest extends BaseRepositoryTest {
    private final RoleRepository roleRepository;

    public RoleRepositoryImplTest() {
        roleRepository = new RoleRepositoryImpl(getDataSource());
    }

    @Test
    void findByIdTest_shouldReturnTheFirstRoleInDB() throws RepositoryException {
        //given
        Role expected = findRoleForFindById();

        //when
        Role actual = roleRepository.findById(expected.getId());

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTest_shouldReturnListOfAllRoles() throws RepositoryException {
        //given
        List<Role> expected = findRolesForFindAll();

        //when
        List<Role> actual = roleRepository.findAll();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest_shouldReturnAddedRole() throws RepositoryException {
        //given
        Role expected = new Role("superUser");

        //when
        Role actual = roleRepository.add(expected);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest_shouldUpdateRole() throws RepositoryException {
        //given
        Role role = new Role(2L, "wrg");

        // when
        boolean isUpdated = roleRepository.update(role);

        //then
        Assertions.assertTrue(isUpdated);
    }

    @Test
    void deleteTest_shouldDeleteRole() throws RepositoryException {
        //given
        Long roleId = 1L;

        // when
        boolean isDeleted = roleRepository.delete(roleId);

        //then
        Assertions.assertTrue(isDeleted);
    }
}
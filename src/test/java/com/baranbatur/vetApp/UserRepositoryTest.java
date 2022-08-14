package com.baranbatur.vetApp;

import com.baranbatur.vetApp.model.Role;
import com.baranbatur.vetApp.model.User;
import com.baranbatur.vetApp.repository.IRoleRepository;
import com.baranbatur.vetApp.repository.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void testAddRoleToNewUser() {
        User user = new User();

        user.setUsername("baturbarann");
        user.setPassword("12345678");
        Role role = roleRepository.findByName("User");
        user.addRole(role);
        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser.getRoles().size()).isEqualTo(1);

    }

    @Test
    public void testAddRolesToExistingUser() {
        User user = userRepository.findById(2L).get();

        Role roleUser = roleRepository.findByName("Admin");
        user.addRole(roleUser);

        Role roleAdmin = new Role(2L);
        user.addRole(roleAdmin);
        User savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser.getRoles().size()).isEqualTo(2);

    }
}

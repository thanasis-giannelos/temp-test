package com.sakis.temptest.repository;

import com.sakis.temptest.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    // interface to be tested
    @Autowired
    private UserRepository userRepository;

    // test data
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("kakao", "dneio", "d3d3d3", "male");
    }

    @Test
    void givenExistingUserFirstNameAndLastName_whenCheckUserExists_thenReturnTrue() {

        //given
        userRepository.save(user);

        //when
        Boolean userExists = userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName());

        //then
        Assertions.assertThat(userExists).isTrue();
    }

    @Test
    void givenNonExistingUserFirstNameAndLastName_whenCheckUserExists_thenReturnFalse() {

        //given
        userRepository.save(user);

        //when
        Boolean userExists = userRepository.existsByFirstNameAndLastName(user.getFirstName()+'k', user.getLastName());

        //then
        Assertions.assertThat(userExists).isFalse();
    }
}

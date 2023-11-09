package com.sakis.temptest.repository;

import com.sakis.temptest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByFirstNameAndLastName(String firstname, String lastname);
}

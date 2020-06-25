package com.example.effort.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User u set u.username = ?1 where u.id = ?#{ principal?.id }")
    void editUsername(String newUsername);

    @Modifying
    @Transactional
    @Query("delete from User u where u.id = ?#{ principal?.id }")
    void deleteUser();

}

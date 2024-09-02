package com.example.lap12.Repository;

import com.example.lap12.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

    User findUserById(int id);

    User findUserByUsername(String username);
}

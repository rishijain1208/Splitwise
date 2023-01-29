package com.example.splitwise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.splitwise.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User save(User user);

    User findUsersById(Long id);

    List<User> findUsersByUserNameAndPhoneNumber(String userName,String phoneNumber);
}

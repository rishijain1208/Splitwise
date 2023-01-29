package com.example.splitwise.services.user;

import com.example.splitwise.models.User;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.services.passwordEncoder.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User RegisterUser(String userName,String password,String phoneNumber)
    {
        User user = new User();
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPassword(passwordEncoder.getEncodedPassword(password));

        User user1 = userRepository.save(user);
        return user1;
    }

    public User updateProfile(long userId,String newPwd)
    {
        User user = userRepository.findUsersById(userId);
        user.setHashedPassword(newPwd);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    private void logIn(long userId,String pwd)
    {
        User user = userRepository.findUsersById(userId);
        if(passwordEncoder.matches(pwd,user.getHashedPassword()))
        {
            System.out.println("Login Successfully");
        }
        else
        {
            System.out.println("Wrong Password");
        }
    }
}

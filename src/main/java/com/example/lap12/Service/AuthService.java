package com.example.lap12.Service;

import com.example.lap12.Api.ApiException;
import com.example.lap12.Model.User;
import com.example.lap12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllUsers() {
        return authRepository.findAll();
    }

    public void Register(User user) {
        // user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public void updateUser(int id,User user) {
        User user1 = authRepository.findUserById(id);
        if(user1==null){
            throw new ApiException("wrong username or password ");
        }
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        authRepository.save(user1);
    }

    public void deleteUser(int id) {
        User user = authRepository.findUserById(id);
        if(user==null){
            throw new ApiException("wrong username or password ");
        }
        authRepository.delete(user);
    }
}

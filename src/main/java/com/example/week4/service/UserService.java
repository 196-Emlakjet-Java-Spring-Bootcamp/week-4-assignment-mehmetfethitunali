package com.example.week4.service;
import com.example.week4.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean createUser ();

    List<User> getAll();

}

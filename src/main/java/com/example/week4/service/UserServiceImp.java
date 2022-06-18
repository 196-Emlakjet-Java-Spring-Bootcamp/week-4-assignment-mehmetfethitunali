package com.example.week4.service;
import com.example.week4.dao.UserRepository;
import com.example.week4.entity.Users;
import com.example.week4.rabbit.producer.RabbitProducer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser() {
        try {
            Users users = new Users();
            rabbitProducer.sendToQueue(users);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }
}

package com.example.week4.service;
import com.example.week4.dao.UserRepository;
import com.example.week4.entity.User;
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
            User user = new User();
            rabbitProducer.sendToQueue(user);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}

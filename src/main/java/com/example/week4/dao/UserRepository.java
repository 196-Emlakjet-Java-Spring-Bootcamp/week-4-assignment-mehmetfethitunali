package com.example.week4.dao;
import com.example.week4.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}

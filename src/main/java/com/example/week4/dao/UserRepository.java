package com.example.week4.dao;
import com.example.week4.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select * from users order by random() limit 1", nativeQuery = true)
    Users getRandomUser();
}

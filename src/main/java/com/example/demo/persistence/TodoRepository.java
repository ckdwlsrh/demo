package com.example.demo.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.model.TodoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{

    @Query(value = "select * from todo t where t.userid = ?1", nativeQuery = true)
    List<TodoEntity> findByUserid(String userid);
}
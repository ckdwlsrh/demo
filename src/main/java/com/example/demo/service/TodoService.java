package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {

        //todoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").userid("hello").build();
        
        //삽입
        repository.save(entity);
        
        //조회
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        
        //반환
        return savedEntity.getTitle();
    }
}

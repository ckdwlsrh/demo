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
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);

        TodoEntity savedEntity = repository.findByUserId(entity.getId()).get(0);
    
        return savedEntity.getTitle();
    }
}

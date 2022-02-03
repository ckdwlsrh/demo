package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public List<TodoEntity> create(final TodoEntity entity) {
        //validation
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserid() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }

        repository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getId());


        return repository.findByUserid(entity.getUserid());
    }
}

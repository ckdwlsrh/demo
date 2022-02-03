package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired // 알아서 빈을 찾은 다음 그 빈을 이 인스턴스 멤버 변수에 연결해라라는뜻
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {

        String str = service.testService();

        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        return ResponseEntity.ok().body(response);
    }
}

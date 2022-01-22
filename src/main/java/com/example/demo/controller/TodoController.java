package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("todo")
public class TodoController {
    @GetMapping("/list")
    public String TodoList(@RequestParam(required = false) int id) {
        return "hi";
    }
    
}

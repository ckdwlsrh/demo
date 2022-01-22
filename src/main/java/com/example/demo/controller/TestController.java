package com.example.demo.controller;

import com.example.demo.dto.TestRequestBodyDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping
    public String testController() {
        return "hello world";
    }
    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "hello world id " + id;
    }
    @GetMapping("/testRequestParam")
    public String testControllerWithRequestParam(@RequestParam(required = false) int id) {
        return "hello world id" + id;
    }
    @GetMapping("/testRequestBody")
    public String testControllerWithRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        return "hello world id " + testRequestBodyDTO.getId() + "message : " + testRequestBodyDTO.getMessage();
    }
}

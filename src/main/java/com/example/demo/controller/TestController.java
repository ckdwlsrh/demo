package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;

import org.springframework.http.ResponseEntity;
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

    @GetMapping("/testResponseDTO")
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("hello im fine thank you");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() { // 이걸로 헤더 조작 가능 기능은 DTO랑 같음
        List<String> list = new ArrayList<>();
        list.add("hello im fine 400");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(response);
    }
}

package com.example.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class TodoController {

    @GetMapping({"/", "/home",""})
    public String showHomePage() {
        return "index";
    }


}

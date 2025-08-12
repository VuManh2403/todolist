package com.example.todolist.controller;

import com.example.todolist.entity.TodoEntity;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping({"/", "/home",""})
    public String showHomePage(Model model) {
        model.addAttribute("todos",todoRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title) {
        TodoEntity todoEntity = TodoEntity.builder()
                .title(title)
                .completed(false)
                .build();
        todoRepository.save(todoEntity);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update (@PathVariable long id) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Todo not found with id: "+id));
        todoEntity.setCompleted(true);
        todoRepository.save(todoEntity);
        return "redirect:/";

    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable long id) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Todo not found with id: "+id));
        todoRepository.delete(todoEntity);
        return "redirect:/";

    }
}

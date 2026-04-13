package com.example.deploy6.controller;

import com.example.deploy6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> userList = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "20230140088".equals(password)) {
            return "redirect:/home";
        }
        model.addAttribute("error", "Username atau Password salah!");
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("users", userList);
        return "home";
    }

    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(User user) {
        userList.add(user);
        return "redirect:/home";
    }
}

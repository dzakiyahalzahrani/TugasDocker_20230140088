package com.example.deploy6.controller;

import com.example.deploy6.model.User;
import com.example.deploy6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    // Kita memanggil jembatan ke database di sini
    @Autowired
    private UserRepository userRepository;

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
        // Mengambil seluruh data mahasiswa langsung dari database
        model.addAttribute("users", userRepository.findAll());
        return "home";
    }

    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(User user) {
        // Menyimpan data yang diisi dari form ke dalam database
        userRepository.save(user);
        return "redirect:/home";
    }
}
package com.example.simplechatprogramfinal.UIController;

import com.example.simplechatprogramfinal.Entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("")
    public String homepage(){
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new Users());
        return "login";
    }
}

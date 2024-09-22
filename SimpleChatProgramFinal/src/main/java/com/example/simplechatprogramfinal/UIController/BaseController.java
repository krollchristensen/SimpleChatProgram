package com.example.simplechatprogramfinal.UIController;

import com.example.simplechatprogramfinal.Entity.Users;
import com.example.simplechatprogramfinal.Usecase.ChatClient;
import com.example.simplechatprogramfinal.Usecase.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/login")
    public String loginUser(Users user, HttpSession session){
        try{
            String serverHost = "localhost";
            int serverPort = 5000;

            ChatClient chatClient = new ChatClient(serverHost, serverPort);
            new Thread(chatClient::startClient).start();
        } catch (Exception e){
            e.printStackTrace();
        }
        session.setAttribute("user", user);
        return "redirect:/home";
    }
}

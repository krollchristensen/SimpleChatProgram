package com.example.simplechatprogramfinal.UIController;

import com.example.simplechatprogramfinal.Entity.Users;
import com.example.simplechatprogramfinal.Usecase.ChatClient;
import com.example.simplechatprogramfinal.Usecase.ReadServerConfigFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class BaseController {
    @GetMapping("")
    public String homepage(){
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) throws IOException {
        /*ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        String serverHost = readServerConfigFile.getServerHost();
        int serverPort = readServerConfigFile.getServerPort();
        ChatClient chatClient = new ChatClient(serverHost, serverPort);
        new Thread(chatClient::startClient).start();*/
        model.addAttribute("user", new Users());
        return "login";
    }
}

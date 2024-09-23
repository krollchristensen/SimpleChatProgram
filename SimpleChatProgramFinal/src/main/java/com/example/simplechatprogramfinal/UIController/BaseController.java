package com.example.simplechatprogramfinal.UIController;

import com.example.simplechatprogramfinal.Entity.Users;
import com.example.simplechatprogramfinal.Usecase.ChatClient;
import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Usecase.ReadServerConfigFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Controller
public class BaseController {
    private static final Logger logger = Logger.getLogger(BaseController.class.getName());



    @Autowired
    private UserRepository userRepository;

    private ConcurrentHashMap<String, Thread> activeClients = new ConcurrentHashMap<>();

    @GetMapping("/home")
    public String homepage(Model model, Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();

            if (!activeClients.containsKey(email)) {
                logger.info("User {} is accessing the homepage. Starting ChatClient..." + email);

                ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
                String serverHost = readServerConfigFile.getServerHost();
                int serverPort = readServerConfigFile.getServerPort();

                Thread clientThread = new Thread(() -> {
                    ChatClient.startChatClient(serverHost, serverPort);
                    logger.info("ChatClient for user {} started successfully." + email);
                });

                clientThread.start();
                activeClients.put(email, clientThread);
            } else {
                logger.info("ChatClient for user {} is already running." + email);
            }

            model.addAttribute("currentUser", email);
            model.addAttribute("activeUsers", activeClients.keySet());
        }
        return "home";
    }

    @GetMapping("/selectUser")
    public String selectUser(@RequestParam String email, Model model, Authentication authentication) {
        Users user = userRepository.getUserByEmail(email);
        model.addAttribute("selectedUser", user);

        if (authentication != null) {
            String currentEmail = authentication.getName();
            model.addAttribute("activeUsers", activeClients.keySet());
            model.addAttribute("currentUser", currentEmail);
        }

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) throws IOException {
        model.addAttribute("user", new Users());
        return "login";
    }
}


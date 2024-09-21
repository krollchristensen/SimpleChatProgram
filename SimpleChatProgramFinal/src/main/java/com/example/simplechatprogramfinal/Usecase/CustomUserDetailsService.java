package com.example.simplechatprogramfinal.Usecase;
import org.springframework.security.core.userdetails.User;
import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.getUserByEmail(email);

        if (users == null) {
            System.out.println("No such user with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        System.out.println("it worked" + email + users.getPassword());
        return User.withUsername(users.getEmail())
                .password(users.getPassword())
                .build();
    }
}

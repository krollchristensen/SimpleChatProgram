package com.example.simplechatprogramfinal.Config;
import com.example.simplechatprogramfinal.Usecase.ChatClient;
import com.example.simplechatprogramfinal.Usecase.CustomUserDetailsService;
import com.example.simplechatprogramfinal.Usecase.ReadServerConfigFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * Security configuration class.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public SecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers(
                                            new OrRequestMatcher(
                                                    new AntPathRequestMatcher("/login"),
                                                    new AntPathRequestMatcher("/home")
                                            )
                                    ).permitAll()
                                    .requestMatchers(new AntPathRequestMatcher("/sendMessage")).authenticated()

                                    .anyRequest().authenticated()
                            
                    )
                    .formLogin(formLogin ->
                            formLogin
                                    .loginPage("/login")
                                    .usernameParameter("email")
                                    .defaultSuccessUrl("/home")
                                    .permitAll()
                    )
                    .logout(logout ->
                            logout
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/LoginPage?logout")
                    );
          
            return http.build();
        }


        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }
}
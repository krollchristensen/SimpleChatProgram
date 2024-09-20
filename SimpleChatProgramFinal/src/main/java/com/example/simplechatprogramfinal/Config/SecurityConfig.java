package com.example.simplechatprogramfinal.Config;
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

    //  private final CustomUserDetailsService customUserDetailsService;
 //   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructs a new SecurityConfig.
     *
     * @param customUserDetailsService the CustomUserDetailsService instance
     * @param bCryptPasswordEncoder the BCryptPasswordEncoder instance
     */
   /* public SecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    */

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity instance
     * @return the SecurityFilterChain instance
     * @throws Exception in case of configuration errors
     */
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
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
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

    /**
     * Configures the authentication manager with custom user details service and password encoder.
     *
     * @param auth the AuthenticationManagerBuilder instance
     * @throws Exception in case of configuration errors
     */
   /* protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    */
}
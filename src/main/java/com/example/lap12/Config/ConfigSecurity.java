package com.example.lap12.Config;

import com.example.lap12.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/update").permitAll()
                .requestMatchers("api/v1/blog/add-blog").hasAuthority("USER")
                .requestMatchers("api/v1/blog/update-blog/").hasAuthority("USER")
                .requestMatchers("api/v1/blog/delete-blog/").hasAuthority("USER")
                .requestMatchers("api/v1/blog/get-by-id/").hasAuthority("USER")
                .requestMatchers("api/v1/blog/get-by-title/").hasAuthority("USER")
                .requestMatchers("api/v1/blog/get-user-blogs").hasAuthority("USER")
                .requestMatchers("/api/v1/auth/getallusers").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/delete").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blog/get-all-blogs").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
//              .hasAnyAuthority("USER")


    }
}

package com.chunjae.test03.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    //passwordEncoder 빈 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //공통 접근 설정
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/","/login.do","/join.do").permitAll()
                .requestMatchers("/css/**","/js/**","/image/**").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .requestMatchers("/emp/**").hasAnyRole("ADMIN","EMP")
                .anyRequest().authenticated();

        //로그인 설정
        http.formLogin()
                .loginPage("/login.do")
                .loginProcessingUrl("/loginByName.do")
                .usernameParameter("name")
                .passwordParameter("pw")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login.do");
        //로그아웃 설정
        http.logout()
                .logoutUrl("/logout.do")
                .logoutSuccessUrl("/");

        return http.build();

    }
}

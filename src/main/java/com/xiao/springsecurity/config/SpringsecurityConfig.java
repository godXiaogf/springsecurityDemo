package com.xiao.springsecurity.config;

import com.xiao.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringsecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserService userService;

    //配置加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //spring security 内置加密算法
    }

    //认证用户的来源
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
/*
        auth.inMemoryAuthentication().withUser("xiao")
                                    .password("{noop}123")  //noop 表示没有加密
                                    .roles("NORMAL");

        auth.inMemoryAuthentication().withUser("admin")
                                    .password("{noop}123")
                                    .roles("NORMAL")
                                    .authorities("add","del");
*/

    }

    //Spring Security配置
    public void configure(HttpSecurity hs) throws Exception {
        hs.authorizeRequests()
                .antMatchers("/index","/css/**").permitAll()
                //.antMatchers("/add","/del").hasAnyAuthority("add","del")
                .antMatchers("/**").hasAnyRole("NORMAL")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .successForwardUrl("/login")
                .failureForwardUrl("/fail")

                .and()
                .csrf()
                .disable();
    }
}

package com.baranbatur.vetApp;

import com.baranbatur.vetApp.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/registration")
                .permitAll().
                antMatchers("/animal/delete/**").hasAuthority("Admin")
                .antMatchers("/owner/delete/**").hasAuthority("Admin")
                .antMatchers("/animal/edit/**").hasAnyAuthority("Admin", "User")
                .antMatchers("/owner/edit/**").hasAnyAuthority("Admin", "User")
                .anyRequest().authenticated().and().formLogin().loginPage("/login")
                .permitAll().and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//        auth.inMemoryAuthentication().withUser("").password(bCryptPasswordEncoder().encode("user")).roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");

    }
}
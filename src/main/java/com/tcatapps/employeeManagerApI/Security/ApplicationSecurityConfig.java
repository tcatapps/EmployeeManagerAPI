package com.tcatapps.employeeManagerApI.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.tcatapps.employeeManagerApI.Security.ApplicationUserPermission.*;
import static com.tcatapps.employeeManagerApI.Security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    //This is being injected because it was annotated as @Bean in it's module
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(EMPLOYEE.name()) //ends points matching this pattern are accessible to users Role
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(EMPLOYEE_READ.name()) //ends points matching this pattern are accessible to users Role
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(EMPLOYEE_READ.name()) //ends points matching this pattern are accessible to users Role
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(EMPLOYEE_READ.name()) //ends points matching this pattern are accessible to users Role
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest().authenticated()// Every request must be authenticated
                .and().httpBasic(); // using Http Basic authentication
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails daniel = User.builder()
                .username("daniel_craig")
                .password(passwordEncoder.encode("password"))
                .roles(EMPLOYEE.name()) // employee role is given, will be converted as ROLE_EMPLOYEE in the DB
                .build();

        UserDetails christian = User.builder()
                .username("christian_smith")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name()) //admin role is given (ROLE_ADMIN)
                .build();

        UserDetails tom = User.builder()
                .username("tommy_lee")
                .password(passwordEncoder.encode("password"))
                .roles(ADMINTRAINEE.name()) // employee role is given, will be converted as ROLE_EMPLOYEE in the DB
                .build(); //ROLE_ADMINTRAINEE

        return new InMemoryUserDetailsManager(daniel, christian, tom);
    }
}

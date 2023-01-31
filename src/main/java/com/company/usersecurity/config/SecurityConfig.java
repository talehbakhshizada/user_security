package com.company.usersecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthFilterConfigurerAdapter jwtAuthFilterConfigurerAdapter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/v2/register").permitAll()
                .antMatchers("/v1/hello/**").permitAll()
                .antMatchers(GET, "/v1/user").hasAnyAuthority("USER", "MANAGER")
                .antMatchers(POST, "/v1/user").hasAuthority("MANAGER")
                .antMatchers("/v1/manager").hasRole("MANAGER")//hasAuthority()
                .and()
                .formLogin()
                .defaultSuccessUrl("http://turbo.az");
        http.apply(jwtAuthFilterConfigurerAdapter);
        super.configure(http);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}10")
//                .roles("USER")
//                .and()
//                .withUser("manager")
//                .password("{noop}20")
//                .roles("MANAGER");
//    }
}

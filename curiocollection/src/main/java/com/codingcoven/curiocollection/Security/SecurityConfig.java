package com.codingcoven.curiocollection.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetails;

    @Autowired
    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("ADMIN")
                .and()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("both").password("{noop}password").roles("ADMIN", "USER")
                .and()
                .withUser("neither").password("{noop}password").roles("STOWAWAYS");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin_or_user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/other").not().hasAnyRole("ADMIN", "USER")
                .antMatchers("/authed").fullyAuthenticated()
                .antMatchers("/", "/home", "/login").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?login_error=1")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}

 */

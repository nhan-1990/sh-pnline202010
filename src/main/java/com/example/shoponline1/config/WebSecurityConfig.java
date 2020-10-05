package com.example.shoponline1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
	                .antMatchers("/admin").hasRole("ADMIN")
	                .antMatchers("/admin/product").hasRole("ADMIN")
	                .antMatchers("/admin/delete").hasRole("ADMIN")
	                .antMatchers("/admin/addproduct").hasRole("ADMIN")
	                .antMatchers("/admin/editproduct").hasRole("ADMIN")
	                .antMatchers("/admin/saveprd").hasRole("ADMIN")
	                .antMatchers("/admin/addproductDt").hasRole("ADMIN")
	                .antMatchers("/admin/editdt").hasRole("ADMIN")
	                .antMatchers("/admin/product/addpromotion").hasRole("ADMIN")
	                .antMatchers("/admin/product/addtrademark").hasRole("ADMIN")
	                .antMatchers("/admin/orders").hasRole("ADMIN")
	                .antMatchers("/admin/orderl").hasRole("ADMIN")
	                .antMatchers("/admin/searchs").hasRole("ADMIN")
	                .antMatchers("/admin/**").hasRole("ADMIN")
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .usernameParameter("email")
	                .passwordParameter("password")
	                .failureUrl("/login?error")
	                .and()
                .logout()
	                .permitAll()
	                .and()
	            .exceptionHandling()
	            	.accessDeniedPage("/403");
    }

}

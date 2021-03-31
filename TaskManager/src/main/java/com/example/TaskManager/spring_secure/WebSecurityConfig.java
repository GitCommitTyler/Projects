package com.example.TaskManager.spring_secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web
                        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
                    .configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.TaskManager.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bcp;
	
	@Autowired
	private MyUserDetailsService uds;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{ 
		auth.userDetailsService(uds).passwordEncoder(bcp);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		String loginPage = "/login";
		String logoutPage = "/logout";
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers(loginPage).permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/**").hasAuthority("ADMIN")
			.anyRequest()
			.authenticated()
			.and().csrf().disable()  //cross site request forgery-> 
            .formLogin()    //responsible for predefined login form
            .loginPage(loginPage)   //customizing the login page
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/tasks")
            .usernameParameter("username")
            .passwordParameter("password")
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
            .logoutSuccessUrl(loginPage).and().exceptionHandling();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "WEB-INF/jsp/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
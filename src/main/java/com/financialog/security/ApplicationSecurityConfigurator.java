package com.financialog.security;

import com.financialog.filters.JwtFilter;
import com.financialog.repository.FinancialLoggerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class ApplicationSecurityConfigurator extends WebSecurityConfigurerAdapter {

    private final FinancialLoggerUserRepository financialLoggerUserRepository;
    private final JwtFilter jwtFilter;

    public ApplicationSecurityConfigurator(
            FinancialLoggerUserRepository financialLoggerUserRepository,
            JwtFilter jwtFilter) {
        this.financialLoggerUserRepository = financialLoggerUserRepository;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Enable CORS and disable CSRF
        http
                .cors().and().csrf().disable();

        //Set session management to stateless
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        //Set Permissions on endpoints
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        //Set Exception handler for unauthorized requests
        http
                .exceptionHandling()
                .authenticationEntryPoint(((request, response, authException) -> response
                                .sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())))
                .and();

        //Set JWT filter
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //Removes X-Frame option from header (For H2 console) - not recommended
        http
                .headers().frameOptions().disable();
    }

    /**
     * Declared instead of explicitly declaring a custom UserDetailsService
     * by passing userDetails as lambda to create an instance of UserDetailsService
     * @param auth AuthenticationManagerBuilder object
     * @throws Exception throws IllegalStateException
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username ->
                financialLoggerUserRepository.findByUsername(username)
                        .orElseThrow(IllegalStateException::new));
        //TODO: Provide JWT Authentication manager
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

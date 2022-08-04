package com.financialog.security;

import com.financialog.filters.JwtFilter;
import com.financialog.repository.FinancialLoggerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfigurator extends WebSecurityConfigurerAdapter {

    private final FinancialLoggerUserRepository financialLoggerUserRepository;
    private final JwtFilter jwtFilter;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public ApplicationSecurityConfigurator(
            FinancialLoggerUserRepository financialLoggerUserRepository,
            JwtFilter jwtFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.financialLoggerUserRepository = financialLoggerUserRepository;
        this.jwtFilter = jwtFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
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
                .antMatchers("/api/v1/authenticate").permitAll()
                .antMatchers("https://www1.nseindia.com/live_market/*").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        //Set Exception handler for unauthorized requests
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
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

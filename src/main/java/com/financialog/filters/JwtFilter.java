package com.financialog.filters;

import com.financialog.constants.HttpConstants;
import com.financialog.repository.FinancialLoggerUserRepository;
import com.financialog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private FinancialLoggerUserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String jwt = null;
        String authHeader = request.getHeader(HttpConstants.AUTHORIZATION_HEADER_KEY);
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(HttpConstants.BEARER_HEADER)) {
            jwt = authHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        // Check if security context is null
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userRepo.findByUsername(username).orElseThrow(IllegalArgumentException::new);

            if(jwtUtil.validateToken(jwt, user)) {
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                        user.getAuthorities() == null ? List.of() : user.getAuthorities());
                authenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

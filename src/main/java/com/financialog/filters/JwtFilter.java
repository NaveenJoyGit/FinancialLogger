package com.financialog.filters;

import com.financialog.constants.HttpConstants;
import com.financialog.repository.FinancialLoggerUserRepository;
import com.financialog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        String authHeader = request.getHeader(HttpConstants.AUTHORIZATION_HEADER_KEY);
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(HttpConstants.BEARER_HEADER)) {
            String jwt = authHeader.substring(7);
            if (jwt.isBlank()) {
                // Invalid JWT
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Bearer token in header.");
            } else {
                try {
                    String username = jwtUtil.validateToken(jwt);
                    UserDetails userDetails = userRepo.findByUsername(username).orElseThrow(IllegalStateException::new);

                    //Create authentication token for spring security context
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities() == null ? List.of() : userDetails.getAuthorities());

                    //Setting principal to spring security context
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT.");
                }

            }
        }
        filterChain.doFilter(request, response);
    }
}

package com.financialog.security;

import com.financialog.entity.FinancialLoggerUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationFacade.class);

    @Override
    public FinancialLoggerUser getLoggedInUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("principal obtained");
            if(principal instanceof UserDetails) {
                return (FinancialLoggerUser) principal;
            }
        } catch (Exception e) {
            logger.info("Error fetching current logged in user: {}", e.getMessage());
            throw e;
        }
        return null;
    }
}

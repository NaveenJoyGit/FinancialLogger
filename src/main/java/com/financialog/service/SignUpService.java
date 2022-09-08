package com.financialog.service;

import com.financialog.constants.ApplicationConstants;
import com.financialog.dto.SignuUpDto;
import com.financialog.entity.FinancialLoggerUser;
import com.financialog.entity.Role;
import com.financialog.repository.FinancialLoggerUserRepository;
import com.financialog.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class SignUpService {

    private final Logger logger = LoggerFactory.getLogger(SignUpService.class);

    @Autowired
    BCryptPasswordEncoder getPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private FinancialLoggerUserRepository userRepository;

    @Transactional
    public void saveUserDetails(SignuUpDto signuUpDto) {
        try {
            FinancialLoggerUser user = new FinancialLoggerUser();
            user.setUsername(signuUpDto.getUserName());
            user.setFirstName(signuUpDto.getFirstName());
            user.setLastName(signuUpDto.getLastName());
            user.setPassword(getPasswordEncoder.encode(signuUpDto.getPassword()));
            Set<Role> roles = new HashSet<>();
            createUserRole(roles);
            user.setRoles(roles);
            userRepository.save(user);
        } catch (Exception e) {
            logger.error("Exception occurred while saving user details: {} ", e.getMessage());
            throw e;
        }
    }

    private void createUserRole(Set<Role> roles) {
        Role role = roleRepository.findFirstByRoleName(ApplicationConstants.ROLE_USER);
        roles.add(role);
    }

}

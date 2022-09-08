package com.financialog.controller;

import com.financialog.dto.AuthRequest;
import com.financialog.dto.CommonResponse;
import com.financialog.dto.SignuUpDto;
import com.financialog.service.SignUpService;
import com.financialog.service.UserDetailServiceImpl;
import com.financialog.util.FinLogControllerPrefix;
import com.financialog.util.JwtUtil;
import com.financialog.util.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@FinLogControllerPrefix
@CrossOrigin
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            ));
        } catch (BadCredentialsException bc) {
            logger.info("Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        logger.info("user {} successfully logged in", userDetails.getUsername());
        return ResponseEntity.ok()
                .body(new CommonResponse<String>(CommonResponse.ResponseCodeEnum.SUCCESS.getCode(),
                "Successfully Authenticated", jwtUtil.generateToken(userDetails)));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@RequestBody SignuUpDto signuUpDto) {
        try {
            signUpService.saveUserDetails(signuUpDto);
            return ResponseEntity.ok().body(ResponseGenerator.getSuccessResponse("Successfully registered user", "Successfully registered user"));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Cannot save user details");
        }
    }

}

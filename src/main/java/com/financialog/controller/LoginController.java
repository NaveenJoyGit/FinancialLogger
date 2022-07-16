package com.financialog.controller;

import com.financialog.constants.HttpConstants;
import com.financialog.dto.AuthRequest;
import com.financialog.dto.CommonResponse;
import com.financialog.entity.FinancialLoggerUser;
import com.financialog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/public")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<String>> loginUser(@RequestBody AuthRequest request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                    );
            FinancialLoggerUser user = (FinancialLoggerUser) authentication.getPrincipal();
            return ResponseEntity.ok()
                    .header(HttpConstants.AUTHORIZATION_HEADER_KEY, jwtUtil.generateToken(user.getPassword()))
                    .body(new CommonResponse(
                            CommonResponse.ResponseCodeEnum.SUCCESS,
                            "User successfully authenticated.",
                            jwtUtil.generateToken(user.getPassword())
                            ))

                    ;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

package com.samarth.expensetrackerapi.controller;

import com.samarth.expensetrackerapi.dto.LoginDTO;
import com.samarth.expensetrackerapi.dto.RegisterDTO;
import com.samarth.expensetrackerapi.models.JWTResponse;
import com.samarth.expensetrackerapi.models.User;
import com.samarth.expensetrackerapi.security.CustomUserDetailsService;
import com.samarth.expensetrackerapi.service.UserService;
import com.samarth.expensetrackerapi.util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/user")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginDTO dto) throws Exception {

        authenticate(dto.getEmail(), dto.getPassword());

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(dto.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<JWTResponse>(new JWTResponse(token), HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }
        catch (DisabledException e){
            throw new Exception("User disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO user){
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
    }
}

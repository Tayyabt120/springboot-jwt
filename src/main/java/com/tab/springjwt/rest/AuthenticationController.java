package com.tab.springjwt.rest;

import com.tab.springjwt.model.AuthenticationRequest;
import com.tab.springjwt.model.AuthenticationResponse;
import com.tab.springjwt.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TAYYAB
 */
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("auth")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid User", e);
        }
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());
        AuthenticationResponse jwtToken = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok(jwtToken);
    }
}

package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.request.AuthenticationRequest;
import com.java1504.ManagerUsers.request.IntrospectRequest;
import com.java1504.ManagerUsers.response.AuthenticationResponse;
import com.java1504.ManagerUsers.response.ResponseData;
import com.java1504.ManagerUsers.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseData<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        try {
            var result = authenticationService.authenticate(request);
            String s = result.getToken();
            return new ResponseData<>(HttpStatus.ACCEPTED.value(),s);
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/introspect")
    ResponseData<AuthenticationResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        String s = String.valueOf(result);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(),s);
    }
}

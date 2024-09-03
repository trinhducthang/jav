package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.request.AuthenticationRequest;
import com.java1504.ManagerUsers.request.IntrospectRequest;
import com.java1504.ManagerUsers.dto.response.ApiResponse;
import com.java1504.ManagerUsers.dto.response.AuthenticationResponse;
import com.java1504.ManagerUsers.dto.response.IntrospectResponse;
import com.java1504.ManagerUsers.request.LogoutRequest;
import com.java1504.ManagerUsers.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;


    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            // Call the authentication service to authenticate the request
            var result = authenticationService.authenticate(request);
            // Build ApiResponse with result
            return ApiResponse.<AuthenticationResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message("Authentication successful")
                    .result(result)
                    .build();
        } catch (RuntimeException e) {
            // Handle any runtime exceptions
            return ApiResponse.<AuthenticationResponse>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Authentication failed: " + e.getMessage())
                    .build();
        }
    }


    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        try {
            var result = authenticationService.introspect(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(result)
                    .build();
        }
        catch (RuntimeException e){

            return ApiResponse.<IntrospectResponse>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }


    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }


}

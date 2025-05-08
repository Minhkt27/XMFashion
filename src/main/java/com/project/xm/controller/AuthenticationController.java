package com.project.xm.controller;

import com.nimbusds.jose.JOSEException;
import com.project.xm.dto.request.AuthenticationRequest;
import com.project.xm.dto.request.IntrospectsRequest;
import com.project.xm.dto.respone.ApiResponse;
import com.project.xm.dto.respone.AuthenticationResponse;
import com.project.xm.dto.respone.IntrospectsResponse;
import com.project.xm.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result= authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectsResponse> authenticate(@RequestBody IntrospectsRequest request) throws ParseException, JOSEException {
        var result= authenticationService.introspect(request);

        return ApiResponse.<IntrospectsResponse>builder()
                .result(result)
                .build();
    }
}

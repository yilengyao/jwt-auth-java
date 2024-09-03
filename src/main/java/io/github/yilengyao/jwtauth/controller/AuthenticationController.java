package io.github.yilengyao.jwtauth.controller;

import io.github.innobridge.security.model.*;
import io.github.innobridge.security.security.JwtUtils;
import io.github.innobridge.security.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static io.github.innobridge.security.constants.HTTPConstants.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(SIGNUP_URL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED,
                    description = "Successful signup",
                    content = @Content(mediaType = CONTENT_TYPE,
                            schema = @Schema(implementation = SignupResponse.class)))
    })
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest, HttpServletResponse response) {
        return ResponseEntity.ok(response);
    }

    /**
     * When the username/email and password are validated during signin, an access token and refresh token are returned
     * to the user. The access token is used to authenticate the user for a short period of time, so it is passed back to
     * user in the response body along with the expiry time.
     * While the refresh token are longer lived and are stored in an HTTP-only cookie on the user's browser.
     */
    @PostMapping(SIGNIN_URL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED, description = "Successful signin",
                    content = @Content(mediaType = CONTENT_TYPE,
                            schema = @Schema(implementation = AccessTokenResponse.class)))
    })
    public ResponseEntity<?> authenticateUser(@RequestBody SigninRequest signinRequest, HttpServletResponse response) {
        return ResponseEntity.ok(response);
    }

    @PostMapping(REFRESH_TOKEN_URL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED, description = "Refresh token successful",
                    content = @Content(mediaType = CONTENT_TYPE,
                            schema = @Schema(implementation = AccessTokenResponse.class)))
    })
    public ResponseEntity<?> refreshToken(HttpServletResponse response) {
        return ResponseEntity.ok(response);
    }

    @PostMapping(SIGNOUT_URL)
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        return ResponseEntity.ok(response);
    }
}


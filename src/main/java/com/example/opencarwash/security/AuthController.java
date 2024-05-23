package com.example.opencarwash.security;

import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/reg")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserCreationDTO dto) {
        try {
            authService.registerUser(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyPresentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<AccessTokenDTO> signIn(@RequestBody UserLoginDTO dto, HttpServletResponse response) throws UserPrincipalNotFoundException {
        try {
            JWTResponse jwtResponse = authService.createAuthTokens(dto);
            Cookie cookie = new Cookie("refreshToken", jwtResponse.refreshToken);
            cookie.setPath("/");
            cookie.setSecure(false);
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
            return new ResponseEntity<>(new AccessTokenDTO(jwtResponse.accessToken), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/refresh")
    @ResponseBody
    public ResponseEntity<AccessTokenDTO> refresh(HttpServletRequest request) {
        try {
            AccessTokenDTO token = authService.generateNewAccess(request);
            if (token != null) return new ResponseEntity<>(token, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrent(){
        try{
            UserDTO user = authService.getCurrent();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletResponse response){
        authService.logout(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

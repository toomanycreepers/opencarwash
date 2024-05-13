package com.example.opencarwash.security;

import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.services.UserDetailServiceImpl;
import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.dtomappers.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class AuthService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public JWTResponse createAuthTokens(UserLoginDTO dto) throws UserPrincipalNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailService.loadUserByUsername(dto.phoneNumber);
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.phoneNumber, dto.password));
            System.out.println("пользователь аутентифицирован");
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
            throw new UserPrincipalNotFoundException(dto.phoneNumber);
        }

        String accessToken = jwtService.generateJwtAccessToken(userDetails);
        String refreshToken = jwtService.generateJwtRefreshToken(userDetails);

        JWTResponse jwtResponse = new JWTResponse(accessToken, refreshToken);

        return jwtResponse;
    }

    public void registerUser(UserCreationDTO dto) throws AlreadyPresentException {
        if(repo.findByPhoneNumber(dto.phoneNumber).isPresent())
            throw new AlreadyPresentException("User with this phone number already registered.");
        repo.save(UserMapper.mapFromDTO(dto));
    }

    public AccessTokenDTO generateNewAccess(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies)
                if ("refreshToken".equals(cookie.getName())) {
                    String refreshToken = cookie.getValue();
                    try {
                        String username = jwtService.getUsernameFromJwtToken(refreshToken);

                        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailService.loadUserByUsername(username);
                        AccessTokenDTO newJwtAccessToken = new AccessTokenDTO(jwtService.generateJwtAccessToken(userDetails));

                        System.out.println("New access token created successfully");
                        return newJwtAccessToken;
                    } catch (Exception ex) {
                        System.out.println("Refresh token is not valid");
                        throw ex;
                    }
                }
        }
        return null;
    }
}

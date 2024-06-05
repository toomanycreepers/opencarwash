package com.example.opencarwash.security;

import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.services.UserDetailServiceImpl;
import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.customExceptions.UnauthorizedException;
import com.example.opencarwash.utils.dtomappers.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.NoSuchElementException;

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
                        if(jwtService.isValid(refreshToken)) {
                            String username = jwtService.getUsernameFromJwtToken(refreshToken);

                            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailService.loadUserByUsername(username);
                            AccessTokenDTO newJwtAccessToken = new AccessTokenDTO(jwtService.generateJwtAccessToken(userDetails));

                            System.out.println("New access token created successfully");
                            return newJwtAccessToken;
                        }
                    else {
                        System.out.println("Refresh token is not valid");
                    }
                }
        }
        return null;
    }

    public UserDTO getCurrent() throws UnauthorizedException, NoSuchElementException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
            String userName = userDetails.getUsername();

            User user = repo.findByPhoneNumber(userName).orElseThrow(
                    () -> new NoSuchElementException("User that specified in token does not exists.")
            );
            return UserMapper.mapToDTO(user);
        }

        throw new UnauthorizedException("User not authenticated or authentication invalid format.");
    }

    public void logout(HttpServletResponse response){
        response.setHeader("Authorization", null);

        Cookie emptyCookie = new Cookie("refreshToken", null);
        emptyCookie.setPath("/");
        emptyCookie.setHttpOnly(true);
        emptyCookie.setMaxAge(0);
        response.addCookie(emptyCookie);

        SecurityContextHolder.clearContext();
    }
}

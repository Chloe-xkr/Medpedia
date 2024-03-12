package cn.edu.zju.sem.g4.taijian.filter;

import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import cn.edu.zju.sem.g4.taijian.service.AppUserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.security.Keys;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import static cn.edu.zju.sem.g4.taijian.security.SecurityConstants.*;
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        //设置默认登录地址
        setFilterProcessesUrl("/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);
        try {
//            System.out.println("=================attemptAuthentication========================");
            AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            appUser.getUserName(),
                            appUser.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            System.out.println("Error in attemptAuthentication");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        String token = JWT.create().withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt((new Date(System.currentTimeMillis()+EXPIRATION_TIME)))
                .sign(Algorithm.HMAC256(SECRET));
        System.out.println(token);
        String body = ((User)authResult.getPrincipal()).getUsername() + " "  + token;
        response.getWriter().write(body);
        response.getWriter().flush();
        response.addHeader("token",token);
//        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
//        Key key = Keys.hmacShaKeyFor(KEY.getBytes());
//        Claims claims = Jwts.claims().setSubject(((User) authResult.getPrincipal()).getUsername());
//        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key).setExpiration(exp).compact();
//        response.addHeader("token", token);
    }
}
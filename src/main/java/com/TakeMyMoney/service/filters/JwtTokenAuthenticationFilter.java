package com.TakeMyMoney.service.filters;

import com.TakeMyMoney.service.controllers.authentication.JwtTokenProvider;
import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.exceptions.JwtTokenException;
import com.TakeMyMoney.service.services.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.AntPathRequestMatcherProvider;
import org.springframework.boot.autoconfigure.security.servlet.RequestMatcherProvider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Order(1)
public class JwtTokenAuthenticationFilter implements Filter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            if (hasNoAuthorization(httpServletRequest)) {
                filterChain.doFilter(request, response);
                return;
            }
            String header = httpServletRequest.getHeader("Authorization");
            if (!header.contains("Bearer ")) {
                throw new JwtTokenException("Unauthorized");
            }
            Claims claims = jwtTokenProvider.getClaims(header.replace("Bearer ", ""));
            User user = userService.getUser(claims.getSubject());
            UserContext.setContext(user);
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clearContext();
        }
    }
    private Boolean hasNoAuthorization(HttpServletRequest request){
        return !(hasAuthHeader(request, AUTHORIZATION));
    }

    private Boolean hasAuthHeader(HttpServletRequest request, String headerKey){
        String header = request.getHeader(headerKey);
        return header != null && header.startsWith("Bearer ");
    }
}

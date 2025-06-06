package com.nerdyGeek.smat.configs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.services.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.nerdyGeek.smat.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	private final HandlerExceptionResolver handlerExceptionResolver;

    private final JwtService jwtService;
    private final UserService userService;
    
    private final List<String> NON_AUTH_END_POINTS = Collections.unmodifiableList(
            Arrays.asList("/**/*.js", "/**/*.css", "/**/*.gif", "/**/images/**", "/**/css/**", "/**/js/**", "/**/fonts/**", "/**/audio/**", "/**/lctest.html/**",
                    "/**/login/**", "/botify", "/logout", "/workflowbuilder/**", "/api/**", "/livechat/**", "/widgets/**", "/*.js", "/**/rc-widget-test.html/**", "/rc-widget/**",
                    "/favicon.ico", "/**/*.map", "/**/payment/payu/callback/**", "/**/payment/checkout/**", "/**/payment/send-payment-link/**"));
    
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return NON_AUTH_END_POINTS.stream().anyMatch(path -> {
            return pathMatcher.match(path, request.getServletPath());
        });
    }
    
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserService userService,
            HandlerExceptionResolver handlerExceptionResolver
        ) {
            this.jwtService = jwtService;
            this.userService = userService;
            this.handlerExceptionResolver = handlerExceptionResolver;
        }
    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserEntity userEntity = (UserEntity) this.userService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userEntity) && userEntity.getOrganization().isEnabled()) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userEntity,
                            null,
                            userEntity.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    request.setAttribute("user", userEntity);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
        	exception.printStackTrace();
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
	
}

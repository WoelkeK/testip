package pl.woelke.testerIp.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.woelke.testerIp.security.authentication.IpAuthentication;
import pl.woelke.testerIp.security.manager.CustomAuthenticationManager;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = request.getRemoteAddr();
        log.info(ipAddress);

        if (ipAddress != null) {
            IpAuthentication ipAuthentication = new IpAuthentication(false, ipAddress, null);
            Authentication authenticatedObject = customAuthenticationManager.authenticate(ipAuthentication);
            SecurityContextHolder.getContext().setAuthentication(authenticatedObject);
            filterChain.doFilter(request, response);

        }
    }
}

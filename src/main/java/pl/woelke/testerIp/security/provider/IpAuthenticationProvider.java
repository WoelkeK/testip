package pl.woelke.testerIp.security.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.woelke.testerIp.security.authentication.IpAuthentication;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class IpAuthenticationProvider implements AuthenticationProvider {

    Set<String> whitelist = new HashSet<String>();

    public IpAuthenticationProvider() {
        whitelist.add("0:0:0:0:0:0:0:1");
        whitelist.add("127.0.0.1");
        whitelist.add("10.0.0.36");
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        IpAuthentication ipAuthentication = (IpAuthentication) auth;
        String ipAddress = ipAuthentication.getIpAddress();

        if (whitelist.contains(ipAddress)) {
            log.info("Request have valid Ip address");
            return new IpAuthentication(true, ipAddress, null);
        }
        throw new BadCredentialsException("Remote Ip address did not match ip address restriction ");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return IpAuthentication.class.equals(authentication);
    }

}
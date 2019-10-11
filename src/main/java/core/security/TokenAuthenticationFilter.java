package core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private TokenUtil tokenUtil;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenUtil tokenUtil) {
        setFilterProcessesUrl("/login");
        setAuthenticationManager(authenticationManager);
        this.tokenUtil = tokenUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String username = authentication.getName();
        String token = tokenUtil.createToken(username);
        response.addHeader(TokenUtil.TOKEN_HEADER, TokenUtil.TOKEN_PREFIX + token);
        log.info("Auth: " + SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

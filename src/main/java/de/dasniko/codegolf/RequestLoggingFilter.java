package de.dasniko.codegolf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/webjars") || requestURI.startsWith("/css") || requestURI.startsWith("/js")) {
            filterChain.doFilter(request, response);
        } else {
            long start = System.currentTimeMillis();

            filterChain.doFilter(request, response);

            long duration = System.currentTimeMillis() - start;

            if (request.getQueryString() != null) {
                requestURI += "?" + request.getQueryString();
            }
            int status = response.getStatus();
            String[] msg = {Integer.toString(status), request.getMethod(), requestURI,
                    request.getHeader("x-forwarded-for"), request.getRemoteUser(), duration + "ms"};
            String msgString = String.join(" ", msg);
            if (status >= 400 && status <= 499) {
                log.warn(msgString);
            } else if (status >= 500) {
                log.error(msgString);
            } else {
                log.info(msgString);
            }
        }
    }
}
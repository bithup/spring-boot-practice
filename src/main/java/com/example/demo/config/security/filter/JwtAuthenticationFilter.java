package com.example.demo.config.security.filter;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {



    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("auth");
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        String user = null;
        try {
            Long t1 = System.currentTimeMillis();
            user = Jwts.parser()
                    .setSigningKey("e32dfe0u5h98v85u4l53ffd9d5b7t88u")
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            Long t2 = System.currentTimeMillis();
            System.out.println(user);
            //测试解析token的时间
            Long t = t2 - t1;
            System.out.println("parsing token use time: " + t.toString());
        } catch (ExpiredJwtException e) {
            chain.doFilter(request, response);
            return;
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, new ArrayList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}

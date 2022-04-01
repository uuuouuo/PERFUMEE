package com.ssafy.perfumee.config.jwt.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ssafy.perfumee.config.jwt.ErrorCode;
import com.ssafy.perfumee.config.jwt.JwtProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // 요청이 들어올 때 마다 JWT 인증, GenericFilterBean 상속받아도 무관 (OncePerRequestFilter -> GenericFilterBean 상속)

  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("JWT");

    if(authorizationHeader == null) {
      request.setAttribute("exception", ErrorCode.NON_LOGIN.getCode());
      filterChain.doFilter(request,response);
    }

    if(authorizationHeader != null && !authorizationHeader.startsWith("Bearer ")) {
      request.setAttribute("exception", ErrorCode.INVALID_TOKEN.getCode());
      filterChain.doFilter(request, response);


      String jwtToken = authorizationHeader.replace("Bearer ", "");

      try{
        Authentication authentication = jwtProvider.getAuthentication(jwtToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
      } catch(TokenExpiredException e) {
        e.printStackTrace();
        request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getCode());
        filterChain.doFilter(request, response);
      }
    }

  }

}

package com.ssafy.perfumee.config.jwt.filter;

import com.ssafy.perfumee.config.jwt.JwtProvider;
import com.ssafy.perfumee.config.jwt.PrincipalDetails;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.user.UserRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private UserRepository userRepository;
  private JwtProvider jwtProvider;

  public JwtAuthorizationFilter(
      AuthenticationManager authenticationManager,
      UserRepository userRepository, JwtProvider jwtProvider) {
    super(authenticationManager);
    this.userRepository = userRepository;
    this.jwtProvider = jwtProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String jwtHeader = request.getHeader("JWT");

    if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    String jwtToken = request.getHeader("JWT").replace("Bearer ","");
    String id = jwtProvider.getId(jwtToken);

    if(id != null) {
      Optional<User> userOpt = userRepository.findById(id);
      PrincipalDetails principalDetails = new PrincipalDetails(userOpt.get());

      Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
          null, principalDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

      chain.doFilter(request,response);
    }

  }

}

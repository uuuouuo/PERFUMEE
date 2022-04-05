package com.ssafy.perfumee.service.user;

import com.ssafy.perfumee.config.jwt.PrincipalDetails;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

    Optional<User> userOptional = userRepository.findById(id);
    User user = userOptional.get();

    return new PrincipalDetails(user);

  }
}

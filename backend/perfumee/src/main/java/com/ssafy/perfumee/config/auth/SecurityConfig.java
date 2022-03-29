package com.ssafy.perfumee.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // Spring Security 필터가 Spring 필터체인에 등록이 됨
@RequiredArgsConstructor
public class SecurityConfig {

}

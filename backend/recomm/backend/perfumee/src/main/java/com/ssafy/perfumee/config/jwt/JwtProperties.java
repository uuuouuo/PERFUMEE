package com.ssafy.perfumee.config.jwt;

public interface JwtProperties {
  String SECRET = "QUOKKACOLA";
  int EXPIRATION_TIME = 1000 * 60 * 10;
  String TOKEN_PREFIX = "Bearer ";
  String HEADER_STRING = "JWT";
}
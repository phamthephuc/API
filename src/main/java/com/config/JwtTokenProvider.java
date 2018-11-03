package com.config;

import com.dto.LoginDTO;
import com.entity.Role;
import com.entity.Traveler;
import com.exception.CustomException;
import com.service.RoleService;
import com.service.TravelerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;

@Component
public class JwtTokenProvider {


  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:864000000}")
  private long validityInMilliseconds = 864000000; // 10 day

  @Autowired
  private MyUserDetails myUserDetails;

  @Autowired
  private TravelerService travelerService;

  @Autowired
  private RoleService roleService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username) {
    Claims claims = Jwts.claims().setSubject("Login");
    Traveler traveler= travelerService.findByEmail(username);
    Role role = roleService.findById(traveler.getRoleId());
    LoginDTO loginDTO = new LoginDTO(traveler.getId(), traveler.getUsername());
    claims.put("data", loginDTO);
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);
    return Jwts.builder()//
            .setClaims(claims)//
            .setIssuedAt(now)//
            .setExpiration(validity)//
            .signWith(SignatureAlgorithm.HS256, secretKey)//
            .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    String username = String.valueOf(((LinkedHashMap) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("data")).get("username"));
    return username;
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null) {
      return bearerToken;
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("error",500);
    }
  }

}
package com.config;


import com.entity.Role;
import com.entity.Traveler;
import com.repository.RoleRespository;
import com.repository.TravelerResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private TravelerResponsitory travelerResponsitory;

  @Autowired
  private RoleRespository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Traveler traveler = travelerResponsitory.findByUsername(username);

    if (traveler == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }


    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    Role role = roleRepository.findById(traveler.getRoleId()).orElse(new Role());
//
    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));




    return org.springframework.security.core.userdetails.User//
            .withUsername(username)//
            .password(traveler.getPassword())//
            .authorities(grantedAuthorities)//
            .accountExpired(false)//
            .accountLocked(false)//
            .credentialsExpired(false)//
            .disabled(false)//
            .build();
  }

}

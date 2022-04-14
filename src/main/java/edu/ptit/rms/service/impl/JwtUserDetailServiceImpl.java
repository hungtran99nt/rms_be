package edu.ptit.rms.service.impl;

import edu.ptit.rms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
  @Autowired private UserService userService;
  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    return userService
        .findUserByUsername(username)
        .map(
            u -> {
              User.UserBuilder builder = User.withUsername(u.getUsername());
              builder.password(passwordEncoder.encode(u.getPassword()));
              SimpleGrantedAuthority authority = new SimpleGrantedAuthority(u.getRole());
              builder.authorities(authority.toString());
              return builder.build();
            })
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}

package edu.ptit.rms.service.impl;

import edu.ptit.rms.dao.UserDAO;
import edu.ptit.rms.model.Account;
import edu.ptit.rms.model.User;
import edu.ptit.rms.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserDAO userDAO;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
      UserDAO userDAO, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
    this.userDAO = userDAO;
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User findById(Integer id) {
    User user = userDAO.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    logger.info("getUserById: {}", user);
    return user;
  }

  @Override
  public Optional<Account> findAccountByUsername(String username) {
    User user =
        userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found."));
    return Optional.ofNullable(modelMapper.map(user, Account.class));
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    User user =
            userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found."));
    return Optional.ofNullable(user);
  }

  @Override
  public User getCurrentUser() {
    logger.info("Inside getCurrentUser() method");
    UserDetails userDetails =
        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.getUsername();
    return userDAO
        .findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}

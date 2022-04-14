package edu.ptit.rms.service;

import edu.ptit.rms.model.Account;
import edu.ptit.rms.model.User;

import java.util.Optional;

public interface UserService {

  /**
   * Change password
   *
   * @param passwordDTO
   * @return Change status: OK, FAILED
   */
  //    void changePassword(PasswordDTO passwordDTO);

  /**
   * Get current user login
   *
   * @return current {@link User} entity
   */
  User getCurrentUser();

  Optional<Account> findAccountByUsername(String username);

  Optional<User> findUserByUsername(String username);

  User findById(Integer id);
}

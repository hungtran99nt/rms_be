package edu.ptit.rms.dao;

import edu.ptit.rms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
  /**
   * Find user by username
   *
   * @param username of user
   * @return Optional of @{@link User} object
   */
  Optional<User> findByUsername(String username);
}

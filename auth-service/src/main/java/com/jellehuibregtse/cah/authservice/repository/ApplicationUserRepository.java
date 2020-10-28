package com.jellehuibregtse.cah.authservice.repository;

import com.jellehuibregtse.cah.authservice.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The repository for getting users from the database.
 *
 * @author Jelle Huibregtse
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);
}
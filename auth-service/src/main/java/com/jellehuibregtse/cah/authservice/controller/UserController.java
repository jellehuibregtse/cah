package com.jellehuibregtse.cah.authservice.controller;

import com.jellehuibregtse.cah.authservice.model.ApplicationUser;
import com.jellehuibregtse.cah.authservice.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * The controller that handles all the mappings for the user service.
 *
 * @author Jelle Huibregtse
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    @GetMapping("/logged-in")
    public ResponseEntity<Boolean> loggedIn() {
        return ResponseEntity.ok(true);
    }

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, ApplicationUserRepository applicationUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * Check if username is taken.
     *
     * @param username that needs to be found.
     * @return <code>ResponseEntity</code> with a message and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<Boolean> usernameTaken(@RequestParam String username) {
        return ResponseEntity.ok(applicationUserRepository.findByUsername(username).isPresent());
    }

    /**
     * Create a user.
     *
     * @param user that needs to created
     * @return <code>ResponseEntity</code> with a message and HTTP status OK.
     */
    @PostMapping
    public ResponseEntity<String> createApplicationUser(@RequestBody ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        applicationUserRepository.save(user);

        return ResponseEntity.ok(String.format("User with username: %s has been successfully created!",
                                               user.getUsername()));
    }

    /**
     * Update a single user.
     *
     * @param user that needs to be updated
     * @return message and HTTP status OK.
     */
    @PutMapping
    public ResponseEntity<String> updateApplicationUser(@RequestBody ApplicationUser user) {
        var principal = getPrincipal();
        var updatedApplicationUser = applicationUserRepository.findByUsername(principal)
                                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                                      "Not found."));

        updatedApplicationUser.setPassword(user.getPassword());
        updatedApplicationUser.setUsername(user.getUsername());

        applicationUserRepository.save(updatedApplicationUser);

        return ResponseEntity.ok(String.format("User with id: %d has been successfully updated!",
                                               updatedApplicationUser.getId()));
    }

    /**
     * Delete a single user.
     *
     * @return <code>ResponseEntity</code> with a message and HTTP status OK.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteApplicationUser() {
        var principal = getPrincipal();

        var user = applicationUserRepository.findByUsername(principal)
                                            .orElseThrow(() -> new ResourceNotFoundException("Not found."));

        applicationUserRepository.delete(user);

        return ResponseEntity.ok(String.format("User with id: %d has been successfully deleted!", user.getId()));
    }

    /**
     * Gets the principal (username) of the currently logged in user.
     *
     * @return username.
     */
    private String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

package com.jellehuibregtse.cah.authservice.controller;

import com.jellehuibregtse.cah.authservice.model.ApplicationUserDto;
import com.jellehuibregtse.cah.authservice.service.IApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The controller that handles all the mappings for the user service.
 *
 * @author Jelle Huibregtse
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final IApplicationUserService applicationUserService;

    @Autowired
    public UserController(IApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    /**
     * If you can access this endpoint you are logged in, so we can always return true.
     *
     * @return true when endpoint is accessed.
     */
    @GetMapping("/logged-in")
    public ResponseEntity<Boolean> loggedIn() {
        return ResponseEntity.ok(true);
    }

    /**
     * Check if username is taken.
     *
     * @param username that needs to be found.
     * @return <code>ResponseEntity</code> with a message and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<Boolean> usernameTaken(@RequestParam String username) {
        return ResponseEntity.ok(applicationUserService.isUsernameTaken(username));
    }

    /**
     * Create a user.
     *
     * @param user that needs to created
     * @return <code>ResponseEntity</code> with a message and HTTP status OK.
     */
    @PostMapping
    public ResponseEntity<String> createApplicationUser(@RequestBody ApplicationUserDto user) {
        if (!applicationUserService.createApplicationUser(user)) {
            return ResponseEntity.badRequest().body(String.format("Username %s is already taken.", user.getUsername()));
        }
        return ResponseEntity.ok(String.format("User with username: %s has been successfully created!",
                                               user.getUsername()));
    }

    /**
     * Update a single user.
     *
     * @param user that needs to be updated
     * @return message and HTTP status OK.
     */
    @PutMapping("{id}")
    public ResponseEntity<String> updateApplicationUser(@RequestBody ApplicationUserDto user, @PathVariable long id) {
        if (!applicationUserService.updateApplicationUser(user, id)) {
            return ResponseEntity.badRequest().body(String.format("Username %s is already taken.", user.getUsername()));
        }
        return ResponseEntity.ok(String.format("User with id: %d has been successfully updated!", id));
    }

    /**
     * Delete a single user.
     *
     * @return <code>ResponseEntity</code> with a message and HTTP status OK.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteApplicationUser(@PathVariable long id) {
        applicationUserService.deleteApplicationUser(id);

        return ResponseEntity.ok(String.format("User with id: %d has been successfully deleted!", id));
    }
}

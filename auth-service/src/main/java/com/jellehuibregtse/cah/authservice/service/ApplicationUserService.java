package com.jellehuibregtse.cah.authservice.service;

import com.jellehuibregtse.cah.authservice.model.ApplicationUser;
import com.jellehuibregtse.cah.authservice.model.ApplicationUserDto;
import com.jellehuibregtse.cah.authservice.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service that handles all user actions.
 *
 * @author Jelle Huibregtse
 */
@Service
public class ApplicationUserService implements UserDetailsService, IApplicationUserService {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository repository;

    @Autowired
    public ApplicationUserService(PasswordEncoder passwordEncoder, ApplicationUserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = repository.findByUsername(username);

        if (user.isPresent()) {
            var applicationUser = user.get();

            List<GrantedAuthority> grantedAuthorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + applicationUser.getRole());

            return new User(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorities);
        }

        throw new UsernameNotFoundException("Username: " + username + " not found!");
    }

    @Override
    public boolean createApplicationUser(ApplicationUserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (isUsernameTaken(user.getUsername())) {
            return false;
        }

        repository.save(new ApplicationUser(user.getUsername(), user.getUsername()));

        return true;
    }

    @Override
    public boolean updateApplicationUser(ApplicationUserDto user, long id) {
        var updatedUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found."));

        if (!updatedUser.getUsername().equals(getPrincipal())) {
            return false;
        }

        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(updatedUser);

        return true;
    }

    @Override
    public boolean deleteApplicationUser(long id) {
        var user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found."));


        if (!user.getUsername().equals(getPrincipal())) {
            return false;
        }

        repository.delete(user);

        return true;
    }

    /**
     * Checks if a certain username is already taken.
     *
     * @param username to check.
     * @return true if the username is taken.
     */
    @Override
    public boolean isUsernameTaken(String username) {
        return repository.findByUsername(username).isPresent();
    }

    /**
     * Gets the principal (username) of the currently logged in user.
     *
     * @return username.
     */
    @Override
    public String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
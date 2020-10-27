package com.jellehuibregtse.cah.authservice.service;

import com.jellehuibregtse.cah.authservice.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Hard coded users. Passwords must be encoded.
        final List<ApplicationUser> users =
                Arrays.asList(new ApplicationUser(1, "jelle", passwordEncoder.encode("12345"), "USER"),
                              new ApplicationUser(2, "admin", passwordEncoder.encode("12345"), "ADMIN"));

        for (var applicationUser : users) {
            if (applicationUser.getUsername().equals(username)) {
                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities =
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + applicationUser.getRole());

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService.
                // And used by auth manager to verify and check user authentication.
                return new User(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorities);
            }
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}
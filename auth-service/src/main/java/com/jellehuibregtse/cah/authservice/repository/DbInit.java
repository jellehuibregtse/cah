package com.jellehuibregtse.cah.authservice.repository;

import com.jellehuibregtse.cah.authservice.model.ApplicationUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private final ApplicationUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DbInit(ApplicationUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.repository.deleteAll();

        final List<ApplicationUser> users =
                Arrays.asList(new ApplicationUser(1, "jelle", passwordEncoder.encode("12345"), "USER"),
                              new ApplicationUser(2, "admin", passwordEncoder.encode("12345"), "ADMIN"));

        // Save to db
        this.repository.saveAll(users);
    }
}
package com.jellehuibregtse.cah.authservice.controller;

import com.jellehuibregtse.cah.authservice.model.ApplicationUser;
import com.jellehuibregtse.cah.authservice.repository.ApplicationUserRepository;
import com.jellehuibregtse.cah.authservice.service.JwtTokenService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenService jwtTokenService;

    private String token;

    @BeforeEach
    void setup() {
        var user = new ApplicationUser(1, "test-user", passwordEncoder.encode("password"), null);

        userRepository.save(user);

        token = "Bearer " + jwtTokenService.generateToken(user.getUsername());
    }

    @Test
    void existingUsername_returnsStatus200_andTrue() throws Exception {
        this.mockMvc.perform(get("/users/?username=test-user"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));
    }

    @Test
    void nonExistingUsername_returnStatus200_andFalse() throws Exception {
        this.mockMvc.perform(get("/users?username=test-user-2"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));
    }

    @Test
    void auth_returnsStatus200_andAuthorizationHeader() throws Exception {
        JSONObject applicationUser = new JSONObject();
        applicationUser.put("username", "test-user");
        applicationUser.put("password", "password");

        this.mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(applicationUser.toString()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(header().exists("Authorization"));
    }

    @Test
    void invalidUserAuth_returnsStatus404() throws Exception {
        JSONObject applicationUser = new JSONObject();
        applicationUser.put("username", "test-user-2");
        applicationUser.put("password", "password");

        this.mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(applicationUser.toString()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
    }

    @Test
    void register_returnStatus200_andMessage() throws Exception {
        JSONObject applicationUser = new JSONObject();
        applicationUser.put("username", "test-user-2");
        applicationUser.put("password", "password");

        this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(applicationUser.toString()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(endsWith("has been successfully created!")));
    }

    @Test
    void registerWithAlreadyExistingUser_returnStatus400_andMessage() throws Exception {
        JSONObject applicationUser = new JSONObject();
        applicationUser.put("username", "test-user");
        applicationUser.put("password", "password");

        this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(applicationUser.toString()))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(endsWith("is already taken.")));
    }

    @Test
    void updateUser_returnStatus200_andMessage() throws Exception {
        JSONObject applicationUser = new JSONObject();
        applicationUser.put("username", "new-test-user");
        applicationUser.put("password", "new-password");

        this.mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
                                            .content(applicationUser.toString())
                                            .header("Authorization", token))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(endsWith("has been successfully updated!")));
    }

    @Test
    void updateUserWithoutAuth_returnStatus404() throws Exception {
        JSONObject applicationUser = new JSONObject();
        applicationUser.put("username", "new-test-user");
        applicationUser.put("password", "new-password");

        this.mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
                                            .content(applicationUser.toString()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteUser_returnStatus200_andMessage() throws Exception {
        this.mockMvc.perform(delete("/users/1").header("Authorization", token))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(endsWith("has been successfully deleted!")));
    }

    @Test
    void deleteUserWithoutAuth_returnStatus404() throws Exception {
        this.mockMvc.perform(delete("/users/1")).andDo(print()).andExpect(status().isUnauthorized());
    }
}

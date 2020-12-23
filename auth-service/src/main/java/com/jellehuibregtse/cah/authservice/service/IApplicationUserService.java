package com.jellehuibregtse.cah.authservice.service;

import com.jellehuibregtse.cah.authservice.model.ApplicationUserDto;

public interface IApplicationUserService {

    boolean createApplicationUser(ApplicationUserDto userDto);

    boolean updateApplicationUser(ApplicationUserDto userDto, long id);

    boolean deleteApplicationUser(long id);

    boolean isUsernameTaken(String username);

    String getPrincipal();
}

package com.jellehuibregtse.cah.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationUserDto {

    public String username;
    public String password;
}

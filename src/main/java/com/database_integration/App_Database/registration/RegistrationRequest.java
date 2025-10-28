package com.database_integration.App_Database.registration;

import com.database_integration.App_Database.user.Role;
import lombok.Data;

import java.util.List;
@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roles;
}
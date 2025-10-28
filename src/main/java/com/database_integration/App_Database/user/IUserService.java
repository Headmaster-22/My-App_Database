
package com.database_integration.App_Database.user;

import com.database_integration.App_Database.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User registerUser(RegistrationRequest registrationRequest);
    List<User> getAllUsers();
    User findByEmail();
    User findByEmail(String email);

}

package com.database_integration.App_Database.registration.token;
import com.database_integration.App_Database.user.User;
import java.util.Optional;

public interface IVerificationTokenService {
    String validateToken(String token);
    void saveVerificationTokenForUser(User user, String token);
    Optional<VerificationToken> findByToken(String token);
}

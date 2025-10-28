package com.database_integration.App_Database.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

}

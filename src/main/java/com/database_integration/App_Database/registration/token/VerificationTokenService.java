package com.database_integration.App_Database.registration.token;
import com.database_integration.App_Database.user.User;
import com.database_integration.App_Database.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Calendar;

@Service
@RequiredArgsConstructor


public class VerificationTokenService implements IVerificationTokenService {
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    public String validateToken(String token){
        Optional <VerificationToken> theToken = tokenRepository.findByToken(token);
        if (theToken.isEmpty()){
            return "Invalid ";
        }
        User user = theToken.get().getUser();
        Calendar calendar = Calendar.getInstance();
        if(theToken.get().getExpirationTime().getTime()-calendar.getTime().getTime()<= 0){
            return "Expired Verification Token";
        }

        user.set_enabled(true);
        userRepository.save(user);
        return "Valid";
    }
    @Override
    public void saveVerificationTokenForUser (User user, String token){
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public  Optional<VerificationToken> findByToken(String token){
        return tokenRepository.findByToken(token);
    }
}

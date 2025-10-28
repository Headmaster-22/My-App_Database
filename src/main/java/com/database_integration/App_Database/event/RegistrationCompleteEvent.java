package com.database_integration.App_Database.event;

import com.database_integration.App_Database.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter

public class  RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    public String confirmationUrl;
    public RegistrationCompleteEvent(User user, String confirmationUrl) {
        super(user);
        this.user = user;
        this.confirmationUrl = confirmationUrl;
    }
}

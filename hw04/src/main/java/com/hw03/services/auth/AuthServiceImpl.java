package com.hw03.services.auth;

import com.hw03.configs.AppProps;
import com.hw03.model.User;
import com.hw03.services.io.IOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final IOService ioService;

    private final MessageSource messageSource;

    private final AppProps props;

    @Autowired
    public AuthServiceImpl(IOService ioService, MessageSource messageSource, AppProps props) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public User login() {
        String userNameMessage = messageSource.getMessage("auth.name", new String[]{}, props.getLocale());
        String userSurnameMessage = messageSource.getMessage("auth.surname", new String[]{}, props.getLocale());
        String userName = ioService.outputStringAndReadString(userNameMessage);
        String userSurname = ioService.outputStringAndReadString(userSurnameMessage);
        return new User(userName, userSurname);
    }
}

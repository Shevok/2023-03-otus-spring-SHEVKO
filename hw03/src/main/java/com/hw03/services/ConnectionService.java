package com.hw03.services;

import com.hw03.configs.AppProps;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {
    private final AppProps props;

    private final Formatter formatter;

    public ConnectionService(AppProps props, Formatter formatter) {
        this.props = props;
        this.formatter = formatter;
    }

    public String getFileName() {
        return resolveQuestionsFileByLocale();
    }

    private String resolveQuestionsFileByLocale(){
        return formatter.formatFileNameByLocale(props.getFileName(), props.getLocale());
    }
}

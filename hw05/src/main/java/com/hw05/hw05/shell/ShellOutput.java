package com.hw05.hw05.shell;

import com.hw05.hw05.configs.AppProps;
import com.hw05.hw05.converters.PrintConverter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Collection;

@Component
public class ShellOutput {
    private final PrintStream printer = System.out;

    private final AppProps props;

    private final MessageSource messageSource;

    public ShellOutput(AppProps props, MessageSource messageSource) {
        this.props = props;
        this.messageSource = messageSource;
    }

    public void print(String message) {
        printer.println(message);
    }

    public void success(Object objectToPrint, PrintConverter printConverter){
        String message = printConverter.convertSuccess(objectToPrint);
        print(message);
    }

    public void success(Collection objectsToPrint, PrintConverter printConverter){
        objectsToPrint.forEach(objectToPrint -> {
            success(objectToPrint, printConverter);
        });
    }

    public void error() {
        String message = messageSource.getMessage("object.not.found", new String[]{}, props.getLocale());
        print(message);
    }

}

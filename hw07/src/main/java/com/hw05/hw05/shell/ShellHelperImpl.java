package com.hw05.hw05.shell;

import com.hw05.hw05.converters.PrintConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ShellHelperImpl implements ShellHelper{

    private final ShellOutput shellOutput;

    public ShellHelperImpl(ShellOutput shellOutput) {
        this.shellOutput = shellOutput;
    }

    public void validateOptionalObjAndShowResult(Optional optional, PrintConverter printConverter){
        if (optional.isPresent()) {
            shellOutput.success(optional.get(), printConverter);
        } else {
            shellOutput.error();
        }
    }

    public void validateObjColAndShowResult(Collection collection, PrintConverter printConverter){
        if (!collection.isEmpty()) {
            shellOutput.success(collection, printConverter);
        } else {
            shellOutput.error();
        }
    }
}

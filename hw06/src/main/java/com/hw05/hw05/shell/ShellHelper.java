package com.hw05.hw05.shell;

import com.hw05.hw05.converters.PrintConverter;

import java.util.Collection;
import java.util.Optional;

public interface ShellHelper {

     void validateOptionalObjAndShowResult(Optional optional, PrintConverter printConverter);

    void validateObjColAndShowResult(Collection collection, PrintConverter printConverter);
}

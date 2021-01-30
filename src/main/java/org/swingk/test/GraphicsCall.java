package org.swingk.test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GraphicsCall {
    private final String methodName;
    private final List<String> argStr;

    public GraphicsCall(String methodName, Object... args) {
        this.methodName = Objects.requireNonNull(methodName);
        this.argStr = Arrays.stream(args)
                .map(String::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getMethodName() {
        return methodName;
    }

    public List<String> getArgStr() {
        return argStr;
    }
}

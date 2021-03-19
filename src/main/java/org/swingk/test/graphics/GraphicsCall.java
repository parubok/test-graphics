package org.swingk.test.graphics;

import java.awt.Image;
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
                .map(arg -> argumentToString(arg))
                .collect(Collectors.toUnmodifiableList());
    }

    private static String argumentToString(Object argument) {
        if (argument == null) {
            return "null";
        }
        if (argument instanceof Image) {
            return argument.getClass().getSimpleName();
        }
        if (argument.getClass().isArray()) {
            if (argument instanceof char[]) {
                return Arrays.toString((char[]) argument);
            } else if (argument instanceof int[]) {
                return Arrays.toString((int[]) argument);
            }
            return Arrays.toString((Object[]) argument);
        }
        return argument.toString();
    }

    public String getMethodName() {
        return methodName;
    }

    public List<String> getArgStr() {
        return argStr;
    }

    @Override
    public String toString() {
        return getMethodName() + "(" + String.join(", ", getArgStr()) + ")";
    }
}

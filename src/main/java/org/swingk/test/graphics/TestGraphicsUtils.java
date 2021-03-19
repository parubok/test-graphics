package org.swingk.test.graphics;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestGraphicsUtils
{
    private static Graphics createTestGraphics(Graphics2D g) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestGraphics2D.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                String methodName = method.getName();
                ((TestGraphics2D) obj).getCalls().add(new GraphicsCall(methodName, args));
                if (methodName.equals("create")) {
                    return createGraphics(((Graphics) obj).create(), consumer);
                } else {
                    return method.invoke(g, args);
                }
            }
        });
        return (Graphics) enhancer.create();
    }

    public final static class UTestGraphics
    {
        public static final Predicate<String> WITHOUT_FONT_AND_RENDERING_HINT = s -> !s.startsWith("setFont") && !s
                .startsWith("setRenderingHint");
        public final Graphics g2d;
        private final List<String> commands;

        private UTestGraphics(Graphics g2d, List<String> commands)
        {
            this.g2d = g2d;
            this.commands = commands;
        }

        public List<String> getCommands()
        {
            return new ArrayList<>(commands);
        }

        public List<String> getCommands(Predicate<String> predicate)
        {
            return commands.stream().filter(predicate).collect(Collectors.toList());
        }

        public void clearCommands()
        {
            commands.clear();
        }
    }
}


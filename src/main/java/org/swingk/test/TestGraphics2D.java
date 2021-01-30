package org.swingk.test;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestGraphics2D extends Graphics2D {

    private final Graphics2D g2d;
    private boolean inside;

    private final List<GraphicsCall> calls = new ArrayList<>();

    public TestGraphics2D(Graphics2D g2d) {
        this.g2d = Objects.requireNonNull(g2d);
    }

    public List<GraphicsCall> getCalls() {
        return calls;
    }

    @Override
    public void draw(Shape s) {
        if (!inside) {
            inside = true;
            calls.add(new GraphicsCall("draw", s));
        }
        try {
            g2d.draw(s);
        } finally {
            inside = false;
        }
    }
}

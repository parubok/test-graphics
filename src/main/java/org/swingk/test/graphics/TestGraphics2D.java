package org.swingk.test.graphics;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class TestGraphics2D extends Graphics2D {

    private final Graphics2D g2d;
    private final List<GraphicsCall> calls = new ArrayList<>();

    public TestGraphics2D(Graphics2D g2d) {
        this.g2d = Objects.requireNonNull(g2d);
    }

    public List<GraphicsCall> getCalls() {
        return calls;
    }

    public Graphics2D getDelegateGraphics() {
        return g2d;
    }
}

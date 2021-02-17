package graphicseditor;

import java.awt.Graphics2D;

public interface Shape {
    abstract void paintShape(Graphics2D g, int x, int y);
}

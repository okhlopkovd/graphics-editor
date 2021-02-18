package graphicseditor;

import java.awt.Graphics2D;
import java.awt.Color;

public interface Shape {
    abstract void paintShape(Graphics2D g, int x, int y, Color color);
}

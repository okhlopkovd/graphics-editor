package graphicseditor;

import java.awt.Graphics2D;
import java.awt.Color;

public interface Tool {
    abstract void paint(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color);
}

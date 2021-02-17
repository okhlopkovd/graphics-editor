package graphicseditor;

import java.awt.Graphics2D;

public interface Tool {
    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY);
}

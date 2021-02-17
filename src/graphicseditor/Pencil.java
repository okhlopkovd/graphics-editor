package graphicseditor;

import graphicseditor.Tool;

import java.awt.*;

public class Pencil implements Tool {
    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY) {
        g.drawLine(oldX, oldY, curX, curY);
    }
}

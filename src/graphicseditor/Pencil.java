package graphicseditor;

import graphicseditor.Tool;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Pencil implements Tool {
    private int size=1;
    private BasicStroke penStroke = new BasicStroke(1);

    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color) {
        g.setColor(color);
        g.setStroke(penStroke);
        g.drawLine(oldX, oldY, curX, curY);
    }
}

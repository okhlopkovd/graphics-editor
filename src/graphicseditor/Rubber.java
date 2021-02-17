package graphicseditor;

import graphicseditor.Tool;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Rubber implements Tool{
    private int size;
    private BasicStroke rubberStroke;

    public Rubber(int size) {
        this.size = size;
        rubberStroke = new BasicStroke(size);
    }

    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY) {
        g.setStroke(rubberStroke);
        g.setColor(Color.white);
        g.drawLine(oldX, oldY, curX, curY);
    }
}

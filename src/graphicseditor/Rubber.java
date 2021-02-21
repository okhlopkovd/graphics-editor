package graphicseditor;

import graphicseditor.Tool;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Rubber implements Tool{
    private int size;

    public Rubber(int size) { this.size = size; }

    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
        var rubberStroke = new BasicStroke(size * sizeFactor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        g.setStroke(rubberStroke);
        g.setColor(Color.white);

        g.drawLine(oldX, oldY, curX, curY);
    }
}

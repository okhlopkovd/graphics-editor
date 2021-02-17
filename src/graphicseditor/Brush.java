package graphicseditor;

import graphicseditor.Tool;

import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Brush implements Tool{
    private int size;
    private BasicStroke brushStroke;

    public Brush(Graphics2D g, int size) {
        this.size = size;
        brushStroke = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY) {
        g.setStroke(brushStroke);
        g.drawLine(oldX, oldY, curX, curY);
    }
}

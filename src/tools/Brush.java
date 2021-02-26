package tools;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Brush implements Tool {
    private int size;

    public Brush(int size) { this.size = size; }

    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
        var brushStroke = new BasicStroke(sizeFactor * size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        g.setColor(color);
        g.setStroke(brushStroke);
        g.drawLine(oldX, oldY, curX, curY);
    }

    public void reset(Graphics2D g, BufferedImage newImage){ }
}

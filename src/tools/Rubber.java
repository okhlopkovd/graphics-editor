package tools;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Rubber implements Tool {
    private int size;

    public Rubber(int size) { this.size = size; }

    public void paint(Graphics2D imageGraphics, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
        var rubberStroke = new BasicStroke(size * sizeFactor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        imageGraphics.setStroke(rubberStroke);
        imageGraphics.setColor(Color.white);

        imageGraphics.drawLine(oldX, oldY, curX, curY);
    }

    public void reset(Graphics2D g, BufferedImage newImage) {}
}

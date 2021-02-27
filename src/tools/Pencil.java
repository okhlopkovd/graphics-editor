package tools;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Pencil implements Tool {
    private int size = 1;
    private BasicStroke penStroke = new BasicStroke(1);

    public void paint(Graphics2D imageGraphics, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
        imageGraphics.setColor(color);
        imageGraphics.setStroke(penStroke);
        imageGraphics.drawLine(oldX, oldY, curX, curY);
    }

    public void reset(Graphics2D g, BufferedImage newImage) { }
}

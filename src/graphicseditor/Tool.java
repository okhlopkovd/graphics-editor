package graphicseditor;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public interface Tool {
    void paint(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor);

    void reset(Graphics2D g, BufferedImage newImage);
}

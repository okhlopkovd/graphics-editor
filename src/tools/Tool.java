package tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Tool {
    void paint(Graphics2D imageGraphics, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor);

    void reset(Graphics2D imageGraphics, BufferedImage newImage);
}

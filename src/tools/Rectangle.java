package tools;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class Rectangle implements Tool {
    private java.awt.Rectangle currentRect;
    private BufferedImage prevImage;

    private Point anchorPoint = null;
    private int size = 10;

    public Rectangle(BufferedImage prevImage) {
        ColorModel prevModel = prevImage.getColorModel();
        boolean isAlphaMultiplied = prevModel.isAlphaPremultiplied();
        WritableRaster r = prevImage.copyData(null);

        this.prevImage = new BufferedImage(prevModel, r, isAlphaMultiplied, null);
    }

    public void reset(Graphics2D imageGraphics, BufferedImage newImage) {
        ColorModel newModel = newImage.getColorModel();
        boolean isAlphaMultiplied = newModel.isAlphaPremultiplied();
        WritableRaster r = newImage.copyData(null);

        this.prevImage = new BufferedImage(newModel, r, isAlphaMultiplied, null);
        this.anchorPoint = null;
    }

    public void paint(Graphics2D imageGraphics, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
        if (anchorPoint == null) {
            anchorPoint = new Point(oldX, oldY);
            currentRect = new java.awt.Rectangle(anchorPoint);
        }

        imageGraphics.setColor(color);
        imageGraphics.setStroke(new BasicStroke(size * sizeFactor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        currentRect.setRect(
                (int)Math.min(anchorPoint.x, curX),
                (int)Math.min(anchorPoint.y, curY),
                (int)Math.abs(curX-anchorPoint.x),
                (int)Math.abs(curY-anchorPoint.y)
        );

        imageGraphics.drawImage(prevImage, 0, 0, null);
        imageGraphics.draw(currentRect);
    }
}

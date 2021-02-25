package graphicseditor;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Circle implements Tool{
    private Point anchorPoint = null;
    private BufferedImage prevImage;
    private Ellipse2D currentCircle;
    private int size = 10;

    public Circle(BufferedImage prevImage) {
        ColorModel prevModel = prevImage.getColorModel();
        boolean isAlphaMultiplied = prevModel.isAlphaPremultiplied();
        WritableRaster r = prevImage.copyData(null);

        this.prevImage = new BufferedImage(prevModel, r, isAlphaMultiplied, null);
    }

    public void paint(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
        if (anchorPoint == null) {
            anchorPoint = new Point(oldX, oldY);
        }

        g.setColor(color);
        g.setStroke(new BasicStroke(size * sizeFactor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        currentCircle = new Ellipse2D.Double(
                (int)Math.min(anchorPoint.x, curX),
                (int)Math.min(anchorPoint.y, curY),
                (int)Math.abs(curX-anchorPoint.x),
                (int)Math.abs(curX-anchorPoint.x)
        );

        g.drawImage(prevImage, 0, 0, null);
        g.draw(currentCircle);
    }

    public void reset(Graphics2D g, BufferedImage newImage) {
        ColorModel newModel = newImage.getColorModel();
        boolean isAlphaMultiplied = newModel.isAlphaPremultiplied();
        WritableRaster r = newImage.copyData(null);

        this.prevImage = new BufferedImage(newModel, r, isAlphaMultiplied, null);
        this.anchorPoint = null;
    }
}

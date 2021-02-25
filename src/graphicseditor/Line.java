package graphicseditor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class Line implements Tool{
    private Point anchorPoint = null;
    private BufferedImage prevImage;

    private int size = 10;

    public Line(BufferedImage prevImage) {
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

        g.drawImage(prevImage, 0, 0, null);
        g.drawLine(anchorPoint.x, anchorPoint.y, curX, curY);
    }

    public void reset(Graphics2D g, BufferedImage newImage) {
        ColorModel newModel = newImage.getColorModel();
        boolean isAlphaMultiplied = newModel.isAlphaPremultiplied();
        WritableRaster r = newImage.copyData(null);

        this.prevImage = new BufferedImage(newModel, r, isAlphaMultiplied, null);
        this.anchorPoint = null;
    }
}

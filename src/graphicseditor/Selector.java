package graphicseditor;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Selector {

    java.awt.Rectangle currentSelection;
    BufferedImage selectedImage;
    Point anchorPoint;

    Color selectionColor = Color.black;
    BasicStroke selectionStroke = new BasicStroke(1);

    public Selector(BufferedImage selectedImage, Point anchorPoint) {
        ColorModel selectedModel = selectedImage.getColorModel();
        boolean isAlphaMultiplied = selectedModel.isAlphaPremultiplied();
        WritableRaster r = selectedImage.copyData(null);

        this.selectedImage = new BufferedImage(selectedModel, r, isAlphaMultiplied, null);
        this.anchorPoint = new Point(anchorPoint);
        currentSelection = new java.awt.Rectangle(this.anchorPoint);
    }

    public void select(Graphics2D imageGraphics, Point boundPoint) {
        imageGraphics.setColor(selectionColor);
        imageGraphics.setStroke(selectionStroke);

        currentSelection.setRect(
                (int)Math.min(anchorPoint.x, boundPoint.x),
                (int)Math.min(anchorPoint.y, boundPoint.y),
                (int)Math.abs(boundPoint.x-anchorPoint.x),
                (int)Math.abs(boundPoint.y-anchorPoint.y)
        );

        imageGraphics.drawImage(selectedImage, 0, 0, null);
        imageGraphics.draw(currentSelection);
    }
    

    public void reset(Graphics2D imageGraphics) {
        imageGraphics.drawImage(selectedImage, 0, 0, null);
    }
}

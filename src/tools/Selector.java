package tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Selector {

    java.awt.Rectangle currentSelection;
    BufferedImage selectedImage;
    BufferedImage buffer = null;

    Point anchorPoint = null;
    Point boundPoint = null;

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

        this.boundPoint = boundPoint;
    }

    public void copy(Graphics2D imageGraphics) {
        if (anchorPoint != null && boundPoint != null) {
            buffer = selectedImage.getSubimage(
                    (int) Math.min(anchorPoint.x, boundPoint.x),
                    (int) Math.min(anchorPoint.y, boundPoint.y),
                    (int) Math.abs(boundPoint.x - anchorPoint.x),
                    (int) Math.abs(boundPoint.y - anchorPoint.y)
            );
        }
    }

    public void paste(Graphics2D imageGraphics) {
        if (buffer != null) {
            imageGraphics.drawImage(
                    buffer,
                    (int) Math.min(anchorPoint.x, boundPoint.x),
                    (int) Math.min(anchorPoint.y, boundPoint.y),
                    (int) Math.abs(boundPoint.x - anchorPoint.x),
                    (int) Math.abs(boundPoint.y - anchorPoint.y),
                    null
            );
        }
    }

    public void reset(Graphics2D imageGraphics) {
        imageGraphics.drawImage(selectedImage, 0, 0, null);
    }

    public void setAnchorPoint(Point anchorPoint) {
        this.anchorPoint = anchorPoint;
    }

    public java.awt.Rectangle getSelection() {
        return currentSelection;
    }

    public void updateImage(BufferedImage newImage) {
        ColorModel newModel = newImage.getColorModel();
        boolean isAlphaMultiplied = newModel.isAlphaPremultiplied();
        WritableRaster r = newImage.copyData(null);

        this.selectedImage = new BufferedImage(newModel, r, isAlphaMultiplied, null);
    }
}

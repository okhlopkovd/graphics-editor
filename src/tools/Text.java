package tools;

import java.awt.Color;
import java.awt.Point;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Text{
    private Point anchorPoint = null;
    private Point boundPoint = null;
    private Selector currentTextBox;
    private String currentText="";
    private int maxAmount;
    private int currentAmount;


    public Text(BufferedImage prevImage, Point anchorPoint) {
        this.anchorPoint = anchorPoint;
        currentTextBox = new Selector(prevImage, this.anchorPoint);
    }

    public void selectArea(Graphics2D imageGraphics, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
       currentTextBox.select(imageGraphics, new Point(curX, curY));
       boundPoint = new Point(curX, curY);
       maxAmount = Math.abs(boundPoint.x - anchorPoint.x);
    }

    public void drawText(Graphics2D imageGraphics, char textChar) {
        currentText += textChar;

        imageGraphics.setFont(new Font("TimesRoman", Font.PLAIN, Math.abs(anchorPoint.y - boundPoint.y)));
        FontMetrics textMetrics = imageGraphics.getFontMetrics();

        currentAmount = textMetrics.stringWidth(currentText);
        int heightOffset = 5;

        if (currentAmount < maxAmount) {
            imageGraphics.drawString(currentText, Math.min(anchorPoint.x, boundPoint.x), Math.max(anchorPoint.y, boundPoint.y)-heightOffset);
        }
    }

    public void updateImage(BufferedImage newImage) {
        currentTextBox.updateImage(newImage);
    }

    public void reset(Graphics2D imageGraphics) {
        currentText = "";
        java.awt.Rectangle currentSelection = currentTextBox.getSelection();
        imageGraphics.setColor(Color.white);
        imageGraphics.draw(currentSelection);
    }

    public void setAnchorPoint(Point newPoint) {
        this.anchorPoint = new Point(newPoint);
        currentTextBox.setAnchorPoint(newPoint);
    }
}

package graphicseditor;

import java.awt.*;
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

    public void selectArea(Graphics2D g, int oldX, int oldY, int curX, int curY, Color color, int sizeFactor) {
       currentTextBox.select(g, new Point(curX, curY));
       boundPoint = new Point(curX, curY);
       maxAmount = Math.abs(boundPoint.x - anchorPoint.x);
    }

    public void drawText(Graphics2D g, char textChar) {
        currentText += textChar;

        g.setFont(new Font("TimesRoman", Font.PLAIN, Math.abs(anchorPoint.y - boundPoint.y)));
        FontMetrics textMetrics = g.getFontMetrics();

        currentAmount = textMetrics.stringWidth(currentText);
        int heightOffset = 5;

        if (currentAmount < maxAmount) {
            g.drawString(currentText, Math.min(anchorPoint.x, boundPoint.x), Math.max(anchorPoint.y, boundPoint.y)-heightOffset);
        }
    }

    public void updateImage(BufferedImage newImage) {
        currentTextBox.updateImage(newImage);
    }

    public void reset(Graphics2D g) {
        currentText = "";
        java.awt.Rectangle currentSelection = currentTextBox.getSelection();
        g.setColor(Color.white);
        g.draw(currentSelection);
    }

    public void setAnchorPoint(Point newPoint) {
        this.anchorPoint = new Point(newPoint);
        currentTextBox.setAnchorPoint(newPoint);
    }
}

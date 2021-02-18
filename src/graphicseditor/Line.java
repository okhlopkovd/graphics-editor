package graphicseditor;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import graphicseditor.Shape;


public class Line implements Shape{
    private int clicks = 0;
    private int x = 0;
    private int y = 0;

    public void paintShape(Graphics2D g, int x, int y, Color color) {
        if(clicks == 0) {
            this.x = x;
            this.y = y;

            clicks++;
        }
        else {
            g.setColor(color);
            g.setStroke(new BasicStroke(10));
            g.drawLine(this.x, this.y, x, y);
            clicks = 0;
        }
    }
}

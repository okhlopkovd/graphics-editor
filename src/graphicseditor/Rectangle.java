package graphicseditor;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import graphicseditor.Shape;

public class Rectangle implements Shape{
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

            int width = Math.abs(x - this.x);
            int height = Math.abs(y - this.y);
            g.drawRect(Math.min(this.x, x), Math.min(this.y, y), width, height);

            clicks = 0;
        }
    }
}

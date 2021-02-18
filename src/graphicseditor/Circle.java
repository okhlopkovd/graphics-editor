package graphicseditor;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import graphicseditor.Shape;


public class Circle implements Shape{
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

            double diffx = Math.pow(this.x - x, 2);
            double diffy = Math.pow(this.y - y, 2);
            int radius = (int) Math.sqrt(diffx + diffy);

            g.drawOval(x - radius, y - radius, 2*radius, 2*radius);

            clicks = 0;
        }
    }
}

package graphicseditor;

import javax.imageio.ImageIO;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.*;

import java.awt.Point;
import java.awt.Rectangle;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;

import java.io.File;
import java.io.IOException;


public class DrawingArea extends JPanel implements MouseListener, MouseMotionListener{
    private BufferedImage image;
    private Graphics2D graphics;

    private int curX, curY;
    private int oldX, oldY;

    private int width;
    private int height;

    private double scale = 1.0;
    private int currentFactor = 1;

    private boolean zoomMode = false;
    private boolean selectionMode = false;
    private boolean textMode = false;

    private Tool currentTool = new Pencil();
    private Color currentColor = Color.black;
    private Selector currentSelection = null;

    public DrawingArea(int preferredWidth, int preferredHeight) {
        width = preferredWidth;
        height = preferredHeight;

        setLayout(new BorderLayout());

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void mouseDragged(MouseEvent e) {
        if (selectionMode) {
            currentSelection.select(graphics, e.getPoint());
            repaint();
        }
        else {
            curX = (int)(e.getX() / scale);
            curY = (int)(e.getY() / scale);

            if (graphics != null) {
                currentTool.paint(graphics, oldX, oldY, curX, curY, currentColor, currentFactor);
                repaint();

                oldX = curX;
                oldY = curY;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (zoomMode) {
            scale *= 2;

            if (scale > 4) {
                scale = 1;
                zoomMode = false;
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (selectionMode) {
            if (currentSelection == null) {
                currentSelection = new Selector(image, e.getPoint());
            }
            else {
                currentSelection.updateImage(image);
                currentSelection.reset(graphics);
                currentSelection.setAnchorPoint(e.getPoint());
            }
        }
        else {
            oldX = (int)(e.getX()/ scale);
            oldY = (int)(e.getY()/ scale);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentTool.reset(graphics, image);
        if(selectionMode) { currentSelection.reset(graphics); }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent){ }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) { }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            image = (BufferedImage) createImage(width, height);
            graphics = (Graphics2D) image.getGraphics();
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            clear();
        }

        if (zoomMode) {
            g.drawImage(image.getScaledInstance((int)(width*scale), (int)(height*scale), Image.SCALE_FAST), 0, 0, null);
        }
        else {
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(scale * width), (int)(scale*height));
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, width, height);
        graphics.setPaint(Color.black);
        repaint();
    }

    public void brush() {
        currentTool = new Brush(10);
        zoomMode = false;
        selectionMode = false;
    }

    public void pen() {
        currentTool = new Pencil();
        selectionMode = false;
    }

    public void rubber() {
        currentTool = new Rubber(10);
        zoomMode = false;
        selectionMode = false;
    }

    public void line() {
        currentTool = new Line(image);
        selectionMode = false;
        zoomMode = false;
    }

    public void rectangle() {
        selectionMode = false;
        zoomMode = false;
        currentTool = new graphicseditor.Rectangle(image);
    }

    public void circle() {
        selectionMode = false;
        zoomMode = false;
        currentTool = new graphicseditor.Circle(image);
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    public void setSelectionMode() {
        selectionMode = true;
    }

    public void copy() {
        if (selectionMode) {
            currentSelection.copy(graphics);
        }
        selectionMode = false;
    }

    public void paste() {
        if (selectionMode) {
            currentSelection.paste(graphics);
        }
        selectionMode = false;
        repaint();
    }

    public void load(String loadPath) {
        BufferedImage loadingImage=null;

        try {
            loadingImage = ImageIO.read(new File(loadPath));
        }
        catch (IOException ex) {
            ex.getLocalizedMessage();
        }

        graphics.drawImage(loadingImage, 0, 0, null);
        repaint();
    }

    public void save(String savePath) {
        BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D savedGrapics = buffer.createGraphics();
        savedGrapics.drawImage(image, 0, 0, null);

        try {
            ImageIO.write(buffer, "PNG", new File(savePath));
        }
        catch (IOException ex) {
            ex.getLocalizedMessage();
        }
    }

    public void setText() {
        textMode = true;
    }

    public void setSizeFactor(int newFactor) { currentFactor = newFactor; }

    public void setToZoomMode() { zoomMode = true;}


}

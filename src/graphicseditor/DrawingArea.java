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


public class DrawingArea extends JPanel{
    private BufferedImage image;
    private Graphics2D graphics;

    private int curX, curY;
    private int oldX, oldY;

    private int width;
    private int height;

    private double scale = 1.0;
    private int currentFactor = 1;

    private boolean zoomMode = false;
    private boolean shapeMode = false;
    private boolean selectionMode = false;

    private Shape currentShape;
    private Point anchor;

    private Tool currentTool = new Pencil();
    private Color currentColor = Color.black;
    private Selector currentSelection;

    private BufferedImage tempImage;

    public DrawingArea(int preferredWidth, int preferredHeight) {
        width = preferredWidth;
        height = preferredHeight;

        setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                 if (selectionMode) {
                    anchor = e.getPoint();
                    currentSelection = new Selector(image, anchor);
                }
                else if(!shapeMode) {
                    oldX = (int)(e.getX()/ scale);
                    oldY = (int)(e.getY()/ scale);
                }
            }

            public void mouseClicked(MouseEvent e) {
                if (shapeMode) {
                    currentShape.paintShape(graphics, e.getX(), e.getY(), currentColor);
                }
                else if (zoomMode) {
                    scale *= 2;

                    if (scale > 4) {
                        scale = 1;
                        zoomMode = false;
                    }
                }

                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if(selectionMode) { currentSelection.reset(graphics); }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selectionMode) {
                    currentSelection.select(graphics, e.getPoint());
                    repaint();
                }
                else if (!shapeMode) {
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
        });
    }

    private BufferedImage deepCopy() {
        ColorModel cm = image.getColorModel();
        boolean isAlphaMultiplied = cm.isAlphaPremultiplied();
        WritableRaster r = image.copyData(null);
        return new BufferedImage(cm, r, isAlphaMultiplied, null);
    }


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
        shapeMode = false;
        selectionMode = false;
    }

    public void pen() {
        currentTool = new Pencil();
        shapeMode = false;
        selectionMode = false;
    }

    public void rubber() {
        currentTool = new Rubber(10);
        shapeMode = false;
        selectionMode = false;
    }

    public void line() {
        shapeMode = true;
        currentShape = new Line();
        selectionMode = false;
    }

    public void rectangle() {
        shapeMode = true;
        selectionMode = false;
        currentShape = new graphicseditor.Rectangle();
    }

    public void circle() {
        shapeMode = true;
        selectionMode = false;
        currentShape = new Circle();
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    public void setSelectionMode() {
        selectionMode = true;
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

    public void setSizeFactor(int newFactor) { currentFactor = newFactor; }

    public void setToZoomMode() { zoomMode = true;}
}

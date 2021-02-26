package graphicseditor;

import java.awt.*;
import tools.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor implements ActionListener{
    private JFrame window;
    private DrawingArea drawArea;

    final private int windowWidth;
    final private int windowHeight;

    final private int defaultCursor = Cursor.DEFAULT_CURSOR;
    final private int shapeCursor = Cursor.NE_RESIZE_CURSOR;
    final private int selectionCursor = Cursor.HAND_CURSOR;
    final private int magnifierCursor = Cursor.CROSSHAIR_CURSOR;
    final private int textCursor = Cursor.TEXT_CURSOR;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu toolsMenu;
    private JMenu colorsMenu;
    private JMenu sizeMenu;

    private JToolBar toolbar;

    JMenuItem newItem;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem closeItem;

    JMenuItem selectionItem;
    JMenuItem copyItem;
    JMenuItem pasteItem;

    JMenuItem pencilItem;
    JMenuItem brushItem;
    JMenuItem rubberItem;
    JMenuItem magnifierItem;
    JMenuItem lineItem;
    JMenuItem rectangleItem;
    JMenuItem circleItem;
    JMenuItem textItem;

    JMenuItem whiteColorItem;
    JMenuItem blackColorItem;
    JMenuItem redColorItem;
    JMenuItem purpleColorItem;
    JMenuItem pinkColorItem;
    JMenuItem blueColorItem;
    JMenuItem greenColorItem;

    JButton newButton;
    JButton openButton;
    JButton saveButton;

    JButton selectionButton;
    JButton copyButton;
    JButton pasteButton;

    JButton pencilButton;
    JButton brushButton;
    JButton rubberButton;
    JButton magnifierButton;
    JButton lineButton;
    JButton rectangleButton;
    JButton circleButton;
    JButton textButton;

    JMenuItem defaultSizeItem;
    JMenuItem doubleSizeItem;
    JMenuItem tripleSizeItem;


    public Editor(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        createWindow();
        createMenu();

        createFileMenu();
        createEditMenu();
        createToolMenu();
        createColorMenu();
        createSizeMenu();

        createToolbar();

        window.setJMenuBar(menuBar);
        window.setFocusable(true);
        window.setVisible(true);
    }

    private void createWindow() {
        window = new JFrame();

        window.setTitle("Graphics Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowWidth, windowHeight);

        drawArea = new DrawingArea(windowWidth, windowHeight);
        drawArea.setFocusable(true);
        drawArea.requestFocus();

        Container content = window.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(drawArea, BorderLayout.CENTER);

        JScrollPane scrollbar = new JScrollPane(drawArea);
        window.add(scrollbar);
    }

    private void createMenu() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        toolsMenu = new JMenu("Tools");
        colorsMenu = new JMenu("Colors");
        sizeMenu = new JMenu("Size");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);
        menuBar.add(colorsMenu);
        menuBar.add(sizeMenu);
    }

    private void createFileMenu() {
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
    }

    private void createEditMenu() {
        selectionItem = new JMenuItem("Selection");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");

        selectionItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);

        editMenu.add(selectionItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
    }

    private void createToolMenu() {
        pencilItem = new JMenuItem("Pencil");
        brushItem = new JMenuItem("Brush");
        rubberItem = new JMenuItem("Rubber");
        magnifierItem = new JMenuItem("Magnifier");
        lineItem = new JMenuItem("Line");
        rectangleItem = new JMenuItem("Rectangle");
        circleItem = new JMenuItem("Circle");
        textItem = new JMenuItem("Text");

        pencilItem.addActionListener(this);
        brushItem.addActionListener(this);
        rubberItem.addActionListener(this);
        lineItem.addActionListener(this);
        rectangleItem.addActionListener(this);
        circleItem.addActionListener(this);
        magnifierItem.addActionListener(this);

        toolsMenu.add(pencilItem);
        toolsMenu.add(brushItem);
        toolsMenu.add(rubberItem);
        toolsMenu.add(magnifierItem);
        toolsMenu.add(lineItem);
        toolsMenu.add(rectangleItem);
        toolsMenu.add(circleItem);
    }

    private void createColorMenu() {
        whiteColorItem = new JMenuItem("White");
        blackColorItem = new JMenuItem("Black");
        redColorItem = new JMenuItem("Red");
        purpleColorItem = new JMenuItem("Purple");
        pinkColorItem = new JMenuItem("Pink");
        blueColorItem = new JMenuItem("Blue");
        greenColorItem = new JMenuItem("Green");

        colorsMenu.add(whiteColorItem);
        colorsMenu.add(blackColorItem);
        colorsMenu.add(redColorItem);
        colorsMenu.add(purpleColorItem);
        colorsMenu.add(pinkColorItem);
        colorsMenu.add(blueColorItem);
        colorsMenu.add(greenColorItem);

        whiteColorItem.addActionListener(e -> drawArea.setColor(Color.white));
        blackColorItem.addActionListener(e -> drawArea.setColor(Color.black));
        redColorItem.addActionListener(e -> drawArea.setColor(Color.red));
        purpleColorItem.addActionListener(e -> drawArea.setColor(Color.magenta));
        pinkColorItem.addActionListener(e -> drawArea.setColor(Color.pink));
        blueColorItem.addActionListener(e -> drawArea.setColor(Color.blue));
        greenColorItem.addActionListener(e -> drawArea.setColor(Color.green));
    }

    private void createSizeMenu() {
        defaultSizeItem = new JMenuItem("1x");
        doubleSizeItem = new JMenuItem("2x");
        tripleSizeItem= new JMenuItem("3x");

        defaultSizeItem.addActionListener(e -> drawArea.setSizeFactor(1));
        doubleSizeItem.addActionListener(e -> drawArea.setSizeFactor(2));
        tripleSizeItem.addActionListener(e -> drawArea.setSizeFactor(3));

        sizeMenu.add(defaultSizeItem);
        sizeMenu.add(doubleSizeItem);
        sizeMenu.add(tripleSizeItem);
    }

    private void createToolbar(){
        toolbar = new JToolBar();

        newButton = new JButton("New");
        openButton = new JButton("Open");
        saveButton = new JButton("Save");

        selectionButton = new JButton("Selection");
        copyButton = new JButton("Copy");
        pasteButton = new JButton("Paste");

        pencilButton = new JButton("Pencil");
        brushButton = new JButton("Brush");
        rubberButton = new JButton("Rubber");
        magnifierButton = new JButton("Magnifier");
        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");
        textButton = new JButton("Text");

        newButton.addActionListener(this);
        openButton.addActionListener(this);
        saveButton.addActionListener(this);

        selectionButton.addActionListener(this);
        copyButton.addActionListener(this);
        pasteButton.addActionListener(this);

        pencilButton.addActionListener(this);
        brushButton.addActionListener(this);
        rubberButton.addActionListener(this);
        lineButton.addActionListener(this);
        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        magnifierButton.addActionListener(this);
        textButton.addActionListener(this);

        toolbar.add(newButton);
        toolbar.add(openButton);
        toolbar.add(saveButton);

        toolbar.add(selectionButton);
        toolbar.add(copyButton);
        toolbar.add(pasteButton);

        toolbar.add(pencilButton);
        toolbar.add(brushButton);
        toolbar.add(rubberButton);
        toolbar.add(magnifierButton);
        toolbar.add(lineButton);
        toolbar.add(rectangleButton);
        toolbar.add(circleButton);

        toolbar.add(textButton);
        window.add(toolbar, BorderLayout.NORTH);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem || e.getSource() == newButton) {
            drawArea.clear();
        }
        else if (e.getSource() == openItem || e.getSource() == openButton) {
            FileDialog fileDialog = new FileDialog(window, "open", FileDialog.LOAD);
            fileDialog.setVisible(true);

            String loadPath = fileDialog.getDirectory() + fileDialog.getFile();
            drawArea.load(loadPath);
        }
        else if (e.getSource() == saveItem || e.getSource() == saveButton) {
            FileDialog fileDialog = new FileDialog(window, "save", FileDialog.SAVE);

            fileDialog.setFile("Untitled.png");
            fileDialog.setVisible(true);

            String savePath = fileDialog.getDirectory() + fileDialog.getFile();
            drawArea.save(savePath);
        }
        else if (e.getSource() == closeItem) {
            System.exit(0);
        }
        else if (e.getSource() == selectionItem || e.getSource() == selectionButton) {
            drawArea.setCursor(new Cursor(selectionCursor));
            drawArea.setTextMode(false);
            drawArea.setSelectionMode(true);
        }
        else if (e.getSource() == copyItem || e.getSource() == copyButton) {
            drawArea.copy();
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == pasteItem || e.getSource() == pasteButton) {
            drawArea.paste();
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == pencilItem || e.getSource() == pencilButton) {
            drawArea.setCursor(new Cursor(defaultCursor));

            var pencilTool = new Pencil();
            drawArea.setTool(pencilTool);

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == brushItem || e.getSource() == brushButton) {
            drawArea.setCursor(new Cursor(defaultCursor));

            var brushTool = new Brush(10);
            drawArea.setTool(new Brush(10));

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == rubberItem || e.getSource() == rubberButton) {
            drawArea.setCursor(new Cursor(defaultCursor));

            var rubberTool = new Rubber(10);
            drawArea.setTool(rubberTool);

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == magnifierItem || e.getSource() == magnifierButton) {
            drawArea.setCursor(new Cursor(magnifierCursor));
            drawArea.setZoomMode(true);
        }
        else if (e.getSource() == lineItem || e.getSource() == lineButton) {
            drawArea.setCursor(new Cursor(shapeCursor));

            var lineTool = new tools.Line(drawArea.getImage());
            drawArea.setTool(lineTool);

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == rectangleItem || e.getSource() == rectangleButton) {
            drawArea.setCursor(new Cursor(shapeCursor));

            var rectangleTool = new tools.Rectangle(drawArea.getImage());
            drawArea.setTool(rectangleTool);

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == circleItem || e.getSource() == circleButton) {
            drawArea.setCursor(new Cursor(shapeCursor));

            var circleTool = new tools.Circle(drawArea.getImage());
            drawArea.setTool(circleTool);

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(false);
        }
        else if (e.getSource() == textItem || e.getSource() == textButton) {
            drawArea.setCursor(new Cursor(textCursor));

            drawArea.setSelectionMode(false);
            drawArea.setTextMode(true);
        }
    }
}
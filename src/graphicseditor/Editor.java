package graphicseditor;

import java.awt.FileDialog;
import java.awt.Color;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Container;

import java.io.File;

import graphicseditor.DrawingArea;

public class Editor implements ActionListener{
    private JFrame window;
    private DrawingArea drawArea;

    private int windowWidth;
    private int windowHeight;

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
        window.setVisible(true);
    }

    private void createWindow() {
        window = new JFrame();

        window.setTitle("Graphics Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowWidth, windowHeight);

        drawArea = new DrawingArea(windowWidth, windowHeight);
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

        newItem .addActionListener(this);
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

        whiteColorItem.addActionListener(this);
        blackColorItem.addActionListener(this);
        redColorItem.addActionListener(this);
        purpleColorItem.addActionListener(this);
        pinkColorItem.addActionListener(this);
        blueColorItem.addActionListener(this);
        greenColorItem.addActionListener(this);
    }

    private void createSizeMenu() {
        defaultSizeItem = new JMenuItem("1x");
        doubleSizeItem = new JMenuItem("2x");
        tripleSizeItem= new JMenuItem("3x");

        defaultSizeItem.addActionListener(this);
        doubleSizeItem.addActionListener(this);
        tripleSizeItem.addActionListener(this);

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

        selectionButton.addActionListener(e -> drawArea.setSelectionMode());
        copyButton.addActionListener(e -> drawArea.copy());
        pasteButton.addActionListener(e -> drawArea.paste());

        pencilButton.addActionListener(e -> drawArea.pen());
        brushButton.addActionListener(e -> drawArea.brush());
        rubberButton.addActionListener(e -> drawArea.rubber());
        lineButton.addActionListener(e -> drawArea.line());
        rectangleButton.addActionListener(e -> drawArea.rectangle());
        circleButton.addActionListener(e -> drawArea.circle());
        magnifierButton.addActionListener(e -> drawArea.setToZoomMode());

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

        window.add(toolbar, BorderLayout.NORTH);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == whiteColorItem) { drawArea.setColor(Color.white); }
        else if (e.getSource() == blackColorItem) { drawArea.setColor(Color.black); }
        else if (e.getSource() == redColorItem) { drawArea.setColor(Color.red); }
        else if (e.getSource() == purpleColorItem) { drawArea.setColor(Color.magenta); }
        else if (e.getSource() == pinkColorItem) { drawArea.setColor(Color.pink); }
        else if (e.getSource() == blueColorItem) { drawArea.setColor(Color.blue); }
        else if (e.getSource() == greenColorItem) { drawArea.setColor(Color.green); }
        else if (e.getSource() == newItem) { drawArea.clear(); }
        else if (e.getSource() == openItem) {
            FileDialog fileDialog = new FileDialog(window, "open", FileDialog.LOAD);
            fileDialog.setVisible(true);

            String loadPath = fileDialog.getDirectory() + fileDialog.getFile();
            drawArea.load(loadPath);
        }
        else if (e.getSource() == saveItem) {
            FileDialog fileDialog = new FileDialog(window, "save", FileDialog.SAVE);

            fileDialog.setFile("Untitled.png");
            fileDialog.setVisible(true);

            String savePath = fileDialog.getDirectory() + fileDialog.getFile();
            drawArea.save(savePath);
        }
        else if (e.getSource() == closeItem) { System.exit(0); }
        else if (e.getSource() == defaultSizeItem) { drawArea.setSizeFactor(1); }
        else if (e.getSource() == doubleSizeItem) { drawArea.setSizeFactor(2); }
        else if (e.getSource() == tripleSizeItem) { drawArea.setSizeFactor(3); }
    }
}
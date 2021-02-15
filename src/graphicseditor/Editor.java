package graphicseditor;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JFrame implements ActionListener{
    private JFrame window;

    public Editor(int windowWidth, int windowHeight) {
        // creating a window

        window = new JFrame();

        window.setTitle("Graphics Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowWidth, windowHeight);

        // creating a menu

        JMenuBar menuBar = new JMenuBar();

        // file menu

        JMenu fileMenu = new JMenu("File");

        JMenuItem fileMenuItem1 = new JMenuItem("New");
        JMenuItem fileMenuItem2 = new JMenuItem("Open");
        JMenuItem fileMenuItem3 = new JMenuItem("Save");
        JMenuItem fileMenuItem4 = new JMenuItem("Close");

        fileMenuItem1.addActionListener(this);
        fileMenuItem2.addActionListener(this);
        fileMenuItem3.addActionListener(this);
        fileMenuItem4.addActionListener(this);

        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(fileMenuItem3);
        fileMenu.add(fileMenuItem4);

        //  edit menu

        JMenu editMenu = new JMenu("Edit");

        JMenuItem editMenuItem1 = new JMenuItem("Cut");
        JMenuItem editMenuItem2 = new JMenuItem("Copy");
        JMenuItem editMenuItem3 = new JMenuItem("Paste");

        editMenu.add(editMenuItem1);
        editMenu.add(editMenuItem2);
        editMenu.add(editMenuItem3);

        // instruments menu

        JMenu instrumentsMenu = new JMenu("Instruments");

        JMenuItem instrumentsMenuItem1 = new JMenuItem("Pencil");
        JMenuItem instrumentsMenuItem2 = new JMenuItem("Brush");
        JMenuItem instrumentsMenuItem3 = new JMenuItem("Rubber");
        JMenuItem instrumentsMenuItem4 = new JMenuItem("Magnifier");
        JMenuItem instrumentsMenuItem5 = new JMenuItem("Line");
        JMenuItem instrumentsMenuItem6 = new JMenuItem("Rectangle");
        JMenuItem instrumentsMenuItem7 = new JMenuItem("Circle");

        instrumentsMenu.add(instrumentsMenuItem1);
        instrumentsMenu.add(instrumentsMenuItem2);
        instrumentsMenu.add(instrumentsMenuItem3);
        instrumentsMenu.add(instrumentsMenuItem4);
        instrumentsMenu.add(instrumentsMenuItem5);
        instrumentsMenu.add(instrumentsMenuItem6);
        instrumentsMenu.add(instrumentsMenuItem7);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(instrumentsMenu);

        window.setJMenuBar(menuBar);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
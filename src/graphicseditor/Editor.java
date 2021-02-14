package graphicseditor;

import javax.swing.JFrame;

public class Editor {
    private JFrame window;

    public Editor(int windowWidth, int windowHeight) {
        window = new JFrame();

        window.setTitle("Graphics Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(windowWidth, windowHeight);
        window.setVisible(true);
    }
}
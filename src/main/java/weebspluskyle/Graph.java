package weebspluskyle;

import java.awt.*;
import javax.swing.*;

public class Graph extends JPanel {
    private int size;

    public Graph(int x, int y, int initSize) {
        size = initSize;
        setBounds(x, y, size, size);
        setBackground(Color.WHITE);
    }

    public void draw(String equation, int xMin, int yMin, int xMax, int yMax, double stepSize) {
        System.out.println("Equation: " + equation);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    // defaulted to 10 by 10 grid, remind me to make it a paremeter later
    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int x = (size / 10); x < size; x += (size / 10)) {
            g.drawLine(x, 0, x, size);
        }
        for (int y = (size / 10); y < size; y += (size / 10)) {
            g.drawLine(0, y, size, y);
        }
        g.setColor(Color.BLACK);
        g.drawLine(size / 2, 0, size / 2, size);
        g.drawLine(0, size / 2, size, size / 2);
    }
}

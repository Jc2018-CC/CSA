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

    public static void draw(String equation, double xMin, double yMin, double xMax, double yMax, int stepSize) {
        System.out.println("Equation: " + equation);
        System.out.println("xMin: " + xMin);
        System.out.println("yMin: " + yMin);
        System.out.println("xMax: " + xMax);
        System.out.println("yMax: " + yMax);
        System.out.println("Equation: " + stepSize);
        //not working below
        //repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    // defaulted to 10 by 10 grid, remind me to make it a paremeter later
    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        int gridWidth = size / 10;

        for (int x = gridWidth; x < size; x += (size / 10)) {
            g.drawLine(x, 0, x, size);
        }
        for (int y = gridWidth; y < size; y += (size / 10)) {
            g.drawLine(0, y, size, y);
        }
        g.setColor(Color.BLACK);
        g.drawLine(size / 2, 0, size / 2, size);
        g.drawLine(0, size / 2, size, size / 2);
    }
}

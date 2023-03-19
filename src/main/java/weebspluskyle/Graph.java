package weebspluskyle;

import java.awt.*;
import javax.swing.*;

public class Graph extends JPanel {
    private int size;
    private double xMin;
    private double yMin;
    private double xMax;
    private double yMax;
    private int step;
    private Expression e;

    public Graph(int x, int y, int initSize) {
        size = initSize;
        xMin = 0;
        xMax = 0;
        yMin = 0;
        yMax = 0;
        e = null;

        setBounds(x, y, size, size);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawSlopes(g);
    }

    public void draw(Expression e, double xMin, double yMin, double xMax, double yMax, int step) {
        this.e = e;
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        this.step = step;
        repaint();
    }

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

    private void drawSlopes(Graphics g) {
        if (e == null) {
            return;
        }

        System.out.println(xMax);
        System.out.println(xMin);
        System.out.println(yMax);
        System.out.println(yMin);

        double xIncrement = (xMax - xMin) / step;
        double yIncrement = (yMax - yMin) / step;
        double xScaler = size / (xMax - xMin);
        double yScaler = size / (yMax - yMin);
        double dashSize = size / (2 * step);
        double m = 0;
        for (int i = 0; i < step; i++) {
            for (int j = 0; j < step; j++) {
                double xMid = xMin + (0.5 + i) * xIncrement;
                double yMid = yMin + (0.5 + j) * yIncrement;
                m = e.evaluate(xMid, yMid);
                m *= (yScaler / xScaler);
                xMid = xScaler * (xMid - xMin);
                yMid = -(yScaler * (yMid - yMin) - size);
                double deltaX = (dashSize) / (Math.sqrt(1 + m * m));
                double deltaY = (dashSize * m) / (Math.sqrt(1 + m * m));
                double x1 = xMid - deltaX;
                double y1 = yMid + deltaY;
                double x2 = xMid + deltaX;
                double y2 = yMid - deltaY;
                g.setColor(Color.BLACK);
                g.drawLine((int) (x1), (int) (y1), (int) (x2), (int) (y2));
            }
        }
    }
}

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

    public Graph() {
        size = 380;
        xMin = 0;
        xMax = 0;
        yMin = 0;
        yMax = 0;
        e = null;

        setPreferredSize(new Dimension(360, 360));
        setMinimumSize(getPreferredSize());
        setMaximumSize(new Dimension(360, 360));
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

    public void clear() {
        this.e = null;
        this.xMin = 0;
        this.yMin = 0;
        this.xMax = 0;
        this.yMax = 0;
        this.step = 0;
        repaint();
    }

    private void drawGrid(Graphics g) {
        int gridWidth = size / 10;
        g.setColor(Color.BLACK);
        int xAxis = (int) (size * (-xMin / (xMax - xMin)));
        g.drawLine(xAxis, 0, xAxis, size);
        int yAxis = (int) (-size * (-yMin / (yMax - yMin))) + size;
        g.drawLine(0, yAxis, size, yAxis);
        g.setColor(Color.LIGHT_GRAY);
        int x = xAxis - gridWidth;
        while (x > 0) {
            g.drawLine(x, 0, x, size);
            x -= gridWidth;
        }
        x = xAxis + gridWidth;
        while (x < size) {
            g.drawLine(x, 0, x, size);
            x += gridWidth;
        }
        int y = yAxis - gridWidth;
        while (y > 0) {
            g.drawLine(0, y, size, y);
            y -= gridWidth;
        }
        y = yAxis + gridWidth;
        while (y < size) {
            g.drawLine(0, y, size, y);
            y += gridWidth;
        }
    }

    private void drawSlopes(Graphics g) {
        if (e == null) {
            return;
        }

        double xIncrement = (xMax - xMin) / step;
        double yIncrement = (yMax - yMin) / step;
        double xScaler = size / (xMax - xMin);
        double yScaler = size / (yMax - yMin);
        double dashSize = size / (2.5 * step);
        double m = 0;
        for (int i = 0; i < step; i++) {
            for (int j = 0; j < step; j++) {
                double xMid = xMin + (0.5 + i) * xIncrement;
                double yMid = yMin + (0.5 + j) * yIncrement;
                m = e.evaluate(xMid, yMid);
                Color c = new Color((int) (255 / (1 + Math.exp(m))), 0, (int) (255 / (1 + Math.exp(-m))));
                m *= (yScaler / xScaler);
                xMid = xScaler * (xMid - xMin);
                yMid = -(yScaler * (yMid - yMin) - size);
                double deltaX = (dashSize) / (Math.sqrt(1 + m * m));
                double deltaY = (dashSize * m) / (Math.sqrt(1 + m * m));
                int x1 = (int) (xMid - deltaX);
                int y1 = (int) (yMid + deltaY);
                int x2 = (int) (xMid + deltaX);
                int y2 = (int) (yMid - deltaY);
                g.setColor(c);
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}

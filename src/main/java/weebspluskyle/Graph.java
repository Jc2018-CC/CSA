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



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        draw(g, -4, -4, 4, 4, 8);
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
    private void draw(Graphics g, double xMinSet, double yMinSet, double xMaxSet, double yMaxSet, int stepSet){
        //Graphics2D g2 = (Graphics2D) g;
        double xIncrement = (xMaxSet - xMinSet)/stepSet;
        double yIncrement = (yMaxSet - yMinSet)/stepSet;
        double xScaler = size/(xMaxSet - xMinSet);
        double yScaler = size/(yMaxSet - yMinSet);
        double dashSize = 10/(0.5 * (xScaler + yScaler));
        for (int i = 0; i < stepSet; i++){
            for (int j = 0; j < stepSet; j++){
                double m = 0;
                double xMid = xMinSet + (0.5 + i) * xIncrement;
                double yMid = yMinSet + (0.5 + j) * yIncrement;
                m = Math.pow(xMid,2);
                double deltaX = (dashSize)/(Math.sqrt(1 + m * m));
                double deltaY = (dashSize * m)/(Math.sqrt(1 + m * m));
                double x1 = xScaler * ((xMid - deltaX) - xMinSet);
                double y1 = -(yScaler * ((yMid - deltaY) - yMinSet) - size);
                double x2 = xScaler * ((xMid + deltaX) - xMinSet);
                double y2 = -(yScaler * ((yMid + deltaY) - yMinSet) - size);
                //Shape l = new Line2D.Double(x1, y1, x2, y2);
                //g2.draw(l);
                g.setColor(Color.BLACK);
                g.drawLine((int)(x1), (int)(y1), (int)(x2), (int)(y2));
            }
        }
    }
}



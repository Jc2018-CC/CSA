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
        draw(g, -5, -5, 5, 5, 15);
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
        double xIncrement = (xMaxSet - xMinSet)/stepSet;
        double yIncrement = (yMaxSet - yMinSet)/stepSet;
        double xScaler = size/(xMaxSet - xMinSet);
        double yScaler = size/(yMaxSet - yMinSet);
        double dashSize = (size/(2*stepSet))/(0.5*(xScaler+yScaler));
        double m = 0;
        for (int i = 0; i < stepSet; i++){
            for (int j = 0; j < stepSet; j++){
                double xMid = xMinSet + (0.5 + i) * xIncrement;
                double yMid = yMinSet + (0.5 + j) * yIncrement;
                m = xMid*xMid-yMid;
                double deltaX = (dashSize)/(Math.sqrt(1 + m * m));
                double deltaY = (dashSize * m)/(Math.sqrt(1 + m * m));
                double x1 = xScaler * ((xMid - deltaX) - xMinSet);
                double y1 = -(yScaler * ((yMid - deltaY) - yMinSet) - size);
                double x2 = xScaler * ((xMid + deltaX) - xMinSet);
                double y2 = -(yScaler * ((yMid + deltaY) - yMinSet) - size);
                g.setColor(Color.BLACK);
                g.drawLine((int)(x1), (int)(y1), (int)(x2), (int)(y2));
            }
        }
    }

  /*  private void draw(Graphics g, double xMinSet, double yMinSet, double xMaxSet, double yMaxSet, int stepSet){
        double xIncrement = (xMaxSet - xMinSet)/stepSet;
        double yIncrement = (yMaxSet - yMinSet)/stepSet;
        double dashSize = 10;
        for (int i = 0; i < stepSet; i++){
            for (int j = 0; j < stepSet; j++){
                double xMid = xMinSet + (0.5 + i) * xIncrement;
                double yMid = yMinSet + (0.5 + j) * yIncrement;
                m = xMid*xMid-yMid;
                double deltaX = (dashSize)/(Math.sqrt(1 + m * m));
                double deltaY = (dashSize * m)/(Math.sqrt(1 + m * m));
                double x1 = xScaler * ((xMid - deltaX) - xMinSet);
                double y1 = -(yScaler * ((yMid - deltaY) - yMinSet) - size);
                double x2 = xScaler * ((xMid + deltaX) - xMinSet);
                double y2 = -(yScaler * ((yMid + deltaY) - yMinSet) - size);
                g.setColor(Color.BLACK);
                g.drawLine((int)(x1), (int)(y1), (int)(x2), (int)(y2));
            }
        }
    }
    */
}



package weebspluskyle;

import java.awt.*;
import javax.swing.*;

public class App {
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("Slope Field Generator");
        jFrame.setLayout(null);
        jFrame.setSize(650, 450);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Slope Field Title");
        label.setText("Slope Field Generator Lmao");
        label.setBounds(200, 20, 200, 20);
        jFrame.add(label);

        JLabel dytxt = new JLabel("DY");
        dytxt.setBounds(20, 100, 50, 20);
        jFrame.add(dytxt);

        JLabel line = new JLabel("_____");
        line.setBounds(14, 103, 50, 20);
        jFrame.add(line);

        JLabel dxtxt = new JLabel("DX");
        dxtxt.setBounds(20, 120, 50, 20);
        jFrame.add(dxtxt);

        JTextField tf;
        tf = new JTextField("Enter your differential equation");
        tf.setBounds(65, 105, 220, 30);
        jFrame.add(tf);

        JTextField xMin = new JTextField("X Min");
        xMin.setBounds(65, 150, 50, 30);
        jFrame.add(xMin);

        JTextField xMax = new JTextField("X Max");
        xMax.setBounds(130, 150, 50, 30);
        jFrame.add(xMax);

        JTextField yMin = new JTextField("Y Min");
        yMin.setBounds(65, 190, 50, 30);
        jFrame.add(yMin);

        JTextField yMax = new JTextField("Y Max");
        yMax.setBounds(130, 190, 50, 30);
        jFrame.add(yMax);

        JTextField stepSet = new JTextField("Gap Size");
        stepSet.setBounds(130, 240, 80, 30);
        jFrame.add(stepSet);
        
        Button b = new Button("submit");
        b.setBounds(40, 280, 100, 30);
        jFrame.add(b);

        Graph graph = new Graph(300, 70, 300);
        jFrame.add(graph);

        // b button listener
        b.addActionListener(e -> {
            
            String equation = tf.getText();
            String s1 = xMin.getText();
            String s2 = yMin.getText();
            String s3 = xMax.getText();
            String s4 = xMax.getText();
            String s5 = stepSet.getText();
            double xMinSet = Double.parseDouble(s1);
            double yMinSet = Double.parseDouble(s2);
            double xMaxSet = Double.parseDouble(s3);
            double yMaxSet = Double.parseDouble(s4);
            int stepSize = Integer.parseInt(s5);
            
            graph.repaint();
            //Graph.draw(equation, xMinSet, yMinSet, xMaxSet, yMaxSet, stepSize);
        });

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}

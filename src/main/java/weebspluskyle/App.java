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
        label.setText("Slope Field Generator");
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
        tf = new JTextField(20);
        tf.setBounds(65, 105, 220, 30);
        jFrame.add(tf);
        TextPrompt tpEquation = new TextPrompt("Enter your differential equation", tf);
        
        JTextField xMin = new JTextField(10);
        xMin.setBounds(65, 150, 100, 30);
        jFrame.add(xMin);
        TextPrompt tpXMin = new TextPrompt("X Min", xMin);
        
        JTextField xMax = new JTextField("X Max");
        xMax.setBounds(180, 150, 100, 30);
        jFrame.add(xMax);
        TextPrompt tpXMax = new TextPrompt("X Max", xMax);
        
        
        JTextField yMin = new JTextField("Y Min");
        yMin.setBounds(65, 190, 100, 30);
        jFrame.add(yMin);
        TextPrompt tpYMin = new TextPrompt("Y Min", yMin);
        
        JTextField yMax = new JTextField("Y Max");
        yMax.setBounds(180, 190, 100, 30);
        jFrame.add(yMax);
        TextPrompt tpYMax = new TextPrompt("Y Max", yMax);
          
        JTextField stepSet = new JTextField();
        stepSet.setBounds(130, 230, 80, 30);
        jFrame.add(stepSet);
        TextPrompt tpGap = new TextPrompt("Gap Size", stepSet);
        

        Button c = new Button("Meet the Devs (Kyle was the best one)");
        c.setBounds(40, 310, 250, 30);
        jFrame.add(c);
        
        c.addActionListener(e -> {
            Devs.newScreen();
        });
        
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

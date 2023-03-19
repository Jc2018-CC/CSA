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
        label.setBounds(200, 20, 200, 20);
        jFrame.add(label);

        JLabel dytxt = new JLabel("DY");
        dytxt.setBounds(20, 100, 50, 20);
        jFrame.add(dytxt);

        JLabel line = new JLabel("_____ =");
        line.setBounds(14, 103, 50, 20);
        jFrame.add(line);

        JLabel dxtxt = new JLabel("DX");
        dxtxt.setBounds(20, 120, 50, 20);
        jFrame.add(dxtxt);

        JTextField tf = new JTextField(20);
        tf.setBounds(65, 105, 220, 30);
        TextPrompt tpEquation = new TextPrompt("Enter your differential equation", tf);
        tpEquation.changeAlpha(170);
        jFrame.add(tf);

        JTextField xMin = new JTextField(10);
        xMin.setBounds(65, 150, 100, 30);
        TextPrompt tpXMin = new TextPrompt("X Min", xMin);
        tpXMin.changeAlpha(170);
        jFrame.add(xMin);

        JTextField xMax = new JTextField(10);
        xMax.setBounds(180, 150, 100, 30);
        TextPrompt tpXMax = new TextPrompt("X Max", xMax);
        tpXMax.changeAlpha(170);
        jFrame.add(xMax);

        JTextField yMin = new JTextField(10);
        yMin.setBounds(65, 190, 100, 30);
        TextPrompt tpYMin = new TextPrompt("Y Min", yMin);
        tpYMin.changeAlpha(170);
        jFrame.add(yMin);

        JTextField yMax = new JTextField(10);
        yMax.setBounds(180, 190, 100, 30);
        TextPrompt tpYMax = new TextPrompt("Y Max", yMax);
        tpYMax.changeAlpha(170);
        jFrame.add(yMax);

        JTextField stepSet = new JTextField();
        stepSet.setBounds(130, 230, 80, 30);
        TextPrompt tpGap = new TextPrompt("Gap Size", stepSet);
        tpGap.changeAlpha(170);
        jFrame.add(stepSet);

        Button b = new Button("Submit");
        b.setBounds(40, 280, 100, 30);
        jFrame.add(b);

        Graph graph = new Graph(300, 70, 300);
        jFrame.add(graph);

        // b button listener
        b.addActionListener(e -> {
            String rawExpression = tf.getText();
            double xMinSet = Double.parseDouble(xMin.getText());
            double yMinSet = Double.parseDouble(yMin.getText());
            double xMaxSet = Double.parseDouble(xMax.getText());
            double yMaxSet = Double.parseDouble(yMax.getText());
            int stepSize = Integer.parseInt(stepSet.getText());

            try {
                Expression expression = new Expression();
                expression.tokenize(rawExpression);
                graph.draw(expression, xMinSet, yMinSet, xMaxSet, yMaxSet, stepSize);
            } catch (RuntimeException exception) {
                exception.printStackTrace();
            }
        });

        Button c = new Button("Meet the Devs of Weebs + Kyle");
        c.setBounds(40, 310, 250, 30);
        jFrame.add(c);

        c.addActionListener(e -> {
            Devs.newScreen();
        });

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}

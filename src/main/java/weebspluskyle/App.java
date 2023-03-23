package weebspluskyle;

import java.awt.*;
import javax.swing.*;

public class App {
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("Slope Field Generator");
        jFrame.setLayout(null);
        jFrame.setSize(650, 450);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        jFrame.add(bPanel);
        bPanel.setBounds(55,85,270,315);
        
        
        JLabel label = new JLabel("Slope Field Title");
        label.setBounds(200, 20, 200, 20);
        jFrame.add(label);

        JLabel dytxt = new JLabel("DY");
        dytxt.setBounds(10, 100, 50, 20);
        jFrame.add(dytxt);

        JLabel line = new JLabel("_____ =");
        line.setBounds(4, 103, 50, 20);
        jFrame.add(line);

        JLabel dxtxt = new JLabel("DX");
        dxtxt.setBounds(10, 120, 50, 20);
        jFrame.add(dxtxt);

        JTextField tf = new JTextField(20);
        tf.setPreferredSize(new Dimension(220, 30));
        TextPrompt tpEquation = new TextPrompt("Enter your differential equation", tf);
        tpEquation.changeAlpha(170);
        bPanel.add(tf);
        

        JTextField xMin = new JTextField(10);
        xMin.setPreferredSize(new Dimension(80, 30));
        TextPrompt tpXMin = new TextPrompt("X Min", xMin);
        tpXMin.changeAlpha(170);
        bPanel.add(xMin);

        JTextField xMax = new JTextField(10);
        xMax.setPreferredSize(new Dimension(80, 30));
        TextPrompt tpXMax = new TextPrompt("X Max", xMax);
        tpXMax.changeAlpha(170);
        bPanel.add(xMax);

        JTextField yMin = new JTextField(10);
        yMin.setPreferredSize(new Dimension(80, 30));
        TextPrompt tpYMin = new TextPrompt("Y Min", yMin);
        tpYMin.changeAlpha(170);
        bPanel.add(yMin);

        JTextField yMax = new JTextField(10);
        yMax.setPreferredSize(new Dimension(80, 30));
        TextPrompt tpYMax = new TextPrompt("Y Max", yMax);
        tpYMax.changeAlpha(170);
        bPanel.add(yMax);

        JTextField stepSet = new JTextField();
        stepSet.setPreferredSize(new Dimension(200, 30));
        TextPrompt tpGap = new TextPrompt("Gap Size", stepSet);
        tpGap.changeAlpha(170);
        bPanel.add(stepSet);

        Button submit = new Button("Submit");
        submit.setPreferredSize(new Dimension(200, 30));
        bPanel.add(submit);

        Graph graph = new Graph(340, 60, 300);
        jFrame.add(graph);

        // b button listener
        submit.addActionListener(e -> {
            String rawExpression = tf.getText();
            double xMinSet = Double.parseDouble(xMin.getText());
            double yMinSet = Double.parseDouble(yMin.getText());
            double xMaxSet = Double.parseDouble(xMax.getText());
            double yMaxSet = Double.parseDouble(yMax.getText());
            int stepSize = Integer.parseInt(stepSet.getText());
            
            if (xMinSet >= xMaxSet || yMinSet >= yMaxSet || stepSize < 0){
                //annoying popup or smth ig placeholder for now
                System.out.println("You're an idiot, do you not know how cartesian works?");
            } else {
            
                try {
                    Expression expression = new Expression();
                    expression.tokenize(rawExpression);
                    graph.draw(expression, xMinSet, yMinSet, xMaxSet, yMaxSet, stepSize);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                }
            }
        });

        Button devs = new Button("Meet the Devs of Weebs + Kyle");
        devs.setPreferredSize(new Dimension(220, 30));
        bPanel.add(devs);

        devs.addActionListener(e -> {
            Devs.newScreen();
        });

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}

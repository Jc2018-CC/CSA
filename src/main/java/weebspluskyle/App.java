package weebspluskyle;

import java.awt.*;
import javax.swing.*;

public class App {
    private static final Insets insets = new Insets(5, 5, 5, 5);
    
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("Slope Field Generator");
        jFrame.setLayout(new GridBagLayout());
        jFrame.setSize(600, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Slope Field Title");
        addComponent(jFrame, label, 3, 0, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        
        JLabel dytxt = new JLabel("DY");
        addComponent(jFrame, dytxt, 0, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        

        JLabel line = new JLabel("_____ =");
        addComponent(jFrame, line, 0, 2, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        JLabel dxtxt = new JLabel("DX");
        addComponent(jFrame, dxtxt, 0, 3, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        JTextField tf = new JTextField(20);
        TextPrompt tpEquation = new TextPrompt("Enter your differential equation", tf);
        tpEquation.changeAlpha(170);
        addComponent(jFrame, tf, 3, 2, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        
        JTextField xMin = new JTextField(10);
        TextPrompt tpXMin = new TextPrompt("X Min", xMin);
        tpXMin.changeAlpha(170);
        addComponent(jFrame, xMin, 0, 5, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        JTextField xMax = new JTextField(10);
        TextPrompt tpXMax = new TextPrompt("X Max", xMax);
        tpXMax.changeAlpha(170);
        addComponent(jFrame, xMax, 3, 5, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        JTextField yMin = new JTextField(10);
        TextPrompt tpYMin = new TextPrompt("Y Min", yMin);
        tpYMin.changeAlpha(170);
        addComponent(jFrame, yMin, 0, 7, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);


        JTextField yMax = new JTextField(10);
        TextPrompt tpYMax = new TextPrompt("Y Max", yMax);
        tpYMax.changeAlpha(170);
        addComponent(jFrame, yMax, 3, 7, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        JTextField stepSet = new JTextField();
        TextPrompt tpGap = new TextPrompt("Gap Size", stepSet);
        tpGap.changeAlpha(170);
        addComponent(jFrame, stepSet, 1, 9, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        Button devs = new Button("Meet the Devs of Weebs + Kyle");
        addComponent(jFrame, devs, 12, 15, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        devs.addActionListener(e -> {
            Devs.newScreen();
        });

        Button submit = new Button("Submit");
        addComponent(jFrame, submit, 1, 15, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        Graph graph = new Graph(300, 70, 240);
        addComponent(jFrame, graph, 8, 3, 8, 8, GridBagConstraints.CENTER, GridBagConstraints.BOTH);        
        
        submit.addActionListener(e -> {
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

        
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
    private static void addComponent(Container container, Component component, int gridx, int gridy,
      int gridwidth, int gridheight, int anchor, int fill) {
    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
    container.add(component, gbc);
  }
}

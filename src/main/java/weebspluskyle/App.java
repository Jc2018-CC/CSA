package weebspluskyle;

//possible ways to break the code: no input for tf, non (x or y or trig functions in equation tf
import java.awt.*;
import javax.swing.*;

public class App {
    private static void createAndShowGUI() {
        // CREATE FRAME AND BASE PANELS
        JFrame jFrame = new JFrame();
        JPanel inputPanel = new JPanel();
        JPanel txtPanel = new JPanel();
        JPanel inputPanel2 = new JPanel();
        Graph graph = new Graph();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());
        jFrame.setPreferredSize(new Dimension(700, 450));
        jFrame.setMinimumSize(jFrame.getPreferredSize());
        jFrame.setMaximumSize(new Dimension(1200, 800));

        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));
        txtPanel.setPreferredSize(new Dimension(60, 380));
        txtPanel.setMinimumSize(txtPanel.getPreferredSize());
        txtPanel.setMaximumSize(new Dimension(150, 2000));

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(200, 380));
        inputPanel.setMinimumSize(inputPanel.getPreferredSize());
        inputPanel.setMaximumSize(new Dimension(255, 2000));

        inputPanel2.setLayout(new GridBagLayout());
        inputPanel2.setPreferredSize(new Dimension(170, 80));
        inputPanel2.setMinimumSize(inputPanel.getPreferredSize());
        inputPanel2.setMaximumSize(new Dimension(250, 2000));

        // POPULATE TEXT PANEL
        Button exit = new Button("Quit");
        exit.setPreferredSize(new Dimension(60, 30));
        exit.setMinimumSize(new Dimension(60, 20));
        exit.setMaximumSize(new Dimension(90, 30));
        exit.addActionListener(e -> {
            System.exit(0);
        });

        JLabel dytxt = new JLabel("DY");
        dytxt.setPreferredSize(new Dimension(50, 10));
        dytxt.setMaximumSize(new Dimension(75, 15));
        dytxt.setMinimumSize(dytxt.getPreferredSize());

        JLabel line = new JLabel("___");
        line.setPreferredSize(new Dimension(50, 10));
        line.setMaximumSize(new Dimension(75, 15));
        line.setMinimumSize(line.getPreferredSize());

        JLabel dxtxt = new JLabel("DX");
        dxtxt.setPreferredSize(new Dimension(50, 20));
        dxtxt.setMaximumSize(new Dimension(75, 30));
        dxtxt.setMinimumSize(dxtxt.getPreferredSize());

        txtPanel.add(exit);
        txtPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        txtPanel.add(dytxt);
        txtPanel.add(line);
        txtPanel.add(dxtxt);

        // POPULATE INPUT PANEL 2
        GridBagConstraints gbcInner = new GridBagConstraints();

        JTextField xMin = new JTextField();
        TextPrompt tpXMin = new TextPrompt("X Min", xMin);
        tpXMin.changeAlpha(170);
        xMin.setPreferredSize(new Dimension(90, 30));
        gbcInner.insets = new Insets(3, 3, 3, 3);
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        gbcInner.fill = GridBagConstraints.BOTH;
        inputPanel2.add(xMin, gbcInner);

        JTextField xMax = new JTextField();
        xMax.setPreferredSize(new Dimension(90, 30));
        TextPrompt tpXMax = new TextPrompt("X Max", xMax);
        tpXMax.changeAlpha(170);
        gbcInner.gridx = 1;
        gbcInner.gridy = 0;
        inputPanel2.add(xMax, gbcInner);

        JTextField yMin = new JTextField();
        yMin.setPreferredSize(new Dimension(90, 30));
        TextPrompt tpYMin = new TextPrompt("Y Min", yMin);
        tpYMin.changeAlpha(170);
        gbcInner.gridx = 0;
        gbcInner.gridy = 1;
        inputPanel2.add(yMin, gbcInner);

        JTextField yMax = new JTextField();
        TextPrompt tpYMax = new TextPrompt("Y Max", yMax);
        tpYMax.changeAlpha(170);
        yMax.setPreferredSize(new Dimension(90, 30));
        gbcInner.gridx = 1;
        gbcInner.gridy = 1;
        inputPanel2.add(yMax, gbcInner);

        // POPULATE INPUT PANEL
        Label title = new Label("Slope Field Generator", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(230, 30));
        title.setMaximumSize(title.getPreferredSize());
        title.setFont(new Font("Verdana", Font.BOLD, 15));

        Label errorMsg = new Label("", SwingConstants.CENTER);
        errorMsg.setPreferredSize(new Dimension(230, 30));
        errorMsg.setForeground(Color.RED);

        JTextField tf = new JTextField(20);
        TextPrompt tpEquation = new TextPrompt("Equation", tf);
        tpEquation.changeAlpha(170);
        tf.setPreferredSize(new Dimension(300, 30));
        tf.setMaximumSize(tf.getPreferredSize());
        tf.setMinimumSize(tf.getPreferredSize());

        JTextField stepSet = new JTextField();
        TextPrompt tpGap = new TextPrompt("Density", stepSet);
        tpGap.changeAlpha(170);
        stepSet.setPreferredSize(new Dimension(150, 30));
        stepSet.setMaximumSize(tf.getPreferredSize());

        Button submit = new Button("Submit");
        submit.setPreferredSize(new Dimension(130, 30));
        submit.setMaximumSize(tf.getPreferredSize());

        inputPanel.add(title);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(tf);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(inputPanel2);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(stepSet);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(submit);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(errorMsg);
        inputPanel.add(Box.createVerticalGlue());

        submit.addActionListener(e -> {
            String rawExpression = tf.getText().replaceAll(" ", "");
            errorMsg.setText("");
            graph.clear();

            if (rawExpression.equals("")) {
                errorMsg.setText("<html>Expression is empty</html>");
                return;
            }
            if (xMin.getText().replaceAll(" ", "").equals("")) {
                errorMsg.setText("<html>X Min is empty</html>");
                return;
            }
            if (xMin.getText().replaceAll(" ", "").equals("")) {
                errorMsg.setText("<html>X Min is empty</html>");
                return;
            }
            if (xMax.getText().replaceAll(" ", "").equals("")) {
                errorMsg.setText("<html>X Max is empty</html>");
                return;
            }
            if (yMin.getText().replaceAll(" ", "").equals("")) {
                errorMsg.setText("<html>Y Min is empty</html>");
                return;
            }
            if (yMax.getText().replaceAll(" ", "").equals("")) {
                errorMsg.setText("<html>Y Max is empty</html>");
                return;
            }
            if (stepSet.getText().replaceAll(" ", "").equals("")) {
                errorMsg.setText("<html>Density is empty</html>");
                return;
            }

            double xMinSet;
            double yMinSet;
            double xMaxSet;
            double yMaxSet;
            int stepSize;
            try {
                xMinSet = Double.parseDouble(xMin.getText());
                yMinSet = Double.parseDouble(yMin.getText());
                xMaxSet = Double.parseDouble(xMax.getText());
                yMaxSet = Double.parseDouble(yMax.getText());
                stepSize = Integer.parseInt(stepSet.getText());
            } catch (RuntimeException exception) {
                errorMsg.setText("Invalid Text Entered");
                return;
            }

            if (xMinSet >= xMaxSet) {
                errorMsg.setText("<html>X Min is greater than or equal to X Max</html>");
                return;
            }
            if (yMinSet >= yMaxSet) {
                errorMsg.setText("<html>Y Min is greater than or equal to Y Max</html>");
                return;
            }
            if (stepSize < 5) {
                errorMsg.setText("<html>Minimum density should be 5 or higher</html>");
                return;
            }

            try {
                Expression expression = new Expression();
                expression.tokenize(rawExpression);
                expression.evaluate(1, 1); // dummy evaluation to catch errors
                graph.draw(expression, xMinSet, yMinSet, xMaxSet, yMaxSet, stepSize);
            } catch (RuntimeException exception) {
                errorMsg.setText("<html>" + exception.getMessage() + "</html>");
                return;
            }
        });

        // we'll see abt this
        // Button devs = new Button("Meet the Devs of Weebs + Kyle");
        // devs.setPreferredSize(new Dimension(220, 30));
        // bPanel.add(devs);
        //
        // devs.addActionListener(e -> {
        // Devs.newScreen();
        // });

        // POPULATE FRAME
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        jFrame.add(txtPanel, gbc);
        gbc.gridx = 1;
        jFrame.add(inputPanel, gbc);
        gbc.gridx = 2;
        gbc.ipadx = 20;
        jFrame.add(graph, gbc);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}

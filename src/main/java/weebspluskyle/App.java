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
        JPanel graphPanel = new JPanel();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());
        jFrame.setPreferredSize(new Dimension(700, 450));
        jFrame.setMinimumSize(jFrame.getPreferredSize());
        jFrame.setMaximumSize(new Dimension(1200, 800));

        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));
        txtPanel.setPreferredSize(new Dimension(60, 380));
        txtPanel.setMinimumSize(txtPanel.getPreferredSize());
        txtPanel.setMaximumSize(new Dimension(150, 2000));
        txtPanel.setBackground(Color.YELLOW);

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(200, 380));
        inputPanel.setMinimumSize(inputPanel.getPreferredSize());
        inputPanel.setMaximumSize(new Dimension(255, 2000));
        inputPanel.setBackground(Color.BLUE);

        inputPanel2.setLayout(new GridBagLayout());
        inputPanel2.setPreferredSize(new Dimension(170, 80));
        inputPanel2.setMinimumSize(inputPanel.getPreferredSize());
        inputPanel2.setMaximumSize(new Dimension(250, 2000));
        inputPanel.setBackground(Color.BLUE);

        graphPanel.setPreferredSize(new Dimension(360, 380));
        graphPanel.setMinimumSize(graphPanel.getPreferredSize());
        graphPanel.setMaximumSize(new Dimension(720, 720));
        graphPanel.setBackground(Color.RED);

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
        txtPanel.add(Box.createRigidArea(new Dimension(0, 30)));
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
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD, 15));
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

        //Graph graph = new Graph(340, 60, 300);
        //jFrame.add(graph);

        submit.addActionListener(e -> {
            String rawExpression = tf.getText().trim();
            double xMinSet = Double.parseDouble(xMin.getText());
            double yMinSet = Double.parseDouble(yMin.getText());
            double xMaxSet = Double.parseDouble(xMax.getText());
            double yMaxSet = Double.parseDouble(yMax.getText());
            int stepSize = Integer.parseInt(stepSet.getText());

            if (xMinSet >= xMaxSet || yMinSet >= yMaxSet || stepSize < 0 || rawExpression.isEmpty()) {
                App.error();
            } else {

                try {
                    Expression expression = new Expression();
                    expression.tokenize(rawExpression);
                    //graph.draw(expression, xMinSet, yMinSet, xMaxSet, yMaxSet, stepSize);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                }
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
        jFrame.add(graphPanel, gbc);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void error() {
        JFrame errorFrame = new JFrame("ERROR 801");
        errorFrame.setLayout(null);
        errorFrame.setSize(450, 450);
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        errorFrame.add(errorPanel);
        errorPanel.setBounds(0, 0, 450, 450);
        errorPanel.setBackground(Color.RED);
        JLabel errorLabel = new JLabel("ERROR BAD SYNTAX");
        errorLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        errorLabel.setForeground(Color.black);
        errorPanel.add(errorLabel);
        JLabel errorLabel2 = new JLabel("The minimum of domain of X can't be");
        errorLabel2.setFont(new Font("Serif", Font.PLAIN, 16));
        errorLabel2.setForeground(Color.black);
        errorPanel.add(errorLabel2);
        JLabel errorLabel3 = new JLabel("more or equal to the max, Same for Y");
        errorLabel3.setFont(new Font("Serif", Font.PLAIN, 16));
        errorLabel3.setForeground(Color.black);
        errorPanel.add(errorLabel3);
        JLabel errorLabel4 = new JLabel("Gap size must be more than 0");
        errorLabel4.setFont(new Font("Serif", Font.PLAIN, 16));
        errorLabel4.setForeground(Color.black);
        errorPanel.add(errorLabel4);
        errorFrame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}

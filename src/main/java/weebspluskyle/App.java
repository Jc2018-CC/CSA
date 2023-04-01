package weebspluskyle;
//possible ways to break the code: no input for tf, non (x or y or trig functions in equation tf
import java.awt.*;
import javax.swing.*;

public class App {
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());

        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        JPanel inputPanel2 = new JPanel();
        inputPanel2.setLayout(new GridBagLayout());
        JPanel graphPanel = new JPanel();
        txtPanel.setBackground(Color.YELLOW);
        inputPanel.setBackground(Color.BLUE);
        graphPanel.setBackground(Color.RED);

        jFrame.setPreferredSize(new Dimension(600, 400));
        jFrame.setMinimumSize(jFrame.getPreferredSize());
        jFrame.setMaximumSize(new Dimension(1200,800));
        txtPanel.setPreferredSize(new Dimension(50, 380));
        txtPanel.setMaximumSize(new Dimension(150, 2000));
        txtPanel.setMinimumSize(inputPanel.getPreferredSize());
        inputPanel.setPreferredSize(new Dimension(200, 380));
        inputPanel.setMaximumSize(new Dimension(255, 2000));
        inputPanel.setMinimumSize(inputPanel.getPreferredSize());
        inputPanel2.setPreferredSize(new Dimension(170, 80));
        inputPanel2.setMaximumSize(new Dimension(250, 2000));
        inputPanel2.setMinimumSize(inputPanel.getPreferredSize());
        graphPanel.setPreferredSize(new Dimension(360, 380));
        graphPanel.setMaximumSize(new Dimension(720, 720));
        graphPanel.setMinimumSize(graphPanel.getPreferredSize());

        Button exit = new Button("quit");
        JLabel dytxt = new JLabel("DY");
        JLabel line = new JLabel("___");
        JLabel dxtxt = new JLabel("DX");
        exit.setPreferredSize(new Dimension(50, 20));
        exit.setMaximumSize(new Dimension(75, 30));
        exit.setMinimumSize(inputPanel.getPreferredSize());
        dytxt.setPreferredSize(new Dimension(50, 10));
        dytxt.setMaximumSize(new Dimension(75, 15));
        dytxt.setMinimumSize(inputPanel.getPreferredSize());
        line.setPreferredSize(new Dimension(50, 10));
        line.setMaximumSize(new Dimension(75, 15));
        line.setMinimumSize(inputPanel.getPreferredSize());
        dxtxt.setPreferredSize(new Dimension(50, 20));
        dxtxt.setMaximumSize(new Dimension(75, 30));
        dxtxt.setMinimumSize(inputPanel.getPreferredSize());

        txtPanel.add(exit);
        txtPanel.add(Box.createRigidArea(new Dimension(0,50)));
        txtPanel.add(dytxt);
        txtPanel.add(line);
        txtPanel.add(dxtxt);

        //here
        JLabel label = new JLabel("Slope Field Generator");
        JTextField tf = new JTextField(20);
        TextPrompt tpEquation = new TextPrompt("Enter your differential equation", tf);
        tpEquation.changeAlpha(170);
        tf.setPreferredSize(new Dimension(200, 30));
        tf.setMaximumSize(tf.getPreferredSize());
        JTextField stepSet = new JTextField();
        TextPrompt tpGap = new TextPrompt("Lines per Axis", stepSet);
        tpGap.changeAlpha(170);
        stepSet.setPreferredSize(new Dimension(150, 30));
        stepSet.setMaximumSize(tf.getPreferredSize());
        Button submit = new Button("Submit");
        submit.setPreferredSize(new Dimension(130, 30));
        submit.setMaximumSize(tf.getPreferredSize());

        JTextField xMin = new JTextField();
        TextPrompt tpXMin = new TextPrompt("X Min", xMin);
        tpXMin.changeAlpha(170);
        xMin.setPreferredSize(new Dimension(90, 30));
        JTextField xMax = new JTextField();
        xMax.setPreferredSize(new Dimension(90, 30));
        TextPrompt tpXMax = new TextPrompt("X Max", xMax);
        tpXMax.changeAlpha(170);
        JTextField yMin = new JTextField();
        yMin.setPreferredSize(new Dimension(90, 30));
        TextPrompt tpYMin = new TextPrompt("Y Min", yMin);
        tpYMin.changeAlpha(170);
        JTextField yMax = new JTextField();
        TextPrompt tpYMax = new TextPrompt("Y Max", yMax);
        tpYMax.changeAlpha(170);
        yMax.setPreferredSize(new Dimension(90, 30));

        GridBagConstraints gbcInner = new GridBagConstraints();
        gbcInner.insets = new Insets(3,3,3,3);
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        gbcInner.fill = GridBagConstraints.BOTH;
        inputPanel2.add(xMin,gbcInner);
        gbcInner.gridx = 1;
        gbcInner.gridy = 0;
        inputPanel2.add(xMax,gbcInner);
        gbcInner.gridx = 0;
        gbcInner.gridy = 1;
        inputPanel2.add(yMin,gbcInner);
        gbcInner.gridx = 1;
        gbcInner.gridy = 1;
        inputPanel2.add(yMax,gbcInner);
        
        //start of input panel
        inputPanel.add(label);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(tf);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(inputPanel2);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(stepSet);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(submit);
        inputPanel.add(Box.createVerticalGlue());
        

        
        
        
        
        
        
        
        
        

       exit.addActionListener(e -> {
            jFrame.setVisible(false);
            System.out.println("bye broskis :)");
        });
        
        Graph graph = new Graph(340, 60, 300);
        jFrame.add(graph);

        // b button listener
        submit.addActionListener(e -> {
            String rawExpression = tf.getText().trim();
            double xMinSet = Double.parseDouble(xMin.getText());
            double yMinSet = Double.parseDouble(yMin.getText());
            double xMaxSet = Double.parseDouble(xMax.getText());
            double yMaxSet = Double.parseDouble(yMax.getText());
            int stepSize = Integer.parseInt(stepSet.getText());
            
            if (xMinSet >= xMaxSet || yMinSet >= yMaxSet || stepSize < 0 || rawExpression.isEmpty()){
                App.error();
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

        //we'll see abt this
        // Button devs = new Button("Meet the Devs of Weebs + Kyle");
        // devs.setPreferredSize(new Dimension(220, 30));
        // bPanel.add(devs);

        // devs.addActionListener(e -> {
        //     Devs.newScreen();
        // });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,5,0,5);
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        jFrame.add(txtPanel,gbc);
        gbc.gridx = 1;
        jFrame.add(inputPanel,gbc);
        gbc.gridx = 2;
        jFrame.add(graphPanel,gbc);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void error(){
                JFrame errorFrame = new JFrame("ERROR 801");
                errorFrame.setLayout(null);
                errorFrame.setSize(450, 450);
                JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
                errorFrame.add(errorPanel);
                errorPanel.setBounds(0,0,450,450);
                errorPanel.setBackground(Color.RED);
                JLabel errorLabel = new JLabel("ERROR BAD SYNTAX");
                errorLabel.setFont(new Font("Serif", Font.PLAIN, 25));                errorLabel.setForeground(Color.black);
                errorPanel.add(errorLabel);
                JLabel errorLabel2 = new JLabel("The minimum of domain of X can't be");
                errorLabel2.setFont(new Font("Serif", Font.PLAIN, 16));                errorLabel2.setForeground(Color.black);
                errorPanel.add(errorLabel2);
                JLabel errorLabel3 = new JLabel("more or equal to the max, Same for Y");
                errorLabel3.setFont(new Font("Serif", Font.PLAIN, 16));                errorLabel3.setForeground(Color.black);
                errorPanel.add(errorLabel3);
                JLabel errorLabel4 = new JLabel("Gap size must be more than 0");
                errorLabel4.setFont(new Font("Serif", Font.PLAIN, 16));                errorLabel4.setForeground(Color.black);
                errorPanel.add(errorLabel4);
                errorFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        createAndShowGUI();
    }
}
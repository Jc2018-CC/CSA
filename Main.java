import java.awt.*;
import javax.swing.*;

public class Main {
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("Slope Field Generator");
        jFrame.setLayout(null);
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Button b = new Button("submit");
        b.setBounds(40,280,50,30);
        jFrame.add(b);
        
        JLabel label = new JLabel("Slope Field Title");
        label.setText("Slope Field Generator");
        label.setBounds(200,20,200,20);
        jFrame.add(label);

        JLabel dytxt = new JLabel("DY");
        dytxt.setText("DY");
        dytxt.setBounds(20,100,50,20);
        jFrame.add(dytxt);

        JLabel line = new JLabel("line");
        line.setText("_____");
        line.setBounds(14,103,50,20);
        jFrame.add(line);

        JLabel dxtxt = new JLabel("DX");
        dxtxt.setText("DX");
        dxtxt.setBounds(20,120,50,20);
        jFrame.add(dxtxt);

        JTextField tf;
        tf = new JTextField("Enter your differential equation");
        tf.setBounds(65, 105, 220, 30);
        jFrame.add(tf);
        
        jFrame.setVisible(true);
    }
    
  public static void main(String[] args) {
    createAndShowGUI();
  }
}

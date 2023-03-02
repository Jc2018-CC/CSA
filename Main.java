import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("Slope Field Generator");
        jFrame.setLayout(null);
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Button b = new Button("submit");
        b.setBounds(50,30,50,30);
        jFrame.add(b);
        
        JLabel label = new JLabel("Slope Field Title");
        label.setText("Slope Field Generator");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        jFrame.add(label);
        jFrame.setVisible(true);
    }
    
  public static void main(String[] args) {
    createAndShowGUI();
  }
}

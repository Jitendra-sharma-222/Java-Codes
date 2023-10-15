import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;

public class BallMain extends JFrame {
   BallMain() {
      this.setTitle("Moving Red Ball");
      this.setSize(800, 600);
      this.setLocationRelativeTo((Component)null);
      this.setDefaultCloseOperation(3);
      Ball var1 = new Ball(780, 560);
      var1.setBackground(Color.BLACK);
      this.add(var1);
   }

   public static void main(String[] var0) {
      BallMain var1 = new BallMain();
      var1.setVisible(true);
   }
}

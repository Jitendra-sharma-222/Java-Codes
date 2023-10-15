import java.awt.Font;
import javax.swing.*;

public class NotepadAbout extends JFrame{
    NotepadAbout()
    {
        this.setBounds(100, 50, 500, 400);
        this.setTitle("About Notepad Application");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.JFIF"));
        this.setIconImage(icon.getImage());

        JLabel iconlabel=new JLabel(new ImageIcon(getClass().getResource("Notepad1.PNG")));
        iconlabel.setBounds(200, 50, 80, 80);
        this.add(iconlabel);

        JLabel text=new JLabel("<html> Notepad is a generic text editor included with all versions of Microsoft Windows that lets you create, open, and read plaintext files with a .txt file extension. If the file contains special formatting  or is not a plaintext file, it cannot be read in Notepad. The image shown here is a small example of what the Notepad may look like while running.</html>");
        text.setBounds(10, 50, 480, 300);
        text.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
        this.add(text);
    }
    //public static void main(String[] args) {
       // new NotepadAbout().setVisible(true);
   // }
}

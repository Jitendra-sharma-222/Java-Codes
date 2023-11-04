import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchRecord extends JFrame implements ActionListener {
    private JLabel aadhar,title;
    private JTextField t3;
    private Container c;
    private JTextArea output;
    private JButton searchButton,back;
    SearchRecord()
    {
        this.setTitle("Search Bar");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("search.png");
        this.c = getContentPane();
        c.setLayout(null);
        this.setIconImage(icon.getImage());
        c.setBackground(Color.CYAN);

         title = new JLabel("Search bar");
        title.setBounds(300, 40, 200, 25);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        this.c.add(title);

        aadhar = new JLabel("Enter Aadhar No:");
        aadhar.setBounds(100, 200, 100, 20);
        this.c.add(aadhar);

        t3 = new JTextField(20);
        t3.setBounds(220, 200, 100, 20);
        this.c.add(t3);

        searchButton = new JButton("Search");
        searchButton.setBounds(150, 250, 100, 30);
        this.c.add(searchButton);
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setForeground(Color.white);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);

        back = new JButton("back");
        back.setBounds(250, 250, 100, 30);
        this.c.add(back);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.setFocusable(false);
        back.addActionListener(this);
        
        output = new JTextArea();
        output.setBounds(400, 70, 350, 300);
        output.setLineWrap(true);
        output.setEditable(false);

        this.c.add(output);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
        String aadharToSearch = t3.getText();
        if (!aadharToSearch.isEmpty() && aadharToSearch.matches("\\d{12}")) {
        try {
            String url = "jdbc:mysql://localhost:3306/form";
            String username = "root";
            String password = "1234";
            String query = "SELECT * FROM registrationForm WHERE aadhar_no = ?";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, aadharToSearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String mobile = resultSet.getString("mobile");
                String gender = resultSet.getString("gender");
                String dob = resultSet.getString("dob");
                String address = resultSet.getString("address");
                String gmail = resultSet.getString("gmail");
                output.setText("Name: " + name + "\nMobile: " + mobile + "\nGender: " + gender + "\nDOB: " + dob + "\nAddress: " + address + "\nGmail: " + gmail);
            } else {
                output.setForeground(Color.RED);
                output.setText("Record not found.");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else {
        output.setForeground(Color.RED);
        output.setText("Enter a valid Aadhar number to search."); 
    }
    }

        if (e.getSource() == back)
        {
            RegistrationForm r=new RegistrationForm();
        }

}
    public static void main(String[] args) {
        SearchRecord s=new SearchRecord();
        s.setVisible(true);
    }
    
}

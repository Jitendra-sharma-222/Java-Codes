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

import javax.swing.*;

public class ForgotPass extends JFrame implements ActionListener {
    private JLabel username, password, result, loginForm;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Container c;
    private JButton forgot,back;

    public ForgotPass() {
        this.setTitle("Login Form");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon icon = new ImageIcon("Login.png");
        this.c = getContentPane();
        this.c.setLayout(null);
        this.c = getContentPane();
        this.setIconImage(icon.getImage());
        c.setBackground(Color.CYAN);

        loginForm = new JLabel("Login Form");
        loginForm.setBounds(300, 90, 200, 25);
        loginForm.setFont(new Font("Arial", Font.BOLD, 25));
        c.add(loginForm);

        username = new JLabel("Your Username/Gmail :");
        username.setBounds(200, 200, 200, 20);
        username.setFont(new Font("Arial", Font.BOLD, 12));
        c.add(username);

        usernameField = new JTextField(20);
        usernameField.setBounds(350, 196, 200, 30);
        c.add(usernameField);

        password = new JLabel("Your Password :");
        password.setBounds(200, 250, 200, 20);
        password.setFont(new Font("Arial", Font.BOLD, 12));
        c.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(350, 245, 200, 30);
        passwordField.setEchoChar('*');
        passwordField.setEditable(false);
        c.add(passwordField);

        result = new JLabel("");
        result.setBounds(250, 150, 400, 20);
        result.setFont(new Font("Arial", Font.BOLD, 12));
        c.add(result);

        forgot = new JButton("Forgot");
        forgot.setBounds(290, 290, 100, 30);
        forgot.setBackground(Color.BLUE);
        forgot.setForeground(Color.WHITE);
        forgot.setFocusable(false);
        forgot.addActionListener(this);
        c.add(forgot);

        back = new JButton("Back to login page");
        back.setBounds(400, 290, 200, 30);
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        back.setFocusable(false);
        back.addActionListener(this);
        c.add(back);

    }

    public void mySqlConnection()
    {
        String usernameCon=usernameField.getText();
        if(usernameCon.length()>0)
        {
        String url = "jdbc:mysql://localhost:3306/form";
        String DBusername = "root";
        String DBpassword = "1234";
        String query = "SELECT password FROM loginform WHERE Username = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernameCon);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String retrievedPassword = resultSet.getString("password");
                passwordField.setEchoChar('\0'); 
                //passwordField.setBounds(350, 245, 200, 30);
                passwordField.setText(retrievedPassword);
            } else {
                passwordField.setForeground(Color.RED);
                 passwordField.setEchoChar('\0'); 
                passwordField.setText("User doesn't exists");
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            result.setText(e.getMessage());
        } catch (SQLException e) {
            result.setText("Enter valid details (username,password) ");
        }
        }
        else 
        {
            result.setForeground(Color.red);
            result.setText("Enter Valid username.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == forgot) {
            mySqlConnection();
        }
        if(e.getSource()==back)
        {
        LoginForm l=new LoginForm();
        }
    }
}

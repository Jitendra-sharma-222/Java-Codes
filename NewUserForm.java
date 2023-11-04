import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class NewUserForm extends JFrame implements ActionListener {
    private JLabel username, password, result, loginForm;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Container c;
    private JButton login,back;

    public NewUserForm() {
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

        username = new JLabel("Username/Gmail :");
        username.setBounds(200, 200, 200, 20);
        username.setFont(new Font("Arial", Font.BOLD, 15));
        c.add(username);

        usernameField = new JTextField(20);
        usernameField.setBounds(350, 196, 200, 30);
        c.add(usernameField);

        password = new JLabel("Password (only integer):");
        password.setBounds(200, 250, 200, 20);
        password.setFont(new Font("Arial", Font.BOLD, 12));
        c.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(350, 245, 200, 30);
        passwordField.setEchoChar('*');
        c.add(passwordField);

        result = new JLabel("");
        result.setBounds(250, 150, 400, 20);
        result.setFont(new Font("Arial", Font.BOLD, 12));
        c.add(result);

        login = new JButton("Sign up");
        login.setBounds(290, 290, 100, 30);
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);
        login.setFocusable(false);
        login.addActionListener(this);
        c.add(login);

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
        String username=usernameField.getText();
        if(username.length()>0)
        {
            char[] passwordGet=passwordField.getPassword();
            String password=new String(passwordGet);
            if(password.length()>0)
            {
            try
            {
                String usernameCon=usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String passwordCon = new String(passwordChars);
                String url = "jdbc:mysql://localhost:3306/form";
                String DBusername = "root";
                String DBpassword = "1234";
                String query = "INSERT INTO loginform (Username,password) VALUES (?, ?)";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usernameCon);
                preparedStatement.setString(2, passwordCon);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0)
                {
                    result.setForeground(Color.RED);
                    result.setText("Account Created Sucessfully");
                    login.setEnabled(false);
                }
                else
                {
                    result.setForeground(Color.RED);
                    result.setText("Account creation failed.Try Again");
                }
                connection.close();
            }
            catch (ClassNotFoundException e) 
            {
                result.setText(e.getMessage());
            }
            catch (SQLException e) 
            {
                result.setForeground(Color.RED);
                result.setText("User Already Exists");
            }
            }
            else
            {
                result.setForeground(Color.red);
                result.setText("Enter Valid password.");
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
        if (e.getSource() == login) {
            mySqlConnection();
        }
        if(e.getSource()==back)
        {
        LoginForm l=new LoginForm();
        }
    }
}

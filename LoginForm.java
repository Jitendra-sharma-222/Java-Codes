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

public class LoginForm extends JFrame implements ActionListener {
    private JLabel username, password, result, loginForm;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Container c;
    private JButton login, forgot;
    private JLabel newUser;
    private JButton signUp;

    LoginForm() {
        this.setTitle("Login Form");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("Login.png");
        this.c = getContentPane();
        this.c.setLayout(null);
        this.c = getContentPane();
        this.setIconImage(icon.getImage());
        c.setBackground(Color.CYAN);

        loginForm = new JLabel("Login Form");
        loginForm.setBounds(300, 90, 200, 25);
        loginForm.setFont(new Font("Arial", Font.BOLD, 25));
        this.c.add(loginForm);

        username = new JLabel("Username/Gmail :");
        username.setBounds(200, 200, 200, 20);
        username.setFont(new Font("Arial", Font.BOLD, 15));
        this.c.add(username);

        usernameField = new JTextField(20);
        usernameField.setBounds(350, 196, 200, 30);
        this.c.add(usernameField);

        password = new JLabel("Password :");
        password.setBounds(200, 250, 200, 20);
        password.setFont(new Font("Arial", Font.BOLD, 15));
        this.c.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(350, 245, 200, 30);
        passwordField.setEchoChar('*');
        this.c.add(passwordField);

        result = new JLabel("");
        result.setBounds(250, 150, 350, 30);
        result.setFont(new Font("Arial", Font.BOLD, 12));
        this.c.add(result);

        login = new JButton("Login");
        login.setBounds(250, 290, 100, 30);
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);
        login.setFocusable(false);
        login.addActionListener(this);
        this.c.add(login);


        forgot = new JButton("Forgot Password");
        forgot.setBounds(370, 290, 150, 30);
        forgot.setBackground(Color.BLUE);
        forgot.setForeground(Color.WHITE);
        forgot.setFocusable(false);
        forgot.addActionListener(this);
        c.add(forgot);

        newUser = new JLabel("New User?");
        newUser.setFont(new Font("Arial", Font.BOLD, 20));
        newUser.setBounds(250, 340, 200, 30);
        c.add(newUser);

        signUp = new JButton("Sign up");
        signUp.setBounds(380, 340, 200, 30);
        signUp.setBackground(Color.BLUE);
        signUp.setForeground(Color.WHITE);
        signUp.setFocusable(false);
        signUp.addActionListener(this);
        c.add(signUp);

        setVisible(true);

    }

    public void mySqlConnection() {
        String usernameCon = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String passwordCon = new String(passwordChars);
        String url = "jdbc:mysql://localhost:3306/form";
        String username = "root";
        String password = "1234";
        String query = "SELECT * FROM loginform WHERE Username = ? AND password = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernameCon);
            preparedStatement.setString(2, passwordCon);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                RegistrationForm r = new RegistrationForm();
            } else {
                if (usernameExists(usernameCon)) {
                    result.setForeground(Color.red);
                    result.setText("Invalid password");
                   
                } else {
                    result.setForeground(Color.red);
                    result.setText("User does not exist");
                }
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            result.setText(e.getMessage());
        } catch (SQLException e) {
            result.setForeground(Color.red);
            result.setText("Enter valid details (username,password) ");
        }
    }
    public boolean usernameExists(String username) {

        String url = "jdbc:mysql://localhost:3306/form";
        String DBusername = "root";
        String DBpassword = "1234";
        String query = "SELECT * FROM loginform WHERE Username = ?";
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
    
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            mySqlConnection();
        }
        if (e.getSource() == signUp) {
            NewUserForm n = new NewUserForm();
            // result.setText("Sign up button clicked");
        }
        if(e.getSource()==forgot)
        {
            ForgotPass f=new ForgotPass();
        }
    }


    public static void main(String[] args) {
        LoginForm frame = new LoginForm();
        // frame.setVisible(true);
    }

}

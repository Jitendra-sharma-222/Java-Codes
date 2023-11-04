import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

class RegistrationForm extends JFrame implements ActionListener, MouseListener {
    private JLabel label, label1, label2, label3, label4, label5, label6, label0, label7;
    private JLabel nameLabel, mobileLabel, aadharLabel, aadharLabel1;
    private JTextField t1, t2, t3, t4;
    private JRadioButton r1, r2;
    private JButton submit, clear;
    private JTextArea a1;
    private JTextArea screen;
    private Container c;
    private JCheckBox terms;
    private JComboBox day, month, year;
    private JLabel message;
    private JButton searchButton,updateButton,deleteButton,displayAll;
    private JTable dataTable;
    private JScrollPane scrollPane;

    RegistrationForm() {
        displayWelcomeScreen();
        this.setTitle("Registration Form");
        this.setSize(800, 600);
        // this.setLocation(100,30);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("form.jpg");
        this.c = getContentPane();
        this.setIconImage(icon.getImage());
        this.c.setLayout(null);
        c.setBackground(Color.CYAN);

        label = new JLabel("Registration Form");
        label.setBounds(300, 0, 200, 50);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        this.c.add(label);

        label0 = new JLabel("Aadhar No:");
        label0.setBounds(40, 15, 100, 20);
        this.c.add(label0);

        t3 = new JTextField(20);
        t3.setBounds(120, 17, 100, 20);
        this.c.add(t3);
        t3.addMouseListener(this);

        aadharLabel = new JLabel("*This Field is Mandatory*");
        aadharLabel.setBounds(120, 0, 200, 20);
        aadharLabel.setForeground(Color.RED);
        this.c.add(aadharLabel);
        aadharLabel1 = new JLabel("");
        aadharLabel1.setBounds(120, 32, 200, 20);
        this.c.add(aadharLabel1);
        t3.addMouseListener(this);

        label1 = new JLabel("Name:");
        label1.setBounds(40, 50, 100, 20);
        this.c.add(label1);

        t1 = new JTextField(20);
        t1.setBounds(120, 52, 100, 20);
        this.c.add(t1);
        t1.addMouseListener(this);

        nameLabel = new JLabel("");
        nameLabel.setBounds(120, 70, 100, 20);
        this.c.add(nameLabel);
        t1.addMouseListener(this);

        label2 = new JLabel("Mobile:");
        label2.setBounds(40, 90, 100, 20);
        this.c.add(label2);

        t2 = new JTextField(20);
        t2.setBounds(120, 90, 100, 20);
        this.c.add(t2);

        mobileLabel = new JLabel("");
        mobileLabel.setBounds(120, 110, 100, 20);
        this.c.add(mobileLabel);
        t2.addMouseListener(this);

        label3 = new JLabel("Gender:");
        label3.setBounds(40, 130, 100, 20);
        this.c.add(label3);

        r1 = new JRadioButton("Male");
        r1.setBounds(120, 130, 100, 20);
        r1.setFocusable(false);
        r1.setSelected(true);
        this.c.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(230, 130, 100, 20);
        r2.setFocusable(false);
        this.c.add(r2);

        ButtonGroup gender = new ButtonGroup();
        gender.add(r1);
        gender.add(r2);

        label4 = new JLabel("DOB:");
        label4.setBounds(40, 170, 100, 20);
        this.c.add(label4);

        String days[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

        String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };

        String years[] = { "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013",
                "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000",
                "1999", "1998", "1997" };

        year = new JComboBox<>(years);
        year.setBounds(120, 170, 70, 20);
        this.c.add(year);

        month = new JComboBox<>(months);
        month.setBounds(200, 170, 60, 20);
        this.c.add(month);

        day = new JComboBox<>(days);
        day.setBounds(280, 170, 50, 20);
        this.c.add(day);

        label5 = new JLabel("Address:");
        label5.setBounds(40, 200, 100, 20);
        this.c.add(label5);

        a1 = new JTextArea();
        a1.setBounds(120, 200, 220, 50);
        a1.setLineWrap(true);
        this.c.add(a1);

        label7 = new JLabel("Gmail:");
        label7.setBounds(40, 260, 100, 20);
        this.c.add(label7);

        t4 = new JTextField(20);
        t4.setBounds(120, 260, 150, 20);
        this.c.add(t4);
        t4.addMouseListener(this);

        label6 = new JLabel("Please Fill out all the columns.");
        label6.setBounds(120, 300, 200, 20);
        this.c.add(label6);

        terms = new JCheckBox("Please accept terms and conditions");
        terms.setBounds(100, 350, 250, 20);
        terms.setFocusable(false);
        this.c.add(terms);
        terms.addActionListener(this);

        submit = new JButton("insert");
        submit.setBounds(120, 400, 100, 30);
        this.c.add(submit);
        submit.setBackground(Color.DARK_GRAY);
        submit.setForeground(Color.white);
        submit.setFocusable(false);
        submit.setEnabled(false);
        submit.addActionListener(this);

        clear = new JButton("Clear");
        clear.setBounds(230, 400, 100, 30);
        this.c.add(clear);
        clear.setBackground(Color.DARK_GRAY);
        clear.setForeground(Color.white);
        clear.setFocusable(false);

        clear.addActionListener(this);

        screen = new JTextArea();
        screen.setBounds(360, 70, 430, 300);
        screen.setLineWrap(true);
        screen.setEditable(false);
        this.c.add(screen);

        message = new JLabel();
        message.setBounds(120, 375, 250, 20);
        this.c.add(message);

         // Create the Search button
        searchButton = new JButton("Search");
        searchButton.setBounds(340, 400, 100, 30);
        this.c.add(searchButton);
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setForeground(Color.white);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);

        // Create the Delete button
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(450, 400, 100, 30);
        this.c.add(deleteButton);
        deleteButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.white);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(this);

        // Create the Update button
        updateButton = new JButton("Update");
        updateButton.setBounds(560, 400, 100, 30);
        this.c.add(updateButton);
        updateButton.setBackground(Color.DARK_GRAY);
        updateButton.setForeground(Color.white);
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);

        displayAll = new JButton("Display All");
        displayAll.setBounds(670, 400, 100, 30);
        this.c.add(displayAll);
        displayAll.setBackground(Color.DARK_GRAY);
        displayAll.setForeground(Color.white);
        displayAll.setFocusable(false);
        displayAll.addActionListener(this);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Aadhar No");
        model.addColumn("Name");
        model.addColumn("Mobile");
        model.addColumn("Gender");
        model.addColumn("DOB");
        model.addColumn("Address");
        model.addColumn("Gmail");
        dataTable = new JTable(model);

        scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(360, 70, 430, 300);
        this.c.add(scrollPane, BorderLayout.CENTER);
        //this.c.add(scrollPane);
        
        this.setVisible(true);

    }

    public void displayWelcomeScreen() {
        final JWindow w = new JWindow(this);
        w.setSize(800, 600);
        w.setLocationRelativeTo(null);
        w.setVisible(true);

        JPanel panel = new JPanel();
        w.add(panel);
        // String imagePath="C:\\Users\\Jitendra\\Java\\Swing personal\\image.png";
        JLabel label = new JLabel(new ImageIcon("form.jpg"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JProgressBar progress = new JProgressBar(0, 100);
        progress.setForeground(Color.YELLOW);
        w.add(BorderLayout.PAGE_END, progress);
        w.revalidate();
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = progress.getValue();
                if (x == 100) {
                    w.dispose();
                    RegistrationForm.this.setVisible(true);
                    timer.stop();
                } else {
                    progress.setValue(x + 8);
                }
            }
        });
        timer.start();
    }

    Timer timer;

    @Override

    public void actionPerformed(ActionEvent e) {

        if (terms.isSelected()) {
            submit.setEnabled(true);
        } else {
            submit.setEnabled(false);
        }
        if (e.getSource() == submit) {
            constraintsCheck();
        }
        if (e.getSource() == clear) {
           clearScreen();
        }
        if (e.getSource() == searchButton) {
            SearchRecord s=new SearchRecord();
        }
        if (e.getSource() == deleteButton) {
            DeleteRecord d=new DeleteRecord();
        }
        if (e.getSource() == updateButton) {
            String aadharToUpdate = t3.getText();
            if (!aadharToUpdate.isEmpty() && aadharToUpdate.matches("\\d{12}")) {
               
                String name = t1.getText();
                String mobile = t2.getText();
                String gender = r1.isSelected() ? "Male" : "Female";
                String dob = year.getSelectedItem() + "-" + (month.getSelectedIndex() + 1) + "-" + day.getSelectedItem();
                String address = a1.getText();
                String gmail = t4.getText();
        
                try {
                    String url = "jdbc:mysql://localhost:3306/form";
                    String username = "root";
                    String password = "1234";
                    String query = "UPDATE registrationForm SET name=?, mobile=?, gender=?, dob=?, address=?, gmail=? WHERE aadhar_no=?";
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, mobile);
                    preparedStatement.setString(3, gender);
                    preparedStatement.setString(4, dob);
                    preparedStatement.setString(5, address);
                    preparedStatement.setString(6, gmail);
                    preparedStatement.setString(7, aadharToUpdate);
        
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        screen.setForeground(Color.RED);
                        screen.setText("Record updated successfully.");
                    } else {
                        screen.setForeground(Color.RED);
                        screen.setText("Record not found.");
                    }
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                screen.setForeground(Color.RED);
                screen.setText("Enter a valid Aadhar number to update.");
            }
        }
        
        if (e.getSource() == displayAll) {
           displayAll();
        }
    }
    public void displayAll()
    {

        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0); 

        try {
            String url = "jdbc:mysql://localhost:3306/form";
            String username = "root";
            String password = "1234";
            String query = "SELECT * FROM registrationForm";
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String aadhar = resultSet.getString("aadhar_no");
                String name = resultSet.getString("name");
                String mobile = resultSet.getString("mobile");
                String gender = resultSet.getString("gender");
                String dob = resultSet.getString("dob");
                String address = resultSet.getString("address");
                String gmail = resultSet.getString("gmail");

                
                model.addRow(new Object[] { aadhar, name, mobile, gender, dob, address, gmail });
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        message.setText("");
        a1.setText("");
        terms.setSelected(false);
        submit.setEnabled(false);
        screen.setText("");
        
        
    }

    public void constraintsCheck() {
        String aadharText = t3.getText();
        if (aadharText.matches("\\d{12}")) {
            // String mobileText = t2.getText();
            String nameText = t1.getText();
            if (nameText.length() > 0) {
                // String nameText=t2.getText();
                String mobileText = t2.getText();
                if (mobileText.matches("\\d{10}")) {
                    String address = a1.getText();
                    if (address.length() > 0) {
                        String gmailText = t4.getText().trim();
                        if (gmailText.contains("@")) {
                            mySqlConnection();
                            terms.setSelected(false);
                            submitUnselect();
                            t1.setText("");
                            t2.setText("");
                            t3.setText("");
                            t4.setText("");
                            a1.setText("");
                            terms.setSelected(false);
                        } else {
                            message.setForeground(Color.red);
                            message.setText("Enter a valid Gmail.");
                            terms.setSelected(false);
                            submit.setEnabled(false);
                        }
                    } else {
                        message.setForeground(Color.red);
                        message.setText("Adress cannot be empty.");
                        terms.setSelected(false);
                        submit.setEnabled(false);
                    }
                } else {
                    message.setForeground(Color.red);
                    message.setText("Mobile number must contain 10 Digits.");
                    terms.setSelected(false);
                    submit.setEnabled(false);
                }
            } else {
                message.setForeground(Color.red);
                message.setText("Name cannot be Empty.");
                terms.setSelected(false);
                submit.setEnabled(false);
            }
        } else {
            message.setForeground(Color.red);
            message.setText("Aadhar number must contain 12 Digits.");
            terms.setSelected(false);
            submit.setEnabled(false);
        }
    }

    public void submitUnselect() {
        if (terms.isSelected()) {
            submit.setEnabled(true);
        } else {
            submit.setEnabled(false);
        }
    }

    public void mySqlConnection() {

        String aadharText = t3.getText();
        if (aadharText.matches("\\d+")) {
            long aadhar = Long.parseLong(aadharText);
            String name = t1.getText();
            String mobileText = t2.getText();

            if (mobileText.matches("\\d+")) {
                long mobile = Long.parseLong(mobileText);
                String gender = r2.isSelected() ? "Female" : "Male";
                String dob = year.getSelectedItem() + "-" + (month.getSelectedIndex() + 1) + "-" + day.getSelectedItem();
                String address = a1.getText();
                String gmail = t4.getText(); // Get the Gmail address from the new field

                String query = "INSERT INTO registrationForm (aadhar_no, name, mobile, gender, dob, address, gmail) VALUES (?, ?, ?, ?, ?, ?, ?)";

                String url = "jdbc:mysql://localhost:3306/form";
                String username = "root";
                String password = "1234";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setLong(1, aadhar);
                    preparedStatement.setString(2, name);
                    preparedStatement.setLong(3, mobile);
                    preparedStatement.setString(4, gender);
                    preparedStatement.setString(5, dob);
                    preparedStatement.setString(6, address);
                    preparedStatement.setString(7, gmail); // Set the Gmail address in the query

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        screen.setText("Record Insert Successfully");
                    } else {
                        screen.setText("Insert Failed");
                    }
                    connection.close();
                } catch (ClassNotFoundException e) {
                    screen.setText(e.getMessage());
                } catch (SQLException e) {
                    screen.setText(e.getMessage());
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == t1) {
            nameLabel.setForeground(Color.RED);
            nameLabel.setText("Enter name");
        }
        if (e.getSource() == t2) {
            mobileLabel.setForeground(Color.RED);
            mobileLabel.setText("Enter Mobile no.");
            e.getButton();
        }
        if (e.getSource() == t3) {
            aadharLabel1.setForeground(Color.RED);
            aadharLabel1.setText("Enter Aadhar no.");
            e.getButton();
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == t1) {
            nameLabel.setText("");
        }
        if (e.getSource() == t2) {
            mobileLabel.setText("");
        }
        if (e.getSource() == t3) {
            aadharLabel1.setText("");

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) {}


    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        //frame.setVisible(true);
    }
}

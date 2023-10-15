// import javax.swing.BorderFactory;
// import javax.swing.ImageIcon;
// import javax.swing.JFileChooser;
// import javax.swing.JFrame;
// import javax.swing.JMenu;
// import javax.swing.JMenuBar;
// import javax.swing.JMenuItem;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.KeyStroke;
// import javax.swing.UIManager;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.KeyEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

public class NotepadApp extends JFrame implements ActionListener {

    private JMenuBar menubar;
    private JMenu file;
    private JMenu edit;
    private JMenu help;
    private JMenuItem newFile, openFile, saveFile, print, exit;
    private JMenuItem cut, copy, paste, selectAll, clear;
    private JMenuItem about;
    private JTextArea area;
    private JPanel statusbar;
    private JLabel label;

    NotepadApp() {
        this.setTitle("Notepad Application");
        this.setBounds(100, 50, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.JFIF"));
        this.setIconImage(icon.getImage());

        menubar = new JMenuBar();
        this.add(menubar);
        JMenu file = new JMenu("File");
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        clear = new JMenuItem("Clear All");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(clear);

        JMenu help = new JMenu("Help");
        about = new JMenuItem("About");
        help.add(about);

        area = new JTextArea();
        area.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        // area.setBounds(100, 50, 800,600);

        JScrollPane scrollpane = new JScrollPane(area);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollpane,BorderLayout.CENTER);

        statusbar=new JPanel();
        statusbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        label=new JLabel("Line:1, Column:1");
        statusbar.add(label);
        this.add(label,BorderLayout.SOUTH);
        area.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e)
            {
                int dot=e.getDot();
                int line;
                int column;
                try{
                    line=area.getLineOfOffset(dot)+1;
                    column=dot-area.getLineOfOffset(line-1)+1;
                    label.setText("Line: "+line+",Column: "+column);
                }catch(Exception ex)
                {
                    label.setText("Line: 1, Column: 1");
                }
            }
        });

        this.menubar.add(file);
        this.menubar.add(edit);
        this.menubar.add(help);
        this.setJMenuBar(menubar);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        clear.addActionListener(this);
        about.addActionListener(this);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            // area.setText(null);
            NotepadNew n = new NotepadNew();

        } else if (e.getActionCommand().equalsIgnoreCase("Open")) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textFilter);
            int action = filechooser.showOpenDialog(null);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                    area.read(reader, null);
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textFilter);

            int action = filechooser.showSaveDialog(null);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
                // if(filename.contains(".txt")){
                // filename=filename+".txt";
                // }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    area.write(writer);
                } catch (IOException e1) {

                    e1.printStackTrace();
                }

            }

        } else if (e.getActionCommand().equalsIgnoreCase("Print")) {
            try {
                area.print();
            } catch (PrinterException e1) {

                e1.printStackTrace();
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
            System.exit(0);

        } else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
            area.cut();

        } else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
            area.copy();

        } else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
            area.paste();

        } else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
            area.selectAll();

        } else if (e.getActionCommand().equalsIgnoreCase("About")) {
            new NotepadAbout().setVisible(true);

        } else if (e.getActionCommand().equalsIgnoreCase("Clear All")) {
            area.setText("");
        }

    }
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotepadApp().setVisible(true);

    }
}

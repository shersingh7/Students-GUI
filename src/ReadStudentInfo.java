

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadStudentInfo extends JFrame{

    private JPanel content;

    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReadStudentInfo GUI = new ReadStudentInfo();
                    GUI.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ReadStudentInfo(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 250, 400, 300);
        content = new JPanel();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        content.setAlignmentX(Component.LEFT_ALIGNMENT);
        setContentPane(content);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("---------- Student Info ----------");
        content.add(title);


        try {
            ArrayList<Student> student;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student.txt"));
            student = (ArrayList<Student>) ois.readObject();

            if(student != null){
                int i=1;
                for(Student s: student)
                {
                    JTextArea info = new JTextArea(s.toString());
                    info.setEditable(false);
                    JLabel heading = new JLabel("Student " + i + ": ");
                    content.add(heading);
                    content.add(info);
                    ++i;
                }
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }

        JButton btn = new JButton("Exit");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        content.add(btn);
    }

}

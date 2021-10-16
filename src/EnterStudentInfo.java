

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EnterStudentInfo extends JFrame {

    private JPanel content;
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField courseField;
    private JLabel error;
    private JLabel error2;
    private JLabel errorMain;
    ArrayList<Student> arrayList = new ArrayList<>();
    Integer sID;

    public EnterStudentInfo() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        content = new JPanel();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content);
        content.setLayout(null);

        JLabel idLabel = new JLabel("Student ID: ");
        idLabel.setBounds(50, 15, 90, 15);
        content.add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 10, 130, 25);
        content.add(idField);
        idField.setColumns(10);
        error = new JLabel("Wrong Entry!!!");
        error.setForeground(Color.RED);
        error.setBounds(290,15,90,15);
        content.add(error);
        error.setVisible(false);

        JLabel fNameLabel = new JLabel("First Name: ");
        fNameLabel.setBounds(50, 55, 90, 15);
        content.add(fNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(140, 50, 130, 25);
        content.add(firstNameField);
        firstNameField.setColumns(10);

        JLabel lNameLabel = new JLabel("Last Name: ");
        lNameLabel.setBounds(50, 100, 90, 15);
        content.add(lNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(140, 90, 130, 25);
        content.add(lastNameField);
        lastNameField.setColumns(10);

        JLabel courseLabel = new JLabel("Courses: ");
        courseLabel.setBounds(50, 140, 90, 15);
        content.add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(140, 135, 130, 25);
        content.add(courseField);
        courseField.setColumns(10);
        error2 = new JLabel("Can't be empty!!!");
        error2.setForeground(Color.RED);
        error2.setBounds(283,136,96,16);
        content.add(error2);
        error2.setVisible(false);

        errorMain = new JLabel("You have entered something wrong!!!");
        errorMain.setForeground(Color.RED);
        errorMain.setBounds(60,150,89,16);
        content.add(errorMain);
        errorMain.setVisible(false);

        JButton save = new JButton("Save");
        SaveHandler sHandler = new SaveHandler();
        save.addActionListener(sHandler);
        save.setBounds(45, 225, 117, 29);
        content.add(save);

        JButton submit = new JButton("Submit");
        SubmitHandler subHandler = new SubmitHandler();
        submit.addActionListener(subHandler);
        submit.setBounds(290, 225, 117, 29);
        content.add(submit);

    }

    private class SaveHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Student student = new Student();

            String id = idField.getText();

            String courses = courseField.getText();
            if(courses.isEmpty())
            {
                error2.setVisible(true);
            }
            student.setCourses(courses);

            try {
                sID = Integer.parseInt(id);

                if (error.isVisible()) {
                    error.setVisible(false);
                }

                if (error2.isVisible()){
                    error2.setVisible(false);
                }

                student.setStdID(sID);
                student.setFirstName(firstNameField.getText());
                student.setLastName(lastNameField.getText());
                arrayList.add(student);
                clearFields();

            } catch (Throwable e2) {
                error.setVisible(true);
                e2.printStackTrace();
            }
        }
    }

    private class SubmitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                boolean ok = writeInfo();

                if (!ok) {
                    System.out.println("Didn't worked");
                    errorMain.setVisible(true);
                }

                System.exit(0);
            } catch (Throwable e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean writeInfo() {

        boolean ok = true;

        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("Student.txt"))) {
            objOut.writeObject(arrayList);
            objOut.close();
            objOut.flush();
        } catch (IOException e) {
            System.out.println(e);
            ok = false;
        }
        return ok;
    }

    public void clearFields(){

        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        courseField.setText("");

        if(error.isVisible())
        {
            error.setVisible(false);
        }

        if(error2.isVisible())
        {
            error2.setVisible(false);
        }

        if(errorMain.isVisible())
        {
            errorMain.setVisible(false);
        }

    }

    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        EnterStudentInfo main = new EnterStudentInfo();
                        main.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
    }
}
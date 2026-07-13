package travel.tourism;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {

    JButton create, back;
    JTextField textField, tfname, tfpassword, tfanswer, tfphone;
    JComboBox comboBox;

    Signup() {
        setBounds(600, 250, 700, 406);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(133, 193, 233));
        p1.setBounds(0, 0, 400, 400);
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(50, 20, 125, 25);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(l1);

        textField = new JTextField();
        textField.setBounds(190, 20, 180, 25);
        textField.setBorder(BorderFactory.createEmptyBorder());
        p1.add(textField);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(50, 60, 125, 25);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblName);

        tfname = new JTextField();
        tfname.setBounds(190, 60, 180, 25);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50, 100, 125, 25);
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblPhone);

        tfphone = new JTextField();
        tfphone.setBounds(190, 100, 180, 25);
        tfphone.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfphone);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 140, 125, 25);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblPassword);

        tfpassword = new JTextField();
        tfpassword.setBounds(190, 140, 180, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfpassword);

        JLabel lblsecurity = new JLabel("Security Question");
        lblsecurity.setBounds(50, 180, 150, 25);
        lblsecurity.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblsecurity);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{
            "Your NickName?", "Your Lucky Number?",
            "Your child SuperHero?", "Your childhood Name ?"
        }));
        comboBox.setBounds(190, 180, 180, 25);
        p1.add(comboBox);

        JLabel lblanswer = new JLabel("Answer");
        lblanswer.setBounds(50, 220, 125, 25);
        lblanswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblanswer);

        tfanswer = new JTextField();
        tfanswer.setBounds(190, 220, 180, 25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfanswer);

        create = new JButton("Create");
        create.setFont(new Font("Tahoma", Font.BOLD, 14));
        create.setBounds(80, 280, 100, 30);
        create.setBackground(Color.WHITE);
        create.setForeground(new Color(133, 193, 233));
        create.addActionListener(this);
        p1.add(create);

        back = new JButton("Back");
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.setBounds(250, 280, 100, 30);
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(133, 193, 233));
        back.addActionListener(this);
        p1.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(435, 50, 250, 250);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String username = textField.getText();
            String name = tfname.getText();
            String phone = tfphone.getText();
            String password = tfpassword.getText();
            String question = (String) comboBox.getSelectedItem();
            String answer = tfanswer.getText();
          

            String query = "insert into account(username, name, password, security, answer, phone) "
                    + "values('" + username + "', '" + name + "', '" + password + "', '" + question + "', '" + answer + "', '" + phone + "')";

            

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
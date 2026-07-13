package travel.tourism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgetPassword extends JFrame implements ActionListener {

    JTextField tfphone, tfusername, tfquestion, tfanswer, tfpassword;
    JButton search, retrieve, back;

    ForgetPassword() {
        setBounds(350, 200, 850, 380);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forgotpassword.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 70, 200, 200);
        add(image);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(30, 30, 500, 280);
        add(p1);

        JLabel lblphone = new JLabel("Phone No.");
        lblphone.setBounds(40, 20, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 20, 150, 25);
        tfphone.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfphone);

        search = new JButton("Search");
        search.addActionListener(this);
        search.setForeground(Color.white);
        search.setBackground(Color.gray);
        search.setBounds(380, 20, 100, 25);
        p1.add(search);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 60, 150, 25);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(220, 60, 150, 25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        tfusername.setEditable(false);
        p1.add(tfusername);

        JLabel lblquestion = new JLabel("Security Question");
        lblquestion.setBounds(40, 100, 150, 25);
        lblquestion.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblquestion);

        tfquestion = new JTextField();
        tfquestion.setBounds(220, 100, 150, 25);
        tfquestion.setBorder(BorderFactory.createEmptyBorder());
        tfquestion.setEditable(false);
        p1.add(tfquestion);

        JLabel lblanswer = new JLabel("Answer");
        lblanswer.setBounds(40, 140, 150, 25);
        lblanswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblanswer);

        tfanswer = new JTextField();
        tfanswer.setBounds(220, 140, 150, 25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfanswer);

        retrieve = new JButton("Retrieve");
        retrieve.addActionListener(this);
        retrieve.setForeground(Color.white);
        retrieve.setBackground(Color.gray);
        retrieve.setBounds(380, 140, 100, 25);
        p1.add(retrieve);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(40, 180, 150, 25);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblPassword);

        tfpassword = new JTextField();
        tfpassword.setBounds(220, 180, 150, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        tfpassword.setEditable(false);
        p1.add(tfpassword);

        back = new JButton("Back");
        back.setBounds(150, 230, 100, 25);
        back.setBackground(Color.gray);
        back.setForeground(Color.white);
        back.addActionListener(this);
        p1.add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            try {
                String query = "SELECT * FROM account WHERE phone='" + tfphone.getText() + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    tfusername.setText(rs.getString("username"));
                    tfquestion.setText(rs.getString("security"));
                } else {
                    JOptionPane.showMessageDialog(null, "Phone number not found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == retrieve) {
            try {
                String query = "SELECT * FROM account WHERE phone='" + tfphone.getText() + "' AND answer='" + tfanswer.getText() + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    tfpassword.setText(rs.getString("password"));
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect answer!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new ForgetPassword();
    }
}
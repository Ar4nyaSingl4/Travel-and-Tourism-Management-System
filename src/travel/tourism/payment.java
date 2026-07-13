package travel.tourism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class payment extends JFrame implements ActionListener {

    String username;
    JLabel amountLabel;
    JButton paidBtn, backBtn;

    public payment(String username) {
        this.username = username;

        setTitle("Payment");
        setSize(500, 600);
        setLocation(450, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Scan to Pay");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(150, 20, 300, 40);
        add(heading);

        // ================= QR IMAGE =================
        ImageIcon qr = new ImageIcon(ClassLoader.getSystemResource("icons/qr-code.jpeg"));
        Image qr2 = qr.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel qrLabel = new JLabel(new ImageIcon(qr2));
        qrLabel.setBounds(120, 80, 250, 250);
        add(qrLabel);

        // ================= AMOUNT LABEL =================
        JLabel amtText = new JLabel("Amount to Pay:");
        amtText.setFont(new Font("Tahoma", Font.BOLD, 20));
        amtText.setBounds(150, 350, 200, 30);
        add(amtText);

        amountLabel = new JLabel("₹ 0");
        amountLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        amountLabel.setForeground(Color.RED);
        amountLabel.setBounds(200, 390, 250, 30);
        add(amountLabel);

        loadAmount();

        // ================= BUTTON =================
        paidBtn = new JButton("Amount Paid");
        paidBtn.setBounds(160, 450, 170, 40);
        paidBtn.addActionListener(this);
        add(paidBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(180, 500, 120, 35);
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    // ================= FETCH BOOKING AMOUNT =================
    private void loadAmount() {
        try {
            Conn c = new Conn();

            String query = "SELECT total_amount FROM bookings WHERE username=? ORDER BY id DESC LIMIT 1";
            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int amount = rs.getInt("total_amount");
                amountLabel.setText("₹ " + amount);
            } else {
                amountLabel.setText("No bookings found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paidBtn) {

            // Custom dialog with Generate Invoice button
            int option = JOptionPane.showOptionDialog(
                    this,
                    "Payment Successful!\nClick below to generate invoice.",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Generate Invoice"},
                    "Generate Invoice"
            );

            if (option == 0) {
                new Invoice(username); // open invoice
                dispose();             // close payment window
            }

        } else if (e.getSource() == backBtn) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new payment("test");
    }
}
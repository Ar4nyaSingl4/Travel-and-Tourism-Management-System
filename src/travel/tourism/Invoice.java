package travel.tourism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Invoice extends JFrame implements ActionListener {

    String username;
    JTextArea invoiceArea;
    JButton print, back;

    public Invoice(String username) {
        this.username = username;

        setTitle("Invoice");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel heading = new JLabel("Travel & Tourism Invoice");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(150, 10, 400, 40);
        add(heading);

        invoiceArea = new JTextArea();
        invoiceArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        invoiceArea.setEditable(false);

        JScrollPane sp = new JScrollPane(invoiceArea);
        sp.setBounds(20, 70, 550, 400);
        add(sp);

        print = new JButton("Print Invoice");
        print.setBounds(120, 500, 150, 35);
        print.addActionListener(this);
        add(print);

        back = new JButton("Back");
        back.setBounds(300, 500, 150, 35);
        back.addActionListener(this);
        add(back);

        generateInvoice();
        setVisible(true);
    }

    // ---------------- GENERATE INVOICE ----------------
    private void generateInvoice() {
        try {
            Conn c = new Conn();

            String q = "SELECT * FROM bookings WHERE username='" + username + "' ORDER BY id DESC LIMIT 1";
            ResultSet rs = c.s.executeQuery(q);

            String invoiceText = "------------------ TRAVEL & TOURISM SYSTEM -----------------\n\n";
            invoiceText += "Invoice For User : " + username + "\n";
            invoiceText += "--------------------------------------------------------------\n";

            if (rs.next()) {
                invoiceText += "Booking Type      : " + rs.getString("booking_type") + "\n";
                invoiceText += "Item Name         : " + rs.getString("item_name") + "\n";
                invoiceText += "Destination       : " + rs.getString("destination") + "\n";
                invoiceText += "Persons           : " + rs.getInt("persons") + "\n";
                invoiceText += "Rooms             : " + rs.getInt("rooms") + "\n";
                invoiceText += "Days              : " + rs.getInt("days") + "\n";
                invoiceText += "Start Date        : " + rs.getString("start_date") + "\n";
                invoiceText += "End Date          : " + rs.getString("end_date") + "\n";
                invoiceText += "--------------------------------------------------------------\n";
                invoiceText += "Total Amount      : ₹ " + rs.getInt("total_amount") + "\n";
                invoiceText += "--------------------------------------------------------------\n\n";
                invoiceText += "Thank you for booking with us!\n";
                invoiceText += "For support contact: tourism@gmail.com\n";
            }

            invoiceArea.setText(invoiceText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- BUTTON ACTIONS ----------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == print) {
            try {
                invoiceArea.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new Invoice("piyush");
    }
}
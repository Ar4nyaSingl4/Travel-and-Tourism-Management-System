package travel.tourism;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CancelBooking extends JFrame implements ActionListener {

    JTable table;
    JButton cancel, back;
    String username;

    public CancelBooking(String username) {
        this.username = username;

        setTitle("Cancel Booking");
        setBounds(350, 100, 900, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Your Bookings");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(20, 10, 300, 30);
        add(heading);

        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 60, 850, 300);
        add(sp);

        cancel = new JButton("Cancel Selected Booking");
        cancel.setBounds(200, 380, 220, 40);
        cancel.addActionListener(this);
        add(cancel);

        back = new JButton("Back");
        back.setBounds(450, 380, 150, 40);
        back.addActionListener(this);
        add(back);

        fetchBookings();
        setVisible(true);
    }

    // ===================== LOAD USER BOOKINGS =====================
    private void fetchBookings() {
        try {
            Conn c = new Conn();

            String query = "SELECT id, booking_type, item_name, persons, days, total_amount, "
                    + "destination, start_date, end_date FROM bookings WHERE username=?";

            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            table.setModel(buildTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===================== DELETE SELECTED BOOKING =====================
    private void cancelSelectedBooking() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel.");
            return;
        }

        int bookingId = Integer.parseInt(table.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel this booking?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn c = new Conn();

                String query = "DELETE FROM bookings WHERE id=?";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setInt(1, bookingId);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Booking cancelled successfully!");

                fetchBookings();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ===================== TABLE MODEL BUILDER =====================
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        String[] colNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            colNames[i - 1] = meta.getColumnName(i);
        }

        DefaultTableModel model = new DefaultTableModel(colNames, 0);

        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        return model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            cancelSelectedBooking();
        } else if (e.getSource() == back) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new CancelBooking("piyush");
    }
}
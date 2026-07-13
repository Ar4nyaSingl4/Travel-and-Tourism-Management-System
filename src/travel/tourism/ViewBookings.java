package travel.tourism;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBookings extends JFrame {

    JTable table;
    DefaultTableModel model;
    String username;

    public ViewBookings(String username) {
        this.username = username;

        setTitle("Your Bookings");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {
                "Booking Type", "Item Name", "Destination",
                "Persons", "Rooms", "Days",
                "Start Date", "End Date", "Total Amount"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        loadBookings();

        add(new JScrollPane(table), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadBookings() {
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM bookings WHERE username='" + username + "'";

            ResultSet rs = c.s.executeQuery(q);

            while (rs.next()) {

                String type = rs.getString("booking_type");
                String itemName = rs.getString("item_name");
                String destination = rs.getString("destination");
                int persons = rs.getInt("persons");
                int rooms = rs.getInt("rooms");

                // DAYS is already stored correctly at booking time
                int days = rs.getInt("days");

                String start = rs.getString("start_date");
                String end = rs.getString("end_date");
                int total = rs.getInt("total_amount");

                model.addRow(new Object[]{
                        type, itemName, destination,
                        persons, rooms, days,
                        start, end, total
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewBookings("Alex123");
    }
}
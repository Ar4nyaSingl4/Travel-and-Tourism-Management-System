package travel.tourism;

import javax.swing.*;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class BookingForm extends JFrame {

    private Hotel hotel;
    private Package pack;
    private String destination; // city like "Goa"

    JTextField nameField, phoneField, personsField, roomsField, daysField, startDateField, endDateField;

    String fetchedPhone = "";
    String fetchedName = "";

    // ---------------- HOTEL CONSTRUCTOR ----------------
    public BookingForm(String username, Hotel hotel, String destination) {
        this.hotel = hotel;
        this.pack = null;
        this.destination = destination;

        fetchUserDetails(username);
        initHotelUI(username);
    }

    // ---------------- PACKAGE CONSTRUCTOR ----------------
    public BookingForm(String username, Package pack, String destination) {
        this.pack = pack;
        this.hotel = null;
        this.destination = destination;

        fetchUserDetails(username);
        initPackageUI(username);
    }


    // ---------------- FETCH USER DATA ----------------
    private void fetchUserDetails(String username) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT name, phone FROM customer WHERE username='" + username + "'");
            if (rs.next()) {
                fetchedName = rs.getString("name");
                fetchedPhone = rs.getString("phone");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ---------------- HOTEL BOOKING UI ----------------
    private void initHotelUI(String username) {
        setTitle("Hotel Booking");
        setSize(520, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Complete Your Hotel Booking", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(10, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        form.add(new JLabel("Name:"));
        nameField = new JTextField(fetchedName);
        nameField.setEditable(false);
        form.add(nameField);

        form.add(new JLabel("Phone:"));
        phoneField = new JTextField(fetchedPhone);
        phoneField.setEditable(false);
        form.add(phoneField);

        form.add(new JLabel("Persons:"));
        personsField = new JTextField();
        form.add(personsField);

        form.add(new JLabel("Rooms:"));
        roomsField = new JTextField("1");
        form.add(roomsField);

        form.add(new JLabel("Days:"));
        daysField = new JTextField();
        form.add(daysField);

        form.add(new JLabel("Start Date (yyyy-mm-dd):"));
        startDateField = new JTextField();
        form.add(startDateField);

        form.add(new JLabel("End Date:"));
        endDateField = new JTextField();
        endDateField.setEditable(false);
        form.add(endDateField);

        // Auto calculate end date
        CaretListener listener = e -> calculateEndDate();
        startDateField.addCaretListener(listener);
        daysField.addCaretListener(listener);

        form.add(new JLabel("Price per Day:"));
        form.add(new JLabel(hotel.price));

        add(form, BorderLayout.CENTER);

        JButton btn = new JButton("Confirm Booking");
        btn.addActionListener(e -> saveBooking(username));
        add(btn, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    // ---------------- PACKAGE BOOKING UI ----------------
    private void initPackageUI(String username) {
        setTitle("Package Booking");
        setSize(520, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Complete Package Booking", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        form.add(new JLabel("Name:"));
        nameField = new JTextField(fetchedName);
        nameField.setEditable(false);
        form.add(nameField);

        form.add(new JLabel("Phone:"));
        phoneField = new JTextField(fetchedPhone);
        phoneField.setEditable(false);
        form.add(phoneField);

        form.add(new JLabel("Persons:"));
        personsField = new JTextField();
        form.add(personsField);

        form.add(new JLabel("Rooms:"));
        roomsField = new JTextField("1");
        form.add(roomsField);

        form.add(new JLabel("Start Date (yyyy-mm-dd):"));
        startDateField = new JTextField();
        form.add(startDateField);

        form.add(new JLabel("Package Price:"));
        form.add(new JLabel(pack.price));

        add(form, BorderLayout.CENTER);

        JButton btn = new JButton("Confirm Booking");
        btn.addActionListener(e -> saveBooking(username));
        add(btn, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    // ---------------- END DATE CALC ----------------
    private void calculateEndDate() {
        try {
            if (!startDateField.getText().trim().isEmpty() &&
                !daysField.getText().trim().isEmpty()) {

                LocalDate start = LocalDate.parse(startDateField.getText().trim());
                int days = Integer.parseInt(daysField.getText().trim());
                LocalDate end = start.plusDays(days);
                endDateField.setText(end.toString());
            }
        } catch (Exception ignored) {}
    }


    // ----------- Extract days from "3 Days / 2 Nights" ----------
    private int parseDays(String desc) {
        try {
            return Integer.parseInt(desc.split(" ")[0]);
        } catch (Exception e) {
            return 0;
        }
    }


    // ---------------- SAVE BOOKING TO DB ----------------
    private void saveBooking(String username) {
        try {
            int persons = Integer.parseInt(personsField.getText().trim());
            int rooms = Integer.parseInt(roomsField.getText().trim());
            String startDate = startDateField.getText().trim();

            if (startDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter start date");
                return;
            }

            int price = Integer.parseInt(
                    (hotel != null ? hotel.price : pack.price)
                            .replace("₹", "").replace(",", "").trim()
            );

            int days;
            String endDate;

            if (hotel != null) {
                // ----- HOTEL -----
                days = Integer.parseInt(daysField.getText().trim());
                LocalDate sd = LocalDate.parse(startDate);
                LocalDate ed = sd.plusDays(days);
                endDate = ed.toString();

                // price * days * rooms
                int total = price * days * rooms;

                insertBooking(username, "hotel", hotel.name, persons, days, rooms, startDate, endDate, total);
            } else {
                // ----- PACKAGE -----
                days = parseDays(pack.description);
                LocalDate sd = LocalDate.parse(startDate);
                LocalDate ed = sd.plusDays(days);
                endDate = ed.toString();

                // package price * persons * rooms
                int total = price * persons * rooms;

                insertBooking(username, "package", pack.name, persons, days, rooms, startDate, endDate, total);
            }

            JOptionPane.showMessageDialog(this, "Booking Successful!");
            dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }


    // ---------------- INSERT INTO MYSQL ----------------
    private void insertBooking(String username, String type, String itemName,
                               int persons, int days, int rooms,
                               String startDate, String endDate, int total) {

        try {
            Conn c = new Conn();
            String q = "INSERT INTO bookings (username, booking_type, item_name, destination, persons, days, rooms, start_date, end_date, total_amount) " +
                    "VALUES ('" + username + "','" + type + "','" + itemName + "','" + destination + "'," +
                    persons + "," + days + "," + rooms + ",'" + startDate + "','" + endDate + "'," + total + ")";

            c.s.executeUpdate(q);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
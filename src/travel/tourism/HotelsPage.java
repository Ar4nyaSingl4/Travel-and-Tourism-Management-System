package travel.tourism;

import javax.swing.*;
import java.awt.*;

public class HotelsPage extends JFrame {

    String username;
    String destination;
    Hotel[] hotels;

    Hotel selectedHotel;   // ✅ ADD THIS

    public HotelsPage(String username, String destination, Hotel[] hotels) {
        this.username = username;
        this.destination = destination;
        this.hotels = hotels;

        setTitle(destination + " - Hotels");
        setSize(900, 600);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Hotels in " + destination, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setOpaque(true);
        title.setBackground(new Color(30, 60, 90));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(900, 60));
        add(title, BorderLayout.NORTH);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(0, 1, 10, 10));
        container.setBackground(new Color(235, 245, 255));

        for (Hotel h : hotels) {
            container.add(createHotelPanel(h));
        }

        JScrollPane scroll = new JScrollPane(container);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JPanel createHotelPanel(Hotel hotel) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon(getClass().getResource(hotel.image));
        Image scaled = icon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled));
        img.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.WHITE);

        JLabel name = new JLabel(hotel.name);
        name.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel rating = new JLabel("Rating: " + hotel.rating);
        rating.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel price = new JLabel("Price: ₹" + hotel.price + " / night");
        price.setFont(new Font("Arial", Font.PLAIN, 16));
        price.setForeground(new Color(0, 128, 0));

        textPanel.add(name);
        textPanel.add(rating);
        textPanel.add(price);

        // ⭐ FIXED BOOK BUTTON
        JButton bookBtn = new JButton("Book Now");
        bookBtn.setFont(new Font("Arial", Font.BOLD, 14));
        bookBtn.setBackground(new Color(0, 100, 200));
        bookBtn.setForeground(Color.WHITE);

        bookBtn.addActionListener(e -> {
            selectedHotel = hotel;  // ⭐ set selected hotel

            new BookingForm(username, selectedHotel, destination);  // ⭐ now valid
        });

        panel.add(img, BorderLayout.WEST);
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(bookBtn, BorderLayout.EAST);

        return panel;
    }
}
package travel.tourism;

import javax.swing.*;
import java.awt.*;

public class PackagesPage extends JFrame {

    String username;
    String city;
    Package[] packages;

    public PackagesPage(String username, String city, Package[] packages) {
        this.username = username;
        this.city = city;
        this.packages = packages;

        setTitle(city + " Packages");
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(0, 3, 15, 15));
        container.setBackground(Color.WHITE);
        container.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        for (Package p : packages) {
            container.add(createPackagePanel(p));
        }

        add(new JScrollPane(container));
        setVisible(true);
    }

    private JPanel createPackagePanel(Package p) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        ImageIcon icon = new ImageIcon(getClass().getResource(p.image));
        Image scaled = icon.getImage().getScaledInstance(200,150,Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel name = new JLabel(p.name, JLabel.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel desc = new JLabel(p.description, JLabel.CENTER);
        JLabel price = new JLabel("Price: " + p.price, JLabel.CENTER);

        JButton bookBtn = new JButton("Book Now");
        bookBtn.addActionListener(e -> {
            // pass city as destination
            new BookingForm(username, p, city);
        });

        panel.add(name, BorderLayout.NORTH);
        panel.add(imgLabel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(3,1));
        bottom.add(desc);
        bottom.add(price);
        bottom.add(bookBtn);

        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }
}
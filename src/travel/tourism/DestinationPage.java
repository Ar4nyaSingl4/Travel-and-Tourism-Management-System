package travel.tourism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class DestinationPage extends JFrame {
    
    String username;  

    // ------------------- HOTEL LISTS ---------------------
    Hotel[] goaHotels = {
        new Hotel("Taj Exotica", "/icons/hotel1.jpeg", "4.8 ★", "8500"),
        new Hotel("Goa Paradise Resort", "/icons/hotel2.jpeg", "4.3 ★", "5500"),
        new Hotel("Sea Breeze Hotel", "/icons/hotel3.jpeg", "4.0 ★", "3000")
    };

    Hotel[] rajasthanHotels = {
        new Hotel("Raj Palace", "/icons/hotel4.jpeg", "4.7 ★", "9000"),
        new Hotel("Pink City Residency", "/icons/hotel5.jpeg", "4.2 ★", "4500"),
        new Hotel("Heritage Haveli", "/icons/hotel6.jpeg", "4.1 ★", "3500")
    };

    Hotel[] keralaHotels = {
        new Hotel("Kumarakom Lake Resort", "/icons/hotel7.jpeg", "4.8 ★", "10000"),
        new Hotel("Backwater Retreat", "/icons/hotel8.jpeg", "4.4 ★", "5200"),
        new Hotel("Coconut Grove", "/icons/hotel9.jpeg", "4.0 ★", "3800")
    };

    Hotel[] mumbaiHotels = {
        new Hotel("Snow Valley Resort", "/icons/hotel10.jpeg", "4.6 ★", "6000"),
        new Hotel("Hilltop View Hotel", "/icons/hotel11.jpeg", "4.3 ★", "4200"),
        new Hotel("Mountain Breeze", "/icons/hotel12.jpeg", "4.1 ★", "3500")
    };

    // ------------------- PACKAGE LISTS ---------------------
    Package[] goaPackages = {
        new Package("Goa Budget Trip", "3 Days / 2 Nights", "₹6000", "/icons/goapackage1.jpeg",3),
        new Package("Goa Premium", "5 Days / 4 Nights", "₹12000", "/icons/goapackage2.jpeg",5),
        new Package("Goa Adventure Pack", "4 Days / 3 Nights", "₹9000", "/icons/goapackage3.jpeg",4)
    };

    Package[] rajasthanPackages = {
        new Package("Royal Rajasthan", "4 Days / 3 Nights", "₹8000", "/icons/jaipur.jpeg",4),
        new Package("Jaipur Heritage", "3 Days / 2 Nights", "₹6500", "/icons/jaipur.jpeg",3),
        new Package("Desert Safari", "5 Days / 4 Nights", "₹13000", "/icons/jaipur.jpeg",5)
    };

    Package[] keralaPackages = {
        new Package("Backwater Escape", "4 Days / 3 Nights", "₹10000", "/icons/kerala.jpeg",4),
        new Package("Kerala Wellness", "3 Days / 2 Nights", "₹8500", "/icons/kerala.jpeg",3),
        new Package("Hill & Beach Combo", "6 Days / 5 Nights", "₹15000", "/icons/kerala.jpeg",6)
    };

    Package[] mumbaiPackages = {
        new Package("Mumbai City Tour", "2 Days / 1 Night", "₹4000", "/icons/mumbai.jpeg",2),
        new Package("Mumbai Premium", "4 Days / 3 Nights", "₹10000", "/icons/mumbai.jpeg",4),
        new Package("Lonavala + Mumbai Combo", "3 Days / 2 Nights", "₹7500", "/icons/mumbai.jpeg",3)
    };

    public DestinationPage(String username) {
        this.username = username; 
        setTitle("Choose Destination");
        setSize(900, 600);
        setLayout(new GridLayout(2, 2));
        //    getContentPane().setBackground(new Color(245, 245, 255));  // Light baby blue
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Goa Panel
        JPanel goaPanel = createDestinationPanel(
                "Goa",
                "/icons/goa.jpeg",
                new String[]{"/icons/goa1.jpeg", "/icons/goa2.jpeg", "/icons/goa3.jpeg"}
        );
        add(goaPanel);

        // Jaipur Panel
        JPanel rajasthanPanel = createDestinationPanel(
                "Rajasthan",
                "/icons/jaipur.jpeg",
                new String[]{"/icons/jaipur1.jpeg", "/icons/jaipur2.jpeg", "/icons/jaipur3.jpeg"}
        );
        add(rajasthanPanel);

        // Kerala Panel
        JPanel keralaPanel = createDestinationPanel(
                "Kerala",
                "/icons/kerala.jpeg",
                new String[]{"/icons/kerala1.jpeg", "/icons/kerala2.jpeg", "/icons/kerala3.jpeg"}
        );
        add(keralaPanel);

        JPanel mumbaiPanel = createDestinationPanel(
                "Mumbai",
                "/icons/mumbai.jpeg", // Thumbnail
                new String[]{
                    "/icons/mumbai1.jpeg",
                    "/icons/mumbai2.jpeg",
                    "/icons/mumbai3.jpeg" // Slideshow images
                }
        );
        add(mumbaiPanel);

        setVisible(true);
    }

    private JPanel createDestinationPanel(String name, String image, String[] slides) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel img = new JLabel(new ImageIcon(getClass().getResource(image)));
        img.setHorizontalAlignment(JLabel.CENTER);

        JLabel title = new JLabel(name, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel btnPanel = new JPanel();
        JButton hotelBtn = new JButton("Hotels");
        JButton packageBtn = new JButton("Packages");
        btnPanel.add(hotelBtn);
        btnPanel.add(packageBtn);

        // Slideshow on clicking picture
        img.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new SlideshowWindow(name + " Photos", slides);
            }
        });

        // Open specific hotel list
        hotelBtn.addActionListener(e -> {
            switch (name) {
                case "Goa":
                    new HotelsPage(username,"Goa", goaHotels);
                    break;
                case "Rajasthan":
                    new HotelsPage(username,"Rajasthan", rajasthanHotels);
                    break;
                case "Kerala":
                    new HotelsPage(username,"Kerala", keralaHotels);
                    break;
                case "Mumbai":
                    new HotelsPage(username,"Mumbai", mumbaiHotels);
                    break;
            }
        });

        packageBtn.addActionListener(e -> {
            switch (name) {
                case "Goa":
                    new PackagesPage(username,"Goa ", goaPackages);
                    break;
                case "Rajasthan":
                    new PackagesPage(username,"Rajasthan ", rajasthanPackages);
                    break;
                case "Kerala":
                    new PackagesPage(username,"Kerala ", keralaPackages);
                    break;
                case "Mumbai":
                    new PackagesPage(username,"Mumbai ", mumbaiPackages);
                    break;
            }
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(img, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new DestinationPage("Alex123");
    }
}

class SlideshowWindow extends JFrame {

    JLabel imageLabel;
    int index = 0;

    public SlideshowWindow(String title, String[] images) {
        setTitle(title);
        setSize(750, 550);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Background Panel
        JPanel bgPanel = new JPanel(new BorderLayout());
        bgPanel.setBackground(new Color(30, 30, 30));  // Dark stylish background
        bgPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setOpaque(true);                                // MAKE LABEL SOLID
        imageLabel.setBackground(new Color(50, 50, 60));           // IMAGE BACKGROUND COLOR
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3)); // Optional frame

        updateImage(images);

        JButton next = new JButton("Next >");
        next.setFont(new Font("Arial", Font.BOLD, 16));
        next.addActionListener(e -> {
            index = (index + 1) % images.length;
            updateImage(images);
        });

        bgPanel.add(imageLabel, BorderLayout.CENTER);
        bgPanel.add(next, BorderLayout.SOUTH);

        add(bgPanel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    void updateImage(String[] images) {
        ImageIcon icon = new ImageIcon(getClass().getResource(images[index]));
        Image scaled = icon.getImage().getScaledInstance(650, 450, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaled));
    }
}
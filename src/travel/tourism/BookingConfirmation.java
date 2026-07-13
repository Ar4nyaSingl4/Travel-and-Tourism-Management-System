package travel.tourism;

import javax.swing.*;
import java.awt.*;

public class BookingConfirmation extends JFrame {

    public BookingConfirmation(String name, int amount) {

        setTitle("Booking Confirmed");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel msg = new JLabel(
                "<html><center>Thank you, " + name + "!<br>Your booking is confirmed.<br>Total Amount: ₹" 
                + amount + "</center></html>",
                JLabel.CENTER
        );
        msg.setFont(new Font("Arial", Font.BOLD, 18));

        add(msg);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

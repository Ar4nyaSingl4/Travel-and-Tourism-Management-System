package travel.tourism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {

    String username;
    JButton addPersonalDetails, viewPersonalDetails,updatePersonalDetails,destinations, viewbooking,cancelbooking,deletePersonalDetails,payments,invoice,calculator;

    Dashboard(String username) {
        this.username = username;
        setBounds(0, 0, 1600, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0, 0, 102));
        p1.setBounds(0, 0, 1600, 65);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel icon = new JLabel(i3);
        icon.setBounds(5, 0, 70, 70);
        p1.add(icon);

        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(80, 10, 300, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        p1.add(heading);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(0, 0, 102));
        p2.setBounds(0, 65, 300, 1000);
        add(p2);

        addPersonalDetails = new JButton("Add personal details");
        addPersonalDetails.setBounds(0, 0, 300, 50);
        addPersonalDetails.setBackground(new Color(0, 0, 102));
        addPersonalDetails.setForeground(Color.WHITE);
        addPersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addPersonalDetails.setMargin(new Insets(0, 0, 0, 70));
        addPersonalDetails.addActionListener(this);
        p2.add(addPersonalDetails);

        updatePersonalDetails = new JButton("Update personal details");
        updatePersonalDetails.setBounds(0, 50, 300, 50);
        updatePersonalDetails.setBackground(new Color(0, 0, 102));
        updatePersonalDetails.setForeground(Color.WHITE);
        updatePersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updatePersonalDetails.setMargin(new Insets(0, 0, 0, 40));
        updatePersonalDetails.addActionListener(this);
        p2.add(updatePersonalDetails);

        viewPersonalDetails = new JButton("View details");
        viewPersonalDetails.setBounds(0, 100, 300, 50);
        viewPersonalDetails.setBackground(new Color(0, 0, 102));
        viewPersonalDetails.setForeground(Color.WHITE);
        viewPersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewPersonalDetails.setMargin(new Insets(0, 0, 0, 140));
        viewPersonalDetails.addActionListener(this);
        p2.add(viewPersonalDetails);

        deletePersonalDetails = new JButton("Delete personal details");
        deletePersonalDetails.setBounds(0, 150, 300, 50);
        deletePersonalDetails.setBackground(new Color(0, 0, 102));
        deletePersonalDetails.setForeground(Color.WHITE);
        deletePersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deletePersonalDetails.setMargin(new Insets(0, 0, 0, 40));
        deletePersonalDetails.addActionListener(this);
        p2.add(deletePersonalDetails);
        
        destinations = new JButton("Book your trip");
        destinations.setBounds(0, 200, 300, 50);
        destinations.setBackground(new Color(0, 0, 102));
        destinations.setForeground(Color.WHITE);
        destinations.setFont(new Font("Tahoma", Font.PLAIN, 20));
        destinations.addActionListener(this);
        destinations.setMargin(new Insets(0, 0, 0, 115));
        p2.add(destinations);
        
        viewbooking = new JButton("View booking");
        viewbooking.setBounds(0, 250, 300, 50);
        viewbooking.setBackground(new Color(0, 0, 102));
        viewbooking.setForeground(Color.WHITE);
        viewbooking.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewbooking.setMargin(new Insets(0, 0, 0, 120));
        viewbooking.addActionListener(this);
        p2.add(viewbooking);
        
        cancelbooking = new JButton("Cancel Booking");
        cancelbooking.setBounds(0, 300, 300, 50);
        cancelbooking.setBackground(new Color(0, 0, 102));
        cancelbooking.setForeground(Color.WHITE);
        cancelbooking.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cancelbooking.setMargin(new Insets(0, 0, 0, 110));
        cancelbooking.addActionListener(this);
        p2.add(cancelbooking);

/*        JButton checkpackages = new JButton("Check Package");
        checkpackages.setBounds(0, 200, 300, 50);
        checkpackages.setBackground(new Color(0, 0, 102));
        checkpackages.setForeground(Color.WHITE);
        checkpackages.setFont(new Font("Tahoma", Font.PLAIN, 20));
        checkpackages.setMargin(new Insets(0, 0, 0, 110));
        p2.add(checkpackages);

        JButton bookpackage = new JButton("Book Package");
        bookpackage.setBounds(0, 250, 300, 50);
        bookpackage.setBackground(new Color(0, 0, 102));
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bookpackage.setMargin(new Insets(0, 0, 0, 120));
        p2.add(bookpackage);
*/
        

        

/*        JButton bookhotel = new JButton("Book hotel");
        bookhotel.setBounds(0, 400, 300, 50);
        bookhotel.setBackground(new Color(0, 0, 102));
        bookhotel.setForeground(Color.WHITE);
        bookhotel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bookhotel.setMargin(new Insets(0, 0, 0, 150));
        p2.add(bookhotel);

        JButton viewbookedhotel = new JButton("View Booked hotel");
        viewbookedhotel.setBounds(0, 450, 300, 50);
        viewbookedhotel.setBackground(new Color(0, 0, 102));
        viewbookedhotel.setForeground(Color.WHITE);
        viewbookedhotel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewbookedhotel.setMargin(new Insets(0, 0, 0, 80));
        p2.add(viewbookedhotel);
*/
        

        payments = new JButton("Payments");
        payments.setBounds(0, 350, 300, 50);
        payments.setBackground(new Color(0, 0, 102));
        payments.setForeground(Color.WHITE);
        payments.setFont(new Font("Tahoma", Font.PLAIN, 20));
        payments.setMargin(new Insets(0, 0, 0, 155));
        payments.addActionListener(this);
        p2.add(payments);
/*        
        invoice = new JButton("Generate Invoice");
        invoice.setBounds(0, 400, 300, 50);
        invoice.setBackground(new Color(0, 0, 102));
        invoice.setForeground(Color.WHITE);
        invoice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        invoice.setMargin(new Insets(0, 0, 0, 100));
        invoice.addActionListener(this);
 //       invoice.addActionListener(e -> new Invoice(username));
        p2.add(invoice);
*/

        calculator = new JButton("Calculator");
        calculator.setBounds(0, 400, 300, 50);
        calculator.setBackground(new Color(0, 0, 102));
        calculator.setForeground(Color.WHITE);
        calculator.setFont(new Font("Tahoma", Font.PLAIN, 20));
        calculator.setMargin(new Insets(0, 0, 0, 155));
        calculator.addActionListener(this);
        p2.add(calculator);

        /*        JButton about = new JButton("About");
        about.setBounds(0,650,300,50);
        about.setBackground(new Color(0,0,102));
        about.setForeground(Color.WHITE);
        about.setFont(new Font("Tahoma", Font.PLAIN, 20));
        about.setMargin(new Insets(0,0,0,175));
        p2.add(about);
        */
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(1650, 1000, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(0, 0, 1650, 1000);
        add(image);

        JLabel text = new JLabel("Travel And Tourism");
        text.setBounds(400, 70, 1000, 70);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway", Font.PLAIN, 40));
        image.add(text);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addPersonalDetails) {
            new AddCustomer(username);
        } else if(ae.getSource() == viewPersonalDetails){
            new ViewCustomer(username);           
        } else if(ae.getSource() == updatePersonalDetails){
            new UpdateCustomer(username);
        } 
        else if(ae.getSource() == destinations){
            new DestinationPage(username);
        }
        else if(ae.getSource() == viewbooking){
            new ViewBookings(username);       
        }
        else if(ae.getSource() == cancelbooking){
            new CancelBooking(username);  
        }
        else if(ae.getSource() == deletePersonalDetails){
            new DeleteDetails(username);
        }
        else if(ae.getSource() == payments){
            new payment(username);
        }
 //       else if(ae.getSource() == invoice){
 //           new Invoice(username);
 //       }
        else if(ae.getSource() == calculator){
            try{
                Runtime.getRuntime().exec("calc.exe");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Dashboard("");

    }

}
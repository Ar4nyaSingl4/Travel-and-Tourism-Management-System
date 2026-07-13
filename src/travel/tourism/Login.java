package travel.tourism;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
    JButton login, signup, password;
    JTextField textField, passwordField;
    Login() {
        setSize(900,400);
        setLocation(350,200);
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
         
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(133, 193, 233));
        p1.setBounds(0,0,400,400);
        p1.setLayout(null);
        add(p1);
        //change image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(100,120,200,200);
        p1.add(image);
        
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400,30,450,300);
        add(p2);
        
        JLabel l1 = new JLabel("Username");
	l1.setBounds(60, 20, 100, 25);
        l1.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
	p2.add(l1);
        
        textField = new JTextField();
	textField.setBounds(60, 60, 300, 30);
        textField.setBorder(BorderFactory.createEmptyBorder());
	p2.add(textField);
        
        JLabel l2 = new JLabel("Password");
	l2.setBounds(60, 110, 100, 25);
        l2.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
	p2.add(l2);
        
        passwordField = new JTextField();
	passwordField.setBounds(60, 150, 300, 30);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
	p2.add(passwordField);
        
        login = new JButton("Login");
	login.addActionListener(this);       
	login.setForeground(Color.white);
	login.setBackground(new Color(133, 193, 233));
	login.setBounds(60, 200, 130, 30);
        login.setBorder(new LineBorder(new Color(133, 193, 233)));
	p2.add(login);
        
        signup= new JButton("Signup");
	signup.addActionListener(this);       
	signup.setForeground(Color.white);
	signup.setBackground(new Color(133, 193, 233));
	signup.setBounds(230, 200, 130, 30);
        signup.setBorder(new LineBorder(new Color(133, 193, 233)));
	p2.add(signup);
        
        password = new JButton("Forget Password");
	password.addActionListener(this);       
	password.setForeground(Color.white);
	password.setBackground(new Color(133, 193, 233));
	password.setBounds(130, 250, 130, 30);
        password.setBorder(new LineBorder(new Color(133, 193, 233)));
	p2.add(password);
        
        JLabel text = new JLabel("Trouble in login...");
	text.setBounds(300, 250, 150, 20);
        text.setForeground(Color.RED);
	p2.add(text);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            try{
                String username = textField.getText();
                String pass = passwordField.getText();
                
                String query = "Select * from account where username = '"+username+"' AND password = '"+pass+"'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Loading(username);
                    
                } else{
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == signup) {
            setVisible(false);
            new Signup();
        }
        else {
            setVisible(false);
            new ForgetPassword();
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}

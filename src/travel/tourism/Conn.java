package travel.tourism;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///traveltourism","root","aranya23");
            s = c.createStatement();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
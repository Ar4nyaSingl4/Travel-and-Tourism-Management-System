/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travel.tourism;

import javax.swing.*;

public class TestImage {
    public static void main(String[] args) {
        JFrame f = new JFrame("Test");
        f.setSize(400, 400);

        JLabel label = new JLabel(new ImageIcon(
            TestImage.class.getResource("/icons/goa.jpeg")
        ));

        f.add(label);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
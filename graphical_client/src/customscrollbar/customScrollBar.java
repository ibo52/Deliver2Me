package customscrollbar;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ibrahim
 */

public class customScrollBar extends JScrollBar {

    public customScrollBar() {
        setUI(new customScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }
}
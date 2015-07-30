package com.skettios.summerproject.patcher;

import javax.swing.JFrame;

public class Patcher extends JFrame
{
    private static final long serialVersionUID = 7772149881616416580L;

    public Patcher()
    {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void start()
    {
        setVisible(true);
    }
}

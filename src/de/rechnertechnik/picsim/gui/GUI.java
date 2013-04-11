package de.rechnertechnik.picsim.gui;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;


public class GUI extends JFrame{
	private JTable table;
	private JTable table_1;
	private JMenuBar menuBar;
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setBounds(300, 300, 400,400);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	
	public GUI() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList list = new JList();
		getContentPane().add(list);
		
		
		JCheckBox chckbxTest = new JCheckBox("Test");
		getContentPane().add(chckbxTest);
		
		table_1 = new JTable();
		getContentPane().add(table_1);
		
		table = new JTable();
		getContentPane().add(table);
		
		
		// Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("Datei");
        JMenuItem helpMenu = new JMenuItem("Hilfe");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        // Create and add simple menu item to one of the drop down menu
        
        JMenuItem openAction = new JMenuItem("Öffnen");
        JMenuItem exitAction = new JMenuItem("Beenden");
        
       
     
  
        fileMenu.add(openAction);        
        fileMenu.add(exitAction);
        
        openAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Datei öffnen geklickt");
            }
        });
        
        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Schließen geklickt");
               
            }
        });
        
        helpMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Hilfe geklickt");
            }
        });
	}

	
	
}

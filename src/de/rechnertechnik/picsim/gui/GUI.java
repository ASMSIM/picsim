package de.rechnertechnik.picsim.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;


public class GUI extends JFrame{
	private JTable Text_inp;
	private JTable RegATab;
	private JTable RegBTab;
	private JTable RegCTab;
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setBounds(100, 100, 1000,700);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	
	public GUI() {
		
		
		//Erstellen einer Menüleiste
		JMenuBar menuBar = new JMenuBar();
		
		//Hinzufügen von Menüs
		JMenu menuFile = 
			new JMenu("Datei");
		JMenu menuHelp = 
			new JMenu("Hilfe");
		
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
        
		
		//Hinzufügen von Menüeinträgen in das Dateimenü

		JMenuItem menuItemFileOpen = 
			new JMenuItem("Öffnen");
		JMenuItem menuItemFileExit = 
			new JMenuItem("Beenden");
		

		menuFile.add(menuItemFileOpen);
		menuFile.addSeparator();
		menuFile.add(menuItemFileExit);
		
		
		//Hinzufügen von Menüeinträgen in das Hilfemenü
		JMenuItem menuItemHelpHelp = 
			new JMenuItem("Hilfe");
		
		menuHelp.add(menuItemHelpHelp);
		
		//Hinzufügen der Menüleiste zum Frame
		setJMenuBar(menuBar);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 119, 115, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 43, 43, 43, 43, 42, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel PlatzhalterSenk = new JPanel();
		GridBagConstraints gbc_PlatzhalterSenk = new GridBagConstraints();
		gbc_PlatzhalterSenk.gridheight = 8;
		gbc_PlatzhalterSenk.insets = new Insets(0, 0, 0, 5);
		gbc_PlatzhalterSenk.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterSenk.gridx = 1;
		gbc_PlatzhalterSenk.gridy = 0;
		getContentPane().add(PlatzhalterSenk, gbc_PlatzhalterSenk);
		
		JPanel PlatzhalterWaag = new JPanel();
		GridBagConstraints gbc_PlatzhalterWaag = new GridBagConstraints();
		gbc_PlatzhalterWaag.gridwidth = 9;
		gbc_PlatzhalterWaag.insets = new Insets(0, 0, 5, 0);
		gbc_PlatzhalterWaag.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterWaag.gridx = 2;
		gbc_PlatzhalterWaag.gridy = 0;
		getContentPane().add(PlatzhalterWaag, gbc_PlatzhalterWaag);
		
		JLabel RegVarLabel = new JLabel("Register");
		GridBagConstraints gbc_RegVarLabel = new GridBagConstraints();
		gbc_RegVarLabel.fill = GridBagConstraints.BOTH;
		gbc_RegVarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RegVarLabel.gridx = 2;
		gbc_RegVarLabel.gridy = 1;
		getContentPane().add(RegVarLabel, gbc_RegVarLabel);
		
		JScrollPane RegAPanel = new JScrollPane();
		GridBagConstraints gbc_RegAPanel = new GridBagConstraints();
		gbc_RegAPanel.fill = GridBagConstraints.BOTH;
		gbc_RegAPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RegAPanel.gridx = 2;
		gbc_RegAPanel.gridy = 2;
		getContentPane().add(RegAPanel, gbc_RegAPanel);
		
		RegATab = new JTable();
		RegATab.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RA", "7", "6", "5", "4", "3", "2", "1", "0"
			}
		));
		RegATab.getColumnModel().getColumn(0).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(0).setMinWidth(20);
		RegATab.getColumnModel().getColumn(1).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(1).setMinWidth(20);
		RegATab.getColumnModel().getColumn(2).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(2).setMinWidth(20);
		RegATab.getColumnModel().getColumn(3).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(3).setMinWidth(20);
		RegATab.getColumnModel().getColumn(4).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(4).setMinWidth(20);
		RegATab.getColumnModel().getColumn(5).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(5).setMinWidth(20);
		RegATab.getColumnModel().getColumn(6).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(6).setMinWidth(20);
		RegATab.getColumnModel().getColumn(7).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(7).setMinWidth(20);
		RegATab.getColumnModel().getColumn(8).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(8).setMinWidth(20);
		RegAPanel.setViewportView(RegATab);
		
		JScrollPane RegBPanel = new JScrollPane();
		GridBagConstraints gbc_RegBPanel = new GridBagConstraints();
		gbc_RegBPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RegBPanel.fill = GridBagConstraints.BOTH;
		gbc_RegBPanel.gridx = 2;
		gbc_RegBPanel.gridy = 3;
		getContentPane().add(RegBPanel, gbc_RegBPanel);
		
		RegBTab = new JTable();
		RegBTab.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RB", "7", "6", "5", "4", "3", "2", "1", "0"
			}
		));
		RegBTab.getColumnModel().getColumn(0).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(0).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(1).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(1).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(2).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(2).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(3).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(3).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(4).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(4).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(5).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(5).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(6).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(6).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(7).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(7).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(8).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(8).setMinWidth(20);
		RegBPanel.setViewportView(RegBTab);
		
		JScrollPane RegCPanel = new JScrollPane();
		GridBagConstraints gbc_RegCPanel = new GridBagConstraints();
		gbc_RegCPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RegCPanel.fill = GridBagConstraints.BOTH;
		gbc_RegCPanel.gridx = 2;
		gbc_RegCPanel.gridy = 4;
		getContentPane().add(RegCPanel, gbc_RegCPanel);
		
		RegCTab = new JTable();
		RegCTab.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RC", "7", "6", "5", "4", "3", "2", "1", "0"
			}
		));
		RegCTab.getColumnModel().getColumn(0).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(0).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(1).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(1).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(2).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(2).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(3).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(3).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(4).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(4).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(5).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(5).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(6).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(6).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(7).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(7).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(8).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(8).setMinWidth(20);
		RegCPanel.setViewportView(RegCTab);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 5;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{25, 0, 0, 0, 30, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnGo = new JButton("Go");
		GridBagConstraints gbc_btnGo = new GridBagConstraints();
		gbc_btnGo.anchor = GridBagConstraints.NORTH;
		gbc_btnGo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGo.gridx = 0;
		gbc_btnGo.gridy = 1;
		panel.add(btnGo, gbc_btnGo);
		
		JButton btnStop = new JButton("Stop");
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 0;
		gbc_btnStop.gridy = 2;
		panel.add(btnStop, gbc_btnStop);
		
		JButton btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.anchor = GridBagConstraints.NORTH;
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 3;
		panel.add(btnReset, gbc_btnReset);
		
		JPanel plzHalter = new JPanel();
		GridBagConstraints gbc_plzHalter = new GridBagConstraints();
		gbc_plzHalter.insets = new Insets(0, 0, 5, 5);
		gbc_plzHalter.fill = GridBagConstraints.BOTH;
		gbc_plzHalter.gridx = 0;
		gbc_plzHalter.gridy = 4;
		panel.add(plzHalter, gbc_plzHalter);
		
		JButton btnStepIn = new JButton("Step In");
		GridBagConstraints gbc_btnStepIn = new GridBagConstraints();
		gbc_btnStepIn.insets = new Insets(0, 0, 5, 5);
		gbc_btnStepIn.gridx = 0;
		gbc_btnStepIn.gridy = 5;
		panel.add(btnStepIn, gbc_btnStepIn);
		
		JButton btnStepOut = new JButton("Step Out");
		GridBagConstraints gbc_btnStepOut = new GridBagConstraints();
		gbc_btnStepOut.insets = new Insets(0, 0, 0, 5);
		gbc_btnStepOut.gridx = 0;
		gbc_btnStepOut.gridy = 6;
		panel.add(btnStepOut, gbc_btnStepOut);
		
		JScrollPane TextPanel = new JScrollPane();
		GridBagConstraints gbc_TextPanel = new GridBagConstraints();
		gbc_TextPanel.gridheight = 3;
		gbc_TextPanel.gridwidth = 7;
		gbc_TextPanel.fill = GridBagConstraints.BOTH;
		gbc_TextPanel.gridx = 4;
		gbc_TextPanel.gridy = 5;
		getContentPane().add(TextPanel, gbc_TextPanel);
		
		Text_inp = new JTable();
		Text_inp.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"NR", ""
			}
		));
		Text_inp.getColumnModel().getColumn(0).setPreferredWidth(28);
		Text_inp.getColumnModel().getColumn(1).setPreferredWidth(521);
		TextPanel.setViewportView(Text_inp);
		
		JPanel SpzRegPanel = new JPanel();
		GridBagConstraints gbc_SpzRegPanel = new GridBagConstraints();
		gbc_SpzRegPanel.gridheight = 2;
		gbc_SpzRegPanel.insets = new Insets(0, 0, 5, 5);
		gbc_SpzRegPanel.fill = GridBagConstraints.BOTH;
		gbc_SpzRegPanel.gridx = 2;
		gbc_SpzRegPanel.gridy = 6;
		getContentPane().add(SpzRegPanel, gbc_SpzRegPanel);
		GridBagLayout gbl_SpzRegPanel = new GridBagLayout();
		gbl_SpzRegPanel.columnWidths = new int[]{0, 0};
		gbl_SpzRegPanel.rowHeights = new int[]{0, 0};
		gbl_SpzRegPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_SpzRegPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		SpzRegPanel.setLayout(gbl_SpzRegPanel);
		
		JLabel lblSpezialfunktionsregister = new JLabel("Spezialfunktionsregister");
		GridBagConstraints gbc_lblSpezialfunktionsregister = new GridBagConstraints();
		gbc_lblSpezialfunktionsregister.gridx = 0;
		gbc_lblSpezialfunktionsregister.gridy = 0;
		SpzRegPanel.add(lblSpezialfunktionsregister, gbc_lblSpezialfunktionsregister);


		
        /*
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
        */
	}

	
	
}

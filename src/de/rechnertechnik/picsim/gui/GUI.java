package de.rechnertechnik.picsim.gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JViewport;


import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;



public class GUI extends JFrame implements IGUI{
	
	private JTable Text_inp;
	private JTable RegATab;
	private JTable RegCTab;
	private JTable RegBTab;
	private JTable SpeicherTab;
	private IProzessor prozessor;
	
	private JFileChooser chooser = new JFileChooser();
	
	
//	public static void main(String[] args) {
//		GUI gui = new GUI();
//		gui.setBounds(100, 100, 1000,700);
//		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		gui.setVisible(true);
//	}
	
	public void connectProzessor(IProzessor prozessor){
		this.prozessor = prozessor;
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
		gridBagLayout.columnWidths = new int[]{0, 0, 196, 0, 0, -55, 0, 0, 0, 0, 77, 23, 20, 142, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 98, 64, 0, 15, 131, 43, 42, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel PlatzhalterSenk = new JPanel();
		GridBagConstraints gbc_PlatzhalterSenk = new GridBagConstraints();
		gbc_PlatzhalterSenk.gridheight = 10;
		gbc_PlatzhalterSenk.insets = new Insets(0, 0, 0, 5);
		gbc_PlatzhalterSenk.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterSenk.gridx = 1;
		gbc_PlatzhalterSenk.gridy = 0;
		getContentPane().add(PlatzhalterSenk, gbc_PlatzhalterSenk);
		
		JPanel PlatzhalterWaag = new JPanel();
		GridBagConstraints gbc_PlatzhalterWaag = new GridBagConstraints();
		gbc_PlatzhalterWaag.gridwidth = 12;
		gbc_PlatzhalterWaag.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterWaag.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterWaag.gridx = 2;
		gbc_PlatzhalterWaag.gridy = 0;
		getContentPane().add(PlatzhalterWaag, gbc_PlatzhalterWaag);
		
		JPanel RegisterPanel = new JPanel();
		RegisterPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_RegisterPanel = new GridBagConstraints();
		gbc_RegisterPanel.gridwidth = 2;
		gbc_RegisterPanel.gridheight = 2;
		gbc_RegisterPanel.anchor = GridBagConstraints.NORTH;
		gbc_RegisterPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_RegisterPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RegisterPanel.gridx = 2;
		gbc_RegisterPanel.gridy = 1;
		getContentPane().add(RegisterPanel, gbc_RegisterPanel);
		GridBagLayout gbl_RegisterPanel = new GridBagLayout();
		gbl_RegisterPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_RegisterPanel.rowHeights = new int[]{0, 43, 43, 38, 0};
		gbl_RegisterPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_RegisterPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		RegisterPanel.setLayout(gbl_RegisterPanel);
		
		JLabel RegVarLabel = new JLabel("Register");
		GridBagConstraints gbc_RegVarLabel = new GridBagConstraints();
		gbc_RegVarLabel.anchor = GridBagConstraints.WEST;
		gbc_RegVarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RegVarLabel.gridx = 1;
		gbc_RegVarLabel.gridy = 0;
		RegisterPanel.add(RegVarLabel, gbc_RegVarLabel);
		
		JScrollPane RegAPanel = new JScrollPane();
		GridBagConstraints gbc_RegAPanel = new GridBagConstraints();
		gbc_RegAPanel.fill = GridBagConstraints.BOTH;
		gbc_RegAPanel.gridwidth = 6;
		gbc_RegAPanel.insets = new Insets(0, 0, 5, 0);
		gbc_RegAPanel.gridx = 1;
		gbc_RegAPanel.gridy = 1;
		RegisterPanel.add(RegAPanel, gbc_RegAPanel);
		
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
		gbc_RegBPanel.gridwidth = 6;
		gbc_RegBPanel.insets = new Insets(0, 0, 5, 0);
		gbc_RegBPanel.fill = GridBagConstraints.BOTH;
		gbc_RegBPanel.gridx = 1;
		gbc_RegBPanel.gridy = 2;
		RegisterPanel.add(RegBPanel, gbc_RegBPanel);
		
		RegBTab = new JTable();
		RegBTab.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RC", "7", "6", "5", "4", "3", "2", "1", "0"
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
		gbc_RegCPanel.gridwidth = 6;
		gbc_RegCPanel.fill = GridBagConstraints.BOTH;
		gbc_RegCPanel.gridx = 1;
		gbc_RegCPanel.gridy = 3;
		RegisterPanel.add(RegCPanel, gbc_RegCPanel);
		
		RegCTab = new JTable();
		RegCTab.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RB", "7", "6", "5", "4", "3", "2", "1", "0"
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
		
		JPanel PlatzhalterRegSp = new JPanel();
		GridBagConstraints gbc_PlatzhalterRegSp = new GridBagConstraints();
		gbc_PlatzhalterRegSp.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterRegSp.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterRegSp.gridx = 4;
		gbc_PlatzhalterRegSp.gridy = 1;
		getContentPane().add(PlatzhalterRegSp, gbc_PlatzhalterRegSp);
		
		JPanel SpeicherPanel = new JPanel();
		SpeicherPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_SpeicherPanel = new GridBagConstraints();
		gbc_SpeicherPanel.gridheight = 2;
		gbc_SpeicherPanel.gridwidth = 7;
		gbc_SpeicherPanel.insets = new Insets(0, 0, 5, 5);
		gbc_SpeicherPanel.fill = GridBagConstraints.BOTH;
		gbc_SpeicherPanel.gridx = 5;
		gbc_SpeicherPanel.gridy = 1;
		getContentPane().add(SpeicherPanel, gbc_SpeicherPanel);
		SpeicherPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSpeicher = new JLabel("Speicher");
		SpeicherPanel.add(lblSpeicher, BorderLayout.NORTH);
		
		JScrollPane SpeicherScrPanel = new JScrollPane();
		SpeicherPanel.add(SpeicherScrPanel, BorderLayout.CENTER);
		
		SpeicherTab = new JTable();
		SpeicherTab.setColumnSelectionAllowed(true);
		SpeicherTab.setCellSelectionEnabled(true);
		SpeicherTab.setModel(new DefaultTableModel(
			new Object[][] {
				{"00", null, null, null, null, null, null, null, null},
				{"08", null, null, null, null, null, null, null, null},
				{"10", null, null, null, null, null, null, null, null},
				{"18", null, null, null, null, null, null, null, null},
				{"20", null, null, null, null, null, null, null, null},
				{"28", null, null, null, null, null, null, null, null},
				{"30", null, null, null, null, null, null, null, null},
				{"38", null, null, null, null, null, null, null, null},
				{"40", null, null, null, null, null, null, null, null},
				{"48", null, null, null, null, null, null, null, null},
				{"50", null, null, null, null, null, null, null, null},
				{"58", null, null, null, null, null, null, null, null},
				{"60", null, null, null, null, null, null, null, null},
				{"68", null, null, null, null, null, null, null, null},
				{"70", null, null, null, null, null, null, null, null},
				{"78", null, null, null, null, null, null, null, null},
				{"80", null, null, null, null, null, null, null, null},
				{"88", null, null, null, null, null, null, null, null},
				{"90", null, null, null, null, null, null, null, null},
				{"98", null, null, null, null, null, null, null, null},
				{"A0", null, null, null, null, null, null, null, null},
				{"A8", null, null, null, null, null, null, null, null},
				{"B0", null, null, null, null, null, null, null, null},
				{"B8", null, null, null, null, null, null, null, null},
				{"C0", null, null, null, null, null, null, null, null},
				{"C8", null, null, null, null, null, null, null, null},
				{"D0", null, null, null, null, null, null, null, null},
				{"D8", null, null, null, null, null, null, null, null},
				{"E0", null, null, null, null, null, null, null, null},
				{"E8", null, null, null, null, null, null, null, null},
				{"F0", null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"", "00", "01", "02", "03", "04", "05", "06", "07"
			}
		));
		SpeicherTab.getColumnModel().getColumn(0).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(0).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(1).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(1).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(2).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(2).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(3).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(3).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(4).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(4).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(5).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(5).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(6).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(6).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(7).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(7).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(8).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(8).setMinWidth(30);
		SpeicherScrPanel.setViewportView(SpeicherTab);
		
		JPanel PlatzhalterSpSch = new JPanel();
		GridBagConstraints gbc_PlatzhalterSpSch = new GridBagConstraints();
		gbc_PlatzhalterSpSch.anchor = GridBagConstraints.EAST;
		gbc_PlatzhalterSpSch.gridheight = 2;
		gbc_PlatzhalterSpSch.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterSpSch.fill = GridBagConstraints.VERTICAL;
		gbc_PlatzhalterSpSch.gridx = 12;
		gbc_PlatzhalterSpSch.gridy = 1;
		getContentPane().add(PlatzhalterSpSch, gbc_PlatzhalterSpSch);
		
		JPanel SchalterPanel = new JPanel();
		SchalterPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_SchalterPanel = new GridBagConstraints();
		gbc_SchalterPanel.anchor = GridBagConstraints.WEST;
		gbc_SchalterPanel.gridwidth = 2;
		gbc_SchalterPanel.insets = new Insets(0, 0, 5, 0);
		gbc_SchalterPanel.fill = GridBagConstraints.VERTICAL;
		gbc_SchalterPanel.gridx = 13;
		gbc_SchalterPanel.gridy = 1;
		getContentPane().add(SchalterPanel, gbc_SchalterPanel);
		GridBagLayout gbl_SchalterPanel = new GridBagLayout();
		gbl_SchalterPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_SchalterPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_SchalterPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_SchalterPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		SchalterPanel.setLayout(gbl_SchalterPanel);
		
		JLabel lblSchalter = new JLabel("Schalter");
		GridBagConstraints gbc_lblSchalter = new GridBagConstraints();
		gbc_lblSchalter.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchalter.gridx = 0;
		gbc_lblSchalter.gridy = 0;
		SchalterPanel.add(lblSchalter, gbc_lblSchalter);
		
		JCheckBox chckbxS = new JCheckBox("S5");
		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxS.gridx = 0;
		gbc_chckbxS.gridy = 1;
		SchalterPanel.add(chckbxS, gbc_chckbxS);
		
		JCheckBox chckbxS_3 = new JCheckBox("S4");
		GridBagConstraints gbc_chckbxS_3 = new GridBagConstraints();
		gbc_chckbxS_3.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_3.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxS_3.gridx = 1;
		gbc_chckbxS_3.gridy = 1;
		SchalterPanel.add(chckbxS_3, gbc_chckbxS_3);
		
		JCheckBox chckbxS_2 = new JCheckBox("S3");
		GridBagConstraints gbc_chckbxS_2 = new GridBagConstraints();
		gbc_chckbxS_2.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_2.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxS_2.gridx = 2;
		gbc_chckbxS_2.gridy = 1;
		SchalterPanel.add(chckbxS_2, gbc_chckbxS_2);
		
		JCheckBox chckbxS_1 = new JCheckBox("S2");
		GridBagConstraints gbc_chckbxS_1 = new GridBagConstraints();
		gbc_chckbxS_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxS_1.gridx = 3;
		gbc_chckbxS_1.gridy = 1;
		SchalterPanel.add(chckbxS_1, gbc_chckbxS_1);
		
		JCheckBox checkBox_7 = new JCheckBox("S1");
		GridBagConstraints gbc_checkBox_7 = new GridBagConstraints();
		gbc_checkBox_7.anchor = GridBagConstraints.WEST;
		gbc_checkBox_7.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_7.gridx = 4;
		gbc_checkBox_7.gridy = 1;
		SchalterPanel.add(checkBox_7, gbc_checkBox_7);
		
		JCheckBox chckbxS_8 = new JCheckBox("S10");
		GridBagConstraints gbc_chckbxS_8 = new GridBagConstraints();
		gbc_chckbxS_8.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_8.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS_8.gridx = 0;
		gbc_chckbxS_8.gridy = 2;
		SchalterPanel.add(chckbxS_8, gbc_chckbxS_8);
		
		JCheckBox chckbxS_7 = new JCheckBox("S9");
		GridBagConstraints gbc_chckbxS_7 = new GridBagConstraints();
		gbc_chckbxS_7.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_7.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS_7.gridx = 1;
		gbc_chckbxS_7.gridy = 2;
		SchalterPanel.add(chckbxS_7, gbc_chckbxS_7);
		
		JCheckBox chckbxS_6 = new JCheckBox("S8");
		GridBagConstraints gbc_chckbxS_6 = new GridBagConstraints();
		gbc_chckbxS_6.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_6.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS_6.gridx = 2;
		gbc_chckbxS_6.gridy = 2;
		SchalterPanel.add(chckbxS_6, gbc_chckbxS_6);
		
		JCheckBox chckbxS_5 = new JCheckBox("S7");
		GridBagConstraints gbc_chckbxS_5 = new GridBagConstraints();
		gbc_chckbxS_5.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_5.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS_5.gridx = 3;
		gbc_chckbxS_5.gridy = 2;
		SchalterPanel.add(chckbxS_5, gbc_chckbxS_5);
		
		JCheckBox chckbxS_4 = new JCheckBox("S6");
		GridBagConstraints gbc_chckbxS_4 = new GridBagConstraints();
		gbc_chckbxS_4.anchor = GridBagConstraints.WEST;
		gbc_chckbxS_4.gridx = 4;
		gbc_chckbxS_4.gridy = 2;
		SchalterPanel.add(chckbxS_4, gbc_chckbxS_4);
		
		
		
		
		JPanel PlatzhalterMitte = new JPanel();
		GridBagConstraints gbc_PlatzhalterMitte = new GridBagConstraints();
		gbc_PlatzhalterMitte.anchor = GridBagConstraints.SOUTH;
		gbc_PlatzhalterMitte.gridwidth = 11;
		gbc_PlatzhalterMitte.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterMitte.fill = GridBagConstraints.HORIZONTAL;
		gbc_PlatzhalterMitte.gridx = 2;
		gbc_PlatzhalterMitte.gridy = 4;
		getContentPane().add(PlatzhalterMitte, gbc_PlatzhalterMitte);
		
		JPanel SpzRegPanel = new JPanel();
		SpzRegPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_SpzRegPanel = new GridBagConstraints();
		gbc_SpzRegPanel.gridheight = 3;
		gbc_SpzRegPanel.insets = new Insets(0, 0, 5, 5);
		gbc_SpzRegPanel.fill = GridBagConstraints.BOTH;
		gbc_SpzRegPanel.gridx = 2;
		gbc_SpzRegPanel.gridy = 5;
		getContentPane().add(SpzRegPanel, gbc_SpzRegPanel);
		GridBagLayout gbl_SpzRegPanel = new GridBagLayout();
		gbl_SpzRegPanel.columnWidths = new int[]{86, 0, 0, 0};
		gbl_SpzRegPanel.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_SpzRegPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_SpzRegPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		SpzRegPanel.setLayout(gbl_SpzRegPanel);
		
		JLabel lblSpezialfunktionsregister = new JLabel("Spezialfunktionsregister");
		GridBagConstraints gbc_lblSpezialfunktionsregister = new GridBagConstraints();
		gbc_lblSpezialfunktionsregister.gridwidth = 3;
		gbc_lblSpezialfunktionsregister.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpezialfunktionsregister.gridx = 0;
		gbc_lblSpezialfunktionsregister.gridy = 0;
		SpzRegPanel.add(lblSpezialfunktionsregister, gbc_lblSpezialfunktionsregister);
		
		JPanel SPFRegPlatz = new JPanel();
		GridBagConstraints gbc_SPFRegPlatz = new GridBagConstraints();
		gbc_SPFRegPlatz.anchor = GridBagConstraints.NORTH;
		gbc_SPFRegPlatz.insets = new Insets(0, 0, 5, 5);
		gbc_SPFRegPlatz.fill = GridBagConstraints.HORIZONTAL;
		gbc_SPFRegPlatz.gridx = 0;
		gbc_SPFRegPlatz.gridy = 1;
		SpzRegPanel.add(SPFRegPlatz, gbc_SPFRegPlatz);
		
		JLabel lblWreg = new JLabel("W-Reg");
		GridBagConstraints gbc_lblWreg = new GridBagConstraints();
		gbc_lblWreg.insets = new Insets(0, 0, 5, 5);
		gbc_lblWreg.anchor = GridBagConstraints.WEST;
		gbc_lblWreg.gridx = 0;
		gbc_lblWreg.gridy = 2;
		SpzRegPanel.add(lblWreg, gbc_lblWreg);
		
		JLabel WReg_inp = new JLabel("00");
		GridBagConstraints gbc_WReg_inp = new GridBagConstraints();
		gbc_WReg_inp.anchor = GridBagConstraints.WEST;
		gbc_WReg_inp.insets = new Insets(0, 0, 5, 0);
		gbc_WReg_inp.gridx = 2;
		gbc_WReg_inp.gridy = 2;
		SpzRegPanel.add(WReg_inp, gbc_WReg_inp);
		
		JLabel lblFsr = new JLabel("FSR");
		GridBagConstraints gbc_lblFsr = new GridBagConstraints();
		gbc_lblFsr.anchor = GridBagConstraints.WEST;
		gbc_lblFsr.insets = new Insets(0, 0, 5, 5);
		gbc_lblFsr.gridx = 0;
		gbc_lblFsr.gridy = 3;
		SpzRegPanel.add(lblFsr, gbc_lblFsr);
		
		JLabel FSR_inp = new JLabel("00");
		GridBagConstraints gbc_FSR_inp = new GridBagConstraints();
		gbc_FSR_inp.anchor = GridBagConstraints.WEST;
		gbc_FSR_inp.insets = new Insets(0, 0, 5, 0);
		gbc_FSR_inp.gridx = 2;
		gbc_FSR_inp.gridy = 3;
		SpzRegPanel.add(FSR_inp, gbc_FSR_inp);
		
		JLabel lblPcl = new JLabel("PCL");
		GridBagConstraints gbc_lblPcl = new GridBagConstraints();
		gbc_lblPcl.anchor = GridBagConstraints.WEST;
		gbc_lblPcl.insets = new Insets(0, 0, 5, 5);
		gbc_lblPcl.gridx = 0;
		gbc_lblPcl.gridy = 4;
		SpzRegPanel.add(lblPcl, gbc_lblPcl);
		
		JLabel PCL_inp = new JLabel("00");
		GridBagConstraints gbc_PCL_inp = new GridBagConstraints();
		gbc_PCL_inp.anchor = GridBagConstraints.WEST;
		gbc_PCL_inp.insets = new Insets(0, 0, 5, 0);
		gbc_PCL_inp.gridx = 2;
		gbc_PCL_inp.gridy = 4;
		SpzRegPanel.add(PCL_inp, gbc_PCL_inp);
		
		JLabel lblPclath = new JLabel("PCLATH");
		GridBagConstraints gbc_lblPclath = new GridBagConstraints();
		gbc_lblPclath.anchor = GridBagConstraints.WEST;
		gbc_lblPclath.insets = new Insets(0, 0, 5, 5);
		gbc_lblPclath.gridx = 0;
		gbc_lblPclath.gridy = 5;
		SpzRegPanel.add(lblPclath, gbc_lblPclath);
		
		JLabel PCLath_inp = new JLabel("00");
		GridBagConstraints gbc_PCLath_inp = new GridBagConstraints();
		gbc_PCLath_inp.anchor = GridBagConstraints.WEST;
		gbc_PCLath_inp.insets = new Insets(0, 0, 5, 0);
		gbc_PCLath_inp.gridx = 2;
		gbc_PCLath_inp.gridy = 5;
		SpzRegPanel.add(PCLath_inp, gbc_PCLath_inp);
		
		JLabel lblPc = new JLabel("PC");
		GridBagConstraints gbc_lblPc = new GridBagConstraints();
		gbc_lblPc.anchor = GridBagConstraints.WEST;
		gbc_lblPc.insets = new Insets(0, 0, 5, 5);
		gbc_lblPc.gridx = 0;
		gbc_lblPc.gridy = 6;
		SpzRegPanel.add(lblPc, gbc_lblPc);
		
		JLabel PC_inp = new JLabel("0000");
		GridBagConstraints gbc_PC_inp = new GridBagConstraints();
		gbc_PC_inp.anchor = GridBagConstraints.WEST;
		gbc_PC_inp.insets = new Insets(0, 0, 5, 0);
		gbc_PC_inp.gridx = 2;
		gbc_PC_inp.gridy = 6;
		SpzRegPanel.add(PC_inp, gbc_PC_inp);
		
		JLabel lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 7;
		SpzRegPanel.add(lblStatus, gbc_lblStatus);
		
		JLabel Status_inp = new JLabel("00");
		GridBagConstraints gbc_Status_inp = new GridBagConstraints();
		gbc_Status_inp.anchor = GridBagConstraints.WEST;
		gbc_Status_inp.gridx = 2;
		gbc_Status_inp.gridy = 7;
		SpzRegPanel.add(Status_inp, gbc_Status_inp);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_ButtonPanel = new GridBagConstraints();
		gbc_ButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_ButtonPanel.gridwidth = 3;
		gbc_ButtonPanel.gridheight = 3;
		gbc_ButtonPanel.insets = new Insets(0, 0, 5, 5);
		gbc_ButtonPanel.gridx = 3;
		gbc_ButtonPanel.gridy = 5;
		getContentPane().add(ButtonPanel, gbc_ButtonPanel);
		GridBagLayout gbl_ButtonPanel = new GridBagLayout();
		gbl_ButtonPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_ButtonPanel.rowHeights = new int[]{25, 0, 0, 0, 30, 0, 0, 0, 0, 0};
		gbl_ButtonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ButtonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		ButtonPanel.setLayout(gbl_ButtonPanel);
		
		JButton btnGo = new JButton("Go");
		btnGo.setToolTipText("");
		GridBagConstraints gbc_btnGo = new GridBagConstraints();
		gbc_btnGo.anchor = GridBagConstraints.NORTH;
		gbc_btnGo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGo.gridx = 1;
		gbc_btnGo.gridy = 1;
		ButtonPanel.add(btnGo, gbc_btnGo);
		
		JButton btnStop = new JButton("Stop");
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 1;
		gbc_btnStop.gridy = 2;
		ButtonPanel.add(btnStop, gbc_btnStop);
		
		JButton btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.anchor = GridBagConstraints.NORTH;
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 3;
		ButtonPanel.add(btnReset, gbc_btnReset);
		
		JButton btnStepIn = new JButton("Step In");
		btnStepIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prozessor.nextStep();
			}
		});
		GridBagConstraints gbc_btnStepIn = new GridBagConstraints();
		gbc_btnStepIn.insets = new Insets(0, 0, 5, 5);
		gbc_btnStepIn.gridx = 1;
		gbc_btnStepIn.gridy = 5;
		ButtonPanel.add(btnStepIn, gbc_btnStepIn);
		
		JButton btnStepOut = new JButton("Step Out");
		GridBagConstraints gbc_btnStepOut = new GridBagConstraints();
		gbc_btnStepOut.insets = new Insets(0, 0, 5, 5);
		gbc_btnStepOut.gridx = 1;
		gbc_btnStepOut.gridy = 6;
		ButtonPanel.add(btnStepOut, gbc_btnStepOut);
		
		JScrollPane TextPanel = new JScrollPane();
		GridBagConstraints gbc_TextPanel = new GridBagConstraints();
		gbc_TextPanel.gridheight = 5;
		gbc_TextPanel.gridwidth = 8;
		gbc_TextPanel.fill = GridBagConstraints.BOTH;
		gbc_TextPanel.gridx = 7;
		gbc_TextPanel.gridy = 5;
		getContentPane().add(TextPanel, gbc_TextPanel);
		
		Text_inp = new JTable();
		
		TextPanel.setViewportView(Text_inp);
	
	    
		menuItemFileOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Datei öffnen geklickt");
              
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "LST-Dateien", "lst");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                   System.out.println("Datein gewählt: " +
                        chooser.getSelectedFile().getName());
                }          
            }    
        });
     
		menuItemFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               System.out.println("Schließen geklickt");
               System.exit(0);
            }
        });
        
        /*
        helpMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Hilfe geklickt");
            }
        });
        */
	}



	@Override
	public void showSourcecode(ArrayList<String> sourceLine, Integer fokus) {

			DefaultTableModel model = new DefaultTableModel();
			Object[][] zeilen = new Object[sourceLine.size()][sourceLine.size()];
			
		for(int i=0; i<sourceLine.size(); i++){
			zeilen[i][0] = i+1;
			zeilen[i][1] = sourceLine.get(i); 
		}
		
		
		Text_inp.setModel(new DefaultTableModel(
				zeilen,
				new String[] {
					"NR", ""
				}
			));
		
		Text_inp.getColumnModel().getColumn(0).setMaxWidth(25);
		Text_inp.getColumnModel().getColumn(0).setPreferredWidth(25);
		Text_inp.getColumnModel().getColumn(1).setPreferredWidth(521);
	

		
		Text_inp.setRowSelectionInterval(fokus, fokus);
		
	}
	

	@Override
	public void setFocus(Integer fokus) {
		Text_inp.setRowSelectionInterval(fokus, fokus);
	}
}

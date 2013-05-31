package de.rechnertechnik.picsim.gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JViewport;


import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.prozessor.IPorts;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class GUI extends JFrame implements IGUI{
	
	private JTable Text_inp;
	private JTable RegATab;
	private JTable RegBTab;
	private JTable SpeicherTab;
	private IProzessor prozessor;
	
	private JFileChooser chooser = new JFileChooser();
	private JLabel FSR_inp;
	private JLabel WReg_inp;
	private JLabel PC_inp;
	private boolean userCanEditRAM = true;
	
	
	DefaultTableModel ramModel =new DefaultTableModel(
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
				{"FF", null, null, null, null, null, null, null, null},
			},
			new String[] {
				"", "00", "01", "02", "03", "04", "05", "06", "07"
			}
		);
	
	
	
	DefaultTableModel regA = new DefaultTableModel(
			new Object[][] {
					{"Tris", null, null, null, null, null, null, null, null},
					{"Pin", null, null, null, null, null, null, null, null},
				},
				new String[] {
					"RA", "7", "6", "5", "4", "3", "2", "1", "0"
				}
			);
	private JLabel labelLaufzeitValue;
	private JLabel lblQuartz;
	private IPorts ports;
	private JCheckBox chckbxA0;
	private JCheckBox chckbxSA1;
	private JCheckBox chckbxA2;
	private JCheckBox checkBoxA3;
	private JCheckBox chckbxA4;
	private JCheckBox chckbxA5;
	private JCheckBox chckbxA6;
	private JCheckBox chckbxA7;
	private JLabel Status_inp;
	private JLabel lblIRPVal;
	private JLabel lblRp1val;
	private JLabel lblRp0val;
	private JLabel lblT0val;
	private JLabel lblpdval;
	private JLabel lblZval;
	private JLabel lblDC_Val;
	private JLabel lblC_VAL;
	
	
	
	
//	public static void main(String[] args) {
//		GUI gui = new GUI();
//		gui.setBounds(100, 100, 1000,700);
//		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		gui.setVisible(true);
//	}
	
	
	
	public void connectProzessor(IProzessor prozessor, IPorts ports){
		this.prozessor = prozessor;
		this.ports = ports;
	}
	
	public GUI() {
		super("PIC-Simulator Team Michael,Julian");
		
		
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
		gridBagLayout.columnWidths = new int[]{0, 0, 191, 0, 0, -55, 0, 0, 0, 0, 49, 23, 20, 142, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 98, 0, 64, 28, 15, 131, 43, 42, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel PlatzhalterSenk = new JPanel();
		GridBagConstraints gbc_PlatzhalterSenk = new GridBagConstraints();
		gbc_PlatzhalterSenk.gridheight = 11;
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
		gbc_RegisterPanel.fill = GridBagConstraints.BOTH;
		gbc_RegisterPanel.gridheight = 4;
		gbc_RegisterPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RegisterPanel.gridx = 2;
		gbc_RegisterPanel.gridy = 1;
		getContentPane().add(RegisterPanel, gbc_RegisterPanel);
		GridBagLayout gbl_RegisterPanel = new GridBagLayout();
		gbl_RegisterPanel.columnWidths = new int[]{193, 0};
		gbl_RegisterPanel.rowHeights = new int[]{0, 59, 59, 54, 0};
		gbl_RegisterPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_RegisterPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		RegisterPanel.setLayout(gbl_RegisterPanel);
		
		JLabel RegVarLabel = new JLabel("Register");
		GridBagConstraints gbc_RegVarLabel = new GridBagConstraints();
		gbc_RegVarLabel.anchor = GridBagConstraints.WEST;
		gbc_RegVarLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegVarLabel.gridx = 0;
		gbc_RegVarLabel.gridy = 0;
		RegisterPanel.add(RegVarLabel, gbc_RegVarLabel);
		
		JScrollPane RegAPanel = new JScrollPane();
		GridBagConstraints gbc_RegAPanel = new GridBagConstraints();
		gbc_RegAPanel.fill = GridBagConstraints.BOTH;
		gbc_RegAPanel.insets = new Insets(0, 0, 5, 0);
		gbc_RegAPanel.gridx = 0;
		gbc_RegAPanel.gridy = 1;
		RegisterPanel.add(RegAPanel, gbc_RegAPanel);
		
		RegATab = new JTable();
		RegATab.setEnabled(false);
		
		RegATab.setModel(regA);
		RegATab.getColumnModel().getColumn(0).setPreferredWidth(30);
		RegATab.getColumnModel().getColumn(0).setMinWidth(30);
		RegATab.getColumnModel().getColumn(0).setMaxWidth(30);
		RegATab.getColumnModel().getColumn(1).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(1).setMinWidth(20);
		RegATab.getColumnModel().getColumn(1).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(2).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(2).setMinWidth(20);
		RegATab.getColumnModel().getColumn(2).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(3).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(3).setMinWidth(20);
		RegATab.getColumnModel().getColumn(3).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(4).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(4).setMinWidth(20);
		RegATab.getColumnModel().getColumn(4).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(5).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(5).setMinWidth(20);
		RegATab.getColumnModel().getColumn(5).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(6).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(6).setMinWidth(20);
		RegATab.getColumnModel().getColumn(6).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(7).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(7).setMinWidth(20);
		RegATab.getColumnModel().getColumn(7).setMaxWidth(20);
		RegATab.getColumnModel().getColumn(8).setPreferredWidth(20);
		RegATab.getColumnModel().getColumn(8).setMinWidth(20);
		RegATab.getColumnModel().getColumn(8).setMaxWidth(20);
		RegAPanel.setViewportView(RegATab);
		
		JScrollPane RegBPanel = new JScrollPane();
		RegBPanel.setToolTipText("");
		GridBagConstraints gbc_RegBPanel = new GridBagConstraints();
		gbc_RegBPanel.insets = new Insets(0, 0, 5, 0);
		gbc_RegBPanel.fill = GridBagConstraints.BOTH;
		gbc_RegBPanel.gridx = 0;
		gbc_RegBPanel.gridy = 2;
		RegisterPanel.add(RegBPanel, gbc_RegBPanel);
		
		RegBTab = new JTable();
		RegBTab.setEnabled(false);
		RegBTab.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tris", null, null, null, null, null, null, null, null},
				{"Pin", null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RB", "7", "6", "5", "4", "3", "2", "1", "0"
			}
		));
		RegBTab.getColumnModel().getColumn(0).setPreferredWidth(30);
		RegBTab.getColumnModel().getColumn(0).setMinWidth(30);
		RegBTab.getColumnModel().getColumn(0).setMaxWidth(30);
		RegBTab.getColumnModel().getColumn(1).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(1).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(1).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(2).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(2).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(2).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(3).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(3).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(3).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(4).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(4).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(4).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(5).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(5).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(5).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(6).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(6).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(6).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(7).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(7).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(7).setMaxWidth(20);
		RegBTab.getColumnModel().getColumn(8).setPreferredWidth(20);
		RegBTab.getColumnModel().getColumn(8).setMinWidth(20);
		RegBTab.getColumnModel().getColumn(8).setMaxWidth(20);
		RegBPanel.setViewportView(RegBTab);
		
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
		gbc_SpeicherPanel.gridheight = 4;
		gbc_SpeicherPanel.gridwidth = 7;
		gbc_SpeicherPanel.insets = new Insets(0, 0, 5, 5);
		gbc_SpeicherPanel.fill = GridBagConstraints.BOTH;
		gbc_SpeicherPanel.gridx = 5;
		gbc_SpeicherPanel.gridy = 1;
		getContentPane().add(SpeicherPanel, gbc_SpeicherPanel);
		GridBagLayout gbl_SpeicherPanel = new GridBagLayout();
		gbl_SpeicherPanel.columnWidths = new int[]{287, 0};
		gbl_SpeicherPanel.rowHeights = new int[]{15, 173, 0};
		gbl_SpeicherPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_SpeicherPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		SpeicherPanel.setLayout(gbl_SpeicherPanel);
		
		JLabel lblSpeicher = new JLabel("Speicher");
		GridBagConstraints gbc_lblSpeicher = new GridBagConstraints();
		gbc_lblSpeicher.anchor = GridBagConstraints.NORTH;
		gbc_lblSpeicher.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSpeicher.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpeicher.gridx = 0;
		gbc_lblSpeicher.gridy = 0;
		SpeicherPanel.add(lblSpeicher, gbc_lblSpeicher);
		JScrollPane SpeicherScrPanel = new JScrollPane();
		GridBagConstraints gbc_SpeicherScrPanel = new GridBagConstraints();
		gbc_SpeicherScrPanel.fill = GridBagConstraints.BOTH;
		gbc_SpeicherScrPanel.gridx = 0;
		gbc_SpeicherScrPanel.gridy = 1;
		SpeicherPanel.add(SpeicherScrPanel, gbc_SpeicherScrPanel);
		
		SpeicherTab = new JTable();
		SpeicherTab.setModel(ramModel);
		
		SpeicherTab.getColumnModel().getColumn(0).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(0).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(0).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(1).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(1).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(1).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(2).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(2).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(2).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(3).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(3).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(3).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(4).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(4).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(4).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(5).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(5).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(5).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(6).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(6).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(6).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(7).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(7).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(7).setMaxWidth(30);
		SpeicherTab.getColumnModel().getColumn(8).setPreferredWidth(30);
		SpeicherTab.getColumnModel().getColumn(8).setMinWidth(30);
		SpeicherTab.getColumnModel().getColumn(8).setMaxWidth(30);
		SpeicherScrPanel.setViewportView(SpeicherTab);
		
		JPanel PlatzhalterSpSch = new JPanel();
		GridBagConstraints gbc_PlatzhalterSpSch = new GridBagConstraints();
		gbc_PlatzhalterSpSch.anchor = GridBagConstraints.EAST;
		gbc_PlatzhalterSpSch.gridheight = 3;
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
		SchalterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPortInputs = new JLabel("Ports");
		SchalterPanel.add(lblPortInputs);
		
		JLabel label = new JLabel("");
		SchalterPanel.add(label);
		
		JPanel panel_ports = new JPanel();
		SchalterPanel.add(panel_ports);
		panel_ports.setLayout(new GridLayout(2, 0, 0, 0));
		
		chckbxA7 = new JCheckBox("A7");
		panel_ports.add(chckbxA7);
		
		chckbxA6 = new JCheckBox("A6");
		panel_ports.add(chckbxA6);
		
		chckbxA5 = new JCheckBox("A5");
		panel_ports.add(chckbxA5);
		
		chckbxA4 = new JCheckBox("A4");
		panel_ports.add(chckbxA4);
		
		checkBoxA3 = new JCheckBox("A3");
		panel_ports.add(checkBoxA3);
		
		chckbxA2 = new JCheckBox("A2");
		panel_ports.add(chckbxA2);
		
		chckbxSA1 = new JCheckBox("A1");
		panel_ports.add(chckbxSA1);
		
		chckbxA0 = new JCheckBox("A0");
		panel_ports.add(chckbxA0);
		
		JCheckBox chckbxB = new JCheckBox("B7");
		panel_ports.add(chckbxB);
		
		JCheckBox chckbxB_2 = new JCheckBox("B5");
		panel_ports.add(chckbxB_2);
		
		JCheckBox chckbxB_3 = new JCheckBox("B4");
		panel_ports.add(chckbxB_3);
		
		JCheckBox chckbxB_4 = new JCheckBox("B3");
		panel_ports.add(chckbxB_4);
		
		JCheckBox chckbxB_5 = new JCheckBox("B2");
		panel_ports.add(chckbxB_5);
		
		JCheckBox chckbxB_6 = new JCheckBox("B1");
		panel_ports.add(chckbxB_6);
		
		JCheckBox chckbxB_7 = new JCheckBox("B0");
		panel_ports.add(chckbxB_7);
		
		JCheckBox chckbxB_1 = new JCheckBox("B6");
		panel_ports.add(chckbxB_1);
		
		JLabel label_1 = new JLabel("");
		SchalterPanel.add(label_1);
		chckbxA0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxA0.isSelected()){
					ports.setBitPortA(0);
				}
				else{
					ports.clearBitPortA(0);
				}
			}
		});
		
		
		
		
		JPanel PlatzhalterMitte = new JPanel();
		GridBagConstraints gbc_PlatzhalterMitte = new GridBagConstraints();
		gbc_PlatzhalterMitte.anchor = GridBagConstraints.SOUTH;
		gbc_PlatzhalterMitte.gridwidth = 10;
		gbc_PlatzhalterMitte.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterMitte.fill = GridBagConstraints.HORIZONTAL;
		gbc_PlatzhalterMitte.gridx = 3;
		gbc_PlatzhalterMitte.gridy = 5;
		getContentPane().add(PlatzhalterMitte, gbc_PlatzhalterMitte);
		
		JPanel SpzRegPanel = new JPanel();
		SpzRegPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_SpzRegPanel = new GridBagConstraints();
		gbc_SpzRegPanel.gridheight = 3;
		gbc_SpzRegPanel.insets = new Insets(0, 0, 5, 5);
		gbc_SpzRegPanel.fill = GridBagConstraints.BOTH;
		gbc_SpzRegPanel.gridx = 2;
		gbc_SpzRegPanel.gridy = 6;
		getContentPane().add(SpzRegPanel, gbc_SpzRegPanel);
		GridBagLayout gbl_SpzRegPanel = new GridBagLayout();
		gbl_SpzRegPanel.columnWidths = new int[]{86, 0, 0, 0, 0};
		gbl_SpzRegPanel.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_SpzRegPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_SpzRegPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		SpzRegPanel.setLayout(gbl_SpzRegPanel);
		
		JLabel lblSpezialfunktionsregister = new JLabel("Spezialfunktionsregister");
		GridBagConstraints gbc_lblSpezialfunktionsregister = new GridBagConstraints();
		gbc_lblSpezialfunktionsregister.gridwidth = 3;
		gbc_lblSpezialfunktionsregister.insets = new Insets(0, 0, 5, 5);
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
		
		WReg_inp = new JLabel("00");
		GridBagConstraints gbc_WReg_inp = new GridBagConstraints();
		gbc_WReg_inp.anchor = GridBagConstraints.WEST;
		gbc_WReg_inp.insets = new Insets(0, 0, 5, 5);
		gbc_WReg_inp.gridx = 1;
		gbc_WReg_inp.gridy = 2;
		SpzRegPanel.add(WReg_inp, gbc_WReg_inp);
		
		JLabel lblFsr = new JLabel("FSR");
		GridBagConstraints gbc_lblFsr = new GridBagConstraints();
		gbc_lblFsr.anchor = GridBagConstraints.WEST;
		gbc_lblFsr.insets = new Insets(0, 0, 5, 5);
		gbc_lblFsr.gridx = 0;
		gbc_lblFsr.gridy = 3;
		SpzRegPanel.add(lblFsr, gbc_lblFsr);
		
		FSR_inp = new JLabel("00");
		GridBagConstraints gbc_FSR_inp = new GridBagConstraints();
		gbc_FSR_inp.anchor = GridBagConstraints.WEST;
		gbc_FSR_inp.insets = new Insets(0, 0, 5, 5);
		gbc_FSR_inp.gridx = 1;
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
		gbc_PCL_inp.insets = new Insets(0, 0, 5, 5);
		gbc_PCL_inp.gridx = 1;
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
		gbc_PCLath_inp.insets = new Insets(0, 0, 5, 5);
		gbc_PCLath_inp.gridx = 1;
		gbc_PCLath_inp.gridy = 5;
		SpzRegPanel.add(PCLath_inp, gbc_PCLath_inp);
		
		JLabel lblPc = new JLabel("PC");
		GridBagConstraints gbc_lblPc = new GridBagConstraints();
		gbc_lblPc.anchor = GridBagConstraints.WEST;
		gbc_lblPc.insets = new Insets(0, 0, 5, 5);
		gbc_lblPc.gridx = 0;
		gbc_lblPc.gridy = 6;
		SpzRegPanel.add(lblPc, gbc_lblPc);
		
		PC_inp = new JLabel("0000");
		GridBagConstraints gbc_PC_inp = new GridBagConstraints();
		gbc_PC_inp.anchor = GridBagConstraints.WEST;
		gbc_PC_inp.insets = new Insets(0, 0, 5, 5);
		gbc_PC_inp.gridx = 1;
		gbc_PC_inp.gridy = 6;
		SpzRegPanel.add(PC_inp, gbc_PC_inp);
		
		JLabel lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 7;
		SpzRegPanel.add(lblStatus, gbc_lblStatus);
		
		Status_inp = new JLabel("00");
		GridBagConstraints gbc_Status_inp = new GridBagConstraints();
		gbc_Status_inp.insets = new Insets(0, 0, 5, 5);
		gbc_Status_inp.anchor = GridBagConstraints.WEST;
		gbc_Status_inp.gridx = 1;
		gbc_Status_inp.gridy = 7;
		SpzRegPanel.add(Status_inp, gbc_Status_inp);
		
		JPanel PlatzhalterSpz = new JPanel();
		GridBagConstraints gbc_PlatzhalterSpz = new GridBagConstraints();
		gbc_PlatzhalterSpz.gridwidth = 3;
		gbc_PlatzhalterSpz.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterSpz.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterSpz.gridx = 0;
		gbc_PlatzhalterSpz.gridy = 8;
		SpzRegPanel.add(PlatzhalterSpz, gbc_PlatzhalterSpz);
		
		JPanel StatusPanel = new JPanel();
		GridBagConstraints gbc_StatusPanel = new GridBagConstraints();
		gbc_StatusPanel.insets = new Insets(0, 0, 5, 5);
		gbc_StatusPanel.gridwidth = 3;
		gbc_StatusPanel.fill = GridBagConstraints.BOTH;
		gbc_StatusPanel.gridx = 0;
		gbc_StatusPanel.gridy = 9;
		SpzRegPanel.add(StatusPanel, gbc_StatusPanel);
		StatusPanel.setLayout(new GridLayout(2, 8, 5, 0));
		
		JLabel lblIrp = new JLabel("IRP");
		StatusPanel.add(lblIrp);
		
		JLabel lblRp = new JLabel("RP1");
		StatusPanel.add(lblRp);
		
		JLabel lblRp_1 = new JLabel("RP0");
		StatusPanel.add(lblRp_1);
		
		JLabel lblT = new JLabel("T0");
		StatusPanel.add(lblT);
		
		JLabel lblPd = new JLabel("PD");
		StatusPanel.add(lblPd);
		
		JLabel lblZ = new JLabel("Z");
		StatusPanel.add(lblZ);
		
		JLabel lblDc = new JLabel("DC");
		StatusPanel.add(lblDc);
		
		JLabel lblC = new JLabel("C");
		StatusPanel.add(lblC);
		
		lblIRPVal = new JLabel("x");
		StatusPanel.add(lblIRPVal);
		
		lblRp1val = new JLabel("x");
		StatusPanel.add(lblRp1val);
		
		lblRp0val = new JLabel("x");
		StatusPanel.add(lblRp0val);
		
		lblT0val = new JLabel("x");
		StatusPanel.add(lblT0val);
		
		lblpdval = new JLabel("x");
		StatusPanel.add(lblpdval);
		
		lblZval = new JLabel("x");
		StatusPanel.add(lblZval);
		
		lblDC_Val = new JLabel("x");
		StatusPanel.add(lblDC_Val);
		
		lblC_VAL = new JLabel("x");
		StatusPanel.add(lblC_VAL);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_ButtonPanel = new GridBagConstraints();
		gbc_ButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_ButtonPanel.gridwidth = 3;
		gbc_ButtonPanel.insets = new Insets(0, 0, 5, 5);
		gbc_ButtonPanel.gridx = 3;
		gbc_ButtonPanel.gridy = 6;
		getContentPane().add(ButtonPanel, gbc_ButtonPanel);
		GridBagLayout gbl_ButtonPanel = new GridBagLayout();
		gbl_ButtonPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_ButtonPanel.rowHeights = new int[]{25, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0};
		gbl_ButtonPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ButtonPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		ButtonPanel.setLayout(gbl_ButtonPanel);
		
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prozessor.go();
			}
		});
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
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initRegisterModel();
				prozessor.reset();
			}
		});
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
		gbc_btnStepOut.fill = GridBagConstraints.VERTICAL;
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
		gbc_TextPanel.gridy = 6;
		getContentPane().add(TextPanel, gbc_TextPanel);
		
		Text_inp = new JTable();
		
		TextPanel.setViewportView(Text_inp);
		
		JPanel LaufzeitPanel = new JPanel();
		GridBagConstraints gbc_LaufzeitPanel = new GridBagConstraints();
		gbc_LaufzeitPanel.gridwidth = 3;
		gbc_LaufzeitPanel.insets = new Insets(0, 0, 5, 5);
		gbc_LaufzeitPanel.gridx = 3;
		gbc_LaufzeitPanel.gridy = 7;
		getContentPane().add(LaufzeitPanel, gbc_LaufzeitPanel);
		LaufzeitPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblQuartz = new JLabel("4 MHz");
		LaufzeitPanel.add(lblQuartz);
		
		JLabel lblNewLabel = new JLabel("");
		LaufzeitPanel.add(lblNewLabel);
		
		JLabel lblLaufzeitzähler = new JLabel("Laufzeit:");
		LaufzeitPanel.add(lblLaufzeitzähler);
		
		labelLaufzeitValue = new JLabel("0 µs");
		LaufzeitPanel.add(labelLaufzeitValue);
	
	    
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
        
		initRegisterModel();
        /*
        helpMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Hilfe geklickt");
            }
        });
        */
		
		SpeicherTab.getModel().addTableModelListener((new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
				
				
				//Usereingabe
				if(SpeicherTab.isEditing() && userCanEditRAM ){
					userCanEditRAM = false;
					
					Integer column = arg0.getColumn();
					Integer row = arg0.getLastRow();
					
					column--;

					if( ((row + 1) % 2) == 0 ){	//Ungerade
						column+=8;
					}
					row/=2;
					
					String high = Integer.toHexString(row);
					String low = Integer.toHexString(column);
					
					
					//FEHLEINGABE ERKENNEN
					String eingabe = SpeicherTab.getValueAt(arg0.getLastRow(), arg0.getColumn()).toString();
					
					try{
						Integer wert = Integer.parseInt(eingabe,16);
						
						if(wert < 0 || wert > 255){
							Integer oldVal =  prozessor.get_RAM_Value(Integer.parseInt(high+""+low,16));
							Integer adr = Integer.parseInt(high+""+low);		//TODO CHECK IF OK?
							show_Register(adr, oldVal);
						}
						
						//WERT AKZEPTIERT !!!
						else{
							System.out.println("Registeradresse: "+high+""+low);
							System.out.println("Wert: "+wert);
							prozessor.change_RAM_Value(Integer.parseInt(high+""+low,16), wert);
						}
					}
					catch( java.lang.NumberFormatException e){
						System.out.println("FALSCHEINGABE");
						Integer oldVal =  prozessor.get_RAM_Value(Integer.parseInt(high+""+low,16));
						Integer adr = Integer.parseInt(high+""+low);		//TODO CHECK IF OK?
						show_Register(adr, oldVal);
					}
					
					
					
					PIC_Logger.logger.info("[Usereingabe RAM]: "+high+""+low);
					userCanEditRAM = true;
					
				}
				
			}
		}));
	}



	public void initRegisterModel() {
		for(int x=0; x< 32;x++){
			for(int y=1; y<9; y++){
				ramModel.setValueAt("00", x, y);
			}
		}
	}

	@Override
	public void showSourcecode(ArrayList<String> sourceLine, Integer fokus) {

			DefaultTableModel model = new DefaultTableModel();
			Object[][] zeilen = new Object[sourceLine.size()][sourceLine.size()+1];
			
		for(int i=0; i<sourceLine.size(); i++){
			zeilen[i][0] = i+1;
			zeilen[i][1] = sourceLine.get(i);
			zeilen[i][2] = "BP";
		}
		
		
		Text_inp.setModel(new DefaultTableModel(
				zeilen,
				new String[] {
					"NR", "", "BP"
				}
			));
		
		Text_inp.getColumnModel().getColumn(0).setMaxWidth(25);
		Text_inp.getColumnModel().getColumn(0).setPreferredWidth(25);
		Text_inp.getColumnModel().getColumn(1).setPreferredWidth(521);
		Text_inp.getColumnModel().getColumn(2).setPreferredWidth(10);

		
		Text_inp.setRowSelectionInterval(fokus, fokus);
		
		Text_inp.getTableHeader().setReorderingAllowed(false);
		RegATab.getTableHeader().setReorderingAllowed(false);
		RegBTab.getTableHeader().setReorderingAllowed(false);
		SpeicherTab.getTableHeader().setReorderingAllowed(false);
		
	}
	

	@Override
	public void setFocus(Integer fokus) {
		Text_inp.setRowSelectionInterval(fokus, fokus);
//		Text_inp.scrollRectToVisible(Text_inp.getCellRect(fokus, fokus, true));
		scrollToVisible(Text_inp, fokus, 0);
		Text_inp.repaint();
	}
	
	
	
	
	public static void scrollToVisible(JTable table, int rowIndex, int vColIndex)
	{
	    if (!(table.getParent() instanceof JViewport)) return;
	    JViewport viewport = (JViewport)table.getParent();
	    Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
	    Point pt = viewport.getViewPosition();
	    rect.setLocation(rect.x-pt.x, rect.y-pt.y);
	    try{
	    	viewport.scrollRectToVisible(rect);
	    }
	    catch(java.lang.NullPointerException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	
	
	
	

	@Override
	public void show_W_Register(Integer value) {
		WReg_inp.setText("0x"+Integer.toHexString(value));
	}

	
	//TODO IN INT
	@Override
	public void show_Register(Integer adresse, Integer value) {
		
		String hex_adr = Integer.toHexString(adresse);
		String hex_value = Integer.toHexString(value);
		
		
		if(hex_adr.length()==1) hex_adr="0"+hex_adr;
		if(hex_value.length()==1) hex_value="0"+hex_value;
		
		
		Integer row = Integer.parseInt(hex_adr.substring(0, 1),16);
		Integer column = Integer.parseInt(hex_adr.substring(1, 2),16);
		
		
		row*= 2;
		
		if(column >= 8){
			column-= 8;
			row++;
		}
		
		column++;
			
		ramModel.setValueAt(hex_value, row, column);
	}

	@Override
	public void show_PC(Integer value) {
		PC_inp.setText(Integer.toHexString(value));
	}

	@Override
	public void show_PortA(Integer value) {
		
		PIC_Logger.logger.info("Showing Port A");
		JCheckBox portA[] = { chckbxA0, chckbxSA1, chckbxA2, checkBoxA3, chckbxA4, chckbxA5, chckbxA6, chckbxA7 } ;
		
		int x=7;
		for(int i = 0; i < 8; i++){
			
			if( (value & (int)Math.pow(2, i)) == (int)Math.pow(2, i)){
				regA.setValueAt(1, 1, x+1);
				portA[i].setSelected(true);
			}
			else{
				regA.setValueAt(0, 1, x+1);
				portA[i].setSelected(false);
			}
			x--;
		}
		
	}

	@Override
	public void show_TrisA(Integer value) {

		PIC_Logger.logger.info("Showing Tris A");
		JCheckBox portA[] = { chckbxA0, chckbxSA1, chckbxA2, checkBoxA3, chckbxA4, chckbxA5, chckbxA6, chckbxA7 } ;
		
		//Tris anzeige
		int x=7;
		for(int i = 0; i < 8; i++){
			
			if( (value & (int)Math.pow(2, i)) == (int)Math.pow(2, i)){
				regA.setValueAt("i", 0, x+1);
				portA[i].setEnabled(true);
			}
			else{
				regA.setValueAt("o", 0, x+1);
				portA[i].setEnabled(false);
			}
			x--;
		}
		
		
	}

	@Override
	public void setLaufzeitCounter(Integer microSec) {
		String suffix = " µs";
		String value = String.valueOf(microSec);
		
		this.labelLaufzeitValue.setText(value+suffix);
	}

	@Override
	public void showStatus(Integer value) {
		Status_inp.setText(Integer.toHexString(value));
		
		JLabel statuslabels[] = {  lblIRPVal, lblRp1val, lblRp0val, lblT0val, lblpdval, lblZval, lblDC_Val, lblC_VAL};
		
		//Status anzeigen
		int x=7;
		for(int i = 0; i < 8; i++){
			
			if( (value & (int)Math.pow(2, i)) == (int)Math.pow(2, i)){
				statuslabels[x].setText("1");
			}
			else{
				statuslabels[x].setText("0");
			}
			x--;
		}
		
		
		
		
		
		
	}

	@Override
	public void showFSR(Integer value) {
		FSR_inp.setText(Integer.toHexString(value));
	}
}

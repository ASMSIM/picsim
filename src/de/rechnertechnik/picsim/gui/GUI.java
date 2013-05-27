package de.rechnertechnik.picsim.gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
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



public class GUI extends JFrame implements IGUI{
	
	private JTable Text_inp;
	private JTable RegATab;
	private JTable RegCTab;
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
		gridBagLayout.rowHeights = new int[]{0, 98, 64, 28, 15, 131, 43, 42, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		gbc_RegisterPanel.fill = GridBagConstraints.BOTH;
		gbc_RegisterPanel.gridheight = 3;
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
		
		JScrollPane RegCPanel = new JScrollPane();
		GridBagConstraints gbc_RegCPanel = new GridBagConstraints();
		gbc_RegCPanel.fill = GridBagConstraints.BOTH;
		gbc_RegCPanel.gridx = 0;
		gbc_RegCPanel.gridy = 3;
		RegisterPanel.add(RegCPanel, gbc_RegCPanel);
		
		RegCTab = new JTable();
		RegCTab.setEnabled(false);
		RegCTab.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tris", null, null, null, null, null, null, null, null},
				{"Pin", null, null, null, null, null, null, null, null},
			},
			new String[] {
				"RC", "7", "6", "5", "4", "3", "2", "1", "0"
			}
		));
		RegCTab.getColumnModel().getColumn(0).setPreferredWidth(30);
		RegCTab.getColumnModel().getColumn(0).setMinWidth(30);
		RegCTab.getColumnModel().getColumn(0).setMaxWidth(30);
		RegCTab.getColumnModel().getColumn(1).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(1).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(1).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(2).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(2).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(2).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(3).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(3).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(3).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(4).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(4).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(4).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(5).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(5).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(5).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(6).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(6).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(6).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(7).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(7).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(7).setMaxWidth(20);
		RegCTab.getColumnModel().getColumn(8).setPreferredWidth(20);
		RegCTab.getColumnModel().getColumn(8).setMinWidth(20);
		RegCTab.getColumnModel().getColumn(8).setMaxWidth(20);
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
		gbc_SpeicherPanel.gridheight = 3;
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
		gbc_PlatzhalterMitte.gridwidth = 10;
		gbc_PlatzhalterMitte.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterMitte.fill = GridBagConstraints.HORIZONTAL;
		gbc_PlatzhalterMitte.gridx = 3;
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
		
		JLabel Status_inp = new JLabel("00");
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
		gbc_StatusPanel.gridheight = 2;
		gbc_StatusPanel.insets = new Insets(0, 0, 5, 5);
		gbc_StatusPanel.gridwidth = 3;
		gbc_StatusPanel.fill = GridBagConstraints.BOTH;
		gbc_StatusPanel.gridx = 0;
		gbc_StatusPanel.gridy = 9;
		SpzRegPanel.add(StatusPanel, gbc_StatusPanel);
		GridBagLayout gbl_StatusPanel = new GridBagLayout();
		gbl_StatusPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_StatusPanel.rowHeights = new int[]{0, 0};
		gbl_StatusPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_StatusPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		StatusPanel.setLayout(gbl_StatusPanel);
		
		JLabel lblIrp = new JLabel("IRP");
		GridBagConstraints gbc_lblIrp = new GridBagConstraints();
		gbc_lblIrp.insets = new Insets(0, 0, 0, 5);
		gbc_lblIrp.gridx = 0;
		gbc_lblIrp.gridy = 0;
		StatusPanel.add(lblIrp, gbc_lblIrp);
		
		JLabel lblRp = new JLabel("RP1");
		GridBagConstraints gbc_lblRp = new GridBagConstraints();
		gbc_lblRp.insets = new Insets(0, 0, 0, 5);
		gbc_lblRp.gridx = 1;
		gbc_lblRp.gridy = 0;
		StatusPanel.add(lblRp, gbc_lblRp);
		
		JLabel lblRp_1 = new JLabel("RP0");
		GridBagConstraints gbc_lblRp_1 = new GridBagConstraints();
		gbc_lblRp_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblRp_1.gridx = 2;
		gbc_lblRp_1.gridy = 0;
		StatusPanel.add(lblRp_1, gbc_lblRp_1);
		
		JLabel lblT = new JLabel("T0");
		GridBagConstraints gbc_lblT = new GridBagConstraints();
		gbc_lblT.insets = new Insets(0, 0, 0, 5);
		gbc_lblT.gridx = 3;
		gbc_lblT.gridy = 0;
		StatusPanel.add(lblT, gbc_lblT);
		
		JLabel lblPd = new JLabel("PD");
		GridBagConstraints gbc_lblPd = new GridBagConstraints();
		gbc_lblPd.insets = new Insets(0, 0, 0, 5);
		gbc_lblPd.gridx = 4;
		gbc_lblPd.gridy = 0;
		StatusPanel.add(lblPd, gbc_lblPd);
		
		JLabel lblZ = new JLabel("Z");
		GridBagConstraints gbc_lblZ = new GridBagConstraints();
		gbc_lblZ.insets = new Insets(0, 0, 0, 5);
		gbc_lblZ.gridx = 5;
		gbc_lblZ.gridy = 0;
		StatusPanel.add(lblZ, gbc_lblZ);
		
		JLabel lblDc = new JLabel("DC");
		GridBagConstraints gbc_lblDc = new GridBagConstraints();
		gbc_lblDc.insets = new Insets(0, 0, 0, 5);
		gbc_lblDc.gridx = 6;
		gbc_lblDc.gridy = 0;
		StatusPanel.add(lblDc, gbc_lblDc);
		
		JLabel lblC = new JLabel("C");
		GridBagConstraints gbc_lblC = new GridBagConstraints();
		gbc_lblC.gridx = 7;
		gbc_lblC.gridy = 0;
		StatusPanel.add(lblC, gbc_lblC);
		
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
				prozessor.reset();
				initRegisterModel();
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
							show_Register(high+""+low, Integer.toHexString(oldVal));
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
						show_Register(high+""+low, Integer.toHexString(oldVal));
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
		
		Text_inp.getTableHeader().setReorderingAllowed(false);
		RegATab.getTableHeader().setReorderingAllowed(false);
		RegBTab.getTableHeader().setReorderingAllowed(false);
		RegCTab.getTableHeader().setReorderingAllowed(false);
		SpeicherTab.getTableHeader().setReorderingAllowed(false);
		
	}
	

	@Override
	public void setFocus(Integer fokus) {
		Text_inp.setRowSelectionInterval(fokus, fokus);
		Text_inp.scrollRectToVisible(Text_inp.getCellRect(fokus, fokus, true));
		
	}

	@Override
	public void show_W_Register(String hexvalue) {
		WReg_inp.setText("0x"+hexvalue);
	}

	@Override
	public void show_Register(String adresse, String hexvalue) {
		// TODO Auto-generated method stub
		
		if(adresse.length()==1) adresse="0"+adresse;
		if(hexvalue.length()==1) hexvalue="0"+hexvalue;
		
		System.out.println("setting: addr="+adresse+";hexvalue="+hexvalue);
		
		Integer row = Integer.parseInt(adresse.substring(0, 1),16);
		Integer column = Integer.parseInt(adresse.substring(1, 2),16);
		
		
		row*= 2;
		
		if(column >= 8){
			column-= 8;
			row++;
		}
		
		column++;
			
 
		ramModel.setValueAt(hexvalue, row, column);
	}

	@Override
	public void show_PC(String value) {
		PC_inp.setText(value);
	}

	@Override
	public void show_PortA(Integer value) {
		
		PIC_Logger.logger.info("Showing Port A");
		
		int x=7;
		for(int i = 0; i < 8; i++){
			
			if( (value & (int)Math.pow(2, i)) == (int)Math.pow(2, i)){
				regA.setValueAt(1, 1, x+1);
			}
			else{
				regA.setValueAt(0, 1, x+1);
			}
			x--;
		}
		
	}

	@Override
	public void show_TrisA(Integer value) {

		PIC_Logger.logger.info("Showing Tris A");
		
		int x=7;
		for(int i = 0; i < 8; i++){
			
			if( (value & (int)Math.pow(2, i)) == (int)Math.pow(2, i)){
				regA.setValueAt("i", 0, x+1);
			}
			else{
				regA.setValueAt("o", 0, x+1);
			}
			x--;
		}
		
	}
}

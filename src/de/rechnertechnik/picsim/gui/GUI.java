package de.rechnertechnik.picsim.gui;

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

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class GUI extends JFrame implements IGUI{
	
	private JTable Text_inp;
	private JTable RegATab;
	private JTable RegCTab;
	private JTable RegBTab;
	private JTable SpeicherTab;
	private IProzessor prozessor;
	
	
	
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
		gridBagLayout.columnWidths = new int[]{0, 0, 196, 0, -55, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 185, 0, 43, 43, 42, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
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
		gbc_PlatzhalterWaag.gridwidth = 10;
		gbc_PlatzhalterWaag.insets = new Insets(0, 0, 5, 0);
		gbc_PlatzhalterWaag.fill = GridBagConstraints.BOTH;
		gbc_PlatzhalterWaag.gridx = 2;
		gbc_PlatzhalterWaag.gridy = 0;
		getContentPane().add(PlatzhalterWaag, gbc_PlatzhalterWaag);
		
		JPanel RegisterPanel = new JPanel();
		RegisterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_RegisterPanel = new GridBagConstraints();
		gbc_RegisterPanel.fill = GridBagConstraints.BOTH;
		gbc_RegisterPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RegisterPanel.gridx = 2;
		gbc_RegisterPanel.gridy = 1;
		getContentPane().add(RegisterPanel, gbc_RegisterPanel);
		GridBagLayout gbl_RegisterPanel = new GridBagLayout();
		gbl_RegisterPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_RegisterPanel.rowHeights = new int[]{0, 43, 43, 43, 43, 0};
		gbl_RegisterPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_RegisterPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		RegisterPanel.setLayout(gbl_RegisterPanel);
		
		JLabel RegVarLabel = new JLabel("Register");
		GridBagConstraints gbc_RegVarLabel = new GridBagConstraints();
		gbc_RegVarLabel.anchor = GridBagConstraints.WEST;
		gbc_RegVarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RegVarLabel.gridx = 0;
		gbc_RegVarLabel.gridy = 0;
		RegisterPanel.add(RegVarLabel, gbc_RegVarLabel);
		
		JScrollPane RegAPanel = new JScrollPane();
		GridBagConstraints gbc_RegAPanel = new GridBagConstraints();
		gbc_RegAPanel.fill = GridBagConstraints.BOTH;
		gbc_RegAPanel.gridwidth = 6;
		gbc_RegAPanel.insets = new Insets(0, 0, 5, 0);
		gbc_RegAPanel.gridx = 0;
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
		gbc_RegBPanel.gridx = 0;
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
		gbc_RegCPanel.insets = new Insets(0, 0, 5, 0);
		gbc_RegCPanel.gridx = 0;
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
		gbc_PlatzhalterRegSp.gridx = 3;
		gbc_PlatzhalterRegSp.gridy = 1;
		getContentPane().add(PlatzhalterRegSp, gbc_PlatzhalterRegSp);
		
		JPanel SpeicherPanel = new JPanel();
		SpeicherPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_SpeicherPanel = new GridBagConstraints();
		gbc_SpeicherPanel.gridwidth = 6;
		gbc_SpeicherPanel.insets = new Insets(0, 0, 5, 5);
		gbc_SpeicherPanel.fill = GridBagConstraints.BOTH;
		gbc_SpeicherPanel.gridx = 4;
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
		
		
		
		
		JPanel PlatzhalterMitte = new JPanel();
		GridBagConstraints gbc_PlatzhalterMitte = new GridBagConstraints();
		gbc_PlatzhalterMitte.anchor = GridBagConstraints.SOUTH;
		gbc_PlatzhalterMitte.gridwidth = 9;
		gbc_PlatzhalterMitte.insets = new Insets(0, 0, 5, 5);
		gbc_PlatzhalterMitte.fill = GridBagConstraints.HORIZONTAL;
		gbc_PlatzhalterMitte.gridx = 2;
		gbc_PlatzhalterMitte.gridy = 2;
		getContentPane().add(PlatzhalterMitte, gbc_PlatzhalterMitte);
		
		JPanel SpzRegPanel = new JPanel();
		SpzRegPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_SpzRegPanel = new GridBagConstraints();
		gbc_SpzRegPanel.gridheight = 5;
		gbc_SpzRegPanel.insets = new Insets(0, 0, 0, 5);
		gbc_SpzRegPanel.fill = GridBagConstraints.BOTH;
		gbc_SpzRegPanel.gridx = 2;
		gbc_SpzRegPanel.gridy = 3;
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridheight = 5;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 3;
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
		btnStepIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prozessor.nextStep();
			}
		});
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
		gbc_TextPanel.gridheight = 5;
		gbc_TextPanel.gridwidth = 7;
		gbc_TextPanel.fill = GridBagConstraints.BOTH;
		gbc_TextPanel.gridx = 5;
		gbc_TextPanel.gridy = 3;
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
		Text_inp.getColumnModel().getColumn(0).setPreferredWidth(10);
		Text_inp.getColumnModel().getColumn(1).setPreferredWidth(521);
		TextPanel.setViewportView(Text_inp);


		
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



	@Override
	public void showSourcecode(ArrayList<String> sourceLine, Integer fokus) {
		System.out.println(fokus);

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
		
		Text_inp.setRowSelectionInterval(fokus, fokus);
		
	}
	

	@Override
	public void setFocus(Integer fokus) {
		Text_inp.setRowSelectionInterval(fokus, fokus);
	}
}

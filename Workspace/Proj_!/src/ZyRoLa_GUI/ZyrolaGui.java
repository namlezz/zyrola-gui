package ZyRoLa_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import reused.Help;
import reused.Load;
import frames.Shaft;
import frames.Tab_SR;
import frames.Tab_parameter;
import frames.TestAusgabe;
import reused.LagerBrowser;
import reused.Plaus_Check;
import reused.StatusBar;
import javax.swing.JCheckBoxMenuItem;
import java.awt.SystemColor;

@SuppressWarnings("serial")

public class ZyrolaGui extends JFrame implements ActionListener {

	// Variablen

	public static Tab_parameter P_TabP;
	public static Tab_SR P_TabSR;
	public static TestAusgabe F_TA;
	public static LagerBrowser lager_brwsr;
	public static StatusBar sb;

	public static DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	public static DecimalFormat aFormat = new DecimalFormat("0.#");
	public static DecimalFormat p1Format = new DecimalFormat("#0.#");;
	public static DecimalFormat p3Format = new DecimalFormat("#0.0##");
	public static DecimalFormat p4Format = new DecimalFormat("#0.0#####");
	public static DecimalFormat p5Format = new DecimalFormat("#0.0#####E00");

	private JButton B_Save, B_Lagerdaten;
	private JMenuItem mntmReset, mntmClose, mntmSettings, mntmWelleSys,mntmXML,mntmOpen,mntmSave;
	public static JLabel lblLagertyp;

	private JPanel p_south;
	private JLabel lbl_afspath;
	public static JFormattedTextField ftf_afspath;
	private Component glue;
	private Component rigidArea;
	public static JPanel p_afspath;
	private JButton B_afspath;

	public Spckbuilder spck_bld;


	private JMenu mntmHelp;
	private AbstractButton menuItem5_1;
	private JMenuItem menuItem5_2;
	private JMenuItem menuItem5_3;
	private JMenuItem menuItem5_4;
	private JMenuItem menuItem5_5;
//	private JMenuItem menuItem5_6;
	
	private JMenuBar menuBar2;
	private JMenuItem menuItem5_6;
	public static JCheckBoxMenuItem chckbxmntmManuelleEingabe;

	public static File file, work_pth, spck_pth, sys_pth, f_simpack, f_afs;
	public static File f_db_fag, f_db_fva, f_db_skf;
	public static String lagername;

	public static Preferences prefs = Preferences.userNodeForPackage(Settings.class);

	public static String def = " ! Definition", db_fag = prefs.get("db_fag", "def"),
							db_skf = prefs.get("db_skf", "def"), db_fva = prefs.get("db_fva_daten", "def");
	public static int 		load_mod;

	// zur Ausgabe (angabe in mm)
	public static double 	wk_z, wk_D, wk_E, wk_v, wk_rho,wk_radBsp,wk_axBsp;
	public static int 		wk_N, wk_protype;
	public static double 	wk_pro_cp,wk_pro_kp,wk_pro_rk;

	public static double 	ir_I_D, ir_LFB_D, ir_LFB_B, ir_BORD_D, ir_BORD_B, ir_E, ir_v, ir_rho,ir_LFB_B_r,ir_BORD_Oew,ir_num_contacts;
	public static double 	ar_A_D, ar_LFB_D, ar_LFB_B, ar_BORD_D, ar_BORD_B, ar_E, ar_v, ar_rho,ar_LFB_B_r,ar_BORD_Oew;
	public static int 		ltypindex; // 0 ="NU", 1= "N", 2= "NJ", 3 = "NUP"

	public static int		r_race_f_mod, r_race_f_EHD, r_race_lub_mod, r_tau_mod, r_filmT_mod, r_comprT_mod;
	public static double 	r_vel_s, r_vel_d, r_mu_s, r_mu_d, r_lubtmp, r_etaZero, r_alphaT, r_alphaP, r_lambdaZero,
							r_alphaLambda, r_C1, r_C2, r_AV, r_Bv, r_CV, r_DR, r_ER, r_pR, r_B, r_C, r_K, r_a1, r_a2, r_b1, r_b2,
							r_rhoZero, r_tauRheo, r_sigma, r_BZH, r_CZH, r_alphaHys, r_scaleHys, r_cr, r_rexp;

	public static double 	r_kaefig_vel_s, r_kaefig_vel_d, r_kaefig_mu_s, r_kaefig_mu_d, r_bord_vel_s, r_bord_vel_d,
							r_bord_mu_s, r_bord_mu_d;

	public static int 		d_d_mod;
	public static double 	d_p_t, d_d_max, d_K0, d_KR, d_KL, d_KE, d_Keta, d_Kalpha, d_Kq, d_Ku, d_Kf, d_fe;

	public static int 		s_ctctype_LB;
	public static double 	s_cf_p_max_LB, s_EL_drehzahl, s_EL_F_ax, s_EL_F_rad, s_EL_simrampent, s_EL_simfreq, s_EL_simt,
							s_SL_IR_omega, s_SL_AR_omega, s_no_gauss_LB, s_cf_pnts_LB;

	public static double 	k_v, k_Breite, k_rho, k_D_aussen, k_D_tasche, k_E, k_D_innen, k_DickeSteg;
	public static double 	k_tspiel_tan, k_tspiel_rad, k_tspiel_ax;
	public static int 		kaefigindex, mkaefigindex;

	public static double 	wk_joint_def_transA, wk_joint_def_transB, wk_joint_def_transC, wk_joint_def_rotA,
							wk_joint_def_rotB, wk_joint_def_rotC;
	public static double 	j_innenring_halter_joint_def_transz;

	public static int 		bordkrfte_index, s_lagermod, s_kippen_index; // S_lagermod:
																	// 0 =
																	// Einzellager
																	// , 1 =
																	// Systemlager
	public static int ascii_ausgabe, flex_ar;
	public static double flexAR_maxFE;
	
	public static JFileChooser fc = new JFileChooser() {
		{		  		    
			
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); }
			super.updateUI();
		}

		@Override public void approveSelection() {
			if (getDialogType() == SAVE_DIALOG) {
				File selectedFile = getSelectedFile();
				if ((selectedFile != null) && selectedFile.exists()) {
					int response = JOptionPane.showConfirmDialog(this,
							"Die Datei " + selectedFile.getName() + 
							" existiert bereits. Wollen Sie diese Datei überschreiben?",
							"Speichern bestätigen", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (response != JOptionPane.YES_OPTION)
						return;
				}
			}
			super.approveSelection();
			 
		}
	};
	

	
	// GUI
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ZyrolaGui() throws IOException {

		super("LaMBDA  - Lager-Mehrk\u00f6rperBerechnung und Dynamik Analyse - Zylinderrollenlager");

		setVisible(false);
		int frameWidth = 1100;
		int frameHeight = 750;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(true);
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int response = JOptionPane.showConfirmDialog (null,
						"Wollen Sie das Programm wirklich beenden?",
						"Beenden bestätigen",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION){
//					Help.remTempHelp();
					System.exit(0);}
				else {
					setDefaultCloseOperation(ZyrolaGui.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		
		


		dfs.setDecimalSeparator('.');
		dfs.setGroupingSeparator(',');
		aFormat.setDecimalFormatSymbols(dfs);
		p1Format.setDecimalFormatSymbols(dfs);
		p3Format.setDecimalFormatSymbols(dfs);
		p4Format.setDecimalFormatSymbols(dfs);
		p5Format.setDecimalFormatSymbols(dfs);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Container cp = getContentPane();
		cp.setLayout(new BorderLayout(0, 0));

		lagername = "";

		

		f_db_fag = new File(ZyrolaGui.db_fag);
		f_db_skf = new File(ZyrolaGui.db_skf);
		f_db_fva = new File(ZyrolaGui.db_fva);
		

				
							
					
							// Menüleiste
					
							JPanel P_Menubar = new JPanel();
							P_Menubar.setBackground(Color.WHITE);
							P_Menubar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
							cp.add(P_Menubar, BorderLayout.NORTH);
							P_Menubar.setLayout(new BorderLayout(0, 0));
					
							JMenuBar menuBar1 = new JMenuBar();
							menuBar1.setBorderPainted(false);
							P_Menubar.add(menuBar1, BorderLayout.WEST);
					
							JMenu M_Datei = new JMenu("Datei");
							M_Datei.setMnemonic(KeyEvent.VK_D);
							menuBar1.add(M_Datei);
					
							mntmOpen = new JMenuItem("Öffnen");
							mntmOpen.setMnemonic(KeyEvent.VK_O);
							mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
							mntmOpen.addActionListener(this);
							M_Datei.add(mntmOpen);
							
							mntmSave = new JMenuItem("Speichern unter...");
							mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
							mntmSave.setMnemonic(KeyEvent.VK_S);
							mntmSave.addActionListener(this);
							M_Datei.add(mntmSave);
					
							mntmXML = new JMenuItem("Lade XML-Datei");
							mntmXML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
							mntmXML.setMnemonic(KeyEvent.VK_X);
							mntmXML.addActionListener(this);
							M_Datei.add(mntmXML);
					
							mntmReset = new JMenuItem("Zurücksetzen");
							mntmReset.setMnemonic(KeyEvent.VK_R);
							mntmReset.addActionListener(this);
							M_Datei.add(mntmReset);
					
							mntmClose = new JMenuItem("Schließen");
							mntmClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
							mntmClose.setMnemonic(KeyEvent.VK_C);
							mntmClose.addActionListener(this);
							M_Datei.add(mntmClose);
					
							JMenu M_Optionen = new JMenu("Optionen");
							M_Optionen.setBackground(SystemColor.menu);
							M_Optionen.setIcon(null);
							M_Optionen.setMnemonic(KeyEvent.VK_O);
							menuBar1.add(M_Optionen);
					
							mntmWelleSys = new JMenuItem("Welle Systemlager");
							mntmWelleSys.addActionListener(this);
							mntmWelleSys.setMnemonic(KeyEvent.VK_S);
							M_Optionen.add(mntmWelleSys);
							
							
							chckbxmntmManuelleEingabe = new JCheckBoxMenuItem("Manuelle Eingabe");
							chckbxmntmManuelleEingabe.setSelected(false);
							chckbxmntmManuelleEingabe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
							chckbxmntmManuelleEingabe.addActionListener(this);							
							M_Optionen.add(chckbxmntmManuelleEingabe);
					
							mntmSettings = new JMenuItem("Einstellungen");
							mntmSettings.addActionListener(this);
							mntmSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
							mntmSettings.setMnemonic(KeyEvent.VK_E);
							M_Optionen.add(mntmSettings);
					
							
							lblLagertyp = new JLabel("");
							lblLagertyp.setBackground(SystemColor.menu);
							lblLagertyp.setHorizontalAlignment(SwingConstants.RIGHT);
							P_Menubar.add(lblLagertyp, BorderLayout.CENTER);
					
							menuBar2 = new JMenuBar();
							menuBar2.setBackground(SystemColor.menu);
							menuBar2.setBorderPainted(false);
							P_Menubar.add(menuBar2, BorderLayout.EAST);
							mntmHelp = new JMenu("Hilfe");
							menuBar2.add(mntmHelp);
							
							
							Icon HelpIcon = createImageIcon("/dummies/HelpIcon.png", "Help");
							mntmHelp.setIcon(HelpIcon);
							mntmHelp.setFont(new Font("Arial", Font.PLAIN, 14));
							mntmHelp.setMnemonic(KeyEvent.VK_H);
					
							menuItem5_1 = new JMenuItem("Zeichnung Geometriedaten", new ImageIcon("images/spy.gif"));
							menuItem5_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									ImageIcon icon = createImageIcon("/dummies/Lagergeometrie.png", "Lagergeometrie");
									JLabel label1 = new JLabel("", icon, JLabel.CENTER);
									JFrame frame = new JFrame("Lagergeometrie");
									frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									frame.getContentPane().add(label1);
									frame.pack();
									frame.setVisible(true);
					
								}
							});
							mntmHelp.add(menuItem5_1);
							mntmHelp.addSeparator();
							menuItem5_2 = new JMenuItem("Installation", new ImageIcon("images/spy.gif"));
							menuItem5_2.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
					
									InputStream inputStream = (ZyrolaGui.class.getResourceAsStream("/dummies/InstallHelp.pdf"));
					
									Help.callHelp(inputStream, "Install");
					
								}
							});
							mntmHelp.add(menuItem5_2);
							mntmHelp.addSeparator();
					
							menuItem5_3 = new JMenuItem("ASCII-Ausgabe", new ImageIcon("images/spy.gif"));
							menuItem5_3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
					
									InputStream inputStream = (ZyrolaGui.class.getResourceAsStream("/dummies/AusgabeHelp.pdf"));
					
									Help.callHelp(inputStream, "Ausgabe");
					
								}
							});
							mntmHelp.add(menuItem5_3);
					
							menuItem5_4 = new JMenuItem("Systemeinbindung", new ImageIcon("images/spy.gif"));
							menuItem5_4.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
					
									InputStream inputStream = (ZyrolaGui.class.getResourceAsStream("/dummies/SystemHelp.pdf"));
//									InputStream inputStream1 = (ZyrolaGui.class.getResourceAsStream("/dummies/DokuSystem.jar"));
//									InputStream inputStream2 = (ZyrolaGui.class.getResourceAsStream("/dummies/jh.jar"));
//									InputStream inputStream3 = (ZyrolaGui.class.getResourceAsStream("/dummies/hsviewer.jar"));
					
									Help.callHelp(inputStream, "System");
//									Help.callJARHelp(inputStream1, inputStream2, inputStream3, "DokuSystem.jar");
					
								}
							});
							mntmHelp.add(menuItem5_4);
					
							menuItem5_5 = new JMenuItem("Flexibler Aussenring", new ImageIcon("images/spy.gif"));
							menuItem5_5.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
					
									InputStream inputStream = (ZyrolaGui.class.getResourceAsStream("/dummies/FlexARHelp.pdf"));
					
									Help.callHelp(inputStream, "FlexAR");
					
								}
							});
							mntmHelp.add(menuItem5_5);
							mntmHelp.addSeparator();
					
							menuItem5_6 = new JMenuItem("Programmdokumentation", new ImageIcon("images/spy.gif"));
							menuItem5_6.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
					
									InputStream inputStream = (ZyrolaGui.class.getResourceAsStream("/dummies/DokuHelp.pdf"));
					
									Help.callHelp(inputStream, "Doku");
					
								}
							});
							mntmHelp.add(menuItem5_6);

			
				p_south = new JPanel();
				p_south.setBorder(null);
				getContentPane().add(p_south, BorderLayout.SOUTH);
				p_south.setLayout(new GridLayout(2, 1, 5, 0));
		
						// Buttonleiste
						JPanel P_Buttons = new JPanel();
						P_Buttons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
						p_south.add(P_Buttons);
				
						B_Save = new JButton("Speichern nach *.spck");
						B_Save.addActionListener(this);
						P_Buttons.setLayout(new BoxLayout(P_Buttons, BoxLayout.X_AXIS));
						P_Buttons.add(B_Save);
				
						B_Lagerdaten = new JButton("Lagerdaten aus Datenbank");
						B_Lagerdaten.addActionListener(this);
				
						rigidArea = Box.createRigidArea(new Dimension(20, 20));
						P_Buttons.add(rigidArea);
						P_Buttons.add(B_Lagerdaten);
				
						glue = Box.createGlue();
						P_Buttons.add(glue);
				
						p_afspath = new JPanel();
						p_afspath.setBorder(null);
						p_afspath.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
						p_afspath.setVisible(false);
						P_Buttons.add(p_afspath);
				
						lbl_afspath = new JLabel(".afs-Datei f\u00FCr flex. AR");
						lbl_afspath.setHorizontalAlignment(SwingConstants.TRAILING);
						p_afspath.add(lbl_afspath);
				
						ftf_afspath = new JFormattedTextField();
						p_afspath.add(ftf_afspath);
						ftf_afspath.setColumns(30);
						ftf_afspath.setEditable(false);
						lbl_afspath.setLabelFor(ftf_afspath);
				
						B_afspath = new JButton(".afs-Datei \u00E4ndern");
						B_afspath.addActionListener(this);
						p_afspath.add(B_afspath);
		
				sb = new StatusBar();
				sb.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				p_south.add(sb);
		
				// Tab Pane
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBorder(null);
				cp.add(tabbedPane);
		
				
		
				
				Set forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
				Set newForwardKeys = new HashSet(forwardKeys);
				newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
				
				
				//FocusTraversalPolicy policy = new FocusTraversalPolicy();
				
				P_TabP = new Tab_parameter();
				P_TabP.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,newForwardKeys);
				
				tabbedPane.addTab("Parameter", null, P_TabP, null);			

				P_TabSR = new Tab_SR();
				P_TabSR.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,newForwardKeys);
				tabbedPane.addTab("Schmierung & Reibung", null , P_TabSR, null);
				
				tabbedPane.setMnemonicAt(0, KeyEvent.VK_P);
				tabbedPane.setMnemonicAt(1, KeyEvent.VK_R);

				
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener(){
					public void  propertyChange(final PropertyChangeEvent e){
			    	if (e.getNewValue() instanceof JFormattedTextField)
			    	{
			    		SwingUtilities.invokeLater(new Runnable()
			    		{
			    			public void run()
			    			{
			    				JFormattedTextField textField = (JFormattedTextField) e.getNewValue();
			    				textField.selectAll();
			    			}
			    		});

			    	}
			    }


			});
	}

	
	

		 
	
	
	
	
	// Test-Methode zur Ausgabe
	@SuppressWarnings("unused")
	private void toConsole() {
		System.out.println(this.toString());

	}

	

	// Eventlistener Buttons + ComboBox
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		if (source == B_Save) {
			save_spck();
					

		} else if (source == B_Lagerdaten) {

						if ((f_db_fag.toString() != "def" && !f_db_fag.exists())
								&& (f_db_skf.toString() != "def" && !f_db_skf.exists())
								&& (f_db_fva.toString() != "def" && !f_db_fva.exists())) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Der von Ihnen eingegebene Datenbankpfad führt zu einer nicht vorhandenen Datei.",
									"Datenbank(en) nicht gefunden", JOptionPane.WARNING_MESSAGE);
							Settings.createAndShowGUI(true);
						} else if (!f_db_fag.exists() && !f_db_skf.exists() && !f_db_fva.exists()) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Bitte geben Sie den Pfad zu mindestens einer Datenbank an.", "Datenbanken laden",
									JOptionPane.WARNING_MESSAGE);
							javax.swing.SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									Settings.createAndShowGUI(true);
								}
							});
						} else {
							javax.swing.SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									LagerBrowser.createAndShowGUI();
								}
							});
			}

		} else if (source == B_afspath) {
			change_afspath();

		} else if (source == mntmOpen) {
			 
			Preferences prefs;
			prefs = Preferences.userNodeForPackage(Settings.class);
			String workpath = prefs.get("workpath", "");
			File Fworkpath = new File ( workpath );
			
			fc.setCurrentDirectory(Fworkpath);
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(new FileFilter() {
				public boolean accept(File f) {
					if (f.isDirectory())
						return true;
					return f.getName().toLowerCase().endsWith(".spck");
				}

				public String getDescription() {
					return ".spck-Dateien";
				}
			});
			fc.setMultiSelectionEnabled(false);
			int returnVal = fc.showOpenDialog(ZyrolaGui.this);
			if( returnVal == JFileChooser.APPROVE_OPTION )
				try {
					Load.open(fc.getSelectedFile());
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			
			
			
			

		} else if (source == mntmSave) {
			save_spck();

		} else if (source == mntmWelleSys) {
			Shaft.createAndShowGUI(true);

		} else if (source == mntmReset) {
			String str = "Wollen Sie die Eingaben wirklich l\u00f6schen";
			int go_on = JOptionPane.showConfirmDialog(null, str, "Eingaben l\u00f6schen", JOptionPane.YES_NO_OPTION);
			if (go_on == 0) {
				Tab_parameter.Clearftf();
			}
			
		} else if (source == mntmClose) {
			System.exit(0);
			
		} else if (source == mntmSettings) {
			Settings.createAndShowGUI(true);
			
			
		} else if (source == chckbxmntmManuelleEingabe) {
			
			if (chckbxmntmManuelleEingabe.isSelected()) {
				load_mod = 0;						//manuelle eingabe
				ZyrolaGui.lblLagertyp.setText("Manuelle Eingabe/Override");
				Tab_parameter.ftf_AR_LFB_D.setEditable(true);
				Tab_parameter.ftf_IR_LFB_D.setEditable(true);
				Tab_parameter.ftf_AR_BORD_B.setEditable(false);
				Tab_parameter.ftf_IR_BORD_B.setEditable(false);
				Tab_parameter.ftf_WK_radBsp.setEditable(false);
				
			} else 	if (!chckbxmntmManuelleEingabe.isSelected()) {
				load_mod = 1;						//autoeingabe
				ZyrolaGui.lblLagertyp.setText("Automatische Eingabe");
				Tab_parameter.ftf_AR_LFB_D.setEditable(false);
				Tab_parameter.ftf_IR_LFB_D.setEditable(false);
				Tab_parameter.ftf_AR_BORD_B.setEditable(false);
				Tab_parameter.ftf_IR_BORD_B.setEditable(false);
				Tab_parameter.ftf_WK_radBsp.setEditable(true);
				
			}
			
			
			
			
		} else if (source == mntmXML) {
			
						Preferences prefs;
						prefs = Preferences.userNodeForPackage(Settings.class);
						String workpath = prefs.get("workpath", "");
						File Fworkpath = new File ( workpath );
						int go_on = JOptionPane.showConfirmDialog(null, "Bitte wählen Sie vor dem XML-Import den korrekten K\u00e4figtyp aus.", "XML Import", JOptionPane.OK_CANCEL_OPTION);
						if (go_on == 0) {
							
							fc.setCurrentDirectory(Fworkpath);
							fc.addChoosableFileFilter(new FileFilter() {
								public boolean accept(File f) {
									if (f.isDirectory())
										return true;
									return f.getName().toLowerCase().endsWith(".xml");
								}
			
								public String getDescription() {
									return "XML Dateien";
								}
							});
							fc.setMultiSelectionEnabled(false);
							int returnVal = fc.showOpenDialog(ZyrolaGui.this);
							if( returnVal == JFileChooser.APPROVE_OPTION )
								try {
									Load.getXML(fc.getSelectedFile());
								} catch (Exception e) {
									
									e.printStackTrace();
									
								}
						}
		}

	}



	private void save_spck() {
		if (CheckCorrect()) {
			// .spck Directory asuwählen
			Preferences prefs;
			prefs = Preferences.userNodeForPackage(Settings.class);
			String workpath = prefs.get("workpath", "");
			File work_pth = new File(workpath);
			fc.setCurrentDirectory(work_pth);
			fc.setSelectedFile(new File(lagername + ".spck"));
			int returnVal = fc.showSaveDialog(ZyrolaGui.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				spck_bld = new Spckbuilder(file);
				this.spck_bld.saveit();
				work_pth = file.getParentFile();
				if (flex_ar == 1) {
					try {
						afscopy(work_pth);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				sb.message("'" + file.toString() + "' wurde erstellt.");
			} else if (returnVal == JFileChooser.CANCEL_OPTION) {
				sb.message("Speichern abgebrochen.");
			}


			


			} else {
				// Fehlermeldungs Template
				/*
				 * JFrame dsp = new JFrame(); StatusBar sb = new StatusBar();
				 * dsp.setSize(300,30); dsp.add(sb); sb.message(
				 * "Fehlerhafte Eingabe!"); dsp.setVisible(true);
				 */
			}
		
	}






	// Prozedur um Eingaben auf Korrektheit zu prüfe	

	private boolean CheckCorrect() {
		return (!Plaus_Check.check());
	}

	public static void change_afspath() {
		JFileChooser ch = new JFileChooser();

		int ret;

		ch.setCurrentDirectory(work_pth);
		ch.setFileFilter(new FileNameExtensionFilter(".afs", "afs"));

		ret = ch.showDialog(p_afspath, "Auswählen");
		
		
		if (ret == JFileChooser.APPROVE_OPTION) {
					f_afs = (ch.getSelectedFile());
					ftf_afspath.setText(f_afs.toString());
			}

		if (ret == JFileChooser.CANCEL_OPTION) {
			Tab_parameter.chk_flexAR.setSelected(true);
			Tab_parameter.chk_flexAR.doClick();
			sb.message("Auswahl für flexiblen Aussenring abgebrochen.");
			
		}
		
	}

	public static void afscopy(File p) throws IOException  {

		File copyto = new File(p.toString() + "/ARflex.afs");
		Files.copy(f_afs.toPath(), copyto.toPath());
	}

	// set Initial
	public static void setInitial() {
		Tab_SR.txt_LUBTEMP.setValue(80.0);

		Tab_SR.box_oel.setSelectedItem("FVA3");
		Tab_SR.setOele(null);
		Tab_SR.box_mischreibung.setSelectedIndex(1);
		Tab_SR.setMischreibung(null);

		Tab_SR.panel2_2_unten.setVisible(true);
		LagerBrowser.d_LFB_i = 0.0;
		LagerBrowser.d_LFB_a = 0.0;
		// Settings.getPreferences();

		Tab_SR.race_f_EHD.setSelectedIndex(1);
		Tab_SR.race_lub_mod.setSelectedIndex(0);
		Tab_SR.setracelub();
		Tab_parameter.box_ctctype_LB.setSelectedIndex(1);

		//Viele Values hier erst gegeben damit die Listener auf jeden Fall anspringen
		Tab_SR.ftf_kaefig_vel_s.setValue(50.0);
		Tab_SR.ftf_kaefig_vel_d.setValue(100.0);
		Tab_SR.ftf_kaefig_mu_s.setValue(0.03);
		Tab_SR.ftf_kaefig_mu_d.setValue(0.02);

		Tab_SR.ftf_bord_vel_s.setValue(1.0);
		Tab_SR.ftf_bord_vel_d.setValue(100.0);
		Tab_SR.ftf_bord_mu_s.setValue(0.02);
		Tab_SR.ftf_bord_mu_d.setValue(0.01);

		Tab_SR.ftf_alphalambda.setValue(Double.parseDouble("7.270000000000001e-05"));
		Tab_SR.ftf_lambdazero.setValue(Double.parseDouble("0.134"));
		Tab_SR.ftf_alphaP.setValue(Double.parseDouble("0.022985"));
		Tab_SR.ftf_alphaT.setValue(Double.parseDouble("0.04329"));
		Tab_SR.ftf_etazero.setValue(Double.parseDouble("3.35e-07"));
		Tab_SR.rad_Fva400.doClick();
		
//		Tab_parameter.ftf_WK_N.setValue(1);
//		Tab_parameter.ftf_WK_D.setValue(0.0);
//		Tab_parameter.ftf_WK_Z.setValue(0.0);
//		
		Tab_parameter.box_kaefige.setSelectedIndex(0);
		Tab_parameter.box_kaefige_mod.setSelectedIndex(0);
		Tab_parameter.EButton.doClick();
		Tab_parameter.chk_kippen.doClick();
		Tab_parameter.chk_brdkrft.doClick();
		
		
		
		

		Tab_parameter.set_kaefig_param();
		Tab_parameter.set_J_WK();
		lblLagertyp.setText("");
		chckbxmntmManuelleEingabe.doClick();

		Settings.createAndShowGUI(false);
		Settings.disposeframe();
		// F_TA = new TestAusgabe();

	}

	public static void main(String[] args) throws IOException {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			 
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		// UIManager.put("swing.boldMetal", Boolean.FALSE);

		ZyrolaGui window = new ZyrolaGui();
		setInitial();
		window.setVisible(true);

	}
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

}
	






package frames;





import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import ZyRoLa_GUI.ZyrolaGui;
import reused.SpringUtilities;

@SuppressWarnings("serial")
public class Tab_SR extends JPanel
					implements ActionListener, PropertyChangeListener, ComponentListener{
	

	public static JComboBox<String> box_oel, box_berechnungAuswahl, box_mischreibung;

	public static JRadioButton rad_Fva400, rad_Rodermund;
	public static JLabel lbl_teutsch;
	
	public static double etazero, eta_tp, alphat, alphap, p_ref, lubtemp, rhozero, alpharho, rhoT, K, B, C, kv40, kv100, a1, a2, b1, b2, taueyr;
	
	public static JFormattedTextField		
	txt_vel_s, txt_vel_d, txt_mu_s, txt_mu_d, txt_CR, txt_expR, txt_LUBTEMP, txt_rhozero , txt_alpharho, txt_av, txt_bv,
	txt_cv, txt_pr, txt_dr, txt_er, ftf_R_tauRheo, txt_alphasys, txt_sigma, 
	txt_bzh, txt_czh, txt_fva400_a1, txt_fva400_b1, txt_fva400_a2, txt_fva400_b2, txt_KV40, txt_KV100,
	txt_visk_40, txt_temp_oel, txt_visk_t, txt_dichte_oel, txt_r_u,
	
	
	ftf_etazero,ftf_alphaT,ftf_alphaP,ftf_lambdazero,ftf_alphalambda ;
	
	
	
	
	public static JFormattedTextField 
		ftf_kaefig_mu_s, ftf_kaefig_mu_d, ftf_kaefig_vel_s, ftf_kaefig_vel_d;
	
	public static JFormattedTextField 
		ftf_bord_mu_s, ftf_bord_mu_d, ftf_bord_vel_s, ftf_bord_vel_d;
	
	
	
	public static String[] oele = new String[] {"FVA3" , "FVA2","FVA2a","FVA2b","HVI","PAO/E pur",
			"PAO/E+PMA1","PAO/E+PAO100","PAO/E+PIB","PAO/E+Additiv","PAO/E+Additiv+PMA1",
			"PG 1:1+AO","PG 0:1","PS kom1","PG kom2",
			"PTI","PTI+Additiv","DIA+KE","KE","KE+Additiv",
			"HC","HC+PMA1","HC+Additiv","HC+PMA1+Additiv",
			"frei"} ;
	private String[] mischReibungen = new String[] { "Standard", "Behandelt", "Optisch glatt", "Benutzdefiniert" };

	
	
	public static JPanel panel2_1, panel2_2, panel2_1_1, panel2_1_3, panel2_1_2, panel2_1_4, panel2_2_oben, panel2_2_center, panel2_2_unten,panel2_2_2,panel2_2_3,panel_2_2_5;
	
	
	/** neue Felder **/
	
	@SuppressWarnings("rawtypes")
	public static  JComboBox			 race_f_EHD, race_lub_mod;
	public static JPanel panel_SR_Box;

	private static JButton Btn_beiwerte;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Tab_SR() {
		super( new GridLayout(1,3,0,0) );
		
		
		/** Beginn linke Seite**/ /*reused*/

		panel2_1 = new JPanel (new SpringLayout());
		
		
		
		/*neues PAnel für Einstellboxen*/
		panel_SR_Box = new JPanel( new SpringLayout());
		panel_SR_Box.setBorder(BorderFactory.createTitledBorder(null, "S&R Einstellungen", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial",1,18)));
		String[] labelsSR_Box = {"<html><body>Festkörperreibung</body></html>","<html><body>EHD-Reibung</body></html>","<html><body>Schmierstoffbeschreibung</body></html>",
				"<html><body>Schubspannung im Schmierstoff","<html><body>Therm. Korrekturfaktor</body></html>",
				"<html><body>Schmierstofferwärmung durch Kompression</body></html>"};
		
		
		String[] labelsSR_Box_tt = { "Festkörperreibmodell" ,"EHD-Reibung","Schmierstoffbeschreibung",
				"Modell zur Berechnung der Schubspannung im Schmierstoff","Modell für thermischen Korrekturfaktor",
				"Modell zur Berücksichtigung der Schmierstofferwärmung aufgrund von Kompression" };
		
		
		

		
		JLabel lbl2 = new JLabel(labelsSR_Box[1]);
		panel_SR_Box.add(lbl2);
		
		String[] rfe = new String[] {"0 - aus", "1 - an"}; // TODO tooltip mit restbschreibung
		race_f_EHD = new JComboBox(rfe);
		race_f_EHD.setSelectedIndex(1);
		race_f_EHD.setToolTipText(labelsSR_Box_tt[1]);
		race_f_EHD.addActionListener(this);
		panel_SR_Box.add(race_f_EHD);
		
		
		
		JLabel lbl3 = new JLabel(labelsSR_Box[2]);
		panel_SR_Box.add(lbl3);
		
		String[] rlm = new String[] {"1 - FVA 400" ,"2 - Rodermund/Vogel"};
		race_lub_mod = new JComboBox(rlm);
		race_lub_mod.setSelectedIndex(1);
		race_lub_mod.setToolTipText(labelsSR_Box_tt[2]);
		race_lub_mod.addActionListener(this);
		panel_SR_Box.add(race_lub_mod);
		
		

		SpringUtilities.makeCompactGrid(panel_SR_Box, 1, 4, 0, 0, 6, 10); 
		
		panel2_1.add(panel_SR_Box);
		
		
		
		panel2_1_1 = new JPanel( new SpringLayout() );
		panel2_1_1.setBorder(BorderFactory.createTitledBorder(null, "\u00d6l", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial",1,18)));
		String[] labels2_1_1 = {
				"Auswahl \u00d6l ",
				"<html><nobr>Viskosit\u00E4t &#957;<sub>40\u00B0C</sub> [cSt] </nobr></html>",
				"<html><nobr>\u00d6ltemperatur T<sub>\u00d6l</sub> [\u00B0C] </nobr></html>",
				"<html><nobr>Viskosit\u00E4t &#957;<sub>T<sub>\u00d6l</sub></sub> [cSt] </nobr></html>",
				"<html><nobr>\u00d6ldichte &#961<sub>\u00d6l</sub> [g/ml] </nobr></html>"
				//"Oeltemperatur",
		};

		JLabel lblOel = new JLabel(labels2_1_1[0], JLabel.TRAILING);
		panel2_1_1.add(lblOel);
		box_oel = new JComboBox( oele );
		box_oel.setMaximumSize(getSize());
		box_oel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {    
				setOele(ev);
			}	      
		});
		box_oel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOel.setLabelFor(box_oel);
		panel2_1_1.add(box_oel);
		
		JLabel lblTemp40 = new JLabel(labels2_1_1[1], JLabel.TRAILING);
		lblTemp40.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_1_1.add(lblTemp40);
		txt_visk_40 =  new JFormattedTextField(ZyrolaGui.p3Format);
		txt_visk_40.setEditable(false);
		txt_visk_40.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTemp40.setLabelFor(txt_visk_40);
		panel2_1_1.add(txt_visk_40);
		
		JLabel lblOelTemp = new JLabel(labels2_1_1[2], JLabel.TRAILING);
		lblOelTemp.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_1_1.add(lblOelTemp);
		txt_LUBTEMP = new JFormattedTextField(ZyrolaGui.p3Format);
		txt_LUBTEMP.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOelTemp.setLabelFor(txt_LUBTEMP);
		txt_LUBTEMP.addPropertyChangeListener("value", this);
		panel2_1_1.add(txt_LUBTEMP);
		txt_LUBTEMP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				Beiwerte();
				
			}
		});
		txt_LUBTEMP.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					Beiwerte();
				}
			}
		
		});
		

		JLabel lblTempT = new JLabel(labels2_1_1[3], JLabel.TRAILING);
		lblTempT.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_1_1.add(lblTempT);
		txt_visk_t =  new JFormattedTextField(ZyrolaGui.p3Format);
		txt_visk_t.setEditable(false);
		txt_visk_t.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTempT.setLabelFor(txt_visk_t);
		panel2_1_1.add(txt_visk_t);
		
		JLabel lblDichteOel = new JLabel(labels2_1_1[4], JLabel.TRAILING);
		lblDichteOel.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_1_1.add(lblDichteOel);
		txt_dichte_oel =  new JFormattedTextField(ZyrolaGui.p3Format);
		txt_dichte_oel.setMaximumSize(getPreferredSize());
		txt_dichte_oel.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_1_1.add(txt_dichte_oel);
		
		panel2_1_1.add(new JLabel());
		panel2_1_1.add(new JLabel());
		
		SpringUtilities.makeCompactGrid(panel2_1_1, 3, 4, 0, 0, 6, 10); 
		
		panel2_1.add(panel2_1_1);
		

		
		/** Beginne Panel 2_1_2 **/

		panel2_1_2 = new JPanel( new SpringLayout() );
		panel2_1_2.setBorder(BorderFactory.createTitledBorder(null,
				"Festk\u00f6rperreibung", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 18)));

		String[] labels2_1_2 = {
				"<html><nobr>v<sub>statisch</sub> [mm/s] </nobr></html>",
				"<html><nobr>\u03BC<sub>statisch</sub> [-] </nobr></html>",
				"<html><nobr>v<sub>dynamisch</sub> [mm/s] </nobr></html>",
				"<html><nobr>\u03BC<sub>dynamisch</sub> [-] </nobr></html>"
		};
		
		for(int i = 0; i < labels2_1_2.length; i++) {
			JLabel label2_1_2 = new JLabel(labels2_1_2[i], JLabel.TRAILING);
			JFormattedTextField textField =  new JFormattedTextField(ZyrolaGui.p4Format);
			label2_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
//			textField.setEditable(false);
			if(i==0) txt_vel_s = textField; 
			if(i==1) txt_mu_s = textField;
			if(i==2) txt_vel_d = textField;
			if(i==3) txt_mu_d = textField;
			label2_1_2.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_1_2.add(label2_1_2);
			panel2_1_2.add(textField);
		}
		
		JPanel panel1 = new JPanel();
		panel2_1_2.add(panel1);
		JPanel panel2 = new JPanel();
		panel2_1_2.add(panel2);
		JPanel panel3 = new JPanel();
		panel2_1_2.add(panel3);
		JPanel panel4 = new JPanel();
		panel2_1_2.add(panel4);

		
		SpringUtilities.makeCompactGrid(panel2_1_2, 3, 4, 0, 0, 6, 10); 

		panel2_1.add(panel2_1_2);
		
		/** Beginne Panel 2_1_3 **/
		
		panel2_1_3 = new JPanel( new SpringLayout() );
		panel2_1_3.setBorder(BorderFactory.createTitledBorder(null,
				"Mischreibung", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 18)));
		
		String[] labels2_1_3 = {
				"Oberfl\u00e4che ",
				"Sigma [mm] ",
				"<html><nobr>B<sub>ZH</sub> [-] </nobr></html>",
				"<html><nobr>C<sub>ZH</sub> [-] </nobr></html>"
		};
		JLabel labelModus = new JLabel(labels2_1_3[0], JLabel.TRAILING);
		panel2_1_3.add(labelModus);
		box_mischreibung = new JComboBox( mischReibungen );
		box_mischreibung.setFont(new Font("Arial", Font.PLAIN, 12));
		box_mischreibung.setMaximumSize(getSize());
		box_mischreibung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) { 
				setMischreibung(ev);
			}	      
		});
		labelModus.setLabelFor(box_mischreibung);
		panel2_1_3.add(box_mischreibung);
		
		for(int i = 0; i < labels2_1_3.length-1; i++) {
			JLabel label = new JLabel(labels2_1_3[i+1], JLabel.TRAILING);
			JFormattedTextField textField =  new JFormattedTextField(ZyrolaGui.p4Format);
			label.setFont(new Font("Arial", Font.PLAIN, 12));
//			textField.setEditable(false);
			if(i==0) txt_sigma = textField; 
			if(i==1) txt_bzh = textField;
			if(i==2) txt_czh = textField;
			label.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_1_3.add(label);
			panel2_1_3.add(textField);
		}
		

		panel2_1_3.add(panel1);
		panel2_1_3.add(panel2);
		panel2_1_3.add(panel3);

		panel2_1_3.add(panel4);
		SpringUtilities.makeCompactGrid(panel2_1_3, 3, 4, 0, 0, 6, 10);
		
		panel2_1.add(panel2_1_3);
		
		/** Beginne Panel 2_1_4 - Verluste **/
		
		panel2_1_4 = new JPanel( new SpringLayout() );
		panel2_1_4.setBorder(BorderFactory.createTitledBorder(null,
				"Verluste", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 18)));

		String[] labels2_1_4 = {
				"Materialhysteresekoeffizient [-] ",
				"Rollwiderstandskoeffizient [-] ",
				"Rollwiderstandsexponent [-] "
		};
		
		for(int i = 0; i < labels2_1_4.length; i++) {
			JLabel label2_1_4 = new JLabel(labels2_1_4[i], JLabel.TRAILING);
			label2_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
			JFormattedTextField textField =  new JFormattedTextField(ZyrolaGui.p3Format);
//			textField.setEditable(false);
			if(i==0) txt_alphasys = textField; 
			if(i==1) txt_CR = textField;
			if(i==2) txt_expR = textField;
			label2_1_4.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_1_4.add(label2_1_4);
			panel2_1_4.add(textField);
		}
		SpringUtilities.makeCompactGrid(panel2_1_4, 3, 2, 0, 0, 6, 10); 
		
		panel2_1.add(panel2_1_4);
		
		
		SpringUtilities.makeCompactGrid(panel2_1, 5, 1, 0, 0, 6, 10); 
		
		add(panel2_1);
		
		
		/** Beginn rechte Seite **/
		
		panel2_2 = new JPanel( new BorderLayout() ); 
		panel2_2.setBorder(BorderFactory.createTitledBorder(null,
				"Schmierstoffparameter", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 18)));
		
		
		panel2_2_oben = new JPanel( new GridLayout(2,3) );
		//panel2_2_oben.setBorder(BorderFactory.createTitledBorder(""));
		
		JLabel lblGrenzSchubSpannung = new JLabel("Grenzschubspannung [MPa] ", JLabel.TRAILING);
		lblGrenzSchubSpannung.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_2_oben.add(lblGrenzSchubSpannung);
		ftf_R_tauRheo =  new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_R_tauRheo.addPropertyChangeListener("value", this);
		
		panel2_2_oben.add(ftf_R_tauRheo);
		
		//Dummys
		panel2_2_oben.add(new JLabel());
//		panel2_2_oben.add(new JLabel());
		
		
		rad_Fva400 = new JRadioButton("FVA400");
		rad_Rodermund = new JRadioButton("Rodermund/Vogel");
		
		rad_Fva400.setFont(new Font("Arial", Font.PLAIN, 12));
		rad_Rodermund.setFont(new Font("Arial", Font.PLAIN, 12));
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rad_Fva400);
		btnGrp.add(rad_Rodermund);
		
		rad_Rodermund.addActionListener(this);
		
		rad_Fva400.addActionListener(this);
		panel2_2_oben.add(rad_Fva400);
		panel2_2_oben.add(rad_Rodermund);
		
		lbl_teutsch = new JLabel("Teutsch");
		lbl_teutsch.setFont(new Font("Arial", Font.PLAIN, 12));
		panel2_2_oben.add(lbl_teutsch);

		
		panel2_2.add(panel2_2_oben, BorderLayout.NORTH);
		
		/** Beginn panel2_2_2 Rodermund/Vogel **/
		
		panel2_2_center = new JPanel( new GridLayout(1,3) );
//		panel2_2_center.setBorder(BorderFactory.createEtchedBorder());
		panel2_2_2 = new JPanel( new SpringLayout() );
		String[] labels2_1_2_FVA400 = {
				"a1 [bar]", 
				"a2 [bar/°C]", 
				"b1 [-]", 
				"b2 [1/°C]", 
				"KV40 [cSt]", 
				"KV100 [cSt]", 
				"<html><nobr>&#961<sub>0</sub> [g/ml]</nobr></html>", 
				"<html><nobr>&#945<sub>&#961</sub> [g/(ml °C)]</nobr></html>"};
		
		for (int i = 0; i < labels2_1_2_FVA400.length; i++) {
			JLabel l = new JLabel(labels2_1_2_FVA400[i], JLabel.TRAILING);
			l.setFont(new Font("Arial", Font.PLAIN, 12));
			panel2_2_2.add(l);
			JFormattedTextField textField = new JFormattedTextField(ZyrolaGui.p4Format);
//			textField.setEditable(false);
			if(i==0) txt_fva400_a1 = textField; 
			if(i==1) txt_fva400_a2 = textField;
			if(i==2) txt_fva400_b1 = textField;
			if(i==3) txt_fva400_b2 = textField;
			if(i==4) txt_KV40 = textField;
			if(i==5) txt_KV100 = textField;
			if(i==6) txt_rhozero = textField;
			if(i==7) txt_alpharho = textField;
			l.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_2_2.add(textField);
		}
		
		JPanel panel9 = new JPanel();
		panel2_2_2.add(panel9);
		
		Btn_beiwerte = new JButton("<html>Beiwerte<br>berechnen</html>");
		Btn_beiwerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Beiwerte();
				ZyrolaGui.sb.message("Beiwerte erfolgreich berechnet.");
				txt_LUBTEMP.grabFocus();
			}
			
		});
		Btn_beiwerte.setFocusTraversalKeysEnabled(false);
		Btn_beiwerte.setVisible(false);
	
		panel2_2_2.add(Btn_beiwerte);
		
		

		JPanel panel10 = new JPanel();
		panel2_2_2.add(panel10);			
		JPanel panel11 = new JPanel();
		panel2_2_2.add(panel11);
		JPanel panel12 = new JPanel();
		panel2_2_2.add(panel12);
		
		
		



		
		SpringUtilities.makeCompactGrid(panel2_2_2, panel2_2_2.getComponentCount()/2, 2, 0, 0, 6, 10); 
		panel2_2_center.add(panel2_2_2);
		
		
		panel2_2_3 = new JPanel( new SpringLayout() );
		String[] labels2_1_2_Rodermund = {
				"Parameter A [Pa]",
				"Parameter B [\u00B0C]",
				"Parameter C [\u00B0C]",
				"Parameter D [-]",
				"Parameter E [-]",
				"Referenzdruck [bar]"};
		for (int i = 0; i < labels2_1_2_Rodermund.length; i++) {
			JLabel l = new JLabel(labels2_1_2_Rodermund[i], JLabel.TRAILING);
			l.setFont(new Font("Arial", Font.PLAIN, 12));
			panel2_2_3.add(l);
			JFormattedTextField textField = new JFormattedTextField(ZyrolaGui.p3Format);
//			textField.setEditable(false);
			if(i==0) txt_av = textField;
			if(i==1) txt_bv = textField;
			if(i==2) txt_cv = textField;
			if(i==3) txt_dr = textField;
			if(i==4) txt_er = textField;
			if(i==5) txt_pr = textField;
			l.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_2_3.add(textField);
		}
		panel2_2_3.add(panel9);
		panel2_2_3.add(panel10);			
		panel2_2_3.add(panel11);
		panel2_2_3.add(panel12);
		JPanel panel13 = new JPanel();		
		JPanel panel14 = new JPanel();
		JPanel panel15 = new JPanel();
		panel2_2_3.add(panel15);
		JPanel panel16 = new JPanel();
		panel2_2_3.add(panel16);
		SpringUtilities.makeCompactGrid(panel2_2_3, panel2_2_3.getComponentCount()/2, 2, 0, 0, 6, 10);
		panel2_2_3.setVisible(false);
		panel2_2_center.add(panel2_2_3);
		
		// Panel für Handeingabe Teutsch
		
		
		panel_2_2_5 = new JPanel( new SpringLayout() );
		String[] labels2_1_4_Teutsch = {
				"<html>&#951<sub>0</sub> [MPas]</html>",
				"<html>&#945<sub>t</sub> [-]</html>",
				"<html>&#945<sub>p</sub> [1/MPa]</html>",
				"<html>&#955<sub>0</sub> [W/mK]</html>",
				"<html>&#945&#955 [W/mK²]</html>"};
		for (int i = 0; i < labels2_1_4_Teutsch.length; i++) {
			JLabel l = new JLabel(labels2_1_4_Teutsch[i], JLabel.TRAILING);
			l.setFont(new Font("Arial", Font.PLAIN, 12));
			panel_2_2_5.add(l);
			JFormattedTextField textField = new JFormattedTextField(ZyrolaGui.p5Format);
			textField.setEditable(true);
			if(i==0) ftf_etazero = textField;
			if(i==1) ftf_alphaT = textField;
			if(i==2) ftf_alphaP = textField;
			if(i==3) ftf_lambdazero = textField;
			if(i==4) ftf_alphalambda = textField;
			l.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			textField.setValue(0.0);
			panel_2_2_5.add(textField);
		}
		panel_2_2_5.add(panel9);
		panel_2_2_5.add(panel10);		
		panel_2_2_5.add(panel11);		
		panel_2_2_5.add(panel12);		
		panel_2_2_5.add(panel13);		
		panel_2_2_5.add(panel14);
		panel_2_2_5.add(panel15);
		panel_2_2_5.add(panel16);

		
		SpringUtilities.makeCompactGrid(panel_2_2_5, panel_2_2_5.getComponentCount()/2, 2, 0, 0, 6, 10); 
		
		panel2_2_2.addComponentListener(this);
		panel2_2_3.addComponentListener(this);
		panel_2_2_5.addComponentListener(this);
		
		
		
		panel_2_2_5.setVisible(false);
		panel2_2_center.add(panel_2_2_5);
				
		panel2_2.add(panel2_2_center, BorderLayout.CENTER);
		


		

		
		
		/** Beginn Panel Kaefigreibung & Bordreibung **/ 
		
		panel2_2_unten = new JPanel( new GridLayout(1,2) );

		final JPanel panel2_2_4 = new JPanel( new SpringLayout() );
		panel2_2_4.setBorder(BorderFactory.createTitledBorder(null,
				"K\u00E4figreibung", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 18)));

		
		String[] labelsKaefig = {
				"<html><nobr>v<sub>statisch</sub> [mm/s] </nobr></html>",
				"<html><nobr>v<sub>dynamisch</sub> [mm/s] </nobr></html>",
				"<html><nobr>\u03BC<sub>statisch</sub> [-] </nobr></html>",
				"<html><nobr>\u03BC<sub>dynamisch</sub> [-] </nobr></html>"
		};
		for (int i = 0; i < labelsKaefig.length; i++) {
			JLabel l = new JLabel(labelsKaefig[i], JLabel.TRAILING);
			panel2_2_4.add(l);
			l.setFont(new Font("Arial", Font.PLAIN, 12));
			JFormattedTextField textField = new JFormattedTextField(ZyrolaGui.p3Format);
//			textField.setEditable(false);
			if(i==0) ftf_kaefig_vel_s = textField; 
			if(i==1) ftf_kaefig_vel_d = textField;
			if(i==2) ftf_kaefig_mu_s = textField;
			if(i==3) ftf_kaefig_mu_d = textField;
			l.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_2_4.add(textField);
		}
		SpringUtilities.makeCompactGrid(panel2_2_4, panel2_2_4.getComponentCount()/2, 2, 0, 0, 6, 10); 
		
		
		final JPanel panel2_2_5 = new JPanel( new SpringLayout() );
		panel2_2_5.setBorder(BorderFactory.createTitledBorder(null,
				"Bordreibung", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 18)));
		
		
		String[] labelsBord = {
				"<html><nobr>v<sub>statisch</sub> [mm/s] </nobr></html>",
				"<html><nobr>v<sub>dynamisch</sub> [mm/s] </nobr></html>",
				"<html><nobr>\u03BC<sub>statisch</sub> [-] </nobr></html>",
				"<html><nobr>\u03BC<sub>dynamisch</sub> [-] </nobr></html>"
		};
		for (int i = 0; i < labelsBord.length; i++) {
			JLabel l = new JLabel(labelsBord[i], JLabel.TRAILING);
			panel2_2_5.add(l);
			l.setFont(new Font("Arial", Font.PLAIN, 12));
			JFormattedTextField textField = new JFormattedTextField(ZyrolaGui.p3Format);
//			textField.setEditable(false);
			if(i==0) ftf_bord_vel_s = textField; 
			if(i==1) ftf_bord_vel_d = textField;
			if(i==2) ftf_bord_mu_s = textField;
			if(i==3) ftf_bord_mu_d = textField;
			l.setLabelFor(textField);
			textField.setFont(new Font("Arial", Font.PLAIN, 12));
			textField.addPropertyChangeListener("value", this);
			panel2_2_5.add(textField);
		}
		SpringUtilities.makeCompactGrid(panel2_2_5, panel2_2_5.getComponentCount()/2, 2, 0, 0, 6, 10); 
		
		
		
		
		panel2_2_unten.add(panel2_2_4);
		panel2_2_unten.add(panel2_2_5);

		
		panel2_2.add(panel2_2_unten, BorderLayout.SOUTH);

		add(panel2_2);

		

		// Tooltips
		txt_vel_s.setToolTipText("Grenzgeschwindigkeit f\u00FCr statische Reibung. \nBis zu dieser Geschwindigkeit wirkt der statische Reibwert");
		txt_vel_d.setToolTipText("Grenzgeschwindigkeit f\u00FCr dynamische Reibung. \n Ab dieser Geschwindigkeit wirkt der dynamische Reibwert");
		txt_mu_s.setToolTipText("Statischer Reibwert");
		txt_mu_d.setToolTipText("Dynamischer Reibwert");
		txt_CR.setToolTipText("Koeffizient f\u00FCr Rollreibwiderstand");
		txt_expR.setToolTipText("Exponent f\u00FCr Rollreibwiderstand");
		txt_LUBTEMP.setToolTipText("Temperatur des Schmierstoffs");
		txt_rhozero .setToolTipText("Schmierstoffdichte bei 15\u00B0C");
		txt_alpharho.setToolTipText("Dichte-Temperaturkoeffizient des Schmierstoffs");
		txt_av.setToolTipText("Parameter AV nach Vogel");
		txt_bv.setToolTipText("Parameter BV nach Vogel");
		txt_cv.setToolTipText("Parameter CV nach Vogel");
		txt_pr.setToolTipText("Referenzdruck nach Rodermund");
		txt_dr.setToolTipText("Parameter DR nach Rodermund");
		txt_er.setToolTipText("Parameter ER nach Rodermund");
		

		ftf_R_tauRheo.setToolTipText("Grenzschubspannung f\u00FCr Ree-Eyring-Modell");
		txt_alphasys.setToolTipText("Koeffizient f\u00FCr Materialhysterese");
		txt_sigma.setToolTipText("Oberfl\u00E4chenrauigkeit f\u00FCr Mischreibmodell nach Zhou/Hoeprich");
		txt_bzh.setToolTipText("Parameter BZH f\u00FCr Mischreibmodell nach Zhou/Hoeprich");
		txt_czh.setToolTipText("Parameter CZH f\u00FCr Mischreibmodell nach Zhou/Hoeprich");
		txt_fva400_a1.setToolTipText("Parameter a1 nach FVA400 (FVA-Heft 743)");
		txt_fva400_b1.setToolTipText("Parameter b1 nach FVA400 (FVA-Heft 743)");
		txt_fva400_a2.setToolTipText("Parameter a2 nach FVA400 (FVA-Heft 743)");
		txt_fva400_b2.setToolTipText("Parameter b2 nach FVA400 (FVA-Heft 743)");
		txt_KV40.setToolTipText("Schmierstoffviskosit\u00E4t bei 40\u00B0C");
		txt_KV100.setToolTipText("Schmierstoffviskosit\u00E4t bei 100\u00B0C");
		txt_visk_40.setToolTipText("Schmierstoffviskosit\u00E4t bei 40\u00B0C");
		txt_visk_t.setToolTipText("Schmierstoffviskosit\u00E4t bei angegebener Schmierstofftemperatur");
		txt_dichte_oel.setToolTipText("Dichte des Schmierstoffs bei angegebener Schmierstofftemperatur");
		box_oel.setToolTipText("Auswahl des Schmierstoffs");
		box_mischreibung.setToolTipText("Auswahl der Mischreibungsparameter nach Zhou und Hoeprich");
		
		ftf_kaefig_mu_s.setToolTipText("Statischer Reibwert f\u00FCr den W\u00E4lzk\u00F6rper-Käfig-Kontakt");
		ftf_kaefig_mu_d .setToolTipText("Dynamischer Reibwert f\u00FCr den W\u00E4lzk\u00F6rper-Käfig-Kontakt");
		ftf_kaefig_vel_s.setToolTipText("<html>Grenzgeschwindigkeit f\u00FCr statische Reibung f\u00FCr den W\u00E4lzk\u00F6rper-Käfig-Kontakt. <br>Bis zu dieser Geschwindigkeit wirkt der statische Reibwert.</html>");
		ftf_kaefig_vel_d.setToolTipText("<html>Grenzgeschwindigkeit f\u00FCr dynamische Reibung f\u00FCr den W\u00E4lzk\u00F6rper-Käfig-Kontakt. <br>Ab dieser Geschwindigkeit wirkt der dynamische Reibwert.</html>");
		
		ftf_etazero.setToolTipText(" Dynamische Viskosität bei lubTmp"); 	
		ftf_alphaT.setToolTipText("Temperaturviskositätskoeffizient");		
		ftf_alphaP.setToolTipText("Druckviskositätskoeffizient"); 
		ftf_lambdazero.setToolTipText("Wärmeleitfähigkeit"); 	
		ftf_alphalambda.setToolTipText("Wärmeleitfähigkeitskoeffizient");
		
		
		
	}
	
	public static void setracelub() {
 		
		if (race_lub_mod.getSelectedIndex() == 0) {
			rad_Fva400.setVisible(true);
			lbl_teutsch.setVisible(false);
			rad_Rodermund.setVisible(false);
			
			box_oel.setEnabled(true);
			
			ftf_R_tauRheo.setEditable(false);
			
			
			rad_Fva400.doClick();
			box_oel.setSelectedIndex(0);
			setGesperrt();
			
		}
				
		
		if (race_lub_mod.getSelectedIndex() == 1){
			rad_Fva400.setVisible(false);
			rad_Rodermund.setVisible(true);
			lbl_teutsch.setVisible(true);
			box_oel.setEnabled(false);
			
			ftf_R_tauRheo.setEditable(true);
			
			
			rad_Rodermund.doClick();
			setFrei();
		}
			
		
		
	}
	
	
	
	
	public static void setOele(ActionEvent ev){
		String oeltyp = (String) box_oel.getSelectedItem(); 
//		box_oel.setSelectedItem(0);

		if(oeltyp.equals("FVA3"))				setFVA3();
		if(oeltyp.equals("FVA2"))				setFVA2();
		if(oeltyp.equals("FVA2a"))				setFVA2a();
		if(oeltyp.equals("FVA2b"))				setFVA2b();
		if(oeltyp.equals("HVI"))				setHVI();
		if(oeltyp.equals("PAO/E pur"))			setPAO_E_pur();
		if(oeltyp.equals("PAO/E+PMA1"))			setPAO_E_PMA1();
		if(oeltyp.equals("PAO/E+PAO100"))		setPAO_E_PAO100();
		if(oeltyp.equals("PAO/E+PIB"))			setPAO_E_PIB();
		if(oeltyp.equals("PAO/E+Additiv"))		setPAO_E_Additiv();
		if(oeltyp.equals("PAO/E+Additiv+PMA1"))	setPAO_E_Additiv_PMA1();
		if(oeltyp.equals("PG 1:1+AO"))			setPG_1_1_AO();
		if(oeltyp.equals("PG 0:1"))				setPG_0_1();
		if(oeltyp.equals("PS kom1"))			setPG_kom1();
		if(oeltyp.equals("PG kom2"))			setPG_kom2();
		if(oeltyp.equals("PTI"))				setPTI();	
		if(oeltyp.equals("PTI+Additiv"))		setPTI_Additiv();
		if(oeltyp.equals("DIA+KE"))				setDIA_KE();
		if(oeltyp.equals("KE"))					setKE();
		if(oeltyp.equals("KE+Additiv"))			setKE_Additiv();
		if(oeltyp.equals("HC"))					setHC();
		if(oeltyp.equals("HC+PMA1"))			setHC_PMA1();
		if(oeltyp.equals("HC+Additiv"))			setHC_Additiv();
		if(oeltyp.equals("HC+PMA1+Additiv"))	setHC_PMA1_Additiv();
		if(oeltyp.equals("frei"))				setFrei();
		
		
	}
	
	
	public static void Beiwerte()
	{
		double H100, H210, VSF, S_neu, MolMass, Vtau, VM, kv100f, kv210f;
		
//		txt_KV40.setValue(String.valueOf(kv40));
//		txt_KV100.setValue(String.valueOf(kv100));
//		txt_rhozero.setValue(String.valueOf(rhozero));
//		txt_alpharho.setValue(String.valueOf(alpharho));
		kv40 =  ((Number)txt_KV40.getValue()).doubleValue();
		kv100 = ((Number) txt_KV100.getValue()).doubleValue();
		rhozero = ((Number) txt_rhozero.getValue()).doubleValue();
		alpharho = ((Number) txt_alpharho.getValue()).doubleValue();
		
		lubtemp =((Number) txt_LUBTEMP.getValue()).doubleValue();
		rhoT = rhozero+alpharho*(lubtemp-15);
		K = Math.exp(Math.log(kv40*rhoT)+(13.0*(Math.log(kv100*rhoT)-Math.log(kv40*rhoT))/4.0));
	ZyrolaGui.r_K = K;
		B = -1755.0*(Math.log(kv100*rhoT)-Math.log(kv40*rhoT))/4;
	ZyrolaGui.r_B = B;
		C = 95.0;
	ZyrolaGui.r_C = C;
		etazero = K*Math.exp(B/(lubtemp+C));
	ZyrolaGui.r_etaZero = etazero;
	
	
		txt_dichte_oel.setValue(rhoT);
		txt_visk_40.setValue(kv40);
		txt_visk_t.setValue(etazero/rhoT);
		
		p_ref = 200.0; // [MPa]
		a1 = ((Number)txt_fva400_a1.getValue()).doubleValue();
		a2 = ((Number)txt_fva400_a2.getValue()).doubleValue();
		b1 = ((Number)txt_fva400_b1.getValue()).doubleValue();
		b2 = ((Number)txt_fva400_b2.getValue()).doubleValue();
		alphap = (1.0/(a1+a2*lubtemp+(b1+b2*lubtemp)*p_ref*10.0));
		ZyrolaGui.r_alphaP = alphap;
		
		eta_tp = etazero*Math.exp(alphap*p_ref*10.0);
		
		
		alphat = Math.log((K*Math.exp(B/(lubtemp+50+C))*Math.exp(p_ref*10.0*alphap))/eta_tp)/(lubtemp-(lubtemp+50));
		ZyrolaGui.r_alphaT = alphat;
	
		
		
		kv100f = K*Math.exp(B/(37.78+C))/rhoT;
		kv210f = K*Math.exp(B/(98.89+C))/rhoT;
		H100 = 870.*Math.log10(Math.log10(kv100f+0.6))+154.0;
		H210 = 870.*Math.log10(Math.log10(kv210f+0.6))+154.0;
		VSF = H100 - H210;
		// S = 4.146-1.733*Math.log10(VSF-145.0);
		S_neu = 3.562-0.01129*VSF-0.00001857*Math.pow(VSF,2)+0.00000006843*Math.pow(VSF,3);
		MolMass = 180.0+S_neu*(H100+60.0);
		
		VM = MolMass/1000.0/(rhoT*1000.0);
		Vtau = VM/2.0/6.0221E+23;
		taueyr = 1.38066E-23*(lubtemp+273.15)/Vtau/1000000.0;
		
		ftf_R_tauRheo.setValue(taueyr);
		
		if (rad_Rodermund.isSelected()) {
			eta_tp	= Double.valueOf(txt_visk_t.getText())*rhoT;

		}

//		KONTROLLAUSGABEN		
//		String msg = String.valueOf(kv100f) + ' ' + String.valueOf(kv210f) + ' ' + String.valueOf(MolMass);
//		MainFrame.sb.message(msg);
	}


	
	
	

	
	private static void setGesperrt () { 
		txt_dichte_oel.setEditable(false); 
		txt_av.setEditable(false); 
		txt_bv.setEditable(false);
		txt_cv.setEditable(false);
		txt_pr.setEditable(false);
		txt_dr.setEditable(false);
		txt_er.setEditable(false);
		ftf_R_tauRheo.setEditable(false);
		txt_fva400_a1.setEditable(false); 
		txt_fva400_b1.setEditable(false); 
		txt_fva400_a2.setEditable(false); 
		txt_fva400_b2.setEditable(false);
		txt_KV40.setEditable(false);
		txt_KV100.setEditable(false);
		txt_rhozero.setEditable(false);
		txt_alpharho.setEditable(false);
		txt_visk_t.setEditable(false);
		Btn_beiwerte.setVisible(false);
	}
	
	private static void setFrei () {
		txt_av.setEditable(true); 
		txt_bv.setEditable(true);
		txt_cv.setEditable(true);
		txt_pr.setEditable(true);
		txt_dr.setEditable(true);
		txt_er.setEditable(true);
		ftf_R_tauRheo.setEditable(true);
		txt_fva400_a1.setEditable(true); 
		txt_fva400_b1.setEditable(true); 
		txt_fva400_a2.setEditable(true); 
		txt_fva400_b2.setEditable(true);
		txt_KV40.setEditable(true);
		txt_KV100.setEditable(true);
		txt_rhozero.setEditable(true);
		txt_alpharho.setEditable(true);
		
		Btn_beiwerte.setVisible(true);
		txt_visk_t.setEditable(true);
		txt_dichte_oel.setEditable(true);
		Beiwerte();
		
		
	}
	
	public static void setMischreibung(ActionEvent ev){
		String Oberflaechenguete = (String) box_mischreibung.getSelectedItem(); 

		if (Oberflaechenguete.equals("Standard")) {	
			txt_sigma.setEditable(false); 
			txt_bzh.setEditable(false);
			txt_czh.setEditable(false);
			txt_sigma.setValue(0.00035); 
			txt_bzh.setValue(1.3); 
			txt_czh.setValue(0.75); 
		} 
		
		if (Oberflaechenguete.equals("Behandelt")) {	
			txt_sigma.setEditable(false); 
			txt_bzh.setEditable(false);
			txt_czh.setEditable(false);
			txt_sigma.setValue(0.00010); 
			txt_bzh.setValue(2.1); 
			txt_czh.setValue(0.85); 
		} 
		
		if (Oberflaechenguete.equals("Optisch glatt")) {	
			txt_sigma.setEditable(false); 
			txt_bzh.setEditable(false);
			txt_czh.setEditable(false);
			txt_sigma.setValue(0.00001); 
			txt_bzh.setValue(4.0); 
			txt_czh.setValue(0.6); 
		} 
		
		if (Oberflaechenguete.equals("Benutzdefiniert")) {	
			txt_sigma.setEditable(true); 
			txt_bzh.setEditable(true);
			txt_czh.setEditable(true);
//			txt_sigma.setValue(0.00035); 
//			txt_bzh.setValue(1.3); 
//			txt_czh.setValue(0.75); 
		} 
	}

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		
		
 	 if (source == race_f_EHD) {
 		ZyrolaGui.r_race_f_EHD =  race_f_EHD.getSelectedIndex();
 		setracelub();
 		
 	}else if (source == race_lub_mod) {
 		ZyrolaGui.r_race_lub_mod =  race_lub_mod.getSelectedIndex()+1;
 		setracelub();
 		
 	} else if ((source == rad_Fva400) || (source ==  rad_Rodermund)) {
 		
 		
 		
		if(rad_Fva400.isSelected()) {
			panel2_2_2.setVisible(true);
			panel2_2_3.setVisible(false);
			panel_2_2_5.setVisible(false);
			Beiwerte();
			
			
		
		}
		if (rad_Rodermund.isSelected()){
			panel2_2_3.setVisible(true);
			panel2_2_2.setVisible(false);
			panel_2_2_5.setVisible(true);
			
		
			
		}

 	}
 		
		
	}


	


 //Listener

	// Werte für Variablenübergabe in ZyRoLAGUI. sollen überschreiben werden 
	@Override
	public void componentShown(ComponentEvent e) {
		Object source = e.getSource();
		if (source == panel2_2_2){

			
			
			
 
			ZyrolaGui.r_a1 = ((Number) txt_fva400_a1.getValue()).doubleValue();			
 			ZyrolaGui.r_a2 = ((Number) txt_fva400_a2.getValue()).doubleValue();
 			ZyrolaGui.r_b1 = ((Number) txt_fva400_b1.getValue()).doubleValue();
 			ZyrolaGui.r_b2 = ((Number) txt_fva400_b2.getValue()).doubleValue();
 			ZyrolaGui.r_rhoZero = ((Number) txt_rhozero.getValue()).doubleValue();
			
	
 			
			
 			
		}else if (source == panel2_2_3){


 		
 			ZyrolaGui.r_AV = ((Number) txt_av.getValue()).doubleValue(); 		
 			ZyrolaGui.r_Bv = ((Number) txt_bv.getValue()).doubleValue();		
 			ZyrolaGui.r_CV = ((Number) txt_cv.getValue()).doubleValue();
 			ZyrolaGui.r_DR = ((Number) txt_dr.getValue()).doubleValue();
 			ZyrolaGui.r_ER = ((Number) txt_er.getValue()).doubleValue();
 			ZyrolaGui.r_pR = ((Number) txt_pr.getValue()).doubleValue();
			
		}else if (source == panel_2_2_5){
			

 		
 			ZyrolaGui.r_etaZero = ((Number) ftf_etazero.getValue()).doubleValue();
 			ZyrolaGui.r_alphaT = ((Number) ftf_alphaT.getValue()).doubleValue();		
 			ZyrolaGui.r_alphaP = ((Number) ftf_alphaP.getValue()).doubleValue(); 		
 			ZyrolaGui.r_lambdaZero = ((Number) ftf_lambdazero.getValue()).doubleValue(); 		
 			ZyrolaGui.r_alphaLambda = ((Number) ftf_alphalambda.getValue()).doubleValue();
			 
		}
		
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent e) {
			Object source = e.getSource(); 
			

			if (source == txt_vel_s){
	 			ZyrolaGui.r_vel_s = ((Number) txt_vel_s.getValue()).doubleValue();  
	 		} else if (source == txt_vel_d){
	 			ZyrolaGui.r_vel_d = ((Number) txt_vel_d.getValue()).doubleValue();
	 		} else if (source == txt_mu_s){
	 			ZyrolaGui.r_mu_s = ((Number) txt_mu_s.getValue()).doubleValue();
	 		} else if (source == txt_mu_d){
	 			ZyrolaGui.r_mu_d = ((Number) txt_mu_d.getValue()).doubleValue();
	 		} else if (source == txt_LUBTEMP){
	 			ZyrolaGui.r_lubtmp = ((Number) txt_LUBTEMP.getValue()).doubleValue();
	 		} else if (source == ftf_etazero){
	 			ZyrolaGui.r_etaZero = ((Number) ftf_etazero.getValue()).doubleValue();
	 			
	 		} else if (source == ftf_alphaT){
	 			ZyrolaGui.r_alphaT = ((Number) ftf_alphaT.getValue()).doubleValue();
	 		} else if (source == ftf_alphaP){
	 			ZyrolaGui.r_alphaP = ((Number) ftf_alphaP.getValue()).doubleValue();
	 		} else if (source == ftf_lambdazero){
	 			ZyrolaGui.r_lambdaZero = ((Number) ftf_lambdazero.getValue()).doubleValue();
	 		} else if (source == ftf_alphalambda){
	 			ZyrolaGui.r_alphaLambda = ((Number) ftf_alphalambda.getValue()).doubleValue();
/*C1 & C2 sind konstanten */
	 //		} else if (source == ftf_R_C1){
	 //			ZyRoLaGUI.r_C1 = ((Number) ftf_R_C1.getValue()).doubleValue();
	 //		} else if (source == ftf_R_C2){
	 //			ZyRoLaGUI.r_C2 = ((Number) ftf_R_C2.getValue()).doubleValue();
	 		} else if (source == txt_av){
	 			ZyrolaGui.r_AV = ((Number) txt_av.getValue()).doubleValue();
	 		} else if (source == txt_bv){
	 			ZyrolaGui.r_Bv = ((Number) txt_bv.getValue()).doubleValue();
	 		} else if (source == txt_cv){
	 			ZyrolaGui.r_CV = ((Number) txt_cv.getValue()).doubleValue();
	 		} else if (source == txt_dr){
	 			ZyrolaGui.r_DR = ((Number) txt_dr.getValue()).doubleValue();
	 		} else if (source == txt_er){
	 			ZyrolaGui.r_ER = ((Number) txt_er.getValue()).doubleValue();
	 		} else if (source == txt_pr){
	 			ZyrolaGui.r_pR = ((Number) txt_pr.getValue()).doubleValue();
	 	//	} else if (source == ftf_R_B){
	 	//		ZyRoLaGUI.r_B = ((Number) ftf_R_B.getValue()).doubleValue();
	 	//	} else if (source == ftf_R_C){
	 	//		ZyRoLaGUI.r_C = ((Number) ftf_R_C.getValue()).doubleValue();
	 	//	} else if (source == ftf_R_K){
	 	//		ZyRoLaGUI.r_K = ((Number) ftf_R_K.getValue()).doubleValue();
	 		} else if (source == txt_fva400_a1){
	 			ZyrolaGui.r_a1 = ((Number) txt_fva400_a1.getValue()).doubleValue();
	 		
	 		} else if (source == txt_fva400_a2){
	 			ZyrolaGui.r_a2 = ((Number) txt_fva400_a2.getValue()).doubleValue();
	 		} else if (source == txt_fva400_b1){
	 			ZyrolaGui.r_b1 = ((Number) txt_fva400_b1.getValue()).doubleValue();
	 		} else if (source == txt_fva400_b2){
	 			ZyrolaGui.r_b2 = ((Number) txt_fva400_b2.getValue()).doubleValue();
	 		} else if (source == txt_rhozero){
	 			ZyrolaGui.r_rhoZero = ((Number) txt_rhozero.getValue()).doubleValue();
	 		} else if (source == ftf_R_tauRheo){
	 			ZyrolaGui.r_tauRheo = ((Number) ftf_R_tauRheo.getValue()).doubleValue();
	 		} else if (source == txt_sigma){
	 			ZyrolaGui.r_sigma = ((Number) txt_sigma.getValue()).doubleValue();
	 		} else if (source == txt_bzh){
	 			ZyrolaGui.r_BZH = ((Number) txt_bzh.getValue()).doubleValue();
	 		} else if (source == txt_czh){
	 			ZyrolaGui.r_CZH = ((Number) txt_czh.getValue()).doubleValue();
	 	//	} else if (source == ftf_R_alphaHys){
	 	//		ZyRoLaGUI.r_alphaHys = ((Number) ftf_R_alphaHys.getValue()).doubleValue();
	 	//	} else if (source == ftf_R_scaleHys){
	 	//		ZyRoLaGUI.r_scaleHys = ((Number) ftf_R_scaleHys.getValue()).doubleValue();
	 		} else if (source == txt_CR){
	 			ZyrolaGui.r_cr = ((Number) txt_CR.getValue()).doubleValue();
	 		} else if (source == txt_expR){
	 			ZyrolaGui.r_rexp = ((Number) txt_expR.getValue()).doubleValue();
	 		} 
	 		
	 		
	 			else if (source == ftf_kaefig_vel_s){
	 			ZyrolaGui.r_kaefig_vel_s = ((Number) ftf_kaefig_vel_s.getValue()).doubleValue();  
	 		} else if (source == ftf_kaefig_vel_d){
	 			ZyrolaGui.r_kaefig_vel_d = ((Number) ftf_kaefig_vel_d.getValue()).doubleValue();
	 		} else if (source == ftf_kaefig_mu_s){
	 			ZyrolaGui.r_kaefig_mu_s = ((Number) ftf_kaefig_mu_s.getValue()).doubleValue();
	 		} else if (source == ftf_kaefig_mu_d){
	 			ZyrolaGui.r_kaefig_mu_d = ((Number) ftf_kaefig_mu_d.getValue()).doubleValue();
	 		}	else if (source == ftf_bord_vel_s){
	 			ZyrolaGui.r_bord_vel_s = ((Number) ftf_bord_vel_s.getValue()).doubleValue();  
	 		} else if (source == ftf_bord_vel_d){
	 			ZyrolaGui.r_bord_vel_d = ((Number) ftf_bord_vel_d.getValue()).doubleValue();
	 		} else if (source == ftf_bord_mu_s){
	 			ZyrolaGui.r_bord_mu_s = ((Number) ftf_bord_mu_s.getValue()).doubleValue();
	 		} else if (source == ftf_bord_mu_d){
	 			ZyrolaGui.r_bord_mu_d = ((Number) ftf_bord_mu_d.getValue()).doubleValue();	
	 		}
	 	}
	
	
	
	public static void setFVA3 () {
		setGesperrt ();
		
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8776);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(94.02);
		txt_KV100.setValue(10.09);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(361.0);
		txt_fva400_a2.setValue	(2.775); 
		txt_fva400_b1.setValue	(6.6530e-03); 
		txt_fva400_b2.setValue	(3.3610e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setFVA2 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8692);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(31.21);
		txt_KV100.setValue(5.25);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(243.1);
		txt_fva400_a2.setValue	(5.39); 
		txt_fva400_b1.setValue	(4.0190e-02); 
		txt_fva400_b2.setValue	(-1.130e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	private static void setFVA2a () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8938);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(154.59);
		txt_KV100.setValue(20);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(224.4);
		txt_fva400_a2.setValue	(4.565); 
		txt_fva400_b1.setValue	(42.040e-03); 
		txt_fva400_b2.setValue	(-2.8370e-05);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	private static void setFVA2b () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8901);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(120.75);
		txt_KV100.setValue(16.66);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(276.90);
		txt_fva400_a2.setValue	(3.724); 
		txt_fva400_b1.setValue	(48.810e-03); 
		txt_fva400_b2.setValue	(-5.66e-05);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	private static void setHVI () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8941);
		txt_alpharho.setValue(-5.89E-04);
		txt_KV40.setValue(213.07);
		txt_KV100.setValue(18.53);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(227.7);
		txt_fva400_a2.setValue	(4.022); 
		txt_fva400_b1.setValue	(55.330e-03); 
		txt_fva400_b2.setValue	(-3.05e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	private static void setPAO_E_pur () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8382);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(26.88);
		txt_KV100.setValue(5.82);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(357.9);
		txt_fva400_a2.setValue	(5.91); 
		txt_fva400_b1.setValue	(60.14e-03); 
		txt_fva400_b2.setValue	(-1.046e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setPAO_E_PMA1 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8939);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(104.75);
		txt_KV100.setValue(16.37);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(571.7);
		txt_fva400_a2.setValue	(-2.013); 
		txt_fva400_b1.setValue	(-46.66e-03); 
		txt_fva400_b2.setValue	(35.22e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setPAO_E_PAO100 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8452);
		txt_alpharho.setValue(-5.00E-04);
		txt_KV40.setValue(132.74);
		txt_KV100.setValue(20.32);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(426.4);
		txt_fva400_a2.setValue	(8.686); 
		txt_fva400_b1.setValue	(1.886e-03); 
		txt_fva400_b2.setValue	(1.644e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setPAO_E_PIB () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8486);
		txt_alpharho.setValue(-5.00E-04);
		txt_KV40.setValue(147.19);
		txt_KV100.setValue(21.31);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(270.4);
		txt_fva400_a2.setValue	(8.399); 
		txt_fva400_b1.setValue	(57.94e-03); 
		txt_fva400_b2.setValue	(-5.921e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setPAO_E_Additiv () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8525);
		txt_alpharho.setValue(-6.34E-04);
		txt_KV40.setValue(27.67);
		txt_KV100.setValue(5.8);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue(498.6);
		txt_fva400_a2.setValue(4.281); 
		txt_fva400_b1.setValue(27.09e-03); 
		txt_fva400_b2.setValue(1.796e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setPAO_E_Additiv_PMA1 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8939);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(119.4);
		txt_KV100.setValue(19.85);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(361.6);
		txt_fva400_a2.setValue	(3.544); 
		txt_fva400_b1.setValue	(55.47e-03); 
		txt_fva400_b2.setValue	(-6.858e-05);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setPG_1_1_AO () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(1.056);
		txt_alpharho.setValue(-7.24E-04);
		txt_KV40.setValue(223.64);
		txt_KV100.setValue(41.23);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(816.4);
		txt_fva400_a2.setValue	(2.992); 
		txt_fva400_b1.setValue	(-29.4e-03); 
		txt_fva400_b2.setValue	(8.971e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setPG_0_1 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(1.0019);
		txt_alpharho.setValue(-7.73E-04);
		txt_KV40.setValue(232.26);
		txt_KV100.setValue(41.71);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(425.2);
		txt_fva400_a2.setValue	(3.941); 
		txt_fva400_b1.setValue	(-5.492e-03); 
		txt_fva400_b2.setValue	(4.176e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setPG_kom1 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(1.0593);
		txt_alpharho.setValue(-7.85E-04);
		txt_KV40.setValue(233.59);
		txt_KV100.setValue(40.74);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(472.6);
		txt_fva400_a2.setValue	(5.892); 
		txt_fva400_b1.setValue	(47.97e-03); 
		txt_fva400_b2.setValue	(-2.494e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setPG_kom2 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(1.0001);
		txt_alpharho.setValue(-4.23E-04);
		txt_KV40.setValue(234.29);
		txt_KV100.setValue(36.14);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(280.5);
		txt_fva400_a2.setValue	(7.264); 
		txt_fva400_b1.setValue	(9.375e-03); 
		txt_fva400_b2.setValue	(-5.991e-05);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setPTI () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8939);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(142.77);
		txt_KV100.setValue(18.18);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(459.8);
		txt_fva400_a2.setValue	(0.5052); 
		txt_fva400_b1.setValue	(18.39e-03); 
		txt_fva400_b2.setValue	(15.05e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setPTI_Additiv () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.9255);
		txt_alpharho.setValue(-6.48E-04);
		txt_KV40.setValue(136.19);
		txt_KV100.setValue(24.11);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(329.7);
		txt_fva400_a2.setValue	(6.51); 
		txt_fva400_b1.setValue	(38.48e-03); 
		txt_fva400_b2.setValue	(1.746e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setDIA_KE () {
		setGesperrt ();
		rad_Fva400.doClick();
		txt_rhozero.setValue(0.9273);
		txt_alpharho.setValue(-7.00E-04);
		txt_KV40.setValue(114.26);
		txt_KV100.setValue(24.51);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(208.4);
		txt_fva400_a2.setValue	(6.75); 
		txt_fva400_b1.setValue	(1.061e-01); 
		txt_fva400_b2.setValue	(-1.019e-03);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setDIA_PMA1 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.9288);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(65.12);
		txt_KV100.setValue(18.04);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(319.0);
		txt_fva400_a2.setValue	(8.738); 
		txt_fva400_b1.setValue	(28.43e-03); 
		txt_fva400_b2.setValue	(-2.633e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setKE () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.9609);
		txt_alpharho.setValue(-6.97E-04);
		txt_KV40.setValue(317.57);
		txt_KV100.setValue(36.44);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(617.7);
		txt_fva400_a2.setValue	(1.306); 
		txt_fva400_b1.setValue	(-10.26e-03); 
		txt_fva400_b2.setValue	(7.996e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setKE_Additiv () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.9625);
		txt_alpharho.setValue(-7.16E-04);
		txt_KV40.setValue(294.44);
		txt_KV100.setValue(33.42);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(357.9);
		txt_fva400_a2.setValue	(4.151); 
		txt_fva400_b1.setValue	(55.17e-03); 
		txt_fva400_b2.setValue	(-5.286e-05);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setHC () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8253);
		txt_alpharho.setValue(-6.14E-04);
		txt_KV40.setValue(24.52);
		txt_KV100.setValue(5.38);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(477.7);
		txt_fva400_a2.setValue	(4.901); 
		txt_fva400_b1.setValue	(29.21e-03); 
		txt_fva400_b2.setValue	(2.886e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setHC_PMA1 () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8939);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(106.13);
		txt_KV100.setValue(17.19);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(411.5);
		txt_fva400_a2.setValue	(4.797); 
		txt_fva400_b1.setValue	(36.1e-03); 
		txt_fva400_b2.setValue	(4.055e-05);
		rad_Fva400.doClick();
		Beiwerte();
	}
	
	
	private static void setHC_Additiv () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8939);
		txt_alpharho.setValue(-6.00E-04);
		txt_KV40.setValue(25.1);
		txt_KV100.setValue(5.22);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.0); 
		txt_bv.setValue(0.0);
		txt_cv.setValue(0.0);
		txt_pr.setValue(0.0);
		txt_dr.setValue(0.0);
		txt_er.setValue(0.0);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(380.1);
		txt_fva400_a2.setValue	(7.349); 
		txt_fva400_b1.setValue	(60.14e-03); 
		txt_fva400_b2.setValue	(-3.33e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}


	public static void setHC_PMA1_Additiv () {
		setGesperrt ();
		rad_Rodermund.setVisible(false);
		txt_rhozero.setValue(0.8936);
		txt_alpharho.setValue(-5.89E-04);
		txt_KV40.setValue(118.47);
		txt_KV100.setValue(19.86);
		txt_vel_s.setValue(5.0); 
		txt_vel_d.setValue(10.0); 
		txt_mu_s.setValue(0.1); 
		txt_mu_d.setValue(0.08); 
		txt_CR.setValue(0.005); 
		txt_expR.setValue(0.65); 
		txt_av.setValue(0.028447); 
		txt_bv.setValue(1235.573);
		txt_cv.setValue(115.0);
		txt_pr.setValue(2050.0);
		txt_dr.setValue(0.509074);
		txt_er.setValue(0.00888);
		ftf_R_tauRheo.setValue(8.0);
		txt_alphasys.setValue(0.007); 
		txt_fva400_a1.setValue	(437);
		txt_fva400_a2.setValue	(3.96); 
		txt_fva400_b1.setValue	(27.21e-03); 
		txt_fva400_b2.setValue	(1.594e-04);
		rad_Fva400.doClick();
		Beiwerte();
	}

	// Component Listenermethoden die nicht gebraucht werden
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



}

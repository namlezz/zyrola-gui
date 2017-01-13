package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ZyRoLa_GUI.ZyrolaGui;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })

public class Tab_parameter extends JPanel implements PropertyChangeListener, ActionListener {

	public JLabel lbl_IR_BORD_B, lbl_IR_BORD_D, lbl_AR_BORD_B, lbl_AR_BORD_D,lbl_IR_BORD_Oew,lbl_AR_BORD_Oew;
	public static JFormattedTextField ftf_WK_Z, ftf_WK_D, ftf_WK_E, ftf_WK_N, ftf_WK_v, ftf_WK_rho,ftf_WK_radBsp,ftf_WK_axBsp;

	public static JFormattedTextField ftf_IR_I_D, ftf_IR_LFB_D, ftf_IR_LFB_B, ftf_IR_BORD_D, ftf_IR_BORD_B,ftf_IR_BORD_Oew, ftf_IR_E,
									ftf_IR_v, ftf_IR_rho;

	public static JFormattedTextField ftf_AR_A_D, ftf_AR_LFB_D, ftf_AR_LFB_B, ftf_AR_BORD_D,ftf_AR_BORD_Oew, ftf_AR_BORD_B, ftf_AR_E,
			ftf_AR_v, ftf_AR_rho;

	public static JFormattedTextField ftf_K_D_aussen, ftf_K_D_tasche, ftf_K_E, ftf_K_D_innen, ftf_K_Breite, ftf_K_v,
			ftf_K_rho, ftf_K_DickeSteg;

	public static JFormattedTextField ftf_S_cf_p_max_LB, ftf_S_no_gauss_LB, ftf_S_cf_pnts_LB;

	public static JComboBox box_ctctype_LB;
	// public String [] ctctyp = {"AST/Tripp", "Tripp approx.", "DIN ISO 281",
	// "Hertz"};
	public String[] ctctyp = { "Tripp approx.", "DIN ISO 281", "Kunert" };

	public static JComboBox box_kaefige;
	public String[] ktyp = { "virtueller Käfig", "Taschenfederkäfig", "Massivkäfig" };

	public static JComboBox box_kaefige_mod;
	public String[] kmod = { "M", "MA", "MB" };

	public static JComboBox box_lagertyp;
	public String[] ltyp = { "NU", "N", "NJ", "NUP" };

	public static JComboBox box_protype;
	public String[] ptyp = { "1 - Zylindrisches Profil", "2 - Kreisprofil", "3 - DIN ISO 281",
			"4 - Kreisprofil w/Rundungsradius", "5 - Zylindrisches Profil w/Kantenradius" };

	public static JPanel kaefig_panel, kaefigJN_panel;
	public static JLabel lbl_kaefig_breite, lbl_K_D_aussen, lbl_K_D_tasche, lbl_K_E, lbl_K_D_innen, lbl_K_Breite,
						lbl_K_v, lbl_K_rho, lbl_K_DickeSteg;

	public static JLabel 				lbl_K_tspiel_tan, lbl_K_tspiel_rad, lbl_K_tspiel_ax, lbl_kaefig_mod, lbl_K1_4;
	public static JFormattedTextField ftf_K_tspiel_tan, ftf_K_tspiel_rad, ftf_K_tspiel_ax;

	public static JLabel lbl_syslager, lbl_einzellager, lbl_S_EL_drehzahl, lbl_S_EL_F_ax, lbl_S_EL_F_rad, lbl_S_EL_simt,
						lbl_S_EL_simfreq, lbl_S_EL_simrampent, lbl_S_SL_IR_omega, lbl_S_SL_AR_omega;;

	public static JFormattedTextField ftf_S_EL_drehzahl, ftf_S_EL_F_ax, ftf_S_EL_F_rad, ftf_S_EL_simt, ftf_S_EL_simfreq,
										ftf_S_EL_simrampent, ftf_S_SL_IR_omega, ftf_S_SL_AR_omega;
	public static JRadioButton SButton;
	public static JRadioButton EButton;

	public static JLabel lbl_K1_5, lbl_maxFE;
	public static JFormattedTextField ftf_maxFE;

	public static JCheckBox chk_kippen, chk_brdkrft;
	public static JCheckBox chk_ascii, chk_flexAR;

	public Tab_parameter() {
		
		
		
		
		
		
		
		
		setLayout(new GridLayout(2, 3, 0, 0));

		// Außenring Panel
		JPanel P_Aussenring = new JPanel();
		P_Aussenring.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Aussenring",
				TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new Color(0, 0, 0)));
		this.add(P_Aussenring);
		P_Aussenring.setLayout(new GridLayout(10, 2, 5, 5));

		JLabel lbl_AR_A_D = new JLabel("Aussendurchmesser (mm)");
		P_Aussenring.add(lbl_AR_A_D);

		ftf_AR_A_D = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_A_D.addPropertyChangeListener(this);
		ftf_AR_A_D.setColumns(10);
		ftf_AR_A_D.setValue(0.0);
		lbl_AR_A_D.setLabelFor(ftf_AR_A_D);
		P_Aussenring.add(ftf_AR_A_D);

		JLabel lbl_AR_LFB_D = new JLabel("Laufbahndurchmesser (mm)");
		P_Aussenring.add(lbl_AR_LFB_D);

		ftf_AR_LFB_D = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_LFB_D.addPropertyChangeListener(this);
		ftf_AR_LFB_D.setColumns(10);
		ftf_AR_LFB_D.setValue(0.0);
		lbl_AR_LFB_D.setLabelFor(ftf_AR_LFB_D);
		P_Aussenring.add(ftf_AR_LFB_D);

		JLabel lbl_AR_LFB_B = new JLabel("Laufbahnbreite (mm)");
		P_Aussenring.add(lbl_AR_LFB_B);

		ftf_AR_LFB_B = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_LFB_B.addPropertyChangeListener(this);
		ftf_AR_LFB_B.setColumns(10);
		ftf_AR_LFB_B.setValue(0.0);
		lbl_AR_LFB_B.setLabelFor(ftf_AR_LFB_B);
		P_Aussenring.add(ftf_AR_LFB_B);

		JLabel lbl_AR_E = new JLabel("E-Modul (N/mm\u00B2)");
		P_Aussenring.add(lbl_AR_E);

		ftf_AR_E = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_E.addPropertyChangeListener(this);
		ftf_AR_E.setColumns(10);
		ftf_AR_E.setValue(0.0);
		lbl_AR_E.setLabelFor(ftf_AR_E);
		P_Aussenring.add(ftf_AR_E);

		JLabel lbl_AR_v = new JLabel("Querkontraktionszahl (-)");
		P_Aussenring.add(lbl_AR_v);

		ftf_AR_v = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_v.addPropertyChangeListener(this);
		ftf_AR_v.setColumns(10);
		ftf_AR_v.setValue(0.0);
		lbl_AR_v.setLabelFor(ftf_AR_v);
		P_Aussenring.add(ftf_AR_v);

		JLabel lbl_AR_rho = new JLabel("Dichte (kg/m³)");
		P_Aussenring.add(lbl_AR_rho);

		ftf_AR_rho = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_rho.addPropertyChangeListener(this);
		ftf_AR_rho.setColumns(10);
		ftf_AR_rho.setValue(0.0);
		lbl_AR_rho.setLabelFor(ftf_AR_rho);
		P_Aussenring.add(ftf_AR_rho);

		JLabel lblLagertyp = new JLabel("Lagertyp");
		P_Aussenring.add(lblLagertyp);

		box_lagertyp = new JComboBox(ltyp);
		box_lagertyp.setSelectedIndex(-1);
		box_lagertyp.addActionListener(this);
		P_Aussenring.add(box_lagertyp);

		lbl_AR_BORD_D = new JLabel("Borddurchmesser (mm)");
		P_Aussenring.add(lbl_AR_BORD_D);

		ftf_AR_BORD_D = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_BORD_D.addPropertyChangeListener(this);
		ftf_AR_BORD_D.setColumns(10);
		ftf_AR_BORD_D.setValue(0.0);
		lbl_AR_BORD_D.setLabelFor(ftf_AR_BORD_D);
		P_Aussenring.add(ftf_AR_BORD_D);

		lbl_AR_BORD_B = new JLabel("Bordbreite (mm)");
		P_Aussenring.add(lbl_AR_BORD_B);

		ftf_AR_BORD_B = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_BORD_B.addPropertyChangeListener(this);
		ftf_AR_BORD_B.setColumns(10);
		ftf_AR_BORD_B.setValue(0.0);
		lbl_AR_BORD_B.setLabelFor(ftf_AR_BORD_B);
		P_Aussenring.add(ftf_AR_BORD_B);
		
		lbl_AR_BORD_Oew = new JLabel("Bordöffnungswinkel (°)");
		P_Aussenring.add(lbl_AR_BORD_Oew);

		ftf_AR_BORD_Oew = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_AR_BORD_Oew.addPropertyChangeListener(this);
		ftf_AR_BORD_Oew.setColumns(10);
		ftf_AR_BORD_Oew.setValue(0.0);
		lbl_AR_BORD_Oew.setLabelFor(ftf_AR_BORD_Oew);
		P_Aussenring.add(ftf_AR_BORD_Oew);

		// Combobox einstellungen
		ftf_AR_BORD_D.setVisible(false);
		lbl_AR_BORD_D.setVisible(false);
		ftf_AR_BORD_B.setVisible(false);
		lbl_AR_BORD_B.setVisible(false);
		ftf_AR_BORD_Oew.setVisible(false);
		lbl_AR_BORD_Oew.setVisible(false);

		//////// Innering Panel
		JPanel P_Innenring = new JPanel();
		P_Innenring.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Innenring",
				TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), null));
		this.add(P_Innenring);
		P_Innenring.setLayout(new GridLayout(10, 2, 5, 5));

		JLabel lbl_IR_I_D = new JLabel("Innendurchmesser (mm)");
		P_Innenring.add(lbl_IR_I_D);

		ftf_IR_I_D = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_I_D.addPropertyChangeListener(this);
		ftf_IR_I_D.setColumns(10);
		ftf_IR_I_D.setValue(0.0);
		lbl_IR_I_D.setLabelFor(ftf_IR_I_D);
		P_Innenring.add(ftf_IR_I_D);

		JLabel lbl_IR_LFB_D = new JLabel("Laufbahndurchmesser (mm)");
		P_Innenring.add(lbl_IR_LFB_D);

		ftf_IR_LFB_D = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_LFB_D.addPropertyChangeListener(this);
		ftf_IR_LFB_D.setColumns(10);
		ftf_IR_LFB_D.setValue(0.0);
		lbl_IR_LFB_D.setLabelFor(ftf_IR_LFB_D);
		P_Innenring.add(ftf_IR_LFB_D);

		JLabel lbl_IR_LFB_B = new JLabel("Laufbahnbreite (mm)");
		P_Innenring.add(lbl_IR_LFB_B);

		ftf_IR_LFB_B = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_LFB_B.addPropertyChangeListener(this);
		ftf_IR_LFB_B.setColumns(10);
		ftf_IR_LFB_B.setValue(0.0);
		lbl_IR_LFB_B.setLabelFor(ftf_IR_LFB_B);
		P_Innenring.add(ftf_IR_LFB_B);

		JLabel lbl_IR_E = new JLabel("E-Modul (N/mm\u00B2)");
		P_Innenring.add(lbl_IR_E);

		ftf_IR_E = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_E.addPropertyChangeListener(this);
		ftf_IR_E.setColumns(10);
		ftf_IR_E.setValue(0.0);
		lbl_IR_E.setLabelFor(ftf_IR_E);
		P_Innenring.add(ftf_IR_E);

		JLabel lbl_IR_v = new JLabel("Querkontraktionszahl (-)");
		P_Innenring.add(lbl_IR_v);

		ftf_IR_v = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_v.addPropertyChangeListener(this);
		ftf_IR_v.setColumns(10);
		ftf_IR_v.setValue(0.0);
		lbl_IR_v.setLabelFor(ftf_IR_v);
		P_Innenring.add(ftf_IR_v);

		JLabel lbl_IR_rho = new JLabel("Dichte (kg/m³)");
		P_Innenring.add(lbl_IR_rho);

		ftf_IR_rho = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_rho.addPropertyChangeListener(this);
		ftf_IR_rho.setColumns(10);
		ftf_IR_rho.setValue(0.0);
		lbl_IR_rho.setLabelFor(ftf_IR_rho);
		P_Innenring.add(ftf_IR_rho);

		JLabel lbl_IR_p1 = new JLabel("");
		P_Innenring.add(lbl_IR_p1);

		JLabel lbl_IR_p2 = new JLabel("");
		P_Innenring.add(lbl_IR_p2);

		lbl_IR_BORD_D = new JLabel("Borddurchmesser (mm)");// ftf_IR_BORD_D,ftf_IR_BORD_B
		P_Innenring.add(lbl_IR_BORD_D);

		ftf_IR_BORD_D = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_BORD_D.addPropertyChangeListener(this);
		ftf_IR_BORD_D.setColumns(10);
		ftf_IR_BORD_D.setValue(0.0);
		lbl_IR_BORD_D.setLabelFor(ftf_IR_BORD_D);
		P_Innenring.add(ftf_IR_BORD_D);

		lbl_IR_BORD_B = new JLabel("Bordbreite (mm)");
		P_Innenring.add(lbl_IR_BORD_B);

		ftf_IR_BORD_B = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_BORD_B.addPropertyChangeListener(this);
		ftf_IR_BORD_B.setColumns(10);
		ftf_IR_BORD_B.setValue(0.0);
		lbl_IR_BORD_D.setLabelFor(ftf_IR_BORD_B);
		P_Innenring.add(ftf_IR_BORD_B);
		
		
		lbl_IR_BORD_Oew = new JLabel("Bordöffnungswinkel (°)");
		P_Innenring.add(lbl_IR_BORD_Oew);

		ftf_IR_BORD_Oew = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_IR_BORD_Oew.addPropertyChangeListener(this);
		ftf_IR_BORD_Oew.setColumns(10);
		ftf_IR_BORD_Oew.setValue(0.0);
		lbl_IR_BORD_Oew.setLabelFor(ftf_IR_BORD_Oew);
		P_Innenring.add(ftf_IR_BORD_Oew);
		
		ftf_IR_BORD_D.setVisible(false);
		lbl_IR_BORD_D.setVisible(false);
		ftf_IR_BORD_B.setVisible(false);
		lbl_IR_BORD_B.setVisible(false);
		ftf_IR_BORD_Oew.setVisible(false);
		lbl_IR_BORD_Oew.setVisible(false);

		// Wäzkörper Panel
		JPanel P_Waelzkoerper = new JPanel();
		P_Waelzkoerper
				.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "W\u00E4lzk\u00F6rper",
						TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), null));
		this.add(P_Waelzkoerper);
		P_Waelzkoerper.setLayout(new GridLayout(9, 2, 5, 5));

		JLabel lbl_D = new JLabel("Durchmesser (mm)");
		P_Waelzkoerper.add(lbl_D);

		ftf_WK_D = new JFormattedTextField(ZyrolaGui.p3Format);
		lbl_D.setLabelFor(ftf_WK_D);
		ftf_WK_D.setValue(0.0);
		ftf_WK_D.addPropertyChangeListener(this);
		ftf_WK_D.setColumns(10);
		P_Waelzkoerper.add(ftf_WK_D);

		JLabel lbl_z = new JLabel("L\u00E4nge (mm)");
		P_Waelzkoerper.add(lbl_z);

		ftf_WK_Z = new JFormattedTextField(ZyrolaGui.p3Format);
		lbl_z.setLabelFor(ftf_WK_Z);
		ftf_WK_Z.setColumns(10);
		ftf_WK_Z.setValue(0.0);
		ftf_WK_Z.addPropertyChangeListener(this);
		P_Waelzkoerper.add(ftf_WK_Z);

		JLabel lbl_N = new JLabel("Anzahl (-) ");
		P_Waelzkoerper.add(lbl_N);

		ftf_WK_N = new JFormattedTextField(ZyrolaGui.aFormat);
		lbl_D.setLabelFor(ftf_WK_N);
		ftf_WK_N.setValue(1);
		ftf_WK_N.addPropertyChangeListener(this);
		
		ftf_WK_N.setColumns(10);
		P_Waelzkoerper.add(ftf_WK_N);

		JLabel lbl_WK_E = new JLabel("E-Modul (N/mm\u00B2)");
		P_Waelzkoerper.add(lbl_WK_E);

		ftf_WK_E = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_WK_E.addPropertyChangeListener(this);
		ftf_WK_E.setColumns(10);
		ftf_WK_E.setValue(0.0);
		lbl_WK_E.setLabelFor(ftf_WK_E);
		P_Waelzkoerper.add(ftf_WK_E);

		JLabel lbl_WK_v = new JLabel("Querkontraktionszahl (-)");
		P_Waelzkoerper.add(lbl_WK_v);

		ftf_WK_v = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_WK_v.addPropertyChangeListener(this);
		ftf_WK_v.setColumns(10);
		ftf_WK_v.setValue(0.0);
		lbl_WK_v.setLabelFor(ftf_WK_v);
		P_Waelzkoerper.add(ftf_WK_v);

		JLabel lbl_WK_rho = new JLabel("Dichte (kg/m³)");
		P_Waelzkoerper.add(lbl_WK_rho);

		ftf_WK_rho = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_WK_rho.addPropertyChangeListener(this);
		ftf_WK_rho.setColumns(10);
		ftf_WK_rho.setValue(0.0);
		lbl_WK_rho.setLabelFor(ftf_WK_rho);
		P_Waelzkoerper.add(ftf_WK_rho);

		JLabel lblprotype = new JLabel("Profiltyp");
		P_Waelzkoerper.add(lblprotype);

		box_protype = new JComboBox(ptyp);
		box_protype.setSelectedIndex(2);
		box_protype.addActionListener(this);
		P_Waelzkoerper.add(box_protype);


		
		JLabel lbl_WK_radBsp = new JLabel("radiales Betriebsspiel (µm)");
		P_Waelzkoerper.add(lbl_WK_radBsp);

		ftf_WK_radBsp = new JFormattedTextField(ZyrolaGui.p3Format);		
		ftf_WK_radBsp.addPropertyChangeListener(this);
		ftf_WK_radBsp.setColumns(10);
		ftf_WK_radBsp.setValue(0.0);
		lbl_WK_radBsp.setLabelFor(ftf_WK_radBsp);
		P_Waelzkoerper.add(ftf_WK_radBsp);
		
		JLabel lbl_WK_axBsp = new JLabel("axiales Wälzkörperspiel (µm)");
		P_Waelzkoerper.add(lbl_WK_axBsp);

		ftf_WK_axBsp = new JFormattedTextField(ZyrolaGui.p3Format);		
		ftf_WK_axBsp.addPropertyChangeListener(this);
		ftf_WK_axBsp.setColumns(10);
		ftf_WK_axBsp.setValue(0.0);
		lbl_WK_axBsp.setLabelFor(ftf_WK_axBsp);
		P_Waelzkoerper.add(ftf_WK_axBsp);

		
		
		
		
		JPanel P_Kaefig = new JPanel();
		P_Kaefig.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "K\u00E4fig",
				TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), null));
		this.add(P_Kaefig);
		P_Kaefig.setLayout(new GridLayout(11, 2, 5, 5));

		JLabel lblKaefigtyp = new JLabel("Käfigtyp");
		P_Kaefig.add(lblKaefigtyp);

		box_kaefige = new JComboBox(ktyp);
		box_kaefige.setSelectedIndex(2);
		box_kaefige.addActionListener(this);

		P_Kaefig.add(box_kaefige);

		lbl_K_D_aussen = new JLabel("Aussendurchmesser (mm)");
		P_Kaefig.add(lbl_K_D_aussen);

		ftf_K_D_aussen = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_D_aussen.addPropertyChangeListener(this);
		ftf_K_D_aussen.setColumns(10);
		ftf_K_D_aussen.setValue(0.0);
		lbl_K_D_aussen.setLabelFor(ftf_K_D_aussen);
		P_Kaefig.add(ftf_K_D_aussen);

		lbl_K_D_innen = new JLabel("Innendurchmesser (mm)");
		P_Kaefig.add(lbl_K_D_innen);

		ftf_K_D_innen = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_D_innen.addPropertyChangeListener(this);
		ftf_K_D_innen.setColumns(10);
		ftf_K_D_innen.setValue(0.0);
		lbl_K_D_innen.setLabelFor(ftf_K_D_innen);
		P_Kaefig.add(ftf_K_D_innen);

		lbl_K_Breite = new JLabel("Käfigbreite (mm)");
		P_Kaefig.add(lbl_K_Breite);

		ftf_K_Breite = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_Breite.addPropertyChangeListener(this);
		ftf_K_Breite.setColumns(10);
		ftf_K_Breite.setValue(0.0);
		lbl_K_Breite.setLabelFor(ftf_K_Breite);
		P_Kaefig.add(ftf_K_Breite);

		lbl_K_DickeSteg = new JLabel("Dicke Steg (mm)");
		P_Kaefig.add(lbl_K_DickeSteg);

		ftf_K_DickeSteg = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_DickeSteg.addPropertyChangeListener(this);
		ftf_K_DickeSteg.setColumns(10);
		ftf_K_DickeSteg.setValue(0.0);
		lbl_K_DickeSteg.setLabelFor(ftf_K_DickeSteg);
		P_Kaefig.add(ftf_K_DickeSteg);

		lbl_K_rho = new JLabel("Dichte (kg/m³)");
		P_Kaefig.add(lbl_K_rho);

		ftf_K_rho = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_rho.addPropertyChangeListener(this);
		ftf_K_rho.setColumns(10);
		ftf_K_rho.setValue(0.0);
		P_Kaefig.add(ftf_K_rho);

		lbl_K_E = new JLabel("Elastizitätsmodul (N/mm²)");
		P_Kaefig.add(lbl_K_E);

		ftf_K_E = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_E.addPropertyChangeListener(this);
		ftf_K_E.setColumns(10);
		ftf_K_E.setValue(0.0);
		lbl_K_E.setLabelFor(ftf_K_E);
		P_Kaefig.add(ftf_K_E);

		lbl_K_v = new JLabel("Querkontraktionszahl (-)");
		P_Kaefig.add(lbl_K_v);

		ftf_K_v = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_v.addPropertyChangeListener(this);
		ftf_K_v.setColumns(10);
		ftf_K_v.setValue(0.0);
		((JLabel) lbl_K_v).setLabelFor(ftf_K_v);
		P_Kaefig.add(ftf_K_v);

		lbl_kaefig_mod = new JLabel("Käfigmodell:");
		P_Kaefig.add(lbl_kaefig_mod);
		/*
		 * JPanel kaefigmod = new JPanel(new GridLayout(1, 3, 5, 5)); Idee für
		 * Eingabe mit Buttons kmodGroup = new ButtonGroup();
		 * 
		 * TVPbtn = new JRadioButton("TVP"); TVPbtn.setFont(new Font("Tahoma",
		 * Font.PLAIN, 12)); kmodGroup.add(TVPbtn); kaefigmod.add(TVPbtn);
		 * 
		 * MAbtn = new JRadioButton("MA"); MAbtn.setFont(new Font("Tahoma",
		 * Font.PLAIN, 12)); kmodGroup.add(MAbtn); kaefigmod.add(MAbtn);
		 * 
		 * MBbtn = new JRadioButton("MP"); MBbtn.setFont(new Font("Tahoma",
		 * Font.PLAIN, 12)); kmodGroup.add(MBbtn); kaefigmod.add(MBbtn);
		 * 
		 * P_Kaefig.add(kaefigmod);
		 */
		box_kaefige_mod = new JComboBox(kmod);
		box_kaefige_mod.setSelectedIndex(0);
		box_kaefige_mod.addActionListener(this);
		P_Kaefig.add(box_kaefige_mod);

		lbl_K_D_tasche = new JLabel("Taschendurchmesser (mm)");
		P_Kaefig.add(lbl_K_D_tasche);

		ftf_K_D_tasche = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_D_tasche.addPropertyChangeListener(this);
		ftf_K_D_tasche.setColumns(10);
		ftf_K_D_tasche.setValue(0.0);
		lbl_K_D_tasche.setLabelFor(ftf_K_D_tasche);
		P_Kaefig.add(ftf_K_D_tasche);

		JLabel j = new JLabel();
		P_Kaefig.add(j);
		JLabel k = new JLabel();
		P_Kaefig.add(k);

		JPanel P_Optionen = new JPanel();
		P_Optionen.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Optionen",
				TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), null));
		P_Optionen.setLayout(new GridLayout(0, 2, 5, 5));
		this.add(P_Optionen);

		chk_brdkrft = new JCheckBox("Bordkr\u00E4fte", true);
		chk_brdkrft.setToolTipText("ASCII-Ausgabe für Simulationsvariablen aktivieren");
		chk_brdkrft.setHorizontalAlignment(SwingConstants.LEFT);
		chk_brdkrft.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chk_brdkrft.addActionListener(this);
		P_Optionen.add(chk_brdkrft);

		chk_kippen = new JCheckBox("Kippen/Schränken", true);
		chk_kippen.setToolTipText("ASCII-Ausgabe für Simulationsvariablen aktivieren");
		chk_kippen.setHorizontalAlignment(SwingConstants.LEFT);
		chk_kippen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chk_kippen.addActionListener(this);
		P_Optionen.add(chk_kippen);

		chk_ascii = new JCheckBox("ASCII-Ausgabe aktivieren", false);
		chk_ascii.setHorizontalAlignment(SwingConstants.LEFT);
		chk_ascii.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chk_ascii.addActionListener(this);
		P_Optionen.add(chk_ascii);

		chk_flexAR = new JCheckBox("Flexibler Aussenring");
		chk_flexAR.setHorizontalAlignment(SwingConstants.LEFT);
		chk_flexAR.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chk_flexAR.addActionListener(this);
		P_Optionen.add(chk_flexAR);

		EButton = new JRadioButton("Einzellager");
		EButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(EButton);
		P_Optionen.add(EButton);
		EButton.setSelected(true);
		EButton.addActionListener(this);

		SButton = new JRadioButton("Systemlager");
		SButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SButton.addActionListener(this);
		btnGrp.add(SButton);
		P_Optionen.add(SButton);

		lbl_syslager = new JLabel("Systemlagerparameter:");
		lbl_syslager.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_syslager.setVisible(false);
		P_Optionen.add(lbl_syslager);
		JLabel label_73 = new JLabel("");
		P_Optionen.add(label_73);

		lbl_S_SL_AR_omega = new JLabel("Drehzahl Aussenring (U/min)");
		P_Optionen.add(lbl_S_SL_AR_omega);

		ftf_S_SL_AR_omega = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_SL_AR_omega.addPropertyChangeListener(this);
		ftf_S_SL_AR_omega.setColumns(10);
		ftf_S_SL_AR_omega.setValue(0.0);
		lbl_S_SL_AR_omega.setLabelFor(ftf_S_SL_AR_omega);
		ftf_S_SL_AR_omega.setVisible(false);
		lbl_S_SL_AR_omega.setVisible(false);
		P_Optionen.add(ftf_S_SL_AR_omega);

		lbl_S_SL_IR_omega = new JLabel("Drehzahl Innenring (U/min)");
		P_Optionen.add(lbl_S_SL_IR_omega);

		ftf_S_SL_IR_omega = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_SL_IR_omega.addPropertyChangeListener(this);
		ftf_S_SL_IR_omega.setColumns(10);
		ftf_S_SL_IR_omega.setValue(0.0);
		lbl_S_SL_IR_omega.setLabelFor(ftf_S_SL_IR_omega);
		ftf_S_SL_IR_omega.setVisible(false);
		lbl_S_SL_IR_omega.setVisible(false);
		P_Optionen.add(ftf_S_SL_IR_omega);

		lbl_K1_4 = new JLabel("<html>Taschenfederkäfigparameter:</html>");
		lbl_K1_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_K1_4.setVisible(false);
		P_Optionen.add(lbl_K1_4);

		JLabel label_76 = new JLabel("");
		P_Optionen.add(label_76);

		lbl_K_tspiel_tan = new JLabel("tangentiales Taschenspiel (mm)");
		lbl_K_tspiel_tan.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Optionen.add(lbl_K_tspiel_tan);

		ftf_K_tspiel_tan = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_tspiel_tan.addPropertyChangeListener(this);
		ftf_K_tspiel_tan.setColumns(10);
		ftf_K_tspiel_tan.setValue(0.0);
		lbl_K_tspiel_tan.setLabelFor(ftf_K_tspiel_tan);
		ftf_K_tspiel_tan.setVisible(false);
		lbl_K_tspiel_tan.setVisible(false);
		P_Optionen.add(ftf_K_tspiel_tan);

		lbl_K_tspiel_rad = new JLabel("radiales Taschenspiel (mm)");
		lbl_K_tspiel_rad.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Optionen.add(lbl_K_tspiel_rad);

		ftf_K_tspiel_rad = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_tspiel_rad.addPropertyChangeListener(this);
		ftf_K_tspiel_rad.setColumns(10);
		ftf_K_tspiel_rad.setValue(0.0);
		lbl_K_tspiel_rad.setLabelFor(ftf_K_tspiel_rad);
		ftf_K_tspiel_rad.setVisible(false);
		lbl_K_tspiel_rad.setVisible(false);
		P_Optionen.add(ftf_K_tspiel_rad);

		lbl_K_tspiel_ax = new JLabel("axiales Taschenspiel (mm)");
		lbl_K_tspiel_ax.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Optionen.add(lbl_K_tspiel_ax);

		ftf_K_tspiel_ax = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_K_tspiel_ax.addPropertyChangeListener(this);
		ftf_K_tspiel_ax.setColumns(10);
		ftf_K_tspiel_ax.setValue(0.0);
		lbl_K_tspiel_ax.setLabelFor(ftf_K_tspiel_ax);
		ftf_K_tspiel_ax.setVisible(false);
		lbl_K_tspiel_ax.setVisible(false);
		P_Optionen.add(ftf_K_tspiel_ax);

		lbl_K1_5 = new JLabel("<html>FlexAR-Parameter:</html>");
		lbl_K1_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_K1_5.setVisible(false);
		P_Optionen.add(lbl_K1_5);

		JLabel label_78 = new JLabel("");
		P_Optionen.add(label_78);

		lbl_maxFE = new JLabel("Max. Kraft aus FE");
		lbl_maxFE.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_maxFE.setVisible(false);
		P_Optionen.add(lbl_maxFE);

		ftf_maxFE = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_maxFE.addPropertyChangeListener(this);
		ftf_maxFE.setColumns(10);
		ftf_maxFE.setValue(0.0);
		lbl_maxFE.setLabelFor(ftf_maxFE);
		ftf_maxFE.setVisible(false);
		ftf_maxFE.setVisible(false);
		P_Optionen.add(ftf_maxFE);

		JPanel P_Simulation = new JPanel();
		P_Simulation.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Simulation",
				TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), null));
		P_Simulation.setLayout(new GridLayout(11, 2, 5, 5));
		this.add(P_Simulation);

		JLabel lblctctype_LB = new JLabel("Contact-Type");
		P_Simulation.add(lblctctype_LB);

		box_ctctype_LB = new JComboBox(ctctyp);
		box_ctctype_LB.setSelectedIndex(0);
		box_ctctype_LB.addActionListener(this);
		lblctctype_LB.setLabelFor(box_ctctype_LB);
		P_Simulation.add(box_ctctype_LB);

		JLabel lbl_S_no_gauss_LB = new JLabel("<html>Gauss-Integrationspunkte</html>");

		P_Simulation.add(lbl_S_no_gauss_LB);

		ftf_S_no_gauss_LB = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_no_gauss_LB.addPropertyChangeListener(this);
		ftf_S_no_gauss_LB.setColumns(10);
		ftf_S_no_gauss_LB.setValue(5.0);
		lbl_S_no_gauss_LB.setLabelFor(ftf_S_no_gauss_LB);
		P_Simulation.add(ftf_S_no_gauss_LB);

		JLabel lbl_S_cf_pnts_LB = new JLabel("Anzahl der Curvefit-Punkte");
		P_Simulation.add(lbl_S_cf_pnts_LB);

		ftf_S_cf_pnts_LB = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_cf_pnts_LB.addPropertyChangeListener(this);
		ftf_S_cf_pnts_LB.setColumns(10);
		ftf_S_cf_pnts_LB.setValue(100.0);
		lbl_S_cf_pnts_LB.setLabelFor(ftf_S_cf_pnts_LB);
		P_Simulation.add(ftf_S_cf_pnts_LB);

		JLabel lbl_S_cf_p_max_LB = new JLabel("<html>  p<sub>max</sub>  für Curvefit</html>");

		P_Simulation.add(lbl_S_cf_p_max_LB);

		ftf_S_cf_p_max_LB = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_cf_p_max_LB.addPropertyChangeListener(this);
		ftf_S_cf_p_max_LB.setColumns(10);
		ftf_S_cf_p_max_LB.setValue(4000.0);
		lbl_S_cf_p_max_LB.setLabelFor(ftf_S_cf_p_max_LB);
		P_Simulation.add(ftf_S_cf_p_max_LB);

		lbl_einzellager = new JLabel("Einzellagerparameter:");
		lbl_einzellager.setFont(new Font("Tahoma", Font.BOLD, 11));
		P_Simulation.add(lbl_einzellager);
		JLabel label_80 = new JLabel("");
		P_Simulation.add(label_80);

		lbl_S_EL_drehzahl = new JLabel("Drehzahl (U/min)");
		lbl_S_EL_drehzahl.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Simulation.add(lbl_S_EL_drehzahl);

		ftf_S_EL_drehzahl = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_EL_drehzahl.addPropertyChangeListener(this);
		ftf_S_EL_drehzahl.setColumns(10);
		ftf_S_EL_drehzahl.setValue(0.0);
		lbl_S_EL_drehzahl.setLabelFor(ftf_S_EL_drehzahl);
		P_Simulation.add(ftf_S_EL_drehzahl);

		lbl_S_EL_F_ax = new JLabel("Axialkraft (N)");
		lbl_S_EL_F_ax.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Simulation.add(lbl_S_EL_F_ax);

		ftf_S_EL_F_ax = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_EL_F_ax.addPropertyChangeListener(this);
		ftf_S_EL_F_ax.setColumns(10);
		ftf_S_EL_F_ax.setValue(0.0);
		lbl_S_EL_F_ax.setLabelFor(ftf_S_EL_F_ax);
		P_Simulation.add(ftf_S_EL_F_ax);

		lbl_S_EL_F_rad = new JLabel("Radialkraft (N)");
		lbl_S_EL_F_rad.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Simulation.add(lbl_S_EL_F_rad);

		ftf_S_EL_F_rad = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_EL_F_rad.addPropertyChangeListener(this);
		ftf_S_EL_F_rad.setColumns(10);
		ftf_S_EL_F_rad.setValue(0.0);
		lbl_S_EL_F_rad.setLabelFor(ftf_S_EL_F_rad);
		P_Simulation.add(ftf_S_EL_F_rad);

		lbl_S_EL_simt = new JLabel("Simulationszeit (s)");
		lbl_S_EL_simt.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Simulation.add(lbl_S_EL_simt);

		ftf_S_EL_simt = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_EL_simt.addPropertyChangeListener(this);
		ftf_S_EL_simt.setColumns(10);
		ftf_S_EL_simt.setValue(0.0);
		lbl_S_EL_simt.setLabelFor(ftf_S_EL_simt);
		P_Simulation.add(ftf_S_EL_simt);

		lbl_S_EL_simfreq = new JLabel("Sampling-Frequenz (Hz)");
		lbl_S_EL_simfreq.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Simulation.add(lbl_S_EL_simfreq);

		ftf_S_EL_simfreq = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_EL_simfreq.addPropertyChangeListener(this);
		ftf_S_EL_simfreq.setColumns(10);
		ftf_S_EL_simfreq.setValue(0.0);
		lbl_S_EL_simfreq.setLabelFor(ftf_S_EL_simfreq);
		P_Simulation.add(ftf_S_EL_simfreq);

		lbl_S_EL_simrampent = new JLabel("Rampenzeit (s)");
		lbl_S_EL_simrampent.setHorizontalAlignment(SwingConstants.RIGHT);
		P_Simulation.add(lbl_S_EL_simrampent);

		ftf_S_EL_simrampent = new JFormattedTextField(ZyrolaGui.p3Format);
		ftf_S_EL_simrampent.addPropertyChangeListener(this);
		ftf_S_EL_simrampent.setColumns(10);
		ftf_S_EL_simrampent.setValue(0.0);
		lbl_S_EL_simrampent.setLabelFor(ftf_S_EL_simrampent);
		P_Simulation.add(ftf_S_EL_simrampent);

		
		
		
		// !!! Tooltipps

		ftf_AR_A_D.setToolTipText("Au\u00DFendurchmesser des Au\u00DFenrings");
		ftf_AR_LFB_B.setToolTipText("Breite des Au\u00DFenrings");
		ftf_AR_BORD_D.setToolTipText("Borddurchmesser des Au\u00DFenrings");
		ftf_AR_LFB_D.setToolTipText("Laufbahndurchmesser des Au\u00DFenrings");
		ftf_AR_BORD_B.setToolTipText("Bordbreite des Au\u00DFenrings");
		ftf_AR_BORD_Oew.setToolTipText("Bordöffnungswinkel des Au\u00DFenrings");
		ftf_AR_E.setToolTipText("E-Modul des Au\u00DFenrings");
		ftf_AR_v.setToolTipText("Querkontraktionszahl des Au\u00DFenrings");
		ftf_AR_rho.setToolTipText("Dichte des Au\u00DFenrings");
		box_lagertyp.setToolTipText("Auswahl Lagertyp");

		ftf_IR_BORD_D.setToolTipText("Borddurchmesser des Innenrings");
		ftf_IR_LFB_B.setToolTipText("Breite des Innenrings");
		ftf_IR_I_D.setToolTipText("Innendurchmesser des Innenrings");
		ftf_IR_BORD_Oew.setToolTipText("Bordöffnungswinkel des Innenrings");
		ftf_IR_LFB_D.setToolTipText("Laufbahndurchmesser des Innenrings");
		ftf_IR_BORD_B.setToolTipText("Bordbreite des Innenrings");
		ftf_IR_E.setToolTipText("E-Modul des Innenrings");
		ftf_IR_v.setToolTipText("Querkontraktionszahl des Innenrings");
		ftf_IR_rho.setToolTipText("Dichte des Innenrings");

		ftf_WK_D.setToolTipText("Durchmesser der W\u00E4lzk\u00F6rper");
		ftf_WK_N.setToolTipText("Anzahl der W\u00E4lzk\u00F6rper");
		ftf_WK_E.setToolTipText("E-Modul der W\u00E4lzk\u00F6rper");
		ftf_WK_v.setToolTipText("Querkontraktionszahl der W\u00E4lzk\u00F6rper");
		ftf_WK_rho.setToolTipText("Dichte der W\u00E4lzk\u00F6rper");
		ftf_WK_Z.setToolTipText("Geometrische L\u00E4nge der W\u00E4lzk\u00F6rpers");
		box_protype.setToolTipText("Auswahl des Profiltyps der Wälzkörper");
		ftf_WK_radBsp.setToolTipText("Radiales Betriebspiel der Wälzkörper");
		ftf_WK_axBsp.setToolTipText("Axiales Betriebspiel der Wälzkörper");

		ftf_K_Breite.setToolTipText("Breite des Käfigs");
		ftf_K_D_aussen.setToolTipText("Aussendurchmesser des Käfigs");
		ftf_K_D_innen.setToolTipText("Innendurchmesser des Käfigs");
		ftf_K_D_tasche.setToolTipText("Taschendurchmesser des  Käfigs");
		ftf_K_DickeSteg.setToolTipText("Dicke des Stegs/Blechdicke des Käfigs");
		ftf_K_E.setToolTipText("Elastizitätsmodul des Käfigs");
		ftf_K_rho.setToolTipText("Dichte des Käfigs");
		ftf_K_tspiel_ax.setToolTipText("Axiales Taschenspiel bei Taschenfederkäfig");
		ftf_K_tspiel_rad.setToolTipText("Radiales Taschenspiel bei Taschenfederkäfig");
		ftf_K_tspiel_tan.setToolTipText("Tangentiales Taschenspiel bei Taschenfederkäfig");
		ftf_K_v.setToolTipText("Querkontraktionszahl des Käfigs");
		box_kaefige.setToolTipText("Berechnungsmodell für Käfig auswählen");
		box_kaefige_mod.setToolTipText("Modelltyp des Käfigs auswählen (Zentral-/Aussenring-/Innenringgeführt)");

		chk_ascii.setToolTipText("ASCII-Ausgabe für Simulationsvariablen aktivieren");
		chk_brdkrft.setToolTipText("Lagermodell mit Berechnung der Bordkräfte erstellen");
		chk_kippen.setToolTipText("Kippen/Schränken der Wälzkörper zur Berechnung aktivieren");
		chk_flexAR.setToolTipText(	"<html>Berechnung mit flexiblem Au\u00DFenring durchführen. <br>Ben\u00F6tigt die Datei 'ARflex.afs', <br>in der das Verformungskennfeld hinterlegt ist.</html>");

		box_ctctype_LB.setToolTipText("Berechnungsmodell für WK/Laufbahn-Kontakt auswählen");
		ftf_S_cf_p_max_LB.setToolTipText("<html>Maximale Flächenpressung für Curvefit</html>");
		ftf_S_cf_pnts_LB.setToolTipText("");
		ftf_S_EL_drehzahl.setToolTipText("Innenringdrehzahl bei Einzellagersimulation");
		ftf_S_EL_F_ax.setToolTipText("Axiale Kraft bei Einzellagersimualtion");
		ftf_S_EL_F_rad.setToolTipText("Radiale Kraft bei Einzellagersimualtion");
		ftf_S_EL_simfreq.setToolTipText("Anzahl der Ausgabeschritte");
		ftf_S_EL_simrampent.setToolTipText(	"Dauer der Last- und Drehzahlrampe vorgeben, in der die Last/Drehzahl aufgebracht wird");
		ftf_S_EL_simt.setToolTipText("Dauer der Simulation angeben");
		ftf_S_no_gauss_LB.setToolTipText("<html>Anzahl der Gauss-Integrationspunkte</html>");
		ftf_S_SL_AR_omega.setToolTipText("<html>Startdrehzahl des Au\\u00DFenrings bei Systemsimulation vorgeben. <br>Muss den Startrandbedingungen des Systemmodells entsprechen.</html>");
		ftf_S_SL_IR_omega.setToolTipText("<html>Startdrehzahl des Innenrings bei Systemsimulation vorgeben. <br>Muss den Startrandbedingungen des Systemmodells entsprechen.</html>");

		EButton.setToolTipText("Lagermodell f\u00FCr Einzellagersimulation erstellen");
		SButton.setToolTipText("Lagermodell f\u00FCr Systemlagersimulation erstellen");

	

		
	}


	public static void updateLabel() {
		// Label Update
		if (ZyrolaGui.load_mod == 1) {
		
			ZyrolaGui.lblLagertyp.setVisible(true);
			try {
				ZyrolaGui.lagername = ZyrolaGui.lagername.substring(0, ZyrolaGui.lagername.indexOf("-"));
	
			} catch (StringIndexOutOfBoundsException e) {
			}
	
			if (ZyrolaGui.lagername.contains("NUP")) {
				ZyrolaGui.lagername = ZyrolaGui.lagername.replace("NUP", "");
			} else if (ZyrolaGui.lagername.contains("NU")) {
				ZyrolaGui.lagername = ZyrolaGui.lagername.replace("NU", "");
			} else if (ZyrolaGui.lagername.contains("NJ")) {
				ZyrolaGui.lagername = ZyrolaGui.lagername.replace("NJ", "");
			} else if (ZyrolaGui.lagername.contains("N")) {
				ZyrolaGui.lagername = ZyrolaGui.lagername.replace("N", "");
			} else if (ZyrolaGui.lagername.isEmpty()) {
				ZyrolaGui.lagername = "kein Lager ausgewählt";
			}
			if (ZyrolaGui.ltypindex == 0) {
				ZyrolaGui.lagername = "NU" + ZyrolaGui.lagername;
			} else if (ZyrolaGui.ltypindex == 1) {
				ZyrolaGui.lagername = "N" + ZyrolaGui.lagername;
			} else if (ZyrolaGui.ltypindex == 2) {
				ZyrolaGui.lagername = "NJ" + ZyrolaGui.lagername;
			} else if (ZyrolaGui.ltypindex == (3)) {
				ZyrolaGui.lagername = "NUP" + ZyrolaGui.lagername;
			} else if (ZyrolaGui.ltypindex == (-1)) {
				ZyrolaGui.lagername = "kein Lager ausgewählt";
			}
	
			switch (ZyrolaGui.kaefigindex) {
			case -1:
				break;
			case 0:
				ZyrolaGui.lagername = ZyrolaGui.lagername + "-VK";
				break;
			case 1:
				ZyrolaGui.lagername = ZyrolaGui.lagername + "-TFK";
				break;
			case 2:
				switch (box_kaefige_mod.getSelectedIndex()) {
				case 0:
					ZyrolaGui.lagername = ZyrolaGui.lagername + "-M";
					ZyrolaGui.mkaefigindex = 0;
					break;
				case 1:
					ZyrolaGui.lagername = ZyrolaGui.lagername + "-MA";
					ZyrolaGui.mkaefigindex = 1;
					break;
				case 2:
					ZyrolaGui.lagername = ZyrolaGui.lagername + "-MB";
					ZyrolaGui.mkaefigindex = 2;
					;
					break;
				}
			}
	
			// flexAR, ASCII, Bordkräfte, Kippen/Schränken
			// chk_kippen,chk_brdkrft,chk_ascii,chk_flexAR;
	
			if (chk_ascii.isSelected())
				ZyrolaGui.lagername = ZyrolaGui.lagername + "-ASCII";
			if (chk_flexAR.isSelected())
				ZyrolaGui.lagername = ZyrolaGui.lagername + "-flexAR";
			if (chk_kippen.isSelected())
				ZyrolaGui.lagername = ZyrolaGui.lagername + "-TILT";
			if (chk_brdkrft.isSelected())
				ZyrolaGui.lagername = ZyrolaGui.lagername + "-BORDF";
	
			ZyrolaGui.lblLagertyp.setText("Lagertyp: " + ZyrolaGui.lagername);
		}
	}

	public static void set_J_WK() {
		if (ZyrolaGui.kaefigindex == 0) { // virtueller Käfig
			if (!chk_brdkrft.isSelected()) {

				ZyrolaGui.wk_joint_def_transA = 2.0;
				ZyrolaGui.wk_joint_def_transB = 0.0;
				ZyrolaGui.wk_joint_def_transC = 0.0;

			} else if (chk_brdkrft.isSelected()) {

				ZyrolaGui.wk_joint_def_transA = 2.0;
				ZyrolaGui.wk_joint_def_transB = 3.0;
				ZyrolaGui.wk_joint_def_transC = 0.0;
			}

		} else if (ZyrolaGui.kaefigindex == 1) { // Taschenfederkäfig
			if (!chk_brdkrft.isSelected()) {

				ZyrolaGui.wk_joint_def_transA = 1.0;
				ZyrolaGui.wk_joint_def_transB = 2.0;
				ZyrolaGui.wk_joint_def_transC = 0.0;

			} else if (chk_brdkrft.isSelected()) {

				ZyrolaGui.wk_joint_def_transA = 1.0;
				ZyrolaGui.wk_joint_def_transB = 2.0;
				ZyrolaGui.wk_joint_def_transC = 3.0;

			}

		} else if (ZyrolaGui.kaefigindex == 2) { // Kontaktkäfig
			if (!chk_brdkrft.isSelected()) {

				ZyrolaGui.wk_joint_def_transA = 1.0;
				ZyrolaGui.wk_joint_def_transB = 2.0;
				ZyrolaGui.wk_joint_def_transC = 0.0;

			} else if (chk_brdkrft.isSelected()) {

				ZyrolaGui.wk_joint_def_transA = 1.0;
				ZyrolaGui.wk_joint_def_transB = 2.0;
				ZyrolaGui.wk_joint_def_transC = 3.0;
			}
		}

		// Variable für $J_Innenring_halter

		if (!chk_brdkrft.isSelected()) {
			ZyrolaGui.j_innenring_halter_joint_def_transz = 0.0;
		} else if (chk_brdkrft.isSelected()) {
			ZyrolaGui.j_innenring_halter_joint_def_transz = 3.0;
		}
	}

	// KÄFIGPARAMETER berechnen

	public static void set_kaefig_param() {

		int mod = box_kaefige_mod.getSelectedIndex(); // 0 = TVP (mitte) , 1 =
														// MA
														// (Aussenringgeführt) ,
														// 2 = MB
														// (Innenringgeführt)

		double tkd, d_k_a, d_k_i, k_b,l_arc;
		int wk_n;
		tkd = (((Number) ftf_AR_LFB_D.getValue()).doubleValue() + ((Number) ftf_IR_LFB_D.getValue()).doubleValue()) / 2;
		d_k_a = tkd + 0.3 * ((Number) ftf_WK_D.getValue()).doubleValue();
		d_k_i = tkd - 0.3 * ((Number) ftf_WK_D.getValue()).doubleValue();
		k_b = ((Number) ftf_WK_Z.getValue()).doubleValue() * 1.05;

		if (mod == 1) {
			d_k_a = ((Number) ftf_AR_BORD_D.getValue()).doubleValue() - 0.3;
			d_k_i = tkd + 0.3;

		} else if (mod == 2) {
			d_k_i = ((Number) ftf_IR_BORD_D.getValue()).doubleValue() + 0.3;
			d_k_a = tkd - 0.3;
		}

		ftf_K_D_aussen.setValue(d_k_a);
		ftf_K_D_innen.setValue(d_k_i);
		ftf_K_Breite.setValue(k_b);
		
		
		wk_n = ((Number) ftf_WK_N.getValue()).intValue();		
		l_arc = 2.0*Math.PI/wk_n*(tkd/2);		
		ftf_K_DickeSteg.setValue((l_arc - ((Number) ftf_WK_D.getValue()).doubleValue()));
		
		//Taschendurchmesser
		
		double t_D = ((Number) ftf_WK_D.getValue()).doubleValue() *1.04; //Ref. to LambdaGUI
		
		ftf_K_D_tasche.setValue(t_D);

	}
	
	public static void setRadBetriebsspiel(){
		double tkd,wk_d,LL_MED,d_lfb_a,d_lfb_i;

		
		tkd = (((Number) ftf_AR_LFB_D.getValue()).doubleValue() + ((Number) ftf_IR_LFB_D.getValue()).doubleValue()) / 2;
		
		wk_d = ((Number) ftf_WK_D.getValue()).doubleValue();
		LL_MED = ((Number) ftf_WK_radBsp.getValue()).doubleValue();
		
		d_lfb_a	= tkd + wk_d+(LL_MED/2000.0);
		d_lfb_i = tkd - wk_d-(LL_MED/2000.0);
		
		ftf_AR_LFB_D.setValue(d_lfb_a);
		ftf_IR_LFB_D.setValue(d_lfb_i);
		
	}
	
	public static void setAxBetriebsspiel(){
		double ax_bsp,wk_z,ar_lfb_b,ir_lfb_b,bord_ar_b,bord_ir_b;

		ax_bsp 		= ((Number) ftf_WK_axBsp.getValue()).doubleValue()/1000;
		wk_z 		= ((Number) ftf_WK_Z.getValue()).doubleValue();
		ar_lfb_b	= ((Number) ftf_AR_LFB_B.getValue()).doubleValue();
		System.out.println(ar_lfb_b);
		ir_lfb_b	= ((Number) ftf_IR_LFB_B.getValue()).doubleValue();
		
		bord_ar_b = (ar_lfb_b - wk_z-ax_bsp)/2;
		//System.out.println("ax bsb ar:" + bord_ar_b);
		bord_ir_b = (ir_lfb_b - wk_z-ax_bsp)/2;
		//System.out.println("ax bsb ir:" + bord_ir_b);

		if (ZyrolaGui.load_mod == 1 ) ftf_AR_BORD_B.setValue(bord_ar_b);
		if (ZyrolaGui.load_mod == 1 ) ftf_IR_BORD_B.setValue(bord_ir_b);
		ZyrolaGui.ar_LFB_B_r = wk_z +ax_bsp;
		ZyrolaGui.ir_LFB_B_r = wk_z +ax_bsp;
		
		
	}
	
	
	public static void setWKProfilierung() {
		double wk_d,wk_z;
		double r_D[]	= {4,8,12,16,20,26,34,42,56,64,75,95,115,120};
		double r2[] 	= {0.7,0.7,1.0,1.2,1.2,1.3,1.4,1.7,2.1,2.4,2.6,3.2,3.8,4.3}; //kp
		double r1[]		= {0.4,0.6,0.7,0.8,1.0,1.1,1.3,1.6,2.0,2.3,2.5,3.1,3.7,4.2}; //rk

		
		wk_d = ((Number) ftf_WK_D.getValue()).doubleValue();
		wk_z = ((Number) ftf_WK_Z.getValue()).doubleValue();
		
		  
		   for (int i=(r2.length-1);i>0 ;i--){
			   if (wk_d < r_D[i]) {
				   ZyrolaGui.wk_pro_cp = wk_z- 2.0*r1[i];
				   ZyrolaGui.wk_pro_cp = Math.round(ZyrolaGui.wk_pro_cp*100.0)/100.0;
				   ZyrolaGui.wk_pro_kp = r2[i];
				   ZyrolaGui.wk_pro_rk = r1[i];
				   
			   }
		   }


		
	}

	public static void Clearftf() {

		ftf_AR_A_D.setValue(0.0);
		ftf_S_cf_p_max_LB.setValue(4000.0);
		ftf_AR_BORD_B.setValue(0.0);
		ftf_AR_BORD_Oew.setValue(0.0);
		ftf_S_cf_pnts_LB.setValue(100.0);
		ftf_AR_BORD_D.setValue(0.0);
		ftf_S_EL_drehzahl.setValue(0.0);
		ftf_AR_E.setValue(0.0);
		ftf_S_EL_F_ax.setValue(0.0);
		ftf_AR_LFB_B.setValue(0.0);
		ftf_S_EL_F_rad.setValue(0.0);
		ftf_AR_LFB_D.setValue(0.0);
		ftf_S_EL_simfreq.setValue(0.0);
		ftf_AR_rho.setValue(0.0);
		ftf_S_EL_simrampent.setValue(0.0);
		ftf_AR_v.setValue(0.0);
		ftf_S_EL_simt.setValue(0.0);
		ftf_IR_BORD_B.setValue(0.0);
		ftf_S_no_gauss_LB.setValue(5.0);
		ftf_IR_BORD_D.setValue(0.0);
		ftf_S_SL_AR_omega.setValue(0.0);
		ftf_IR_E.setValue(0.0);
		ftf_S_SL_IR_omega.setValue(0.0);
		ftf_IR_I_D.setValue(0.0);
		ftf_WK_D.setValue(0.0);
		ftf_IR_LFB_B.setValue(0.0);
		ftf_WK_E.setValue(0.0);
		ftf_IR_LFB_D.setValue(0.0);
		ftf_WK_N.setValue(1);
		ftf_IR_rho.setValue(0.0);
		ftf_WK_radBsp.setValue(0.0);
		ftf_WK_axBsp.setValue(0.0);

		ftf_IR_v.setValue(0.0);
		ftf_WK_rho.setValue(0.0);
		ftf_IR_BORD_Oew.setValue(0.0);
		ftf_K_Breite.setValue(0.0);
		ftf_WK_v.setValue(0.0);
		ftf_K_D_aussen.setValue(0.0);
		ftf_WK_Z.setValue(0.0);
		ftf_K_D_innen.setValue(0.0);
		ftf_K_D_tasche.setValue(0.0);
		ftf_K_DickeSteg.setValue(0.0);
		ftf_K_E.setValue(0.0);
		ftf_K_rho.setValue(0.0);
		ftf_K_tspiel_ax.setValue(0.0);
		ftf_K_tspiel_rad.setValue(0.0);
		ftf_K_tspiel_tan.setValue(0.0);
		ftf_K_v.setValue(0.0);
		
		chk_ascii.setSelected(false);
		chk_kippen.setSelected(false);
		chk_flexAR.setSelected(false);
		chk_brdkrft.setSelected(false);
		ZyrolaGui.lblLagertyp.setVisible(false);
	}

	// Action & Property Change Listener

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();

		if (source == box_lagertyp) { 
			ZyrolaGui.ltypindex = box_lagertyp.getSelectedIndex(); // 0 ="NU",
																	// 1= "N",
																	// 2= "NJ",
																	// 3 = "NUP"

			//Index für NJ-Lager für Bordforces
			if (ZyrolaGui.ltypindex == 2) ZyrolaGui.ir_num_contacts = 1; else ZyrolaGui.ir_num_contacts =2;
			
			if (ZyrolaGui.ltypindex == 0) {
				ftf_AR_BORD_D.setVisible(true);
				lbl_AR_BORD_D.setVisible(true);
				ftf_AR_BORD_B.setVisible(true);
				lbl_AR_BORD_B.setVisible(true);
				ftf_AR_BORD_Oew.setVisible(true);
				lbl_AR_BORD_Oew.setVisible(true);
				ftf_IR_BORD_D.setVisible(false);
				lbl_IR_BORD_D.setVisible(false);
				ftf_IR_BORD_B.setVisible(false);
				lbl_IR_BORD_B.setVisible(false);
				ftf_IR_BORD_Oew.setVisible(false);
				lbl_IR_BORD_Oew.setVisible(false);
				updateLabel();
			} else if (ZyrolaGui.ltypindex == 1) {
				ftf_AR_BORD_D.setVisible(false);
				lbl_AR_BORD_D.setVisible(false);
				ftf_AR_BORD_B.setVisible(false);
				lbl_AR_BORD_B.setVisible(false);
				ftf_AR_BORD_Oew.setVisible(false);
				lbl_AR_BORD_Oew.setVisible(false);
				ftf_IR_BORD_D.setVisible(true);
				lbl_IR_BORD_D.setVisible(true);
				ftf_IR_BORD_B.setVisible(true);
				lbl_IR_BORD_B.setVisible(true);
				ftf_IR_BORD_Oew.setVisible(true);
				lbl_IR_BORD_Oew.setVisible(true);
				updateLabel();
			} else if ((ZyrolaGui.ltypindex == 2) || (ZyrolaGui.ltypindex == 3)) {
				ftf_AR_BORD_D.setVisible(true);
				lbl_AR_BORD_D.setVisible(true);
				ftf_AR_BORD_B.setVisible(true);
				lbl_AR_BORD_B.setVisible(true);
				ftf_AR_BORD_Oew.setVisible(true);
				lbl_AR_BORD_Oew.setVisible(true);
				ftf_IR_BORD_D.setVisible(true);
				lbl_IR_BORD_D.setVisible(true);
				ftf_IR_BORD_B.setVisible(true);
				lbl_IR_BORD_B.setVisible(true);
				ftf_IR_BORD_Oew.setVisible(true);
				lbl_IR_BORD_Oew.setVisible(true);
				updateLabel();
			} else if (ZyrolaGui.ltypindex == (-1)) {
				ftf_AR_BORD_D.setVisible(false);
				lbl_AR_BORD_D.setVisible(false);
				ftf_AR_BORD_B.setVisible(false);
				lbl_AR_BORD_B.setVisible(false);
				ftf_AR_BORD_Oew.setVisible(false);
				lbl_AR_BORD_Oew.setVisible(false);
				ftf_IR_BORD_D.setVisible(false);
				lbl_IR_BORD_D.setVisible(false);
				ftf_IR_BORD_B.setVisible(false);
				lbl_IR_BORD_B.setVisible(false);
				ftf_IR_BORD_Oew.setVisible(false);
				lbl_IR_BORD_Oew.setVisible(false);
				updateLabel();
			}

		} else if (source == chk_ascii) {
			if (chk_ascii.isSelected()) {
				if (ZyrolaGui.s_lagermod == 0) {
					ZyrolaGui.ascii_ausgabe = 1;
				} else if (ZyrolaGui.s_lagermod == 1) {
					ZyrolaGui.ascii_ausgabe = 2;
				}
			}

			if (!chk_ascii.isSelected())
				ZyrolaGui.ascii_ausgabe = 0;
			updateLabel();

		} else if (source == chk_brdkrft) {

			if (chk_brdkrft.isSelected())
				ZyrolaGui.bordkrfte_index = 1;
			if (!chk_brdkrft.isSelected())
				ZyrolaGui.bordkrfte_index = 0;
			updateLabel();
			set_J_WK();

		} else if (source == chk_kippen) {
			if (!chk_kippen.isSelected()) {
				ZyrolaGui.s_kippen_index = 1;
				ZyrolaGui.wk_joint_def_rotA = 3.0;
				ZyrolaGui.wk_joint_def_rotB = 0.0;
				ZyrolaGui.wk_joint_def_rotC = 0.0;
			} else if (chk_kippen.isSelected()) {
				ZyrolaGui.s_kippen_index = 3;
				ZyrolaGui.wk_joint_def_rotA = 1.0;
				ZyrolaGui.wk_joint_def_rotB = 2.0;
				ZyrolaGui.wk_joint_def_rotC = 3.0;
			}
			updateLabel();
		} else if (source == chk_flexAR) {
			if (!chk_flexAR.isSelected()) {
				ZyrolaGui.p_afspath.setVisible(false);
				lbl_K1_5.setVisible(false);
				lbl_maxFE.setVisible(false);
				ftf_maxFE.setVisible(false);
				ZyrolaGui.ftf_afspath.setText(null);
				ZyrolaGui.flex_ar = 0;
				updateLabel();

			} else if (chk_flexAR.isSelected()) {
				ZyrolaGui.p_afspath.setVisible(true);
				lbl_K1_5.setVisible(true);
				lbl_maxFE.setVisible(true);
				ftf_maxFE.setVisible(true);
				ZyrolaGui.flex_ar = 1;
				ZyrolaGui.change_afspath();
				updateLabel();
			}

			

		} else if ((source == EButton) || (source == SButton)) {
			// S_lagermod: 0 = Einzellager , 1 = Systemlager
			if (EButton.isSelected()) {
				ZyrolaGui.s_lagermod = 0;
				lbl_syslager.setVisible(false);
				lbl_einzellager.setVisible(true);
				ftf_S_EL_drehzahl.setVisible(true);
				lbl_S_EL_drehzahl.setVisible(true);
				ftf_S_EL_F_ax.setVisible(true);
				lbl_S_EL_F_ax.setVisible(true);
				ftf_S_EL_F_rad.setVisible(true);
				lbl_S_EL_F_rad.setVisible(true);
				ftf_S_EL_simt.setVisible(true);
				lbl_S_EL_simt.setVisible(true);
				ftf_S_EL_simfreq.setVisible(true);
				lbl_S_EL_simfreq.setVisible(true);
				ftf_S_EL_simrampent.setVisible(true);
				lbl_S_EL_simrampent.setVisible(true);
				ftf_S_SL_IR_omega.setVisible(false);
				lbl_S_SL_IR_omega.setVisible(false);
				ftf_S_SL_AR_omega.setVisible(false);
				lbl_S_SL_AR_omega.setVisible(false);

				ZyrolaGui.sb.message(" ");
				updateLabel();

			} else if (SButton.isSelected()) {
				ZyrolaGui.s_lagermod = 1;
				lbl_syslager.setVisible(true);
				lbl_einzellager.setVisible(false);
				ftf_S_EL_drehzahl.setVisible(false);
				lbl_S_EL_drehzahl.setVisible(false);
				ftf_S_EL_F_ax.setVisible(false);
				lbl_S_EL_F_ax.setVisible(false);
				ftf_S_EL_F_rad.setVisible(false);
				lbl_S_EL_F_rad.setVisible(false);
				ftf_S_EL_simt.setVisible(false);
				lbl_S_EL_simt.setVisible(false);
				ftf_S_EL_simfreq.setVisible(false);
				lbl_S_EL_simfreq.setVisible(false);
				ftf_S_EL_simrampent.setVisible(false);
				lbl_S_EL_simrampent.setVisible(false);
				ftf_S_SL_IR_omega.setVisible(true);
				lbl_S_SL_IR_omega.setVisible(true);
				ftf_S_SL_AR_omega.setVisible(true);
				lbl_S_SL_AR_omega.setVisible(true);

				ZyrolaGui.sb.message("Welle für Systemlager erstellt?");
				updateLabel();
			}

		} else if (source == box_kaefige) {
			ZyrolaGui.kaefigindex = box_kaefige.getSelectedIndex();

			if (ZyrolaGui.kaefigindex == 0) { // virtueller Käfig

				ftf_K_D_aussen.setVisible(false);
				lbl_K_D_aussen.setVisible(false);
				ftf_K_D_innen.setVisible(false);
				lbl_K_D_innen.setVisible(false);
				ftf_K_Breite.setVisible(false);
				lbl_K_Breite.setVisible(false);
				ftf_K_D_tasche.setVisible(false);
				lbl_K_D_tasche.setVisible(false);
				ftf_K_E.setVisible(false);
				lbl_K_E.setVisible(false);
				ftf_K_v.setVisible(false);
				lbl_K_v.setVisible(false);
				ftf_K_rho.setVisible(false);
				lbl_K_rho.setVisible(false);
				ftf_K_DickeSteg.setVisible(false);
				lbl_K_DickeSteg.setVisible(false);
				box_kaefige_mod.setVisible(false);
				lbl_kaefig_mod.setVisible(false);

				ftf_K_tspiel_tan.setVisible(false);
				lbl_K_tspiel_tan.setVisible(false);
				ftf_K_tspiel_rad.setVisible(false);
				lbl_K_tspiel_rad.setVisible(false);
				ftf_K_tspiel_ax.setVisible(false);
				lbl_K_tspiel_ax.setVisible(false);
				lbl_K1_4.setVisible(false);

			} else if (ZyrolaGui.kaefigindex == 1) { // Taschenfederkäfig
				ftf_K_D_aussen.setVisible(true);
				lbl_K_D_aussen.setVisible(true);
				ftf_K_D_innen.setVisible(true);
				lbl_K_D_innen.setVisible(true);
				ftf_K_Breite.setVisible(true);
				lbl_K_Breite.setVisible(true);
				ftf_K_D_tasche.setVisible(false);
				lbl_K_D_tasche.setVisible(false);
				ftf_K_E.setVisible(false);
				lbl_K_E.setVisible(false);
				ftf_K_v.setVisible(false);
				lbl_K_v.setVisible(false);
				ftf_K_rho.setVisible(true);
				lbl_K_rho.setVisible(true);
				ftf_K_DickeSteg.setVisible(true);
				lbl_K_DickeSteg.setVisible(true);
				box_kaefige_mod.setVisible(false);
				lbl_kaefig_mod.setVisible(false);

				ftf_K_tspiel_tan.setVisible(true);
				lbl_K_tspiel_tan.setVisible(true);
				ftf_K_tspiel_rad.setVisible(true);
				lbl_K_tspiel_rad.setVisible(true);
				ftf_K_tspiel_ax.setVisible(true);
				lbl_K_tspiel_ax.setVisible(true);
				lbl_K1_4.setVisible(true);

			} else if (ZyrolaGui.kaefigindex == 2) { // Kontaktkäfig
				ftf_K_D_aussen.setVisible(true);
				lbl_K_D_aussen.setVisible(true);
				ftf_K_D_innen.setVisible(true);
				lbl_K_D_innen.setVisible(true);
				ftf_K_Breite.setVisible(true);
				lbl_K_Breite.setVisible(true);
				ftf_K_D_tasche.setVisible(true);
				lbl_K_D_tasche.setVisible(true);
				ftf_K_E.setVisible(true);
				lbl_K_E.setVisible(true);
				ftf_K_v.setVisible(true);
				lbl_K_v.setVisible(true);
				ftf_K_rho.setVisible(true);
				lbl_K_rho.setVisible(true);
				ftf_K_DickeSteg.setVisible(true);
				lbl_K_DickeSteg.setVisible(true);
				box_kaefige_mod.setVisible(true);
				lbl_kaefig_mod.setVisible(true);

				ftf_K_tspiel_tan.setVisible(false);
				lbl_K_tspiel_tan.setVisible(false);
				ftf_K_tspiel_rad.setVisible(false);
				lbl_K_tspiel_rad.setVisible(false);
				ftf_K_tspiel_ax.setVisible(false);
				lbl_K_tspiel_ax.setVisible(false);
				lbl_K1_4.setVisible(false);

			}
			set_kaefig_param();
			set_J_WK();
			updateLabel();
		} else if (source == box_kaefige_mod) {
			set_kaefig_param();
			updateLabel();

		} else if (source == box_protype) {
			ZyrolaGui.wk_protype = box_protype.getSelectedIndex() + 1;

		} else if (source == box_ctctype_LB) {
			switch (box_ctctype_LB.getSelectedIndex()) {
			case -1:
				break;
			case 0:
				ZyrolaGui.s_ctctype_LB = 2;
				break; // 2-"Tripp approx.",3-"DIN ISO 281", 5-"Kunert"
			case 1:
				ZyrolaGui.s_ctctype_LB = 3;
				break;
			case 2:
				ZyrolaGui.s_ctctype_LB = 5;
				break;
			}

		}
	}
	
	

	public void propertyChange(PropertyChangeEvent e) {
		String mindesb = "<html>Mindestbelastung für Zylinderrollenlager beachten! - P/C<sub>dyn</sub> ≥ 0.02</html>";
		
		Object source = e.getSource();
		String prop = e.getPropertyName();  
		
			if (prop == "value") {	
				if (source == ftf_WK_Z) {
					ZyrolaGui.wk_z = ((Number) ftf_WK_Z.getValue()).doubleValue();
					setWKProfilierung();
					setAxBetriebsspiel();
				} else if (source == ftf_WK_D) {
					ZyrolaGui.wk_D = ((Number) ftf_WK_D.getValue()).doubleValue();
					setWKProfilierung();
					if (ZyrolaGui.load_mod == 1 )setRadBetriebsspiel();
				} else if (source == ftf_WK_E) {
					ZyrolaGui.wk_E = ((Number) ftf_WK_E.getValue()).doubleValue();
				} else if (source == ftf_WK_v) {
					ZyrolaGui.wk_v = ((Number) ftf_WK_v.getValue()).doubleValue();
				} else if (source == ftf_WK_rho) {
					ZyrolaGui.wk_rho = ((Number) ftf_WK_rho.getValue()).doubleValue();
				} else if (source == ftf_WK_N) {
					ZyrolaGui.wk_N = ((Number) ftf_WK_N.getValue()).intValue();
					set_kaefig_param();		
				} else if (source == ftf_WK_radBsp) {
					ZyrolaGui.wk_radBsp = ((Number) ftf_WK_radBsp.getValue()).doubleValue();
					if (ZyrolaGui.load_mod == 1 )setRadBetriebsspiel();
				} else if (source == ftf_WK_axBsp) {
					ZyrolaGui.wk_axBsp = ((Number) ftf_WK_axBsp.getValue()).doubleValue();
					setAxBetriebsspiel();
					
					// Innenring-Panel
				} else if (source == ftf_IR_BORD_B) {
					ZyrolaGui.ir_BORD_B = ((Number) ftf_IR_BORD_B.getValue()).doubleValue();
				} else if (source == ftf_IR_BORD_Oew) {
					ZyrolaGui.ir_BORD_Oew = ((Number) ftf_IR_BORD_Oew.getValue()).doubleValue();
				} else if (source == ftf_IR_BORD_D) {
					ZyrolaGui.ir_BORD_D = ((Number) ftf_IR_BORD_D.getValue()).doubleValue();
				} else if (source == ftf_IR_I_D) {
					ZyrolaGui.ir_I_D = ((Number) ftf_IR_I_D.getValue()).doubleValue();
				} else if (source == ftf_IR_LFB_D) {
					ZyrolaGui.ir_LFB_D = ((Number) ftf_IR_LFB_D.getValue()).doubleValue();
				} else if (source == ftf_IR_LFB_B) {
					ZyrolaGui.ir_LFB_B = ((Number) ftf_IR_LFB_B.getValue()).doubleValue();
				} else if (source == ftf_IR_E) {
					ZyrolaGui.ir_E = ((Number) ftf_IR_E.getValue()).doubleValue();
				} else if (source == ftf_IR_v) {
					ZyrolaGui.ir_v = ((Number) ftf_IR_v.getValue()).doubleValue();
				} else if (source == ftf_IR_rho) {
					ZyrolaGui.ir_rho = ((Number) ftf_IR_rho.getValue()).doubleValue();
				}
		
				// Aussenring-Panel
				else if (source == ftf_AR_BORD_B) {
					ZyrolaGui.ar_BORD_B = ((Number) ftf_AR_BORD_B.getValue()).doubleValue();
				}else if (source == ftf_AR_BORD_Oew) {
						ZyrolaGui.ar_BORD_Oew = ((Number) ftf_AR_BORD_Oew.getValue()).doubleValue();
				} else if (source == ftf_AR_BORD_D) {
					ZyrolaGui.ar_BORD_D = ((Number) ftf_AR_BORD_D.getValue()).doubleValue();
				} else if (source == ftf_AR_LFB_D) {
					ZyrolaGui.ar_LFB_D = ((Number) ftf_AR_LFB_D.getValue()).doubleValue();
				} else if (source == ftf_AR_LFB_B) {
					ZyrolaGui.ar_LFB_B = ((Number) ftf_AR_LFB_B.getValue()).doubleValue();
				} else if (source == ftf_AR_A_D) {
					ZyrolaGui.ar_A_D = ((Number) ftf_AR_A_D.getValue()).doubleValue();
				} else if (source == ftf_AR_E) {
					ZyrolaGui.ar_E = ((Number) ftf_AR_E.getValue()).doubleValue();
				} else if (source == ftf_AR_v) {
					ZyrolaGui.ar_v = ((Number) ftf_AR_v.getValue()).doubleValue();
				} else if (source == ftf_AR_rho) {
					ZyrolaGui.ar_rho = ((Number) ftf_AR_rho.getValue()).doubleValue();
				}
		
				// Käfig-Panel
		
				else if (source == ftf_K_v) {
					ZyrolaGui.k_v = ((Number) ftf_K_v.getValue()).doubleValue();
				} else if (source == ftf_K_Breite) {
					ZyrolaGui.k_Breite = ((Number) ftf_K_Breite.getValue()).doubleValue();
				} else if (source == ftf_K_rho) {
					ZyrolaGui.k_rho = ((Number) ftf_K_rho.getValue()).doubleValue();
				} else if (source == ftf_K_D_aussen) {
					ZyrolaGui.k_D_aussen = ((Number) ftf_K_D_aussen.getValue()).doubleValue();
				} else if (source == ftf_K_D_tasche) {
					ZyrolaGui.k_D_tasche = ((Number) ftf_K_D_tasche.getValue()).doubleValue();
				} else if (source == ftf_K_E) {
					ZyrolaGui.k_E = ((Number) ftf_K_E.getValue()).doubleValue();
				} else if (source == ftf_K_D_innen) {
					ZyrolaGui.k_D_innen = ((Number) ftf_K_D_innen.getValue()).doubleValue();
				} else if (source == ftf_K_DickeSteg) {
					ZyrolaGui.k_DickeSteg = ((Number) ftf_K_DickeSteg.getValue()).doubleValue();
				} else if (source == ftf_K_tspiel_tan) {
					ZyrolaGui.k_tspiel_tan = ((Number) ftf_K_tspiel_tan.getValue()).doubleValue();
				} else if (source == ftf_K_tspiel_rad) {
					ZyrolaGui.k_tspiel_rad = ((Number) ftf_K_tspiel_rad.getValue()).doubleValue();
				} else if (source == ftf_K_tspiel_ax) {
					ZyrolaGui.k_tspiel_ax = ((Number) ftf_K_tspiel_ax.getValue()).doubleValue();
				}
		
				else if (source == ftf_maxFE) {
					ZyrolaGui.flexAR_maxFE = ((Number) ftf_maxFE.getValue()).doubleValue();
				}
		
				// Simulations Panel
				else if (source == ftf_S_cf_p_max_LB) {
					ZyrolaGui.s_cf_p_max_LB = ((Number) ftf_S_cf_p_max_LB.getValue()).doubleValue();
				} else if (source == ftf_S_cf_pnts_LB) {
					ZyrolaGui.s_cf_pnts_LB = ((Number) ftf_S_cf_pnts_LB.getValue()).doubleValue();
				} else if (source == ftf_S_no_gauss_LB) {
					ZyrolaGui.s_no_gauss_LB = ((Number) ftf_S_no_gauss_LB.getValue()).doubleValue();
				} else if (source == ftf_S_EL_drehzahl) {
					ZyrolaGui.s_EL_drehzahl = ((Number) ftf_S_EL_drehzahl.getValue()).doubleValue();
					ZyrolaGui.sb.message(mindesb);
				} else if (source == ftf_S_EL_F_ax) {
					ZyrolaGui.s_EL_F_ax = ((Number) ftf_S_EL_F_ax.getValue()).doubleValue();
					ZyrolaGui.sb.message(mindesb);
				} else if (source == ftf_S_EL_F_rad) {
					ZyrolaGui.s_EL_F_rad = (((Number) ftf_S_EL_F_rad.getValue()).doubleValue());
					ZyrolaGui.sb.message(mindesb);
				} else if (source == ftf_S_EL_simt) {
					ZyrolaGui.s_EL_simt = ((Number) ftf_S_EL_simt.getValue()).doubleValue();
				} else if (source == ftf_S_EL_simfreq) {
					ZyrolaGui.s_EL_simfreq = ((Number) ftf_S_EL_simfreq.getValue()).doubleValue();
				} else if (source == ftf_S_EL_simrampent) {
					ZyrolaGui.s_EL_simrampent = (((Number) ftf_S_EL_simrampent.getValue()).doubleValue());
				} else if (source == ftf_S_SL_IR_omega) {
					ZyrolaGui.s_SL_IR_omega = ((Number) ftf_S_SL_IR_omega.getValue()).doubleValue();
				} else if (source == ftf_S_SL_AR_omega) {
					ZyrolaGui.s_SL_AR_omega = (((Number) ftf_S_SL_AR_omega.getValue()).doubleValue());
				}
				
		}

	}




}

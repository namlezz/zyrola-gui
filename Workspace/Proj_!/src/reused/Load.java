package reused;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ZyRoLa_GUI.ZyrolaGui;
import frames.Tab_SR;
import frames.Tab_parameter;


@SuppressWarnings("unused")
public class Load {
	
	public static Double ParseDouble(String s) {
		double d =  Double.parseDouble(s);
		return d;		
	}
	public static int ParseInt(String s) {
		int i =  Integer.parseInt(s);
		return i;		
	}
	
	
	private static String subvar = "";

	@SuppressWarnings("resource")
	public static void open(File f) throws NumberFormatException {
		Tab_parameter.Clearftf();
		String dateiName = f.getName().substring(0, f.getName().lastIndexOf('.'));
		boolean dateinamemod = false;
		boolean errormod = false;

		
			 
		try {
			
			BufferedReader inputStream = new BufferedReader(new FileReader(f));
			String regex = "", c = "",hk =" '";
			
			subvar = "subvar.str";
			while ((c = inputStream.readLine()) != null) {	        
		       
				 
					if (c.contains("!Anzahl Wälzkörper:")) {
						String s = c.substring(c.lastIndexOf(":")+1,c.length()-1);
						s = s.trim();
						Tab_parameter.ftf_WK_N.setValue(ParseInt(s));
					}
					
					

					
					
			    	
			    	if(c.contains("Dateiname: ")) {
			    		if (c.contains("N")) 		Tab_parameter.box_lagertyp.setSelectedIndex(1);
			    		if (c.contains("NU")) 		Tab_parameter.box_lagertyp.setSelectedIndex(0);
			    		if (c.contains("NJ")) 		Tab_parameter.box_lagertyp.setSelectedIndex(2);
			    		if (c.contains("NUP")) 		Tab_parameter.box_lagertyp.setSelectedIndex(3);
			    		
			    		
			    		if (c.contains("-VK")) 		Tab_parameter.box_kaefige.setSelectedIndex(0);
			    		if (c.contains("-TFK"))		Tab_parameter.box_kaefige.setSelectedIndex(1);
			    		if (c.contains("-M"))	{	Tab_parameter.box_kaefige.setSelectedIndex(2);Tab_parameter.box_kaefige_mod.setSelectedIndex(0);}
			    		if (c.contains("-MA"))	{  	Tab_parameter.box_kaefige.setSelectedIndex(2);Tab_parameter.box_kaefige_mod.setSelectedIndex(1);}		
			    		if (c.contains("-MB"))	{	Tab_parameter.box_kaefige.setSelectedIndex(2);Tab_parameter.box_kaefige_mod.setSelectedIndex(2);}
			    		
			    		if (c.contains("-TILT"))  {Tab_parameter.chk_kippen.setSelected(true);} else {Tab_parameter.chk_kippen.setSelected(false);}
			    		if (c.contains("-ASCII")) {Tab_parameter.chk_ascii.setSelected(true);} else {Tab_parameter.chk_ascii.setSelected(false);}
			    	    if (c.contains("-flexAR"))	{Tab_parameter.chk_flexAR.setSelected(true);} else {Tab_parameter.chk_flexAR.setSelected(false);}
			    		if (c.contains("-BORDF"))	{Tab_parameter.chk_brdkrft.setSelected(true);} else {Tab_parameter.chk_flexAR.setSelected(false);}
			    		
			    		dateinamemod = true;
			    		
			    	}
					if(c.contains(subvar) && !c.contains("$G_WK_PositionsDaten") && !c.contains("$_ZyRoLA_AR_Dicke")) {
						try{
						if (c.contains(hk)){
				    		regex =  c.substring(c.lastIndexOf("=")+1,c.lastIndexOf("'"));
				    		regex = regex.replace("'", " ");
				    		regex = regex.trim();
				    		
				    		
						}else {
							regex =  c.substring(c.lastIndexOf("=")+1,c.length()-1);
							regex = regex.trim();
						}
						
			    	
			    	
				    
						if (c.contains("$_ZyRoLA_dREH_U"))  	   Tab_parameter.ftf_S_EL_drehzahl.setValue((ParseDouble(regex)*60/2/Math.PI));   
						if (c.contains("$_ZyRoLA_FA"))  	   Tab_parameter.ftf_S_EL_F_ax.setValue(ParseDouble(regex)); 
						if (c.contains("$_ZyRoLA_FR"))  	   Tab_parameter.ftf_S_EL_F_rad.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_simt"))  	   Tab_parameter.ftf_S_EL_simt.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_simfreq"))  	   Tab_parameter.ftf_S_EL_simfreq.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_simrampent"))  	   Tab_parameter.ftf_S_EL_simrampent.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLa_sim_dreh_AR"))  	   Tab_parameter.ftf_S_SL_AR_omega.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLa_sim_dreh_IR"))  	   Tab_parameter.ftf_S_SL_IR_omega.setValue(ParseDouble(regex));
						
						if (c.contains("$_ZyRoLA_ctctype_LB"))  {
									int i = ParseInt(regex);
									switch (i) {
									case 2:Tab_parameter.box_ctctype_LB.setSelectedIndex(0);break; // 2-"Tripp approx.",3-"DIN ISO 281", 5-"Kunert"
									case 3:Tab_parameter.box_ctctype_LB.setSelectedIndex(1);break;
									case 5:Tab_parameter.box_ctctype_LB.setSelectedIndex(2);break;
									}
		
						}
						if (c.contains("$_ZyRoLA_no_gauss_LB"))  	   Tab_parameter.ftf_S_no_gauss_LB.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_cf_pnts_LB"))  	   Tab_parameter.ftf_S_cf_pnts_LB.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_cf_p_max_LB"))  	   Tab_parameter.ftf_S_cf_p_max_LB.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_FE_NForce_flex	"))  	   Tab_parameter.ftf_maxFE.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_WK_D"))  	  		 Tab_parameter.ftf_WK_D.setValue((ParseDouble(regex)*1000));    
						if (c.contains("$_ZyRoLA_WK_z"))  	   Tab_parameter.ftf_WK_Z.setValue((ParseDouble(regex)*1000));   
						if (c.contains("$_ZyRoLA_AR_A_D"))  		   Tab_parameter.ftf_AR_A_D.setValue((ParseDouble(regex)*1000));   
						if (c.contains("$_ZyRoLA_AR_LFB_D"))  	   Tab_parameter.ftf_AR_LFB_D.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_AR_LFB_B") && !c.contains("$_ZyRoLA_AR_LFB_BRD"))  	   Tab_parameter.ftf_AR_LFB_B.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_AR_Bord_D"))  	   Tab_parameter.ftf_AR_BORD_D.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_AR_Bord_B"))  	   Tab_parameter.ftf_AR_BORD_B.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_IR_I_D"))  	   Tab_parameter.ftf_IR_I_D.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_IR_LFB_D"))  	   Tab_parameter.ftf_IR_LFB_D.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_IR_LFB_B") && !c.contains("$_ZyRoLA_IR_LFB_BRD"))  	   Tab_parameter.ftf_IR_LFB_B.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_IR_Bord_D"))  	   Tab_parameter.ftf_IR_BORD_D.setValue((ParseDouble(regex)*1000));  
						if (c.contains("$_ZyRoLA_IR_Bord_B"))  	   Tab_parameter.ftf_IR_BORD_B.setValue((ParseDouble(regex)*1000));
						if (c.contains("$_ZyRoLA_AR_oew"))  	   Tab_parameter.ftf_AR_BORD_Oew.setValue((ParseDouble(regex)));  
						if (c.contains("$_ZyRoLA_IR_oew"))  	   Tab_parameter.ftf_IR_BORD_Oew.setValue((ParseDouble(regex))); 
						if (c.contains("$_ZyRoLA_protype"))  	   Tab_parameter.box_protype.setSelectedIndex(ParseInt(regex)-1);
						if (c.contains("$_ZyRoLA_IR_E"))  	   Tab_parameter.ftf_IR_E.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_IR_v"))  	   Tab_parameter.ftf_IR_v.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_AR_E"))  	   Tab_parameter.ftf_AR_E.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_AR_v"))  	   Tab_parameter.ftf_AR_v.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_WK_E"))  	   Tab_parameter.ftf_WK_E.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_WK_v"))  	   Tab_parameter.ftf_WK_v.setValue(ParseDouble(regex));;
						if (c.contains("$_ZyRoLA_AR_rho")) 	   Tab_parameter.ftf_AR_rho.setValue(ParseDouble(regex));   
						if (c.contains("$_ZyRoLA_IR_rho")) 	   Tab_parameter.ftf_IR_rho.setValue(ParseDouble(regex));   
						if (c.contains("$_ZyRoLA_WK_rho")) 	   Tab_parameter.ftf_WK_rho.setValue(ParseDouble(regex));   
						if (c.contains("$_ZyRoLA_race_f_EHD"))  	   Tab_SR.race_f_EHD.setSelectedIndex(ParseInt(regex));
						if (c.contains("$_ZyRoLA_race_lub_mod"))  	   Tab_SR.race_lub_mod.setSelectedIndex(ParseInt(regex)-1);
						if (c.contains("$_ZyRoLA_vel_s"))  	   Tab_SR.txt_vel_s.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_vel_d"))  	   Tab_SR.txt_vel_d.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_mu_s"))  	   Tab_SR.txt_mu_s.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_mu_d"))  	   Tab_SR.txt_mu_d.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_lubtmp"))  	   Tab_SR.txt_LUBTEMP.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_etaZero"))  	   Tab_SR.ftf_etazero.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_alphaT"))  	   Tab_SR.ftf_alphaT.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_alphaP"))  	   Tab_SR.ftf_alphaP.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_lambdaZero"))  	   Tab_SR.ftf_lambdazero.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_alphaLambda"))  	   Tab_SR.ftf_alphalambda.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_AV"))  	   Tab_SR.txt_av.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_Bv"))  	   Tab_SR.txt_bv.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_CV"))  	   Tab_SR.txt_cv.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_DR"))  	   Tab_SR.txt_dr.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_ER"))  	   Tab_SR.txt_er.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_pR"))  	   Tab_SR.txt_pr.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_a1"))  	   Tab_SR.txt_fva400_a1.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_a2"))  	   Tab_SR.txt_fva400_a2.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_b1"))  	   Tab_SR.txt_fva400_b1.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_b2"))  	   Tab_SR.txt_fva400_b1.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_rhoZero"))  	   Tab_SR.txt_rhozero.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_tauRheo"))  	   Tab_SR.ftf_R_tauRheo.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_sigma"))  	   Tab_SR.txt_sigma.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_BZH"))  	   Tab_SR.txt_bzh.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_CZH"))  	   Tab_SR.txt_czh.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_cr"))  	   Tab_SR.txt_CR.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_rexp"))  	   Tab_SR.txt_expR.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_vel_s_BRD"))  	   Tab_SR.ftf_bord_vel_s.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_vel_d_BRD"))  	   Tab_SR.ftf_bord_vel_d.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_mu_s_BRD"))  	   Tab_SR.ftf_bord_mu_s.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_mu_d_BRD"))  	   Tab_SR.ftf_bord_mu_d.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_tspiel_tan"))  	   Tab_parameter.ftf_K_tspiel_tan.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_tspiel_rad"))  	   Tab_parameter.ftf_K_tspiel_rad.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_tspiel_ax"))  	   Tab_parameter.ftf_K_tspiel_ax.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_vel_s_KF"))  	   Tab_SR.ftf_kaefig_vel_s.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_vel_d_KF"))  	   Tab_SR.ftf_kaefig_vel_d.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_mu_s_KF"))  	   Tab_SR.ftf_kaefig_mu_s.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_mu_d_KF"))  	   Tab_SR.ftf_kaefig_mu_d.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_rho")) 	   Tab_parameter.ftf_K_rho.setValue(ParseDouble(regex));
						if (c.contains("$_ZyRoLA_K_D_aussen")) 	   Tab_parameter.ftf_K_D_aussen.setValue((ParseDouble(regex)*1000));    
						if (c.contains("$_ZyRoLA_K_D_innen")) 	   Tab_parameter.ftf_K_D_innen.setValue((ParseDouble(regex)*1000));    
						if (c.contains("$_ZyRoLA_K_Breite")) 	   Tab_parameter.ftf_K_Breite.setValue((ParseDouble(regex)*1000));    
						if (c.contains("$_ZyRoLA_K_DickeSteg"))	  	   Tab_parameter.ftf_K_DickeSteg.setValue((ParseDouble(regex)*1000));    
						if (c.contains("$_ZyRoLA_K_E")) 	   Tab_parameter.ftf_K_E.setValue(ParseDouble(regex)); 
						if (c.contains("$_ZyRoLA_K_v")) 	   Tab_parameter.ftf_K_v.setValue(ParseDouble(regex)); 
						if (c.contains("$_ZyRoLA_K_D_tasche")) 	   Tab_parameter.ftf_K_D_tasche.setValue((ParseDouble(regex)*1000));     
			    	
			    	
						} catch (NumberFormatException e) {
							e.printStackTrace();
							errormod = true;
						}	
				}

			
		    	
			}
	
			

			
			//Tab1.txt_wk_anzahl.setText(String.valueOf(DateiMethoden.z_Kugel).toString());        
		

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
	
		// Berechnung von rad. und ax. Betriebspiel
	
		double  d_a, d_i, tkd, wk_d, radbsp_a, radbsp_i, radbsp, wk_z , lfb_b;
	
		d_a =  ((Number) Tab_parameter.ftf_AR_LFB_D.getValue()).doubleValue();
		d_i =  ((Number) Tab_parameter.ftf_IR_LFB_D.getValue()).doubleValue();
		tkd = 	(d_a+d_i)/2;
		wk_d = ((Number) Tab_parameter.ftf_WK_D.getValue()).doubleValue();
		
		radbsp_a = (d_a - wk_d - tkd)/2;
		radbsp_i = (tkd - wk_d - d_i)/2;
		Tab_parameter.ftf_WK_radBsp.setValue((radbsp_a+radbsp_i)*1000);

		

		double ax_bsp_i, ax_bsp_a,ar_lfb_b,ir_lfb_b,bord_ar_b,bord_ir_b;

		wk_z 		= ((Number) 	Tab_parameter.ftf_WK_Z.getValue()).doubleValue();
		ar_lfb_b	= ((Number) 	Tab_parameter.ftf_AR_LFB_B.getValue()).doubleValue();
		ir_lfb_b	= ((Number) 	Tab_parameter.ftf_IR_LFB_B.getValue()).doubleValue();
		bord_ar_b	= ((Number) 	Tab_parameter.ftf_AR_BORD_B.getValue()).doubleValue();
		bord_ir_b	= ((Number) 	Tab_parameter.ftf_IR_BORD_B.getValue()).doubleValue();
		

		ax_bsp_i	=  ar_lfb_b - 2*bord_ir_b -wk_z; 
		ax_bsp_a	=  ar_lfb_b - 2*bord_ar_b -wk_z;

		Tab_parameter.ftf_WK_axBsp.setValue((ax_bsp_a+ax_bsp_i)/2*1000);


		ZyrolaGui.ar_LFB_B_r = wk_z +ax_bsp_a;
		ZyrolaGui.ir_LFB_B_r = wk_z +ax_bsp_i;
		

		
    	ZyrolaGui.sb.message(f.toString() + " gelesen.");
    	if (errormod) ZyrolaGui.sb.message("Fehler - Einige Variablen konnten nicht eingelesen werden.");
    	if (!dateinamemod) ZyrolaGui.sb.message(f.toString() + " konnte nicht komplett gelesen werden da Datei im Simpack geändert wurde. Checkboxen/Buttons manuell neu einstellen. ");
		ZyrolaGui.chckbxmntmManuelleEingabe.setSelected(true);
		ZyrolaGui.chckbxmntmManuelleEingabe.doClick();
		

		ZyrolaGui.lblLagertyp.setText("Lagertyp: " +dateiName);
		ZyrolaGui.lagername = dateiName;
		ZyrolaGui.lblLagertyp.setVisible(true);
		
		

		
		
	}
		
	

	
	@SuppressWarnings("resource")
	public static String getSimpackIni(File f) { 
		
		String param = null;
		
		try {
			
			BufferedReader inputStream = new BufferedReader(new FileReader(f));
			String c = "";	
			subvar = " subvar.str  (              ";
			Tab_parameter.Clearftf();
		    
			while ((c = inputStream.readLine()) != null) {	        
				if(c.contains("simpackUser")) {
					param=c.substring(c.indexOf("=")+1, c.length());
				}
		    }  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return param;
	}
	
	
	
//TODO [Fatal Error] NJ_2212_ECP.XML:1:7: Verarbeitungsanweisungsziel, das "[xX][mM][lL]" entspricht, ist nicht zulässig. Anscheinend leerzeichen im xml header problem
	public static void getXML ( File f ) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try{
			Document document = builder.parse(f);
	    	document.getDocumentElement().normalize();
	
			ArrayList<String> daten = new ArrayList<String>();
			ArrayList<String> description = new ArrayList<String>();
			ArrayList<String> variable = new ArrayList<String>();
			NodeList vars = document.getElementsByTagName("VARIABLE");
			
			for (int i = 0; i < vars.getLength(); i++) {
		
				Node nNode = vars.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					variable.add(eElement.getAttribute("ID"));
					daten.add(Load.getTagValue("VALUE", eElement));
					description.add(Load.getTagValue("DESCRIPTION", eElement));
				}
			}
			
			//System.out.println(variable);
			Tab_parameter.Clearftf();
			boolean  rlbIR, rlbAR, dbIR, dbAR, diKF, daKF, bKF, rtKF, eKF, nuKF, rhoKF;
			rlbIR = false;
			rlbAR = false;
			dbIR = false;
			dbAR = false;
			diKF = false;
			daKF = false;
			bKF = false;
			rtKF = false;
			eKF = false;
			nuKF = false;
			rhoKF = false;
			
			for (int i = 0; i < vars.getLength(); i++) {
				
				if (variable.get(i).equals("Dw")) {

					Tab_parameter.ftf_WK_D.setValue(Double.parseDouble(daten.get(i)));  
				}
				
				if (variable.get(i).equals("Lw")) {
	
					Tab_parameter.ftf_WK_Z.setValue(Double.parseDouble(daten.get(i)));  
				}
				
				if (variable.get(i).equals("Z")) {

					Tab_parameter.ftf_WK_N.setValue(Double.parseDouble(daten.get(i)));  
				}
				
				if (variable.get(i).equals("B")) {
	
					Tab_parameter.ftf_AR_LFB_B.setValue(Double.parseDouble(daten.get(i)));  
					Tab_parameter.ftf_IR_LFB_B.setValue(Double.parseDouble(daten.get(i)));  
				}
				
				if (variable.get(i).equals("D")) {

					Tab_parameter.ftf_AR_A_D.setValue(Double.parseDouble(daten.get(i)));  
				}
				
				if (variable.get(i).equals("DNENN")) {

					Tab_parameter.ftf_IR_I_D.setValue(Double.parseDouble(daten.get(i)));  
				}
				
				if (variable.get(i).equals("dM")) {

					  //LAGERLUFT in mm nach Approxformel wie lagerbrowser 
					double wk_d = ((Number) Tab_parameter.ftf_WK_D.getValue()).doubleValue();
					double d_LFB_i = Double.valueOf(daten.get(i))-wk_d;
					double d_LFB_a = Double.valueOf(daten.get(i))+wk_d;				
					double LL_MED = 0.0;
				    double i_D[] = {24, 30, 40,50,65,80,100,120,140,160,180,200,225,250,280,315,355,400,450,500,560,630,710};
				    double cn[] = { 20,32.5,37.5,45,55,57.5,67.5,70,82.5,95,100,117.5,135,142.5,160,167.5,185,235,260,275,300,320,355};

				    double irD = ((Double) Tab_parameter.ftf_IR_I_D.getValue());
				    for (int j=(i_D.length-1);j>0 ;j--){
					   if (irD <= i_D[j]) {
						   LL_MED = cn[j]/1000;
					   }
				    }				
					Tab_parameter.ftf_AR_LFB_D.setValue(d_LFB_a  +  LL_MED/2 ) ;
					Tab_parameter.ftf_IR_LFB_D.setValue(d_LFB_i  -  LL_MED/2 ) ;
					Tab_parameter.ftf_WK_radBsp.setValue(LL_MED*1000);

				}
			}
				// TODO  Käfig & Profilparameter????
/*				if (variable.get(i).equals("rlbIR")) {
					Tab_parameter.ftf_r_prof_i.setValue(daten.get(i));  
					rlbIR = true;
				}
				
				if (variable.get(i).equals("rlbAR")) {
					Tab_parameter.ftf_r_prof_a.setValue(daten.get(i));
					rlbAR = true;
				}
				
				if (variable.get(i).equals("dbIR")) {
					Tab_parameter.ftf_s_i.setValue(daten.get(i));  
					dbIR = true;
				}
				
				if (variable.get(i).equals("dbAR")) {
					Tab_parameter.ftf_s_a.setValue(daten.get(i)); 
					dbAR = true;
				}
				
				if (variable.get(i).equals("diKF")) {
					Tab_parameter.ftf_kaefig_ir.setValue(daten.get(i));  
					diKF = true;
				}
				
				if (variable.get(i).equals("daKF")) {
					Tab_parameter.ftf_kaefig_ar.setValue(daten.get(i));  
					daKF = true;
				}
				
				if (variable.get(i).equals("bKF")) {
					Tab_parameter.ftf_kaefig_B.setValue(daten.get(i));  
					bKF = true;
				}
				
				if (variable.get(i).equals("rtKF")) {
					Tab_parameter.ftf_kaefig_r.setValue(daten.get(i));  
					rtKF = true;
				}
				
				if (variable.get(i).equals("eKF")) {
					Tab_parameter.ftf_kaefig_youngs.setValue(daten.get(i));  
					eKF = true;
				}
				
				if (variable.get(i).equals("nuKF")) {
					Tab_parameter.ftf_kaefig_poisson.setValue(daten.get(i));  
					nuKF = true;
				}
				
				if (variable.get(i).equals("rhoKF")) {
					Tab_parameter.ftf_kaefig_dichte.setValue(daten.get(i));  
					rhoKF = true;
				}
								
			}
			
			String str = "";
			
			if (!rlbIR) {
				double r_prof_i = Double.valueOf(Tab_parameter.ftf_d_Kugel.getText())*0.52;
				Tab_parameter.ftf_r_prof_i.setValue(String.valueOf(r_prof_i));
				str = str + "Der Innenringprofilradius wurde mit Schmiegung 0.52 berechnet, da keine Angaben in der XML-Datei vorhanden sind.\n";
			}
			if (!rlbAR) {
				double r_prof_a = Double.valueOf(Tab_parameter.ftf_d_Kugel.getText())*0.53;
				Tab_parameter.ftf_r_prof_a.setValue(String.valueOf(r_prof_a));
				str = str + "Der Au\u00dfenringprofilradius wurde mit Schmiegung 0.53 berechnet, da keine Angaben in der XML-Datei vorhanden sind.\n";
			}
			
			if (!dbIR || !dbAR || !diKF || !daKF || !bKF || !rtKF || !eKF || !nuKF || !rhoKF) {
	TODO			str = str + "Folgende Angaben sind nicht in der XML-Datei enthalten.\nBitte erg\u00e4nzen Sie die fehlenden Angaben, wenn notwendig:\n";
						
				if (!dbIR) str = str + " - Borddurchmesser Innenring\n";
				if (!dbAR) str = str + " - Borddurchmesser Au\u00dfenring\n";
				if (!diKF) str = str + " - K\u00e4figinnendurchmesser\n";
				if (!daKF) str = str + " - K\u00e4figau\u00dfendurchmesser\n";
				if (!bKF) str = str +  " - K\u00e4figbreite (M) bzw. Bleckdicke (JN)\n";
				if (!rtKF) str = str + " - K\u00e4figtaschenradius\n";
				if (!eKF) str = str +  " - E-Modul K\u00e4figbreite\n";
				if (!nuKF) str = str + " - Querkontraktionszahl K\u00e4fig\n";
				if (!rhoKF) str = str + " - Dichte K\u00e4fig\n";
			}
			
*/	
		
		    Tab_parameter.ftf_AR_E.setValue(Double.parseDouble("210000"));        
		    Tab_parameter.ftf_AR_v.setValue(Double.parseDouble("0.3"));        
		    Tab_parameter.ftf_AR_rho.setValue(Double.parseDouble("7850"));        

		    Tab_parameter.ftf_IR_E.setValue(Double.parseDouble("210000"));        
		    Tab_parameter.ftf_IR_v.setValue(Double.parseDouble("0.3"));
		    Tab_parameter.ftf_IR_rho.setValue(Double.parseDouble("7850"));

		    Tab_parameter.ftf_WK_E.setValue(Double.parseDouble("210000"));        
		    Tab_parameter.ftf_WK_v.setValue(Double.parseDouble("0.3"));        
		    Tab_parameter.ftf_WK_rho.setValue(Double.parseDouble("7850")); 
		    
		    
		    
		    Tab_parameter.box_protype.setSelectedIndex(3);
		    
		 // BORDPARAMETER berechnen
			double a_approx_wk,a_approx_rdicke; //Bordhöhe
			double i_approx_wk,i_approx_rdicke;
			double d_LFB_a, d_LFB_i, d_Bord_a,d_Bord_i, b_Bord,ax_bsp;

			a_approx_wk = (Double) Tab_parameter.ftf_WK_D.getValue() * 0.1797 + 0.1718;
			a_approx_rdicke = ( ((Double) Tab_parameter.ftf_AR_A_D.getValue()) - ((Double) Tab_parameter.ftf_AR_LFB_D.getValue()) )/2 * 0.3576 + 0.5118;
			
			i_approx_wk = (Double) Tab_parameter.ftf_WK_D.getValue() * 0.1458 + 0.0607;
			i_approx_rdicke = ( ((Double) Tab_parameter.ftf_IR_LFB_D.getValue()) - ((Double) Tab_parameter.ftf_IR_I_D.getValue()) )/2 * 0.3422 + 0.2279;
			
			d_Bord_a =  ((Double) Tab_parameter.ftf_AR_LFB_D.getValue())- ( (a_approx_wk+a_approx_rdicke) /2) *2;
			d_Bord_i =  ((Double) Tab_parameter.ftf_IR_LFB_D.getValue()) + ((i_approx_wk+i_approx_rdicke) /2) *2;
			ax_bsp = 0.005 * (Double) Tab_parameter.ftf_WK_Z.getValue()*1000;
			
			Tab_parameter.ftf_AR_BORD_D.setValue(d_Bord_a);
			Tab_parameter.ftf_IR_BORD_D.setValue(d_Bord_i);
			Tab_parameter.ftf_AR_BORD_Oew.setValue(1.0);
			Tab_parameter.ftf_IR_BORD_Oew.setValue(1.0);
			Tab_parameter.ftf_WK_axBsp.setValue(ax_bsp);
			
			Tab_parameter.setAxBetriebsspiel();
			
			Tab_parameter.ftf_S_EL_simt.setValue(0.3);
			Tab_parameter.ftf_S_EL_simfreq.setValue(20000.0);
			Tab_parameter.ftf_S_EL_simrampent.setValue(0.1);
			
			// Käfigparameterroutine aufrufen 
			Tab_parameter.set_kaefig_param();
			Tab_parameter.set_J_WK();
			
			Tab_parameter.ftf_K_tspiel_ax.setValue(0.01);
			Tab_parameter.ftf_K_tspiel_rad.setValue(0.01);
			Tab_parameter.ftf_K_tspiel_tan.setValue(0.01);
			
			if (Tab_parameter.EButton.isSelected()) Tab_parameter.EButton.doClick();
			if (Tab_parameter.SButton.isSelected()) Tab_parameter.SButton.doClick();
			ZyrolaGui.chckbxmntmManuelleEingabe.setSelected(true);
			ZyrolaGui.chckbxmntmManuelleEingabe.doClick();
			ZyrolaGui.lagername = f.getName();
			    
			    
			    
			    
			String str = "";	
		    str = str + "Lagerdaten wurden aus der Datei " + f.getName() + " importiert.";
			JOptionPane.showMessageDialog(null, str, "XML Import", JOptionPane.CLOSED_OPTION);
			str = "";
			
		} catch (SAXException e) {
			String str = "Die Datei " + f.getName() + " ist keine g\u00fcltige XML-Datei.";
			JOptionPane.showMessageDialog(null, str, "XML Import fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
	    	String str = "Die Datei " + f.getName() + " ist keine g\u00fcltige XML-Datei.";
			JOptionPane.showMessageDialog(null, str, "XML Import fehlgeschlagen", JOptionPane.CLOSED_OPTION);
	    }
		
		
	    //Tab1.txt_D_a.setText(daten.get(4));
	    //Tab1.txt_B_a.setText(daten.get(3));
	    //Tab1.txt_B_i.setText(daten.get(3));
	    //Tab1.txt_s_i.setText(daten.get(5));        
	    //Tab1.txt_d_Kugel.setText(daten.get(0));        
	    //Tab1.txt_wk_anzahl.setText(daten.get(1));          	
	}

	static String getTagValue(String sTag, Element eElement) {
		
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		return nValue.getNodeValue();
	}

}

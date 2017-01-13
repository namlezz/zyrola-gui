package reused;

import javax.swing.JOptionPane;

import ZyRoLa_GUI.ZyrolaGui;
import frames.Tab_parameter;

public class Plaus_Check {
	
	public static boolean err;
	public static String errstr = "";
	
		public static boolean  check() {
			
	
			
			err = false;
			
	
			
			// Abgleich mit D_a
			
			System.out.println(ZyrolaGui.ar_A_D + " " + ZyrolaGui.ar_LFB_D);
			
			if (ZyrolaGui.ar_A_D <= ZyrolaGui.ar_LFB_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringau\u00dfendurchmesser <= Au\u00dfenringlaufbahndurchmesser\n";
			}
			if (ZyrolaGui.ar_A_D <= ZyrolaGui.ar_BORD_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringau\u00dfendurchmesser <= Au\u00dfenringborddurchmesser\n";
			}
			if (ZyrolaGui.ar_A_D <= ZyrolaGui.ir_BORD_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringau\u00dfendurchmesser <= Innenringborddurchmesser\n";
			}
			if (ZyrolaGui.ar_A_D <= ZyrolaGui.ir_LFB_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringau\u00dfendurchmesser <= Innenringlaufbahndurchmesser\n";
			}
			if (ZyrolaGui.ar_A_D <= ZyrolaGui.ir_I_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringau\u00dfendurchmesser <= Innenringinnendurchmesser\n";
			}
			
			// Abgleich mit D_AR_lfb
			if (ZyrolaGui.ar_LFB_D <= ZyrolaGui.ar_BORD_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringlaufbahndurchmesser <= Au\u00dfenringborddurchmesser\n";
			}
			if (ZyrolaGui.ar_LFB_D <= ZyrolaGui.ir_BORD_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringlaufbahndurchmesser <= Innenringborddurchmesser\n";
			}
			if (ZyrolaGui.ar_LFB_D <= ZyrolaGui.ir_LFB_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringlaufbahndurchmesser <= Innenringlaufbahndurchmesser\n";
			}
			if (ZyrolaGui.ar_LFB_D <= ZyrolaGui.ir_I_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringlaufbahndurchmesser <= Innenringinnendurchmesser\n";
			}
			
			// Abgleich mit s_a
			if (ZyrolaGui.ar_BORD_D <= ZyrolaGui.ir_BORD_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringborddurchmesser <= Innenringborddurchmesser\n";
			}
			if (ZyrolaGui.ar_BORD_D <= ZyrolaGui.ir_LFB_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringborddurchmesser <= Innenringlaufbahndurchmesser\n";
			}
			if (ZyrolaGui.ar_BORD_D <= ZyrolaGui.ir_I_D) {
				err = true;
				errstr = errstr + "Au\u00dfenringborddurchmesser <= Innenringinnendurchmesser\n";
			}
			
			// Abgleich mit D_i
			if (ZyrolaGui.ir_BORD_D <= ZyrolaGui.ir_LFB_D) {
				err = true;
				errstr = errstr + "Innenringborddurchmesser <= Innenringlaufbahndurchmesser\n";
			}
			if (ZyrolaGui.ir_BORD_D <= ZyrolaGui.ir_I_D) {
				err = true;
				errstr = errstr + "Innenringborddurchmesser <= Innenringinnendurchmesser\n";
			}
			
			// Abgleich mit d_lfb_i
				if (ZyrolaGui.ir_LFB_D <= ZyrolaGui.ir_I_D) {
					err = true;
					errstr = errstr + "Innenringlaufbahndurchmesser <= Innenringinnendurchmesser\n";
				}
					
	
			if (ZyrolaGui.wk_z > ZyrolaGui.ar_LFB_B	) {
				err = true;
				errstr = errstr + "W\u00e4lzk\u00f6rperdurchmesser > Au\u00dfenringbreite\n";
			}
			if (ZyrolaGui.wk_D > ZyrolaGui.ir_LFB_B) {
				err = true;
				errstr = errstr + "W\u00e4lzk\u00f6rperdurchmesser > Innenringbreite\n";
			}
			
			// Negative Lagerluft
			if (2*ZyrolaGui.wk_D+ZyrolaGui.ir_LFB_D > ZyrolaGui.ar_LFB_D) {
				err = true;
				errstr = errstr + "Negatives Lagerspiel\n";
			}
			
			// Wälzkörperzahl
			if (ZyrolaGui.wk_N < 3) {
				err = true;
				errstr = errstr + "Zu wenige W\u00e4lzk\u00f6rper (<3)\n";
			}
			double alpha_wk = 2*Math.atan(0.5*ZyrolaGui.wk_D/(ZyrolaGui.ir_LFB_D/2+0.5*ZyrolaGui.wk_D));
			if (ZyrolaGui.wk_N > 2*Math.PI/alpha_wk) {
				err = true;
				errstr = errstr + "Zu viele W\u00e4lzk\u00f6rper\n";
			}
			
			// Käfigwerte
			if(ZyrolaGui.kaefigindex != 0) {
				
	
				
				if (ZyrolaGui.wk_D >= ZyrolaGui.k_D_tasche ) {
					err = true;
					errstr = errstr + "W\u00e4lzk\u00f6rperradius => K\u00e4figtaschenradius\n";
				}
				if (ZyrolaGui.ar_BORD_D <= ZyrolaGui.k_D_aussen) {
					err = true;
					errstr = errstr + "Au\u00dfenringborddurchmesser <= K\u00e4figau\u00dfendurchmesser\n";
				}
				if (ZyrolaGui.ir_BORD_D >= ZyrolaGui.k_D_innen) {
					err = true;
					errstr = errstr + "Innenringborddurchmesser => K\u00e4figinnendurchmesser\n";
				}
				if (ZyrolaGui.k_Breite >= ZyrolaGui.ar_LFB_B) {
					err = true;
					errstr = errstr + "KK\u00e4figbreite => Au\u00dfenringbreite\n";
				}
				if (ZyrolaGui.k_Breite >= ZyrolaGui.ir_LFB_B) {
					err = true;
					errstr = errstr + "K\u00e4figbreite => Innenringbreite\n";
				}
				checkKaefigParam();
			}
			
			if (ZyrolaGui.flexAR_maxFE == 0 && ZyrolaGui.flex_ar == 1) {
				err = true;
				errstr = errstr + "Kraft aus FE-Rechnung f\u00FCr flexiblen Au\u00DFenring angeben.\n";
			}
			
			// Check Materialdaten		
			if (ZyrolaGui.ar_E == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr Querkontraktionszahl des Au\u00dfenrings fehlt.\n";
			}
			if (ZyrolaGui.ar_v == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr E-modul des Au\u00dfenrings fehlt.\n";
			}
			if (ZyrolaGui.ar_rho == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr Au\u00dfenringdichte fehlt.\n";
			}
			
			if (ZyrolaGui.ir_E == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr Querkontraktionszahl des Innenrings fehlt.\n";
			}
			if (ZyrolaGui.ir_v == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr E-modul des Innenrings fehlt.\n";
			}
			if (ZyrolaGui.ir_rho == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr Innenringdichte fehlt.\n";
			}
			
			if (ZyrolaGui.wk_E == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr Querkontraktionszahl der W\u00e4lzk\u00f6rper fehlt.\n";
			}
	
			if (ZyrolaGui.wk_v == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr E-modul der W\u00e4lzk\u00f6rper fehlt.\n";
			}
							
							
			if (ZyrolaGui.wk_rho == 0) {
				err = true;
				errstr = errstr + "Wert f\u00FCr W\u00e4lzk\u00f6rperdichte fehlt.\n";
			}
			
			
			if (ZyrolaGui.kaefigindex  != 0 ) 	{
						if (ZyrolaGui.k_rho == 0) {
							err = true;
							errstr = errstr + "Wert f\u00FCr Käfigdichte fehlt.\n";
						}
			}
			
			if (ZyrolaGui.kaefigindex  == 1 ) 	{
				if (ZyrolaGui.k_tspiel_ax == 0) {
					err = true;
					errstr = errstr + "Wert f\u00FCr axiales Taschenspiel beim Taschenfederkäfig fehlt.\n";
				}
				if (ZyrolaGui.k_tspiel_tan == 0) {
					err = true;
					errstr = errstr + "Wert f\u00FCr tangentiales Taschenspiel beim Taschenfederkäfig fehlt.\n";
				}
				if (ZyrolaGui.k_tspiel_rad == 0) {
					err = true;
					errstr  = errstr + "Wert f\u00FCr radiales Taschenspiel beim Taschenfederkäfig fehlt.\n";
				}
			}
			
			
			if (ZyrolaGui.kaefigindex  == 2 ) 	{
					if (ZyrolaGui.k_v == 0) {
						err = true;
						errstr = errstr + "Wert f\u00FCr Querkontraktionszahl des Käfigs fehlt.\n";
					}						
					if (ZyrolaGui.k_E == 0) {
						err = true;
						errstr = errstr + "Wert f\u00FCr E-modul des Käfigs fehlt.\n";
					}
			}
	
			//Check Routinen für Soll/Ist-Vergleich aufrufen
			
			checkBordParam();
			

	
			
			
	
			if (err) {
				errstr = "<html><b>Plausibilit\u00E4tscheck fehlgeschlagen!</b></html>\n" + errstr + "\n<html><b>M\u00F6chten Sie trotzdem weitermachen?</html></b>";
				int go_on = JOptionPane.showConfirmDialog(null, errstr, "Plausibilit\u00E4tscheck", JOptionPane.YES_NO_OPTION);
				if (go_on == 0) {
					err = false;
				}
				errstr = "";
			}
			else {
				// JOptionPane.showMessageDialog(null, "Plausibilitätscheck erfolgreich abgeschlossen", "Plausibilitätscheck", 1);
			}
			
			return err;	
			
		}
	
	public static void checkKaefigParam() {

		int mod = Tab_parameter.box_kaefige_mod.getSelectedIndex(); // 0 = TVP (mitte) , 1 =
																	// MA
																	// (Aussenringgeführt) ,
																	// 2 = MB
																	// (Innenringgeführt)

		double tkd, d_k_a, d_k_i, k_b,l_arc;
		int wk_n;
		tkd = ( ZyrolaGui.ar_LFB_D +  ZyrolaGui.ir_LFB_D) / 2;
		d_k_a = tkd + 0.3 *  ZyrolaGui.wk_D;
		d_k_i = tkd - 0.3 *  ZyrolaGui.wk_D;
		k_b = ( ZyrolaGui.wk_z) * 1.05;

		if (mod == 1) {
			d_k_a = ( ZyrolaGui.ar_BORD_D) - 0.3;
			d_k_i = tkd + 0.3;

		} else if (mod == 2) {
			d_k_i = ( ZyrolaGui.ir_BORD_D) + 0.3;
			d_k_a = tkd - 0.3;
		}


		
		double d_k_a_ist 	= ZyrolaGui.k_D_aussen;
		double d_k_i_ist	= ZyrolaGui.k_D_innen;
		double k_b_ist		= ZyrolaGui.k_Breite;
		
		
		boolean errrs = ((d_k_a * 0.9) <= d_k_a_ist) && ( d_k_a_ist <= (d_k_a * 1.1));
		if (!errrs) {
			err = true;
			errstr = errstr + "Äusserer Käfigborddurchmesser ausserhalb des Sollbereichs (Diff. > 10%)\n";
		}
		boolean errrs2 = ((d_k_i * 0.9) <= d_k_i_ist) && ( d_k_i_ist <= (d_k_i * 1.1));
		System.out.println(d_k_i + "         " + d_k_i_ist );
		if (!errrs2) {
			err = true;
			errstr = errstr + "Innerer Käfigborddurchmesser ausserhalb des Sollbereichs (Diff. > 10%)\n";
		}
		boolean errrs3 = ((k_b * 0.9) <= k_b_ist) && ( k_b_ist <= (k_b * 1.1));
		if (!errrs3) {
			err = true;
			errstr = errstr + "Käfigbreite ausserhalb des Sollbereichs (Diff. > 10%)\n";
		}

		
		
		wk_n = ((Number) ZyrolaGui.wk_N).intValue();		
		l_arc = 2.0*Math.PI/wk_n*(tkd/2);
		double DickeSteg = (l_arc -  ZyrolaGui.wk_D);
		double DickeSteg_ist = ZyrolaGui.k_DickeSteg;
		
		boolean errrs4 = ((DickeSteg * 0.9) <= DickeSteg_ist) && ( DickeSteg_ist <= (DickeSteg * 1.1));
		if (!errrs4) {
			err = true;
			errstr = errstr + "Dicke des Käfigstegs ausserhalb des Sollbereichs (Diff. > 10%)\n";
		}

		
		//Taschendurchmesser
		
		double t_D = ((Number) ZyrolaGui.wk_D).doubleValue() *1.04; //Ref. to LambdaGUI
		double t_D_ist = ZyrolaGui.k_D_tasche;
		
//		ftf_K_D_tasche.setValue(t_D);
		boolean errrs5 = ((t_D * 0.9) <= t_D_ist) && ( t_D_ist <= (t_D * 1.1));
		if (!errrs5) {
			err = true;
			errstr = errstr + "Taschendurchmesser ausserhalb des Sollbereichs (Diff. > 10%)\n";
		}

	}
	


	//BORDPARAMETER - Diff. <= 5-20% -vgl. von eingegebenen Werten mit Sollwerten welche von der GUI sonst berechnet werden
	
	public static void  checkBordParam() {  
		double a_approx_wk,a_approx_rdicke; //Bordhöhe
		double i_approx_wk,i_approx_rdicke;
		
		 a_approx_wk 			= ( ZyrolaGui.wk_D * 0.1797 + 0.1718);
		 a_approx_rdicke		= ( ( ZyrolaGui.ar_A_D) - ( ZyrolaGui.ar_LFB_D) )/2 * 0.3576 + 0.5118;
		
		 i_approx_wk 			=  ZyrolaGui.wk_D * 0.1458 + 0.0607;
		 i_approx_rdicke 		= ( ( ZyrolaGui.ir_LFB_D) - ( ZyrolaGui.ir_I_D) )/2 * 0.3422 + 0.2279;
		
		double d_Bord_a_soll 	= ( ZyrolaGui.ar_LFB_D)- ( (a_approx_wk+a_approx_rdicke) /2) *2;
		double d_Bord_i_soll 	= ( ZyrolaGui.ir_LFB_D) + ((i_approx_wk+i_approx_rdicke) /2) *2;
		double b_Bord_a_soll 	= ( ZyrolaGui.ar_LFB_B -(  ZyrolaGui.wk_z) ) /2 - ZyrolaGui.wk_axBsp/1000;
		double b_Bord_i_soll 	= ( ZyrolaGui.ir_LFB_B -(  ZyrolaGui.wk_z) ) /2 - ZyrolaGui.wk_axBsp/1000;
		
		double d_Bord_a_ist		= ( ZyrolaGui.ar_BORD_D);
		double d_Bord_i_ist		= ( ZyrolaGui.ir_BORD_D);
		double b_Bord_a_ist		= ( ZyrolaGui.ar_BORD_B);
		double b_Bord_i_ist		= ( ZyrolaGui.ir_BORD_B);
		
		
		
		
		//	0 ="NU"(2 A), 1= "N" (2I), 2= "NJ" (2A/1I), 3 = "NUP" (2A/2I)
		if (ZyrolaGui.ltypindex == 0) {
			boolean errrsD = ((d_Bord_a_soll * 0.8) <= d_Bord_a_ist) && ( d_Bord_a_ist <= (d_Bord_a_soll * 1.2));
			if (!errrsD) {
				err = true;
				errstr = errstr + "Durchmesser des Aussenringbords ausserhalb des Sollbereichs (Diff. > 20%)\n";
			}
			boolean errrsB = ((b_Bord_a_soll * 0.95) <= b_Bord_a_ist) && ( b_Bord_a_ist <= (b_Bord_a_soll * 1.05));
			if (!errrsB) {
				err = true;
				errstr = errstr + "Breite des Aussenringbords ausserhalb des Sollbereichs (Diff. > 5%)\n";
			}
			

		} else if (ZyrolaGui.ltypindex == 1) {
			boolean errrsD = ((d_Bord_i_soll * 0.8) <= d_Bord_i_ist) && ( d_Bord_i_ist <= (d_Bord_i_soll * 1.2));
			if (!errrsD) {
				err = true;
				errstr = errstr + "Durchmesser des Innenringbords ausserhalb des Sollbereichs (Diff. > 20%)\n";
			}
			boolean errrsB = ((b_Bord_i_soll * 0.95) <= b_Bord_i_ist) && ( b_Bord_i_ist <= (b_Bord_i_soll * 1.05));
			if (!errrsB) {
				err = true;
				errstr = errstr + "Breite des Innenringbords ausserhalb des Sollbereichs (Diff. > 5%)\n";
			}

		} else if ((ZyrolaGui.ltypindex == 2) || (ZyrolaGui.ltypindex == 3)) {
			boolean errrsD 	= ((d_Bord_i_soll * 0.8) <= d_Bord_i_ist) && ( d_Bord_i_ist <= (d_Bord_i_soll * 1.2));
			boolean errrsD1 = ((d_Bord_a_soll * 0.8) <= d_Bord_a_ist) && ( d_Bord_a_ist <= (d_Bord_a_soll * 1.2));
			if (!errrsD || !errrsD1) {
				err = true;
				errstr = errstr + "Durchmesser des Aussen- oder Innenringbords ausserhalb des Sollbereichs (Diff. > 20%)\n";
			}
			boolean errrsB 	= ((b_Bord_a_soll * 0.95) <= b_Bord_a_ist) && ( b_Bord_a_ist <= (b_Bord_a_soll * 1.05));
			boolean errrsB1 = ((b_Bord_i_soll * 0.95) <= b_Bord_i_ist) && ( b_Bord_i_ist <= (b_Bord_i_soll * 1.05));
			if (!errrsB || !errrsB1) {
				err = true;
				errstr = errstr + "Breite des Aussen- oder Innenringbords ausserhalb des Sollbereichs (Diff. > 5%)\n";
			}
			
			
			

			
			
			
			
			
			
			
		}
	
		
		
		
	}
	
	
}
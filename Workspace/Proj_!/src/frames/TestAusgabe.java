package frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ZyRoLa_GUI.ZyrolaGui;

@SuppressWarnings("serial")
public class TestAusgabe extends JFrame {
	



/**
	 * 
	 */
	
public TestAusgabe() {

	;
	
	   
	int frameWidth = 450;
	int frameHeight = 900;
	setSize(frameWidth, frameHeight);
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (d.width - getSize().width) / 2;
	int y = (d.height - getSize().height) / 2 ;
	setLocation(x, y);
	setResizable(true);

	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	final Container cp = getContentPane();
	cp.setLayout(new BorderLayout());
	setVisible(true);
	
	
	JPanel p1 = new JPanel();

	
	
	
	String [] abc = { "r_a1","r_a2","r_alphaHys","r_alphaLambda","r_alphaP","r_alphaT","r_AV","r_B","r_b1","r_b2","r_Bv","r_BZH",
			"r_C","r_C1","r_C2","r_comprT_mod","r_cr","r_CV","r_CZH","r_DR","r_ER","r_etaZero","r_filmT_mod	","r_K",	
			"r_lambdaZero	","r_lubtmp	","r_mu_d","r_mu_s" ,"r_race_f_EHD","r_race_f_mod	","r_race_lub_mod",
			"r_rexp	","r_rhoZero","r_scaleHys","r_sigma","r_tau_mod	","r_tauRheo","r_vel_d","r_vel_s"};
	
	double [] cde = {ZyrolaGui.r_a1,ZyrolaGui.r_a2,ZyrolaGui.r_alphaHys,ZyrolaGui.r_alphaLambda,ZyrolaGui.r_alphaP,
			ZyrolaGui.r_alphaT,ZyrolaGui.r_AV,ZyrolaGui.r_B,ZyrolaGui.r_b1,ZyrolaGui.r_b2,ZyrolaGui.r_Bv,ZyrolaGui.r_BZH,
			ZyrolaGui.r_C,ZyrolaGui.r_C1,ZyrolaGui.r_C2,ZyrolaGui.r_comprT_mod,ZyrolaGui.r_cr,ZyrolaGui.r_CV,ZyrolaGui.r_CZH,
			ZyrolaGui.r_DR,ZyrolaGui.r_ER,ZyrolaGui.r_etaZero,ZyrolaGui.r_filmT_mod,ZyrolaGui.r_K,ZyrolaGui.r_lambdaZero,
			ZyrolaGui.r_lubtmp,ZyrolaGui.r_mu_d,ZyrolaGui.r_mu_s,ZyrolaGui.r_pR,ZyrolaGui.r_race_f_EHD,ZyrolaGui.r_race_f_mod,
			ZyrolaGui.r_race_lub_mod,ZyrolaGui.r_rexp,ZyrolaGui.r_rhoZero,ZyrolaGui.r_scaleHys,ZyrolaGui.r_sigma,ZyrolaGui.r_tau_mod,
			ZyrolaGui.r_tauRheo,ZyrolaGui.r_vel_d,ZyrolaGui.r_vel_s};
	
	for (int i = 0; i < abc.length; i++) {
		JLabel l = new JLabel(abc[i], JLabel.TRAILING);
		l.setFont(new Font("Arial", Font.PLAIN, 12));
		p1.add(l);

		String str = String.valueOf(cde[i]);
	
		JLabel l1 = new JLabel( str, JLabel.TRAILING);
		l1.setFont(new Font("Arial", Font.PLAIN, 12));
		l.setLabelFor(l1);
		p1.add(l1);
	}
	
 cp.add(p1);
 p1.setLayout(new GridLayout(0, 2, 0, 0));


	
	
}

}

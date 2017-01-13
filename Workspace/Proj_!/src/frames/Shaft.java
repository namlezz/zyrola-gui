package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import ZyRoLa_GUI.Settings;
import ZyRoLa_GUI.ZyrolaGui;
import reused.MainFrame;

@SuppressWarnings({"serial"})
public class Shaft extends JPanel
				implements PropertyChangeListener,ActionListener {
	
	

	private static JFrame frame;
	private static JFileChooser chooser = new JFileChooser();
	private static JFormattedTextField ftf_shaft_AD,ftf_shaft_z,ftf_shaft_rho;
	private static JButton btn_speichern,btn_abbrechen;
	
	
	//Ausgabevariabelen
	private static double shaft_AD , shaft_z, shaft_rho;

	
	
	
	
	
	
	
	
	public Shaft() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel p_shaft_variablen = new JPanel();
		add(p_shaft_variablen, BorderLayout.CENTER);
		p_shaft_variablen.setBorder(BorderFactory.createTitledBorder(null, "Parameter",
				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", Font.BOLD, 12)));
		p_shaft_variablen.setLayout(new GridLayout(3, 2, 10, 10));
						
						JLabel lblDurchmesser = new JLabel("Aussendurchmesser (mm)");
						p_shaft_variablen.add(lblDurchmesser);
						
						ftf_shaft_AD = new JFormattedTextField(ZyrolaGui.p3Format);
						ftf_shaft_AD.setValue(0.0);
						ftf_shaft_AD.addPropertyChangeListener(this);
						lblDurchmesser.setLabelFor(ftf_shaft_AD);
						p_shaft_variablen.add(ftf_shaft_AD);
						ftf_shaft_AD.setColumns(10);
						
						JLabel lblLaenge = new JLabel("L\u00E4nge (mm)");
						p_shaft_variablen.add(lblLaenge);
						
						ftf_shaft_z = new JFormattedTextField(ZyrolaGui.p3Format);
						ftf_shaft_z.setValue(0.0);
						ftf_shaft_z.addPropertyChangeListener(this);
						lblLaenge.setLabelFor(ftf_shaft_z);
						p_shaft_variablen.add(ftf_shaft_z);
						ftf_shaft_z.setColumns(10);
						
						JLabel lblDichte = new JLabel("Dichte (kg/m\u00B3)");
						p_shaft_variablen.add(lblDichte);
						
						ftf_shaft_rho = new JFormattedTextField(ZyrolaGui.p3Format);
						ftf_shaft_rho.setValue(0.0);
						ftf_shaft_rho.addPropertyChangeListener(this);
						lblDichte.setLabelFor(ftf_shaft_rho);
						p_shaft_variablen.add(ftf_shaft_rho);
						ftf_shaft_rho.setColumns(10);
		
		JPanel p_shaft_buttons = new JPanel();
		add(p_shaft_buttons, BorderLayout.SOUTH);
		
					btn_speichern = new JButton("Speichern nach...");
					btn_speichern.addActionListener(this);
					p_shaft_buttons.add(btn_speichern);
					
					btn_abbrechen = new JButton("Abbrechen");
					btn_abbrechen.addActionListener(this);
					p_shaft_buttons.add(btn_abbrechen);
	
	
	}

	
	public static void createAndShowGUI(boolean visible) {
		// Create and set up the window.
		frame = new JFrame("Wellendefinition");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new Shaft();
		newContentPane.setOpaque(true); // content panes must be opaque
		// newContentPane.setSize(1, 1);
		frame.setContentPane(newContentPane);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - newContentPane.getSize().width) / 2;
		int y = (d.height - newContentPane.getSize().height) / 2;
		frame.setLocation(x, y);
		frame.setResizable(true);

		// Display the window.
		frame.pack();
		frame.setVisible(visible);
		
		ftf_shaft_AD.setValue(Tab_parameter.ftf_IR_I_D.getValue());
		ftf_shaft_z.setValue(Tab_parameter.ftf_AR_A_D.getValue());
		ftf_shaft_rho.setValue(Tab_parameter.ftf_IR_rho.getValue());
		
		
	}
	
	public static void disposeframe () {
		frame.dispose();
	}
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btn_abbrechen) {
			frame.dispose();
		
		} else if (source == btn_speichern) {
			File  file = null;
			
		 	Preferences prefs;
			prefs = Preferences.userNodeForPackage(Settings.class);
			String workpath = prefs.get("workpath", "");
			File work_pth = new File ( workpath );
			
		 	chooser.setCurrentDirectory(work_pth);
			
			chooser.setSelectedFile(new File("Systemlager_Welle.spck"));
			int returnVal = chooser.showSaveDialog(Shaft.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
              file = chooser.getSelectedFile();
			} 
			String s = "";
			try {
				Scanner inputStream = new Scanner(MainFrame.class.getResourceAsStream("/dummies/Shaft.spck"));				
				BufferedWriter outputStream = new BufferedWriter(new FileWriter(file.toString()));

				while (inputStream.hasNextLine()) {	        

					s = inputStream.nextLine();
					
					if(s.contains("!RHO_Platzhalter!")) {
						s=s.replaceAll("!RHO_Platzhalter!", shaft_rho+"");
						
					}
					if(s.contains("!Z_Platzhalter!")) {
						s=s.replaceAll("!Z_Platzhalter!", shaft_z+"/1000");
						
					}
					if(s.contains("!AD_Platzhalter!")) {
						s=s.replaceAll("!AD_Platzhalter!", shaft_AD+"/1000");
						
					}
					
					outputStream.write(s);
					outputStream.newLine();

				}

				if (inputStream != null) inputStream.close();
				if (outputStream != null) outputStream.close(); 

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();

			} catch (IOException e1) {
				e1.printStackTrace();

			} 
	 	 frame.dispose();
	 	ZyrolaGui.sb.message("Welle für Systemlager erstellt");	
		}
		
	}

	
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		if (source == ftf_shaft_rho) {
			shaft_rho = ((Number) ftf_shaft_rho.getValue()).doubleValue();
		} else if (source == ftf_shaft_z) {
			shaft_z = ((Number) ftf_shaft_z.getValue()).doubleValue();
		} else if (source == ftf_shaft_AD) {
			shaft_AD = ((Number) ftf_shaft_AD.getValue()).doubleValue();
		}
	}

}

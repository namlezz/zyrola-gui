package reused;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DateiMethoden {
	
	
	public static double txtToFloat (JTextField txtfield) {
    	double floatvalue = 0; 
		String str = txtfield.getText();
		try {
			if(!str.isEmpty()) {
				floatvalue = Double.parseDouble(str);
				txtfield.setBackground(Color.WHITE);
			}
	    }
	    catch (Exception ex) {
	    	floatvalue = 0.0;
	    	txtfield.setBackground(Color.ORANGE);
	    	txtfield.setText(String.valueOf(floatvalue));
	    	JOptionPane.showMessageDialog(null, "Bitte richtiges Zahlenformat verwenden.\nz.B. 123.4567");
	    }
		return floatvalue;
	}
}

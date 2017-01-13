package reused;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JFormattedTextField;
@SuppressWarnings({"serial"})


public class MainFrame {
	
	private static DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	
	
	public static JFormattedTextField newFloatTxt ( final DecimalFormat format ) {
		format.setDecimalFormatSymbols(dfs);
		dfs.setDecimalSeparator('.');
		JFormattedTextField txt = new JFormattedTextField(format) {
			@Override
			public void setText ( String text ) {
				String t = "";			
				if(!text.isEmpty()) {
					if(text.contains(",")) 
						text=text.replace(',', '.');
					if(text.contains("d"))
						text=text.replace("d", "0");
					t = format.format(Float.valueOf(text));	
					if (Float.valueOf(t) < 0) {
						t = format.format(-1.0*Float.valueOf(text));
					}
				}
				super.setText(""+t);
			}
		};
		txt.setValue(0);
		txt.setColumns(30);
		txt.setMaximumSize(new Dimension (0, 12));
		return txt;
	}
	public static JFormattedTextField newFloatTxtneg ( final DecimalFormat format ) {
		format.setDecimalFormatSymbols(dfs);
		dfs.setDecimalSeparator('.');
		JFormattedTextField txt = new JFormattedTextField(format) {
			@Override
			public void setText ( String text ) {
				String t = "";			
				if(!text.isEmpty()) {
					if(text.contains(",")) 
						text=text.replace(',', '.');
					if(text.contains("d"))
						text=text.replace("d", "0");
					t = format.format(Float.valueOf(text));	
				}
				super.setText(""+t);
			}
		};
		txt.setValue(0);
		txt.setColumns(30);
		txt.setMaximumSize(new Dimension (0, 12));
		return txt;
	}
}

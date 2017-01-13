package reused;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Help {
	
	
	public static void callHelp(InputStream iStream, String HelpName) {
		
		try {
			FileOutputStream fout = new FileOutputStream("" + HelpName + "Help.pdf");
			
			byte[] b = new byte[1024];
			int noOfBytes = 0;
			           
			while ((noOfBytes = iStream.read(b)) != -1) {	        
				fout.write(b, 0, noOfBytes);
			}
	
			iStream.close();
			fout.close();   
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br> Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	
	    try {
	        Desktop.getDesktop().open(new File("" + HelpName + "Help.pdf"));
	    } catch (FileNotFoundException e1) {
	    	JOptionPane.showMessageDialog(new JFrame(), "Fehler", "<html>Fehler beim Aufrufen der Hilfe. Datei wurde nicht gefunden. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.", JOptionPane.ERROR_MESSAGE);
	        e1.printStackTrace();
	    }
	    catch (IOException e1) {
	    	JOptionPane.showMessageDialog(new JFrame(), "Fehler", "<html>Fehler beim Aufrufen der Hilfe. Datei wurde nicht gefunden. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.", JOptionPane.ERROR_MESSAGE);
	        e1.printStackTrace();
	    }
	    
	    new File("" + HelpName + "Help.pdf").deleteOnExit();
	    
	}
	
public static void callJARHelp(InputStream iStream1, InputStream iStream2, InputStream iStream3, String HelpName) {
		
		try {
			FileOutputStream fout = new FileOutputStream(HelpName);
			
			byte[] b = new byte[1024];
			int noOfBytes = 0;
			           
			while ((noOfBytes = iStream1.read(b)) != -1) {	        
				fout.write(b, 0, noOfBytes);
			}
	
			iStream1.close();
			fout.close();   
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br> Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		try {
			FileOutputStream fout = new FileOutputStream("jh.jar");
			
			byte[] b = new byte[1024];
			int noOfBytes = 0;
			           
			while ((noOfBytes = iStream2.read(b)) != -1) {	        
				fout.write(b, 0, noOfBytes);
			}
	
			iStream2.close();
			fout.close();   
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br> Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		try {
			FileOutputStream fout = new FileOutputStream("hsviewer.jar");
			
			byte[] b = new byte[1024];
			int noOfBytes = 0;
			           
			while ((noOfBytes = iStream3.read(b)) != -1) {	        
				fout.write(b, 0, noOfBytes);
			}
	
			iStream3.close();
			fout.close();   
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br> Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "<html>Fehler beim Aufrufen der Hilfe. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.</html>", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	
	    try {
	        Desktop.getDesktop().open(new File(HelpName));
	    } catch (FileNotFoundException e1) {
	    	JOptionPane.showMessageDialog(new JFrame(), "Fehler", "<html>Fehler beim Aufrufen der Hilfe. Datei wurde nicht gefunden. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.", JOptionPane.ERROR_MESSAGE);
	        e1.printStackTrace();
	    }
	    catch (IOException e1) {
	    	JOptionPane.showMessageDialog(new JFrame(), "Fehler", "<html>Fehler beim Aufrufen der Hilfe. Datei wurde nicht gefunden. <br>Evtl. ist das Ausf\u00FChrungsverzeichnis schreibgesch\u00FCtzt.", JOptionPane.ERROR_MESSAGE);
	        e1.printStackTrace();
	    }
	    
	    new File(HelpName).deleteOnExit();
	    
	}

}

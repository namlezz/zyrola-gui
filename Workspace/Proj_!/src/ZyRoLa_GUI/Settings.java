package ZyRoLa_GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



import java.nio.channels.FileChannel;


import java.util.Collections;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import reused.Load;
import reused.Speichern;
import reused.SpringUtilities;




@SuppressWarnings({"serial"})
public class Settings extends JPanel {

	private static JFrame frame;
	private static JFileChooser chooser = new JFileChooser();
	private static JFileChooser chooser_workpath = new JFileChooser();
	
	public static JTextField txt_fag, txt_skf, txt_fva_daten, txt_simpack, txt_simpackVersion, txt_param, txt_workpath;
	public static Preferences prefs;
	public static JSpinner routineNrSpinner1;
	private String kompilierteRoutinePfad, simpackIni, iniPfad;
	

	
	public Settings()  {

		super(new BorderLayout());

		
		JPanel dateiPfade = new JPanel( new BorderLayout() );
		
		chooser.setFileFilter( new FileFilter() {		
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith("fvadaten.dat")
		              || f.isDirectory();
		    }
			
		    public String getDescription() {
		          return "FVA-Datenbank (fvadaten.dat)";
		    }
		});
		
		JPanel dbPanel = new JPanel();
		dbPanel.setLayout(new SpringLayout());
		dbPanel.setBorder(BorderFactory.createTitledBorder(null, "Datenbanken",
				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", Font.BOLD, 12)));
//		String[] dbLabels = { "FVA-Daten", "FAG-Daten", "SKF-Daten" };
		String[] dbLabels = { "FVA-Daten" };

		for (int i = 0; i < dbLabels.length; i++) {

			JLabel l = new JLabel(dbLabels[i], JLabel.TRAILING);
			l.setFont(new Font("Arial", Font.PLAIN, 12));

			final JTextField t = new JTextField(32);	
			t.setEditable(false);
			if (i == 1)
				txt_fag = t;
			if (i == 2)
				txt_skf = t;
			if (i == 0)
				txt_fva_daten = t;
			
			JButton b = new JButton("Durchsuchen...");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					int returnVal = chooser.showOpenDialog(Settings.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						t.setText(chooser.getSelectedFile().getAbsoluteFile().toString());
						t.setEditable(false);
					} else {
					}
				}
			});

			l.setLabelFor(t);
			dbPanel.add(l);
			dbPanel.add(t);
			dbPanel.add(b);
			if (i==1) {l.setVisible(false); t.setVisible(false); b.setVisible(false);} 
			if (i==2) {l.setVisible(false); t.setVisible(false); b.setVisible(false);}
			
		}
		
		SpringUtilities.makeCompactGrid(dbPanel, dbLabels.length, 3, 6, 6, 6, 10);
		dateiPfade.add(dbPanel, BorderLayout.CENTER);
		
		JPanel workpathPanel = new JPanel( new SpringLayout() );
		workpathPanel.setBorder(BorderFactory.createTitledBorder(null, "Arbeitsverzeichnis", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", Font.BOLD, 12)));
		
		String pathLabels = "Verzeichnis";
		JLabel l1 = new JLabel(pathLabels, JLabel.TRAILING);
		l1.setFont(new Font("Arial", Font.PLAIN, 12));
		final JTextField t1 = new JTextField(32);	
		t1.setEditable(false);
		txt_workpath = t1;
		l1.setLabelFor(t1);
		
		JButton b1 = new JButton("Durchsuchen...");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				chooser_workpath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser_workpath.showOpenDialog(Settings.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					t1.setText(chooser_workpath.getSelectedFile().getAbsoluteFile().toString());
					t1.setEditable(false);
					ZyrolaGui.work_pth = chooser_workpath.getSelectedFile().getAbsoluteFile();
				} else {
				}
			}
		});
		
		
		workpathPanel.add(l1);
		workpathPanel.add(t1);
		workpathPanel.add(b1);
		
		SpringUtilities.makeCompactGrid(workpathPanel, 1, 3, 6, 6, 6, 10);
		dateiPfade.add(workpathPanel, BorderLayout.NORTH);
	
		
		add(dateiPfade, BorderLayout.CENTER);
		
//-------------------------------------
		
		JPanel routinePanel = new JPanel( new SpringLayout() );
		
		routinePanel.setBorder(BorderFactory.createTitledBorder(null, "Routine Einstellungen", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial",1,12)));
		
		
		
		JButton btn4_1 = new JButton("Erstelle Routinen");
		btn4_1.addActionListener(new ActionListener() {
			public ZipFile zip,zip64;

			public void actionPerformed(ActionEvent ev) {
		 		
					boolean status;
					status = new File(txt_param.getText()).exists();
					
					
				if (status) {
						status = new File(txt_param.getText() + "\\\\src").mkdir();
					    status = new File(txt_param.getText() + "\\\\src\\\\mbselem").mkdir();
					    status = new File(txt_param.getText() + "\\\\simpack32").mkdir();
					    status = new File(txt_param.getText() + "\\\\simpack64").mkdir();
					
/*						Speichern.writeUForceObj(new File(
							txt_param.getText() + "\\\\src\\\\mbselem\\\\rikula32.obj"), "32"); //zerstört obj Code --> copy
						Speichern.writeUForceObj(new File(
								txt_param.getText() + "\\\\src\\\\mbselem\\\\rikula64.obj"), "64"); //zerstört obj Code --> copy
*/					
					    
					 
					    File z = new File((txt_param.getText()+"\\\\simpack32\\\\obj32.zip"));
					    File z64 = new File((txt_param.getText()+"\\\\simpack64\\\\obj64.zip"));
					    Speichern.writeFile(z,32);
					    Speichern.writeFile(z64,64);
					    
					    try {
							zip = new ZipFile(z);
							zip64 = new ZipFile(z64);
						
							for ( ZipEntry entry : Collections.list( zip.entries() ) )
					        {
					          System.out.print( entry.getName() + "." );
					          Speichern.ZipextractEntry( zip, entry, (txt_param.getText()+ "\\\\simpack32") );
					          System.out.println( ".. entpackt" );
					        }
							
							for ( ZipEntry entry : Collections.list( zip64.entries() ) )
					        {
					          System.out.print( entry.getName() + "." );
					          Speichern.ZipextractEntry( zip64, entry, (txt_param.getText() + "\\\\simpack64") );
					          System.out.println( ".. entpackt" );
					        }
					      }
					      catch ( FileNotFoundException e )
					      {
					        System.err.println( "Fehler: ZipFile//s nicht gefunden!" );
					      }
					      catch ( IOException e )
					      {
					        System.err.println( "Fehler: Allgemeiner Ein-/Ausgabefehler!" );
					      }
					    
					    
					    
					    
					    //z.delete();
					  
					    
					    
								File f_routine = new File(txt_param.getText() + "\\\\src\\\\mbselem\\\\uforce"+routineNrSpinner1.getValue()+".f"  );
								//if ((f_routine != null) && f_routine.exists()) {
								if ( f_routine.exists() ) {
									int response = JOptionPane.showConfirmDialog(new JDialog(),
											"Die Datei " + f_routine.getName() + 
											" existiert bereits.\nWollen Sie diese Datei überschreiben?",
											"Speichern bestätigen", JOptionPane.YES_NO_OPTION,
											JOptionPane.WARNING_MESSAGE);
									if (response == JOptionPane.YES_OPTION)
										
										Speichern.writeUForce(f_routine,"uforcecomplete",(Integer) routineNrSpinner1.getValue(),30);
								}
								else
									{
									Speichern.writeUForce(f_routine,"uforcecomplete.f",(Integer) routineNrSpinner1.getValue(),30); //wenn datei nicht da, einfach schreiben
									JOptionPane.showMessageDialog(new JDialog(), "Die Datei " + f_routine.getName() + " wurde erstellt." );
											
								}
					    
					}
					else {
						JOptionPane.showMessageDialog(null, "Der angegebene Ordner " + txt_param.getText() + " existiert nicht.\n Routinen konnten nicht erstellt werden.", "Fehler beim erstellen der Routine", JOptionPane.ERROR_MESSAGE);
					}		
			} 
		});

		
		JButton btn3_5 = new JButton("Lade Simpack.ini");
		btn3_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				 // Horribly platform specific.
		        String appData = System.getenv("APPDATA");
		        File appDataDir = new File(appData);
		        // Get a sub-directory named 'simpack'
		        File simpackDir = new File(appDataDir, "simpack");
		        
				chooser = new JFileChooser(simpackDir);
				chooser.addChoosableFileFilter(new FileFilter() {
					public boolean accept(File f) {
						if (f.isDirectory())
							return true;
						return f.getName().toLowerCase().endsWith(".ini");
					}

					public String getDescription() {
						return "INI Dateien (*.ini)";
					}
				});
				chooser.setMultiSelectionEnabled(false);
				int returnVal = chooser.showOpenDialog(new JDialog());
				if( returnVal == JFileChooser.APPROVE_OPTION ) {
					simpackIni = chooser.getSelectedFile().getAbsolutePath();
					System.out.println(simpackIni);
					File Quelle = new File (simpackIni);
					File Backup = new File (simpackIni+".backup");
//					Path copySourcePath = Paths.get( simpackIni);
//					Path copyTargetPath = Paths.get( simpackIni+".backup" );
					try {
//						Files.copy( copySourcePath, copyTargetPath , REPLACE_EXISTING);
						copyFile(Quelle,Backup);
					} catch (IOException e) {
					
						e.printStackTrace();
					}
			txt_param.setText(Load.getSimpackIni(chooser.getSelectedFile()));
					
					iniPfad = simpackDir.getAbsolutePath();
					
					//System.out.println("PFAD");
					//System.out.println(simpack9pfad);
					
				} else;	
			}


			public void copyFile(File in, File out) throws IOException {
		        FileChannel inChannel = new FileInputStream(in).getChannel();
		        FileChannel outChannel = new FileOutputStream(out).getChannel();
		        try {
		            inChannel.transferTo(0, inChannel.size(), outChannel);
		        } catch (IOException e) {
		            throw e;
		        } finally {
		            if (inChannel != null)
		                inChannel.close();
		            if (outChannel != null)
		                outChannel.close();
		        }
		    } 
			
			
		});
		routinePanel.add(btn3_5);
		
		txt_param = new JTextField();
		routinePanel.add(txt_param);
		
		final String f_flag="//check:bounds //O2";
		
		
		JButton btn3_6 = new JButton("Schreibe Simpack.ini");
		btn3_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				kompilierteRoutinePfad = txt_param.getText();
				String obj_flag32 = kompilierteRoutinePfad + "\\\\simpack32\\\\*.obj";
				String obj_flag64 = kompilierteRoutinePfad + "\\\\simpack64\\\\*.obj";
				chooser = new JFileChooser(iniPfad);
				int returnVal = chooser.showSaveDialog(Settings.this);
//				System.out.println(f_flag);
//				System.out.println(obj_flag);
				if( returnVal == JFileChooser.APPROVE_OPTION ) {
			Speichern.writeSimpackIni(simpackIni+".backup", chooser.getSelectedFile(), txt_param.getText(), f_flag, obj_flag32, obj_flag64);
				}
			}
		});
		routinePanel.add(btn3_6);

		
		JLabel label3_2 = new JLabel("UserForce für ZyRoLa", JLabel.TRAILING);
		routinePanel.add(label3_2);
		
		routinePanel.add(routineNrSpinner1 = new JSpinner());
		
		routinePanel.add(btn4_1);

			
	
		SpringUtilities.makeCompactGrid(routinePanel, 2, 3, 6, 6, 6, 10);
		
		add(routinePanel, BorderLayout.NORTH);

		
//-------------------------------------		
		
		
		JPanel btnPanel = new JPanel(new SpringLayout());
		JButton btn1 = new JButton("Speichern");
		btn1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		    	  try {
					savePreferences();
					frame.dispose();
				} catch (BackingStoreException e) {
					
					e.printStackTrace();
				}
		      }
		});
		btnPanel.add(btn1);
		
		JButton btn2 = new JButton("Abbrechen");
		btn2.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {    
		    	  frame.dispose();
		      }
		});
		btnPanel.add(btn2);
		
		SpringUtilities.makeCompactGrid(btnPanel, 1, btnPanel.getComponentCount(), 6, 6, 6, 10);
		add(btnPanel, BorderLayout.SOUTH);
		
		
				
		getPreferences();
		//Beim Beenden des Einstellungs-Tab die Einstellungen schreiben
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		      public void run() {
		        try {
		          savePreferences();
		        } catch(BackingStoreException ex) {
		          ex.printStackTrace();
		        }
		      }
		}));
	}

	/**
	 * Speichert die aktuellen Einstellungen von Größe, Position und Font über
	 * die Preferences-API ab.
	 */
	private void savePreferences() throws BackingStoreException {
		// Ermitteln des zuständigen Knotens
		prefs = Preferences.userNodeForPackage(Settings.class);
		// Speichern der DB-Einstellungen
		//prefs.put("db_fag", txt_fag.getText());
		//prefs.put("db_skf", txt_skf.getText());
		prefs.put("db_fva_daten", txt_fva_daten.getText());
		prefs.put("simpack_pfad", txt_param.getText());
		prefs.put("routinen_nummer1", String.valueOf(routineNrSpinner1.getValue()));
		prefs.put("workpath", String.valueOf(txt_workpath.getText()));
		//System.out.println(txt_simpack.getText());
		//prefs.put("simpack_version", txt_simpackVersion.getText());
		//MainFrame.f_db_fag = new File (txt_fag.getText());
		//MainFrame.f_db_skf = new File (txt_skf.getText());
		ZyrolaGui.f_db_fva = new File (txt_fva_daten.getText());
		ZyrolaGui.f_simpack = new File (txt_param.getText());
		// Zur Sicherheit alles zurückschreiben
		prefs.flush();
	}

	/**
	 * Lädt die aktuellen Einstellungen über die Preferences-API und wendet sie
	 * auf die GUI an.
	 */
	public static void getPreferences() {

		prefs = Preferences.userNodeForPackage(Settings.class);
		
		String simpack_pfad = prefs.get("simpack_pfad", "");
		Settings.txt_param.setText(simpack_pfad);
		String workpath = prefs.get("workpath", "");
		Settings.txt_workpath.setText(workpath);
		
		String routinen_nummer1 = prefs.get("routinen_nummer1", "30");

		Settings.routineNrSpinner1.setValue(Integer.valueOf(routinen_nummer1));
//		String db_fag = prefs.get("db_fag", "");
//		Settings.txt_fag.setText(db_fag);
//		String db_skf = prefs.get("db_skf", "");
//		Settings.txt_skf.setText(db_skf);
		String db_fva_daten = prefs.get("db_fva_daten", "");
		Settings.txt_fva_daten.setText(db_fva_daten);
		System.out.println("Pref: " + Settings.txt_workpath.getText());
			
	}
	
	public static void createAndShowGUI(boolean visible) {
		// Create and set up the window.
		frame = new JFrame("Einstellungen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new Settings();
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
	}
	
	public static void disposeframe () {
		frame.dispose();
	}
		
	
}

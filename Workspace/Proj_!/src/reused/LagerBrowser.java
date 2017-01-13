package reused;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ZyRoLa_GUI.Settings;
import ZyRoLa_GUI.ZyrolaGui;
import csv.CSVReader;
import frames.Tab_parameter;





@SuppressWarnings("serial")
public class LagerBrowser extends JPanel {

	@SuppressWarnings("rawtypes")
	private static JComboBox box_hersteller, box_lagerart;
	public JTextField suche;
	private static Preferences prefs = Preferences.userNodeForPackage(Settings.class);
    private String[] 	labels = {"Hersteller: ", "Lagerart: ", "Suche: "};
	private static String[] header = { "Kurzzeichen", "d", "D", "B", "Lagerart" };
	private static String[] lagerArten = { "Zylinderrollenlager" };  /*, "Schr\u00E4gkugellager", "Vierpunktlager",
			"Pendelkugellager", "Zylinderrollenlager", "Kegelrollenlager", "Tonnenrollenlager", "Pendelrollenlager",
			"Nadelkr\u00E4nze", "Nadelh\u00FClsen", "Nadelb\u00FCchsen", "Nadellager massiv", "Radialrillenkugellager"
	}; */	
	private String[] lagerArten_skf;
	private static String 	db_skf = prefs.get("db_skf", "def");
							//db_fag = prefs.get("db_fag", "def");
							
	private static JFrame frame;
	private static JTable table;
	private JButton btn1, btn2;
	public static double d_LFB_a, d_LFB_i, d_Bord_a,d_Bord_i, b_Bord,ax_bsp;
	public static double a_approx_wk,a_approx_rdicke; 
	public static double i_approx_wk,i_approx_rdicke;
	private static DefaultTableModel model = new DefaultTableModel(header, 0) {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	private TableRowSorter<DefaultTableModel> sorter;
	private static StatusBar sb = new StatusBar();

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LagerBrowser() {
		super(new BorderLayout());
		sb.message("");
		
		if (ZyrolaGui.f_db_skf.exists()) {
			try {
				lagerArten_skf = getLagerArten(db_skf);
			} catch (Exception e) {}
		}
    	   	        
	    JPanel p1 = new JPanel( new SpringLayout() );   
	    
	    //Füge erste ComboBox hinzu: Hersteller (FAG/SKF)
	    JLabel l1	= new JLabel(labels[0], JLabel.TRAILING);
	    //box_hersteller 	= new JComboBox(new String[] {""});
	    box_hersteller 	= new JComboBox();
	    box_lagerart = new JComboBox(lagerArten);
      		          			    
        if(ZyrolaGui.f_db_skf.exists()) 
      		box_hersteller.addItem("SKF (skf.dat)");
        
        if(ZyrolaGui.f_db_fva.exists()) {
      		box_hersteller.addItem("FAG");
      		box_hersteller.addItem("SKF");
        }
        box_hersteller.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {    
        		JComboBox cb = (JComboBox)ev.getSource();
        		String hersteller = (String)cb.getSelectedItem(); 
        		box_lagerart.setSelectedItem(0);
        		if (hersteller.equals("SKF (skf.dat)")) {
        			box_lagerart.removeAllItems();
        			for (int i = 0; i < lagerArten.length; i++) {
        				box_lagerart.addItem(lagerArten_skf[i]);
        			}
        		} else {
        			box_lagerart.removeAllItems();
        			for (int i = 0; i < lagerArten.length; i++) {
        				box_lagerart.addItem(lagerArten[i]);
        			}
        		}
        	}	      
        });
		l1.setLabelFor(box_hersteller);
	    p1.add(l1);p1.add(box_hersteller);
	    box_hersteller.setSelectedItem("FAG");
	    
		JLabel l2 = new JLabel(labels[1], JLabel.TRAILING);
		box_lagerart.setMaximumRowCount(10);
		box_lagerart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				fuelleTabelle();
			}
		});
	    l2.setLabelFor(box_lagerart);
	    p1.add(l2);
	    p1.add(box_lagerart);
	    	    
	    JLabel l3	= new JLabel(labels[2], JLabel.TRAILING);
	    suche 	= new JTextField();

	    suche.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				suche.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {}
		});
	    suche.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	          newFilter();
	        }
	      });
	    l3.setLabelFor(suche);
	    p1.add(l3);
	    p1.add(suche);
	    
	    SpringUtilities.makeCompactGrid(p1, 3, 2, 6, 6, 6, 10); 
	     
	    
	    //Erstelle Tabelle, die sortiert und gefiltert werden kann.
	    table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(table.getClass()))
        .setHorizontalAlignment(SwingConstants.CENTER);
        sorter = new TableRowSorter<DefaultTableModel>(model);
      
        //Wenn keine Zeile ausgewählt ist, soll der Laden-Button nicht anklickbar sein
        table.getSelectionModel().addListSelectionListener( new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btn1.setEnabled(true);		
			}
		});
        
        //Laden der Daten soll per Doppelklick möglich sein
        table.addMouseListener(new MouseListener() {
        	@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2) {
//					sb.message("Suche nach Lager in der medias\u00AE Datenbank...");
					new Thread() {
	    	  			  public void run() {
	    	  				  ladeDaten(table.getValueAt(table.getSelectedRow(), 0).toString());
	    	  			  }
	    	  		  }.start();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
        
        //Die Spalten mit den Werten sollen nicht sortierbar sein        
        for(int i=1; i<model.getColumnCount(); i++) {
        	sorter.setSortable(i, false);
        }
        table.setRowSorter(sorter);

        //Die Spalte Lagerart soll nicht angezeigt werden
        table.removeColumn(table.getColumnModel().getColumn(4));

        
    	JPanel btnPanel = new JPanel( new GridLayout( 1, 0 ) );
    	btn1 = new JButton("Lade Lagerdaten");
		btn1.setEnabled(false);
    	btn1.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ev) {
    	    	  if(box_hersteller.getSelectedItem().equals("FAG") | box_hersteller.getSelectedItem().equals("SKF")) 
//    		    		sb.message("Suche nach Lager in der medias\u00AE Datenbank...");
    	    	  new Thread() {
    	  			  public void run() {
    	  				  if(model.getValueAt(table.getSelectedRow(), 4).equals(lagerArten[0]))
    	  					  ladeDaten(table.getValueAt(table.getSelectedRow(), 0).toString());
    	  				  else 
    	  					  JOptionPane.showMessageDialog(new JDialog(), "Derzeit können nur Rillenkugellager simuliert werden.\n" +
    	  					  		"Bitte korrigieren Sie Ihre Auswahl.", "", JOptionPane.ERROR_MESSAGE);
    	  			  }
    	  		  }.start();
    	  		  
    	      }
    	});
    	btnPanel.add(btn1);
    	
    	btn2 = new JButton("Schlie\u00DFen");
    	btn2.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ev) {     	    	 
    	    	  frame.dispose();
    	    	  model.getDataVector().removeAllElements();
    	      }
    	});
    	btnPanel.add(btn2);

/*    	btn3 = new JButton("Get Medias");
    	btn3.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ev) {
    			JFileChooser chooser = new JFileChooser();
    			int returnVal = chooser.showSaveDialog(LagerBrowser.this);
    			if(returnVal==JFileChooser.APPROVE_OPTION) {
    				File f = chooser.getSelectedFile();
    				try{
    					BufferedWriter outputStream = new BufferedWriter(new FileWriter(f));
    					//    				int row = sorter.convertRowIndexToModel(table.getSelectedRow());
    					String produktAdresse;
    					for(int i=0; i<model.getRowCount(); i++) {
    						System.out.println((String) model.getValueAt(i, 0));
    						produktAdresse = getProduktAdresse((String) model.getValueAt(i, 0));
    						ArrayList<String> lagerDaten = getMediasDaten( (String) model.getValueAt(i, 0), produktAdresse );	
    						if(!lagerDaten.isEmpty()) {
    							outputStream.write((String) model.getValueAt(i, 0)+";"+lagerDaten.get(3)+";"+lagerDaten.get(5));
    							outputStream.newLine();
    						}
    					}

    					if (outputStream != null) outputStream.close(); 
    				} catch (FileNotFoundException e) {
    					e.printStackTrace();
    				} catch (IOException e) {
    					e.printStackTrace();
    				} 
    			}
    		}
    	});
    	btnPanel.add(btn3);
*/
//    	SpringUtilities.makeCompactGrid(btnPanel, 1, btnPanel.getComponentCount(), 6, 6, 6, 6);
        JPanel unten = new JPanel(new BorderLayout());
        unten.add(btnPanel, BorderLayout.NORTH);
        unten.add(sb, BorderLayout.SOUTH);
                             
        //Füge alle Komponenten dem Panel hinzu
        add(new JScrollPane( table ), BorderLayout.CENTER);    
	    add(p1, BorderLayout.NORTH);
    	add(unten, BorderLayout.SOUTH);
    	
    	fuelleTabelle();
	} 
	
	/**
	 * 
	 * @param f Die Datei aus der gelesen werden soll
	 * @return gibt einen Stringarray der verschriedenen Lagerarten
	 *  zurück, der in die Combobox gepackt wird
	 * @throws IOException
	 */
	private String[] getLagerArten ( String f ) throws IOException {
		String[] nextLine, listLagerArten;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
		Set<String> lagerArten = new HashSet<String>();
		CSVReader reader = new CSVReader( isr, ';', '"', 1 );
		
		//Liest die CSV Datei ein, dabei ist nextLine ein Stringarray aus den einzelnen Einträgen
		while ((nextLine = reader.readNext()) != null) {
		    lagerArten.add(nextLine[2]);
		}		
		
		lagerArten.add("");
		//Konvertiert das Set zu einem Array, und sortiert es alphabetisch
		listLagerArten = lagerArten.toArray(new String[0]);
      	Arrays.sort(listLagerArten);	
	    
      	if (reader != null) reader.close();
		return listLagerArten;	
	}  
	
	@SuppressWarnings("resource")
	private void feulleTabelleSKF( String lagerArt, String f ) throws IOException{
		
		ArrayList<String> zeile = null;   
        String[] nextLine;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
		CSVReader reader = new CSVReader( isr, ';', '"', 1 );
				
		if(model.getRowCount() != 0) model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		btn1.setEnabled(false);
		//Liest die CSV Datei ein, dabei ist nextLine ein Stringarray aus den einzelnen Einträgen
		while ((nextLine = reader.readNext()) != null) {
			if ( nextLine[2].equals(lagerArt) ) {
						
					zeile = new ArrayList<String>(4);
					zeile.add(nextLine[3]);
					zeile.add((nextLine[5]));
					zeile.add((nextLine[4]));
					zeile.add((nextLine[6]));
	    	    	model.addRow(zeile.toArray());	
				
		    }
		}
    }
	
	/*private void feulleTabelleFAG( String lagerArt, String f ) throws IOException{
		
		ArrayList<String> zeile = null;   
        String[] nextLine;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
		CSVReader reader = new CSVReader( isr, ';', '"', 1 );
		
		if(model.getRowCount() != 0) 
			model.getDataVector().removeAllElements(); 	//Löscht alle Einträge
		model.fireTableDataChanged(); 					//Sagt dem TableModel dass sich die Einträge geändert haben
		
		//Liest die CSV Datei ein, dabei ist nextLine ein Stringarray aus den einzelnen Einträgen
		if(lagerArt.equals(lagerArten[8])) lagerArt = "Nadelkranz";
		if(lagerArt.equals(lagerArten[9])) lagerArt = "Nadelhülse";
		if(lagerArt.equals(lagerArten[10])) lagerArt = "Nadelbüchse";
		if(lagerArt.equals(lagerArten[11])) lagerArt = "Nadellager";
				
		while ((nextLine = reader.readNext()) != null) {
			if ( nextLine[2].contains(lagerArt.toLowerCase()) ||  nextLine[2].contains(lagerArt) ) {
				
					zeile = new ArrayList<String>(4);
					zeile.add(nextLine[1]);
					zeile.add(kommaZuPunkt(nextLine[8]));
					zeile.add(kommaZuPunkt(nextLine[7]));
					zeile.add(kommaZuPunkt(nextLine[9]));
	    	    	model.addRow(zeile.toArray());	
								
		    }
		}	
    }*/
	
	private void fuelleTabelleFVA ( String lagerArt, File file, String hersteller ) {

		ArrayList<String> zeile = null;   
		String[] daten = new String[25];
		Scanner inputStream = null;
		try {
			inputStream = new Scanner( file );
		} catch (FileNotFoundException e) {
			sb.message("fvadaten.dat nicht gefunden.");
		}		

		
		if(model.getRowCount() != 0) 
			model.getDataVector().removeAllElements(); 	//Löscht alle Einträge
		model.fireTableDataChanged(); 					//Sagt dem TableModel dass sich die Einträge geändert haben
		btn1.setEnabled(false);

/*
		if(lagerArt.equals(lagerArten[9])) lagerArt = "Nadelkranz";
		if(lagerArt.equals(lagerArten[10])) lagerArt = "Nadelhülse";
		if(lagerArt.equals(lagerArten[11])) lagerArt = "Nadelbüchse";
		if(lagerArt.equals(lagerArten[12])) lagerArt = "Nadellager";
*/
		//Liest die fvadaten.dat Datei ein, dabei ist nextLine ein Stringarray aus den einzelnen Einträgen
		//Überspringe die ersten zwei Zeilen
		inputStream.nextLine();
		inputStream.nextLine();
		
		while (inputStream.hasNextLine()) {

			StringBuffer nextLine = new StringBuffer(inputStream.nextLine());

			daten[0] = nextLine.substring(0, 1).trim();
			daten[1] = nextLine.substring(2, 4).trim();
			daten[2] = nextLine.substring(5, 58).trim();
			daten[3] = nextLine.substring(59, 83).trim();
			daten[4] = nextLine.substring(84, 110).trim();
			daten[5] = nextLine.substring(111, 119).trim();
			daten[6] = nextLine.substring(120, 129).trim();
			daten[7] = nextLine.substring(130, 139).trim();
			daten[8] = nextLine.substring(140, 149).trim();
			daten[9] = nextLine.substring(150, 161).trim();
			daten[10] = nextLine.substring(162, 171).trim();
			daten[11] = nextLine.substring(172, 181).trim();
			daten[12] = nextLine.substring(182, 191).trim();
			daten[13] = nextLine.substring(192, 201).trim();
			daten[14] = nextLine.substring(202, 211).trim();
			daten[15] = nextLine.substring(212, 221).trim();
			daten[16] = nextLine.substring(222, 227).trim();
			daten[17] = nextLine.substring(228, 236).trim();
			daten[18] = nextLine.substring(237, 241).trim();
			daten[19] = nextLine.substring(242, 251).trim();
			daten[20] = nextLine.substring(252, 261).trim();
			daten[21] = nextLine.substring(262, 271).trim();
			daten[22] = nextLine.substring(272, 281).trim();
			daten[23] = nextLine.substring(282, 291).trim();
			daten[24] = nextLine.substring(292, 301).trim();

			if ( (daten[2].contains(lagerArt.toLowerCase()) ||  daten[2].contains(lagerArt)) 
					&& hersteller.equals("SKF") && (daten[4].trim().startsWith("N") || daten[4].trim().startsWith("NU") ||
					daten[4].trim().startsWith("NJ") || daten[4].trim().startsWith("NUP") )
					&& !daten[4].equals("") &&  !daten[4].trim().startsWith("NN") ) {

				zeile = new ArrayList<String>(5);
				zeile.add(daten[4]);
				zeile.add((daten[5]));
				zeile.add((daten[6]));
				zeile.add((daten[7]));
				zeile.add(lagerArt);
				model.addRow(zeile.toArray());	

			}

			if ( (daten[2].contains(lagerArt.toLowerCase()) ||  daten[2].contains(lagerArt))
					&& hersteller.equals("FAG") && (daten[3].trim().startsWith("N") || daten[3].trim().startsWith("NU") || 
					daten[3].trim().startsWith("NJ") || daten[3].trim().startsWith("NUP") ) 
					&& !daten[3].equals("") &&  !daten[3].trim().startsWith("NN")) {

				zeile = new ArrayList<String>(5);
				zeile.add(daten[3]);
				zeile.add((daten[5]));
				zeile.add((daten[6]));
				zeile.add((daten[7]));
				zeile.add(lagerArt);
				model.addRow(zeile.toArray());	

			}
		}	
		
	    if (inputStream != null) inputStream.close();
	    
	}
	
	public static String getProduktAdresse (String query) {
	    
		String adresse =null;
	    try {
	    	// Send data
	    	URL url = new URL("http://medias.schaeffler.com/medias/de!hp.ds/?pattern="+query);

	    	URLConnection conn = url.openConnection();
	    	conn.setDoOutput(true);

	    	// Get the response
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    	String line;
//	    	System.out.println(line);
	    	while ((line = rd.readLine()) != null) {
	    		if(line.trim().equals("<td valign=\"top\" class=\"trefferText\">")) {
	    			String nextLine = rd.readLine().trim();
	    			adresse = "http://medias.schaeffler.com"+
	    					nextLine.substring(nextLine.indexOf("href=")+6, nextLine.indexOf("class")-2);
	    	    	rd.close();
	    		}
	    	}
	    	
	    } catch (Exception e) { }

	    return adresse;
	}
	@SuppressWarnings("unused")
	private static ArrayList<String> getMediasDaten ( String lagerKennz, String produktAdresse ) {

		ArrayList<String> list=new ArrayList<String>();

		try {

			URL url = new URL(produktAdresse);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				if(line.trim().equals("<TD width=\"20%\" valign=\"TOP\" ALIGN=\"RIGHT\">")) {
					String nextLine = rd.readLine().trim();
					list.add(nextLine.substring( nextLine.lastIndexOf("<nobr>")+6, nextLine.indexOf("</nobr>")));
				}
			}	    
			rd.close();

		} catch (Exception e) { }

		return list;
	}

	public void ladeDaten ( String lagerBez )  {
		
		Tab_parameter.Clearftf();
		ZyrolaGui.load_mod = 1;

		int row = sorter.convertRowIndexToModel(table.getSelectedRow());

	    String hersteller = (String)box_hersteller.getSelectedItem();
	    
	
	    
	    
	    
	    Tab_parameter.ftf_AR_A_D.setValue(Double.parseDouble(model.getValueAt(row, 2).toString()));
	    Tab_parameter.ftf_AR_LFB_B.setValue(Double.parseDouble(model.getValueAt(row, 3).toString()));
	    Tab_parameter.ftf_AR_E.setValue(Double.parseDouble("210000"));        
	    Tab_parameter.ftf_AR_v.setValue(Double.parseDouble("0.3"));        
	    Tab_parameter.ftf_AR_rho.setValue(Double.parseDouble("7850"));        

	    Tab_parameter.ftf_IR_I_D.setValue(Double.parseDouble(model.getValueAt(row, 1).toString()));
	    Tab_parameter.ftf_IR_LFB_B.setValue(Double.parseDouble(model.getValueAt(row, 3).toString()));
	    Tab_parameter.ftf_IR_E.setValue(Double.parseDouble("210000"));        
	    Tab_parameter.ftf_IR_v.setValue(Double.parseDouble("0.3"));
	    Tab_parameter.ftf_IR_rho.setValue(Double.parseDouble("7850"));

	    Tab_parameter.ftf_WK_E.setValue(Double.parseDouble("210000"));        
	    Tab_parameter.ftf_WK_v.setValue(Double.parseDouble("0.3"));        
	    Tab_parameter.ftf_WK_rho.setValue(Double.parseDouble("7850")); 
	    Tab_parameter.box_protype.setSelectedIndex(2);
	    

 
	    
	 
	    
		String[] fvaDaten;
		try {  
			fvaDaten  = getFVADaten(ZyrolaGui.f_db_fva, lagerBez, hersteller);
			d_LFB_a = ( Float.valueOf(model.getValueAt(row, 1).toString()).floatValue() 	//D_a Aussendurchmesser
					+ Float.valueOf(model.getValueAt(row, 2).toString()).floatValue() )		//s_i Innendurchemsser
					/ 2 + Float.valueOf(fvaDaten[20]).floatValue();
			
			d_LFB_i = ( Float.valueOf(model.getValueAt(row, 1).toString()).floatValue() 	//D_a Aussendurchmesser
					+ Float.valueOf(model.getValueAt(row, 2).toString()).floatValue() )		//s_i Innendurchemsser
					/ 2 - Float.valueOf(fvaDaten[20]).floatValue();
//			Tab1.txt_r_prof_i.setText(fvaDaten[21]);              
//			Tab1.txt_r_prof_a.setText(fvaDaten[22]);
			Tab_parameter.ftf_WK_D.setValue(Double.parseDouble(fvaDaten[20]));        
			Tab_parameter.ftf_WK_N.setValue(Integer.parseInt(fvaDaten[18]));
			Tab_parameter.ftf_WK_Z.setValue(Double.parseDouble(fvaDaten[24])); // NEU lwe = l#nge?
			
			
			  //LAGERLUFT (radBsp)  nach Tabelle/Lagerkatalog 
			double LL_MED = 0.0;
		    double i_D[] = {24, 30, 40,50,65,80,100,120,140,160,180,200,225,250,280,315,355,400,450,500,560,630,710};
		   double cn[] = { 20,32.5,37.5,45,55,57.5,67.5,70,82.5,95,100,117.5,135,142.5,160,167.5,185,235,260,275,300,320,355};

		   
		   double irD = ((Double) Tab_parameter.ftf_IR_I_D.getValue());
		   for (int i=(i_D.length-1);i>0 ;i--){
			   if (irD <= i_D[i]) {
				   LL_MED = cn[i]/1000;
			   }
		   }
			
		 //   double LL_MED = ((0.4984 * ((Double) Tab_parameter.ftf_IR_I_D.getValue()) + 16.193) / 1000) ;

		  
		// BORDPARAMETER berechnen
			
			Tab_parameter.ftf_AR_LFB_D.setValue(d_LFB_a  +  LL_MED/2 ) ;
			Tab_parameter.ftf_IR_LFB_D.setValue(d_LFB_i  -  LL_MED/2 ) ;
			Tab_parameter.ftf_WK_radBsp.setValue(LL_MED*1000);

			
			
			double a_approx_wk,a_approx_rdicke; //Bordhöhe
			double i_approx_wk,i_approx_rdicke;
			
			 a_approx_wk = (Double) Tab_parameter.ftf_WK_D.getValue() * 0.1797 + 0.1718;
			 a_approx_rdicke = ( ((Double) Tab_parameter.ftf_AR_A_D.getValue()) - ((Double) Tab_parameter.ftf_AR_LFB_D.getValue()) )/2 * 0.3576 + 0.5118;
			
			 i_approx_wk = (Double) Tab_parameter.ftf_WK_D.getValue() * 0.1458 + 0.0607;
			 i_approx_rdicke = ( ((Double) Tab_parameter.ftf_IR_LFB_D.getValue()) - ((Double) Tab_parameter.ftf_IR_I_D.getValue()) )/2 * 0.3422 + 0.2279;
			
			d_Bord_a =  ((Double) Tab_parameter.ftf_AR_LFB_D.getValue())- ( (a_approx_wk+a_approx_rdicke) /2) *2;
			d_Bord_i =  ((Double) Tab_parameter.ftf_IR_LFB_D.getValue()) + ((i_approx_wk+i_approx_rdicke) /2) *2;
				
		
			ax_bsp = 0.005 * (Double) Tab_parameter.ftf_WK_Z.getValue()*1000;
			
			Tab_parameter.ftf_AR_BORD_D.setValue(d_Bord_a);
			Tab_parameter.ftf_IR_BORD_D.setValue(d_Bord_i);
			Tab_parameter.ftf_AR_BORD_Oew.setValue(1.1);
			Tab_parameter.ftf_IR_BORD_Oew.setValue(1.1);
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
			

			
			
			//Schreibt im MainFrame den Namen des Lagers
			ZyrolaGui.lblLagertyp.setText("Lagertyp: " +(String) model.getValueAt(row, 0)) ;
			ZyrolaGui.lagername = (String) model.getValueAt(row, 0);
			ZyrolaGui.sb.message("Lagerdaten des Lagers '"+model.getValueAt(row, 0)+"' geladen.");
			
			
			
			
			if (lagerBez.contains("NUP")) {
				Tab_parameter.box_lagertyp.setSelectedIndex(3);

				
				
			} else if (lagerBez.contains("NU")){
				Tab_parameter.box_lagertyp.setSelectedIndex(0);


			} else if (lagerBez.contains("NJ")){
				Tab_parameter.box_lagertyp.setSelectedIndex(2);

				
			} else if (lagerBez.contains("N")){
				Tab_parameter.box_lagertyp.setSelectedIndex(1);

						
			}

			Tab_parameter.updateLabel();
			
			
		} catch (IOException e) {
			 sb.message("Lager nicht gefunden.");
		}  
		
//		Tab1.box_kaefige.validate();
//		Tab1.setkaefigpara(null);
		

		//Entferne Tabelle
		model.getDataVector().removeAllElements();
		
		//Schließe Frame
		frame.dispose();
		
/*		if (MainFrame.lagerName.contains("M")) {
			int go_on = JOptionPane.showConfirmDialog(null, "Sie haben ein Lager mit der Bezeichnung f\u00fcr Massivk\u00e4fig ausgew\u00e4hlt.\n Soll das Lager direkt mit Massivk\u00e4fig eingeladen werden?", "K\u00e4figauswahl", JOptionPane.YES_NO_OPTION);
			if (go_on == 1) {
				JOptionPane.showMessageDialog(null, "Das Lager wird mit virtuellem K\u00e4fig geladen.");
			}
			if (go_on == 0) {
			Tab1.box_kaefige.setSelectedIndex(2);
			}*/
			
		}
		
	
	
	private void fuelleTabelle(){
		final String lagerArt = (String) box_lagerart.getSelectedItem();
		if (box_hersteller.getSelectedItem().equals("FAG") && box_lagerart.getSelectedItem() != null) {
			new Thread() {
				public void run() {
					fuelleTabelleFVA(lagerArt, ZyrolaGui.f_db_fva, box_hersteller.getSelectedItem().toString());
				}
			}.start();
		} else {}
		
		if (box_hersteller.getSelectedItem().equals("SKF") && box_lagerart.getSelectedItem() != null) {
			new Thread() {
				public void run() {
					fuelleTabelleFVA(lagerArt, ZyrolaGui.f_db_fva, box_hersteller.getSelectedItem().toString());
				}
			}.start();
		} else {}
		
		if (box_hersteller.getSelectedItem().equals("SKF (skf.dat)") && box_lagerart.getSelectedItem() != null) {
			try {
				feulleTabelleSKF(lagerArt, db_skf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {}
		
	}
	
/// NEU coder zum laden der fva datenbank
	
	@SuppressWarnings("resource")
public static String[] getFVADaten ( File f, String lagerBez, String hersteller) throws IOException {
		
		Scanner inputStream = new Scanner( f );
		String[] daten = new String[25];
	
		//Überspringe die ersten zwei Zeilen
		inputStream.nextLine();
		inputStream.nextLine();
	
		while (inputStream.hasNextLine()) {	 
	
			String s = inputStream.nextLine();
	
			daten[0] = s.substring(0, 1).trim();
			daten[1] = s.substring(2, 4).trim();
			daten[2] = s.substring(5, 58).trim();
			daten[3] = s.substring(59, 83).trim();
			daten[4] = s.substring(84, 110).trim();
			daten[5] = s.substring(111, 119).trim();
			daten[6] = s.substring(120, 129).trim();
			daten[7] = s.substring(130, 139).trim();
			daten[8] = s.substring(140, 149).trim();
			daten[9] = s.substring(150, 161).trim();
			daten[10] = s.substring(162, 171).trim();
			daten[11] = s.substring(172, 181).trim();
			daten[12] = s.substring(182, 191).trim();
			daten[13] = s.substring(192, 201).trim();
			daten[14] = s.substring(202, 211).trim();
			daten[15] = s.substring(212, 221).trim();
			daten[16] = s.substring(222, 227).trim();
			daten[17] = s.substring(228, 236).trim();
			daten[18] = s.substring(237, 241).trim();
			daten[19] = s.substring(242, 251).trim();
			daten[20] = s.substring(252, 261).trim();
			daten[21] = s.substring(262, 271).trim();
			daten[22] = s.substring(272, 281).trim();
			daten[23] = s.substring(282, 291).trim();
			daten[24] = s.substring(292, 301).trim();
	
			if(daten[3].equals(lagerBez) && hersteller.equals("FAG")) 
				return daten;
			
			if(daten[4].equals(lagerBez) && hersteller.equals("SKF"))
				return daten;
			
	    }		
	    if (inputStream != null) inputStream.close();
	    
	    return null;
	}
	

	
	
	
	
	/**
	 * Diese Methode wird zum Sortieren der Tabelle benötigt
	 */
	private void newFilter() {
	      RowFilter<DefaultTableModel, Object> rf = null;
	      //If current expression doesn't parse, don't update.
	      try {
	          rf = RowFilter.regexFilter("(?i)" +suche.getText());
	      } catch (java.util.regex.PatternSyntaxException e) {
	      }
	      sorter.setRowFilter(rf);
	} 
			
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        frame = new JFrame("Lagerdatenbank");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JComponent newContentPane = new LagerBrowser();
        newContentPane.setOpaque(true);
	    newContentPane.setSize(640, 300);	      
        frame.setContentPane(newContentPane);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width - newContentPane.getSize().width) / 2;
	    int y = (d.height - newContentPane.getSize().height) / 2 ;
	    frame.setLocation(x, y);
	    
	    //Wenn das Fenster geschlossen wird, soll die Tabelle geleert werde
	    frame.addWindowListener(new WindowAdapter() {							
			public void windowClosing(WindowEvent arg0) {
				model.getDataVector().removeAllElements();		
			}
		});
	    
        frame.pack();
        frame.setVisible(true);
    }


}

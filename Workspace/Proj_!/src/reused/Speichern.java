package reused;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;



public class Speichern {


	
	

	
	
	

	


	public static void writeUForce (File f, String forcef, int routineNr, int i) {
		
		String s = "";
		
		try {

			Scanner inputStream = new Scanner(MainFrame.class.getResourceAsStream("/dummies/"+forcef));
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(f));

			while (inputStream.hasNextLine()) {	        

				s = inputStream.nextLine();
				
				if(s.contains("uforce"+i)) {
					s=s.replaceAll("uforce"+i , "uforce" + routineNr);
				}
				
				if(s.contains("Element type "+i)) {
					s=s.replaceAll("Element type "+i, "Element type " + routineNr);
				}
				
				if(s.contains("Element Type "+i)) {
					s=s.replaceAll("Element Type "+i, "Element Type " + routineNr);
				}
				
				if(s.contains("element type "+i)) {
					s=s.replaceAll("element type "+i, "element type " + routineNr);
				}
				
			/*	if(s.contains("		type_name = 'ATLaS RiKuLa 20 9200          '")) {
					s=s.replaceAll(" 20", " " + Settings.routineNrSpinner1.getValue());
					System.out.println(s);
				}*/
				
				outputStream.write(s);
				outputStream.newLine();

			}

			if (inputStream != null) inputStream.close();
			if (outputStream != null) outputStream.close(); 

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} 
	}
	

	
	public static void writeFile (File f,int ver) {
		
		try {

			InputStream inputStream;
			
			
				inputStream = (MainFrame.class.getResourceAsStream("/dummies/obj"+ver+".zip"));
			
			
            FileOutputStream fout = new FileOutputStream(f);

			byte[] b = new byte[1024];
			int noOfBytes = 0;
			           
			while ((noOfBytes = inputStream.read(b)) != -1) {	        
				fout.write(b, 0, noOfBytes);
			}

			inputStream.close();
			fout.close();   
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	

    	
 
    
    
        

        
    
	
	

	
	
	
	public static void writeSimpackIni (String f_in, File f_out, String pfad, String fflag, String objflag32, String objflag64) {
		
		try {
			InputStream in = new FileInputStream(f_in);  
			Scanner inputStream = new Scanner(in);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(f_out));

			while (inputStream.hasNextLine()) {	        
				String c = inputStream.nextLine();
				if(c.contains("simpackUser")) {
					c=c.replace(c.substring(c.indexOf("=")+1, c.length()), pfad);
				}
				if(c.contains("UserRoutines\\Main\\win32\\fCompFlags=")) {
					if(c.contains(fflag)==false){
					c=c.replace(c.substring(c.indexOf("=")+1, c.length()), fflag);
					}
				}
				if(c.contains("UserRoutines\\Main\\win64\\fCompFlags=")) {
					if(c.contains(fflag)==false){
					c=c.replace(c.substring(c.indexOf("=")+1, c.length()), fflag);
					}
				}
				if(c.contains("UserRoutines\\Main\\win32\\extObject=")) {
					String extline = c + ";" + objflag32;
					System.out.println(extline);
					//c = extline;
					if (c.contains("=\"")) {
						c = c.replace("=\"","=\"" + objflag32 + ";");
					}
					else {
						c = c.replace("=","=\"" + objflag32 + ";");
						c = c + "\"";
					}
					System.out.print(c);
				}
				if(c.contains("UserRoutines\\Main\\win64\\extObject=")) {
					String extline = c + ";" + objflag64;
					System.out.print(extline);
					//c = extline;
					if (c.contains("=\"")) {
						c = c.replace("=\"","=\"" + objflag64 + ";");
					}
					else {
						c = c.replace("=","=\"" + objflag64 + ";");
						c = c + "\"";
					}
					System.out.print(c);
				}
				outputStream.write(c);
				outputStream.newLine();
			}

			if (inputStream != null) inputStream.close();
			if (outputStream != null) outputStream.close(); 

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}




	public static void ZipextractEntry(ZipFile zipFile, ZipEntry entry,
			String destDir) throws IOException {
	    File file = new File( destDir, entry.getName() );
	      byte[] buffer = new byte[ 0xFFFF ];
	    if ( entry.isDirectory() )
	      file.mkdirs();
	    else
	    {
	      new File( file.getParent() ).mkdirs();

	      InputStream  is = null;
	      OutputStream os = null;

	      try
	      {
	        is = zipFile.getInputStream( entry );
	        os = new FileOutputStream( file );

	        for ( int len; (len = is.read(buffer)) != -1; )
	          os.write( buffer, 0, len );
	      }
	      finally
	      {
	        if ( os != null ) os.close();
	        if ( is != null ) is.close();
	      }
	    }
	  }



}




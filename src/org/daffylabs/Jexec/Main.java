package org.daffylabs.Jexec;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 */
	public static void gui(String[] args) {
		MainFrame.main(args);
	}
	
	public static void headless(String[] args) {
		
		try {
			runshell(args[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String runshell(String command) throws IOException {
        
		   String line;
	    	String output = "";
	    try {
	        Process p = Runtime.getRuntime().exec(command);
	        BufferedReader input = new BufferedReader
	            (new InputStreamReader(p.getInputStream()));
	        while ((line = input.readLine()) != null) {
	            //output += (line + '\n');
	            System.out.println(line);
	        	//textArea.append(line+"\n");
	        }
	           input.close();
	        }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return output;
	     
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MainFrame.main(null);
		String[] dummy = new String[3];
		if (args.length < 2 || args.length > 3) {
			System.out.println("invalid parameters .. should be \"command\" \"[title for window]\"");
			System.exit(1);
		}
		//int i = 0;
		//System.out.println(""+args.length);
		//while (i < args.length) {
	    //	System.out.println(args[i]);
		//	i++;
		//}
		
		if (args.length == 1) {
			dummy[0] = "";
			dummy[1] = args[0];
			dummy[2] = "Jexec";
		}
		if (args.length == 2) {
			dummy[0] = "";
			dummy[1] = args[0];
			dummy[2] = args[1];
		}
		

		if (GraphicsEnvironment.isHeadless()) {
			headless(dummy);
		} else {
			gui(dummy);			
		}
		
	}

}

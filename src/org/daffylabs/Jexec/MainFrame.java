package org.daffylabs.Jexec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static JButton button;
	public static JTextArea textArea;
	public static String title = "";
	public static String command = "";	
	
	public static void main(String[] args) {
		command = args[1];
		title = args[2];		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(title);
					Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
					Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/3));
					int x=(int)(frameSize.width/2);
					int y=(int)(frameSize.height/2);
					frame.setBounds(x,y,frameSize.width,frameSize.height);
					frame.setVisible(true);
					try {			
			    		runshell(command);
					} catch (Exception ex) {
			       		ex.printStackTrace();
					}
					button.setText("OK");
					button.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static synchronized String runshell(String command) throws IOException {
        
		   String line;
	    	String output = "";
	    try {
	        Process p = Runtime.getRuntime().exec(command);
	        BufferedReader input = new BufferedReader
	            (new InputStreamReader(p.getInputStream()));
	        while ((line = input.readLine()) != null) {
	            //output += (line + '\n');
	            //System.out.println(line);
	        	textArea.append(line+"\n");
	        }
	           input.close();
	        }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return output;
	     
		}
	
	/**
	 * Create the frame.
	 */
	
	
	public MainFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		button = new JButton("Running Please Wait...");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button.setEnabled(false);
		getContentPane().add(button, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
	}

}

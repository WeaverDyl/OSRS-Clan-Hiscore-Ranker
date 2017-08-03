package hiscore;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * Handles the GUI and the logic/processing behind the different components
 * 
 * @author Dylan Weaver
 *
 */
public class HiscoreGui {
	
	private JFrame frmClanHiscoreTool;
	
	private JTextArea textAreaPlayers;
	private JTextArea textAreaResults;
	private JTextArea textAreaErrors;
	
	private JScrollPane scrollPanePlayers;
	private JScrollPane scrollPaneResults;
	private JScrollPane scrollPaneErrors;
	
	private JLabel labelPlayers;
	private JLabel labelResults;
	private JLabel lableErrors;
	private JLabel lableProgress;
	
	private JButton buttonCalculate;
	private JButton buttonInstructions;
	private JButton buttonSave;
	private JButton buttonLoad;
	private JButton btnNewButton;
	private JButton buttonCopy;
	private JButton buttonAbout;
	
	private JProgressBar progressBar;

	// Marks the position/rank of the current player
	private static int position = 1;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Make it look pretty
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				}
				catch (Exception e) {
					
				}
				
				HiscoreGui window = new HiscoreGui();
				window.frmClanHiscoreTool.setVisible(true);
			}
		});
	}

	/**
	 * Create the application
	 */
	private HiscoreGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		frmClanHiscoreTool = new JFrame();
		frmClanHiscoreTool.setResizable(false);
		frmClanHiscoreTool.setIconImage(Toolkit.getDefaultToolkit().getImage(HiscoreGui.class.getResource("/media/hiscore.gif")));
		frmClanHiscoreTool.setTitle("Clan Hiscore Tool");
		frmClanHiscoreTool.setBounds(100, 100, 753, 480);
		frmClanHiscoreTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClanHiscoreTool.getContentPane().setLayout(null);
		
		labelPlayers = new JLabel("List of Players:");
		labelPlayers.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPlayers.setBounds(10, 11, 105, 21);
		frmClanHiscoreTool.getContentPane().add(labelPlayers);
		
		scrollPanePlayers = new JScrollPane();
		scrollPanePlayers.setBounds(113, 11, 613, 125);
		frmClanHiscoreTool.getContentPane().add(scrollPanePlayers);
		
		textAreaPlayers = new JTextArea();
		textAreaPlayers.setWrapStyleWord(true);
		textAreaPlayers.setLineWrap(true);
		scrollPanePlayers.setViewportView(textAreaPlayers);
		
		labelResults = new JLabel("Results:");
		labelResults.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelResults.setBounds(10, 147, 105, 21);
		frmClanHiscoreTool.getContentPane().add(labelResults);
		
		scrollPaneResults = new JScrollPane();
		scrollPaneResults.setBounds(113, 147, 613, 125);
		frmClanHiscoreTool.getContentPane().add(scrollPaneResults);
		
		textAreaResults = new JTextArea();
		scrollPaneResults.setViewportView(textAreaResults);
		textAreaResults.setEditable(false);
		textAreaResults.setWrapStyleWord(true);
		textAreaResults.setLineWrap(true);
		
		lableErrors = new JLabel("Errors:");
		lableErrors.setFont(new Font("Tahoma", Font.BOLD, 13));
		lableErrors.setBounds(10, 283, 105, 21);
		frmClanHiscoreTool.getContentPane().add(lableErrors);
		
		scrollPaneErrors = new JScrollPane();
		scrollPaneErrors.setBounds(113, 283, 613, 125);
		frmClanHiscoreTool.getContentPane().add(scrollPaneErrors);
		
		textAreaErrors = new JTextArea();
		textAreaErrors.setWrapStyleWord(true);
		textAreaErrors.setLineWrap(true);
		textAreaErrors.setEditable(false);
		scrollPaneErrors.setViewportView(textAreaErrors);
		
		buttonCalculate = new JButton("Calculate");
		buttonCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printResultsToScreen();
			}
		});
		buttonCalculate.setBounds(12, 419, 89, 23);
		frmClanHiscoreTool.getContentPane().add(buttonCalculate);
		
		buttonInstructions = new JButton("Instructions");
		buttonInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String instructions = 
						"Type, paste, or load a list of players that you want to compare. If you load a file,"
					  + "\nbe aware that only '.txt' files are supported.\n\nYou can save a list of players by "
					  + "clicking save, which will save all of the players in"
				      + "\nthe 'List of Players' box into a text file. This will make it more convenient to\n"
					  + "compare the list of players at a later time.\n\nWhen you are ready to compare the players, "
				      + "click 'calculate', and wait for the progress \nbar to reach 100%. Then, you can copy the "
					  + "results to your clipboard by pressing 'Copy Data'.\n\nAny errors that are caused will appear in the 'Errors' box. "
					  + "These errors should be fixed\nbefore using the results, as the error may impact the accuracy of the results."
					  + "\n\nIf you want to clear the text from all of the boxes and reset the progress bar, click 'Clear All'.";
				
				JOptionPane.showMessageDialog(frmClanHiscoreTool, 
						instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonInstructions.setBounds(212, 419, 105, 23);
		frmClanHiscoreTool.getContentPane().add(buttonInstructions);
		
		buttonSave = new JButton("Save List");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save(textAreaPlayers.getText());
			}
		});
		buttonSave.setBounds(12, 43, 89, 23);
		frmClanHiscoreTool.getContentPane().add(buttonSave);
		
		buttonLoad = new JButton("Load List");
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(textAreaPlayers);
			}
		});
		buttonLoad.setBounds(12, 77, 89, 23);
		frmClanHiscoreTool.getContentPane().add(buttonLoad);
		
		btnNewButton = new JButton("Clear All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.clearTextAreas(textAreaPlayers, textAreaResults, textAreaErrors);
				progressBar.setValue(0);
			}
		});
		btnNewButton.setBounds(113, 419, 89, 23);
		frmClanHiscoreTool.getContentPane().add(btnNewButton);
		
		buttonCopy = new JButton("Copy Data");
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.copyToClipboard(frmClanHiscoreTool, textAreaResults);
			}
		});
		buttonCopy.setBounds(12, 179, 89, 23);
		frmClanHiscoreTool.getContentPane().add(buttonCopy);
		
		lableProgress = new JLabel("Progress:");
		lableProgress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lableProgress.setBounds(455, 418, 71, 23);
		frmClanHiscoreTool.getContentPane().add(lableProgress);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(519, 419, 207, 21);
		progressBar.setStringPainted(true);
		frmClanHiscoreTool.getContentPane().add(progressBar);
		
		buttonAbout = new JButton("About");
		buttonAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aboutMessage = "Created by Dylan Weaver in July of 2017.";
				JOptionPane.showMessageDialog(frmClanHiscoreTool, aboutMessage, 
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonAbout.setBounds(327, 419, 89, 23);
		frmClanHiscoreTool.getContentPane().add(buttonAbout);
	}
	
	/**
	 * Outputs each player's position, name, level, and experience to {@code textAreaResults}. 
	 * Also outputs any errors that occurred to {@code textAreaErrors}
	 */
	private void printResultsToScreen() {
		// Clear the results and errors so that they don't stack up upon consecutive runs
		Utility.clearTextAreas(textAreaResults, textAreaErrors);
		if (!textAreaPlayers.getText().isEmpty()) {
			// Create an array of player names by splitting textAreaPlayers anywhere a comma appears
			String[] arrayOfPlayers = textAreaPlayers.getText().replaceAll(",", ", ").split(", ");
			Calculations.runScraper(arrayOfPlayers);
			for (int i = 0; i < Calculations.players.size(); i++) {
//TODO Put progressBar and textAreaResults.append processes on a seperate thread
				progressBar.setValue(((i + 1) / Calculations.players.size()) * 100);
				textAreaResults.append(position + Utility.getPositionSuffix(position) + Calculations.players.get(i).toString() + "\n");
				position++;
			}
			// Handles printing the list of errors to the screen (if there are any)
			if (Calculations.playerErrors.isEmpty()) {
				textAreaErrors.setText("None!");
			} else {
				// Go through the list of errors, and print out a specific response if it is known why the error
				// could have occurred, or a generic response otherwise
				for (int i = 0; i < Calculations.playerErrors.size(); i++) {
					// OSRS usernames can't be any more than 12 characters, so check if that's the issue
					if (Calculations.playerErrors.get(i).length() > 12) {
						textAreaErrors.append("\"" + Calculations.playerErrors.get(i).replaceAll("\"", "") + 
								"\"" + " ERROR! This name contains more than 12 characters.\n");
					}
					// Otherwise, just print a generic error. The OSRS hiscores automatically remove invalid
					// characters, so there is no need to handle those here
					else {
						textAreaErrors.append("\"" + Calculations.playerErrors.get(i).replaceAll("\"", "") + "\"" + " ERROR! Check for name change or spelling.\n");
					}
				}
			}
		}
		// Reset the position so that it remains accurate upon consecutive runs
		position = 1;
	}
	
	/**
	 * Saves the current list of names to a .txt file in a user specified location
	 * 
	 * @param content The text within textAreaPlayers
	 */
	private void save(String content) {
	    JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	    	// Create a File representation from the String path
		    File fileToSave = new File(Utility.addExtension(chooser.getSelectedFile().toString(), "txt"));
		    // If the file exists, confirm that the user would like to overwrite the existing file
		    if (fileToSave.exists()) {
		    	int response = JOptionPane.showConfirmDialog(frmClanHiscoreTool, "This file already exists. Would you like to overwrite it?", 
		    			"Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	switch(response) {
		    	// If the user selectes 'yes', break from the switch statement and continue to writing the data to the file
		    	case JOptionPane.YES_OPTION:
		    		break;
		    	// If the user says 'no', bring up the save dialog again so they can choose a new path or filename
		    	case JOptionPane.NO_OPTION:
					save(textAreaPlayers.getText());
					return;
		    	default:
		    		return;
		    	}
		    }
		    // Write the data to the file
	        try (FileWriter fw = new FileWriter(Utility.addExtension(chooser.getSelectedFile().toString(), "txt"))) {
	            fw.write(content.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
				JOptionPane.showMessageDialog(frmClanHiscoreTool, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	/**
	 * Loads a list of names from a user specified location into an appropriate JTextArea
	 * 
	 * @param box Where to put the loaded data.
	 */
	private void load(JTextArea box) {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Load");
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	// Create a new FileReader wrapped in a BufferedReader that will read the selected file.
	        	try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
	    			try {
	    				// If the file is valid, clear the JTextArea being used to write the data to, and
	    				// write the data to it.
	    				if (Utility.getFileExtension(chooser.getSelectedFile()).equals("txt")) {
	    	        		box.setText("");
	    	        		String line = null;
	    	        		while((line = br.readLine()) != null) {
	    		        		box.append(line);	
	    	        		}
	    				}
	    				// Throw an exception if the selected file does not have a .txt extension.
	    				else {
	    					throw new FileFormatException("Invalid file type!\nThis program currently only supports .txt files.");
	    				}
	    			} catch (FileFormatException e) {
	    				e.printStackTrace();
	    				JOptionPane.showMessageDialog(frmClanHiscoreTool, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    			}
	    		}
	        } catch (Exception e) {
	            e.printStackTrace();
				JOptionPane.showMessageDialog(frmClanHiscoreTool, "Error loading file.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
}

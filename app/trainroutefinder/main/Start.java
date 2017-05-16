package app.trainroutefinder.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import app.trainroutefinder.gui.MainScreen;
import app.trainroutefinder.gui.utils.GuiStyle;
import app.trainroutefinder.objects.StationsManager;
import app.trainroutefinder.utils.FileUtils;

/**
 * Start of the application.
 * Creates the window and user interface.
 * 
 * @author William Baker
 *
 */
public class Start
{
	/**
	 * The JFrame window for the application.
	 */
	private JFrame window;
	
	/**
	 * Stores the stations and routes available for the user to travel between.
	 */
	private StationsManager stationsManager;
	
	/**
	 * Constructor, initialises the application.
	 * Adds default stations and reads file.
	 * Sets up JFrame window.
	 */
	public Start()
	{
		setupStations(); // Sets up the stations the user can travel to
		
		setupWindow(); // Creates and sets up the JFrame window
	}
	
	/**
	 * Initialises the JFrame window.
	 */
	private void setupWindow()
	{
		// Sets up the JFrame
		window = new JFrame();
		window.setTitle("Train Route Finder | William Baker");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes application when exit button is pressed
		window.setSize(new Dimension(1024, 576));
		
		setDefaultStyle();
		window.getContentPane().setBackground(Color.WHITE); // Sets background of window
		new MainScreen(window.getContentPane(), this.stationsManager); // Adds main components to window
		
		window.setLocationRelativeTo(null); // Centre the window onto the screen
		window.setVisible(true); // Shows the window
	}
	
	/**
	 * Sets up the stations the user can travel to and from.
	 * Attempts to read from the save location.
	 * If the file cannot be read, default value will be created and a new file will be written.
	 */
	private void setupStations()
	{
		this.stationsManager = new StationsManager(); // Creates a new instance of stations and routes
		
		try
		{
			this.stationsManager.routes = FileUtils.readRoutesFromFile(this.stationsManager.getSaveLocation().getAbsolutePath()); // Attempts to read stations from a file
		}
		catch (Exception e)
		{
			try
			{
				FileUtils.writeStationsToFile(this.stationsManager.routes, this.stationsManager.getSaveLocation().getAbsolutePath()); // Attempts to write stations from a file
			}
			catch(Exception e2) { }
		}
	}
	
	/**
	 * Sets the default style and look and feel of components in the application.
	 */
	private void setDefaultStyle()
	{
		try
		{
			// Sets the looks and feel to the system's looks and feel
			// For example Windows or iOS
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) { }
		
		// Sets the default styles of components
		// Will change the background colour and fonts of all components in the GUI
		UIManager.getDefaults().put("Panel.background", Color.WHITE);
		UIManager.getDefaults().put("Button.font", GuiStyle.Fonts.REGULAR);
		UIManager.getDefaults().put("ComboBox.font", GuiStyle.Fonts.REGULAR);
		UIManager.getDefaults().put("Label.font", GuiStyle.Fonts.REGULAR);
		UIManager.getDefaults().put("List.font", GuiStyle.Fonts.REGULAR);
		UIManager.getDefaults().put("TabbedPane.font", GuiStyle.Fonts.REGULAR);
		UIManager.getDefaults().put("TextField.font", GuiStyle.Fonts.REGULAR);
	}
}

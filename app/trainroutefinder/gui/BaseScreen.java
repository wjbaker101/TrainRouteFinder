package app.trainroutefinder.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.trainroutefinder.objects.StationsManager;

/**
 * Base class for a screen.
 * Contains some useful methods when adding and modifying components contained in the screen.
 * 
 * @author William Baker
 *
 */
public abstract class BaseScreen
{
	/**
	 * Stores the listener for when an action has been performed.
	 * Mainly used for comboBoxes or buttons when they have been selected/ clicked.
	 */
	protected ActionListener actionListener;
	
	/**
	 * Stores the listener for when a textField is interacted with.
	 * Mainly used for textFields when the text has been changed.
	 */
	protected DocumentListener textFieldListener;
	
	/**
	 * Constructor of the Base Screen class.
	 * 
	 * @param container The parent container to add the new components to.
	 * @param stationsManager Stations and Routes available.
	 */
	public BaseScreen(Container container, StationsManager stationsManager)
	{
		// Creates the action listener for components in this screen
		this.actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) { actionListenerPerformed(e); }
		};
		
		// Creates the document listener for components in this screen
		// Overrides different update types to call the same method
		this.textFieldListener = new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) { textFieldAction(e); }

			@Override
			public void removeUpdate(DocumentEvent e) { textFieldAction(e); }

			@Override
			public void changedUpdate(DocumentEvent e) { textFieldAction(e); }
		};
	}
	
	/**
	 * Creates a new panel containing a JSeparator.
	 * Panel has extra vertical margin.
	 * 
	 * @return The new panel.
	 */
	protected JPanel createSeparatorPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(16, 0, 16, 0)); // Adds vertical padding
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(new JSeparator()); // Adds a separator into the panel
		
		return panel;
	}
	
	/**
	 * Sets the icon of a given label to an image from the given directory.
	 * 
	 * @param image Directory of the image.
	 * @param label Label to set the icon to.
	 */
	protected void addImageToLabel(String image, JLabel label)
	{
		try
		{
			BufferedImage bufferedImage = ImageIO.read(new File(image)); // Reads from the given directory and creates an image if possible
			label.setIcon(new ImageIcon(bufferedImage)); // Sets the icon to the image
		}
		catch (Exception e) { }
	}
	
	/**
	 * Called when the action listener is triggered.
	 * 
	 * @param e Event information
	 */
	protected void actionListenerPerformed(ActionEvent e) { }
	
	/**
	 * Called when a textField is interacted with by the user.
	 * 
	 * @param e Event information.
	 */
	protected void textFieldAction(DocumentEvent e) { }
}

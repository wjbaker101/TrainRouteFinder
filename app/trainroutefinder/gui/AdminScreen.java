package app.trainroutefinder.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.trainroutefinder.gui.utils.GuiStyle;
import app.trainroutefinder.objects.Route;
import app.trainroutefinder.objects.StationsManager;
import app.trainroutefinder.utils.FileUtils;

/**
 * Contains components for the "Admin Tools" tab from the main screen.
 * 
 * @author William Baker
 *
 */
public class AdminScreen extends BaseScreen
{
	// Stores the stations information available to the user
	private StationsManager stationsManager;

	// ComboBoxes, allows the user to pick an item from the given options
	private JComboBox<String> departingCombo, destinationCombo;
	
	// JLists, allows the user to pick an item from a set of items
	private JList<String> stopsList;
	
	// Stores the items for the stopsList
	private DefaultListModel<String> stopsListModel; 

	// TextFields, allows the user to input text
	private JTextField newStopTextField;
	
	/**
	 * Initialises screen by adding components to the tab.
	 * 
	 * @param container The parent components to add the new components to.
	 * @param stationsManager Stations and Routes available.
	 */
	public AdminScreen(Container container, StationsManager sm)
	{
		super(container, sm);
		stationsManager = sm;
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new EmptyBorder(16, 8, 16, 8));
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel leftComponentsPanel = new JPanel();
		leftComponentsPanel.setLayout(new BoxLayout(leftComponentsPanel, BoxLayout.Y_AXIS));
		
		addLeftComponents(leftComponentsPanel);
		
		leftPanel.add(leftComponentsPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new EmptyBorder(16, 8, 16, 8));
		rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel rightComponentsPanel = new JPanel();
		rightComponentsPanel.setLayout(new BoxLayout(rightComponentsPanel, BoxLayout.Y_AXIS));
		
		addRightComponents(rightComponentsPanel);
		
		rightPanel.add(rightComponentsPanel);

		// Add components to main panel
		container.add(leftPanel);
		container.add(rightPanel);
		
		addStopsToList();
	}

	/**
	 * Adds components to the left panel.
	 * 
	 * @param panel Left panel.
	 */
	private void addLeftComponents(JPanel panel)
	{
		// Title panel
		// Add title components to title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel titleLabel = new JLabel("Edit Routes:");
		titleLabel.setFont(GuiStyle.Fonts.SUBHEADING);
		
		titlePanel.add(titleLabel);
		
		// Departing station panel
		// Add departing components to departing panel
		JPanel departingPanel = new JPanel();
		departingPanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		departingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel departingLabel = new JLabel("Select Departing station:");
		departingLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		departingCombo = new JComboBox<String>();
		departingCombo.setActionCommand("departingCombo"); // Sets unique action command
		departingCombo.addActionListener(this.actionListener); // Sets listener for the comboBox
		addDepartingStations(); // Adds initial departing stations
		addDepartingStations();
		
		departingPanel.add(departingLabel);
		departingPanel.add(departingCombo);
		
		// Destination station panel
		// Add destination components to destination panel
		JPanel destinationPanel = new JPanel();
		destinationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel destinationLabel = new JLabel("Select Destination station:");
		destinationLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		destinationCombo = new JComboBox<String>();
		destinationCombo.setActionCommand("destinationCombo"); // Sets unique action command
		destinationCombo.addActionListener(this.actionListener); // Sets listener for the comboBox
		addDestinationStations(); // Adds initial destination stations
		addDestinationStations();
		
		destinationPanel.add(destinationLabel);
		destinationPanel.add(destinationCombo);

		// Add components to main panel
		panel.add(titlePanel);
		panel.add(departingPanel);
		panel.add(destinationPanel);
	}

	/**
	 * Adds components to the right panel.
	 * 
	 * @param panel Right panel.
	 */
	private void addRightComponents(JPanel panel)
	{
		// Title panel
		// Add title components to title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(0, 0, 8, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel titleLabel = new JLabel("Stops:");
		titleLabel.setFont(GuiStyle.Fonts.SUBHEADING);
		
		titlePanel.add(titleLabel);
		
		// Description panel
		// Add description components to description panel
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		descriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel descriptionLabel = new JLabel("List of stops in the selected route, from first to last.");
		
		descriptionPanel.add(descriptionLabel);
		
		// List panel
		// Add list components to list panel
		JPanel listPanel = new JPanel();
		listPanel.setBorder(new EmptyBorder(0, 0, 8, 0));
		listPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		stopsList = new JList<String>();
		stopsListModel = new DefaultListModel<String>();
		stopsList.setModel(stopsListModel); // Creates model and adds it to stops list
		
		JScrollPane stopsScrollPane = new JScrollPane(stopsList); // Add scrollbar to list
		stopsScrollPane.setPreferredSize(new Dimension(150, 150));
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		
		JButton upButton = new JButton("\u25B2"); // Unicode for UP arrow
		upButton.setFocusable(false);
		upButton.setActionCommand("upButton");
		upButton.addActionListener(actionListener);
		
		JButton downButton = new JButton("\u25Bc"); // Unicode for DOWN arrow
		downButton.setFocusable(false);
		downButton.setActionCommand("downButton");
		downButton.addActionListener(actionListener);
		
		controlsPanel.add(upButton);
		controlsPanel.add(downButton);
		
		listPanel.add(stopsScrollPane);
		listPanel.add(controlsPanel);
		
		// New stop panel
		// Add new stop components to new stop panel
		JPanel newStopPanel = new JPanel();
		newStopPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		newStopTextField = new JTextField();
		newStopTextField.setPreferredSize(new Dimension(150, newStopTextField.getPreferredSize().height));
		
		JButton newStopButton = new JButton("+");
		newStopButton.setActionCommand("newStopButton");
		newStopButton.addActionListener(actionListener);
		
		JButton removeStopButton = new JButton("-");
		removeStopButton.setActionCommand("removeStopButton");
		removeStopButton.addActionListener(actionListener);
		
		newStopPanel.add(newStopTextField);
		newStopPanel.add(newStopButton);
		newStopPanel.add(removeStopButton);

		// Add components to main panel
		panel.add(titlePanel);
		panel.add(descriptionPanel);
		panel.add(listPanel);
		panel.add(newStopPanel);
	}

	/**
	 * Called when a ComboBox or Button is interacted with by the user.
	 */
	@Override
	protected void actionListenerPerformed(ActionEvent e)
	{
		// Checks what the action command of the incoming comboBox is
		// Each comboBox has a unique action command to differentiate between them 
		switch (e.getActionCommand())
		{
			case "departingCombo":
				addDestinationStations();
				addStopsToList();
				break;
			case "destinationCombo":
				addStopsToList();
				break;
			case "saveButton":
				saveToFile();
				break;
			case "newStopButton":
				newStop();
				saveToFile();
				break;
			case "removeStopButton":
				removeStop();
				saveToFile();
				break;
			case "upButton":
				swapStopList(-1);
				break;
			case "downButton":
				swapStopList(1);
				break;
		}
	}
	
	/**
	 * Adds departing stations.
	 */
	private void addDepartingStations()
	{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for (String station : stationsManager.stations)
		{
			model.addElement(station); // Adds the departing station name to the array
		}
		departingCombo.setModel(model);
	}
	
	/**
	 * Adds destination stations for the current departing station.
	 */
	private void addDestinationStations()
	{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for (Route route : stationsManager.routes)
		{
			// Checks whether the route's departing station is equal to the station that has been selected by the user
			if (route.getDepartingStation().equals(departingCombo.getSelectedItem().toString()))
				model.addElement(route.getDestinationStation()); // Adds destination to the array
		}
		destinationCombo.setModel(model);
	}
	
	/**
	 * Adds stops from current route to the JList.
	 */
	private void addStopsToList()
	{
		// Attempts to find a route from the currently selected departing and destination stations
		Route route = stationsManager.findRoute(departingCombo.getSelectedItem().toString(), destinationCombo.getSelectedItem().toString());
		
		if (route != null) // Checks whether the route exists
		{
			stopsListModel.clear(); // Clears model of existing stops
			
			// Loops through each stop
			// Adds each stop to the model
			for(String stop : route.getStops())
			{
				stopsListModel.addElement(stop);
			}
		}
	}
	
	/**
	 * Saves the current routes to file.
	 */
	private void saveToFile()
	{
		try
		{
			FileUtils.writeStationsToFile(stationsManager.routes, stationsManager.getSaveLocation().getAbsolutePath()); // Attempts to write stations to a file
		}
		catch(Exception ex) { ex.printStackTrace(); }
	}
	
	/**
	 * Adds a new stop into the currently selected route and updates list.
	 */
	private void newStop()
	{
		if (newStopTextField.getText().length() > 0) // Checks whether there is a stop to add
		{
			// Attempts to find a route from the currently selected departing and destination stations
			Route route = stationsManager.findRoute(departingCombo.getSelectedItem().toString(), destinationCombo.getSelectedItem().toString());
					
			if (route != null) // Checks whether the route exists
			{
				route.getStops().add(newStopTextField.getText());
				stopsListModel.addElement(newStopTextField.getText());
				
				newStopTextField.setText("");
			}
		}
	}
	
	/**
	 * Removes the currently selected stop from the current route.
	 */
	private void removeStop()
	{
		if (stopsList.getSelectedIndex() > -1) // Checks whether a stop has been selected
		{
			// Attempts to find a route from the currently selected departing and destination stations
			Route route = stationsManager.findRoute(departingCombo.getSelectedItem().toString(), destinationCombo.getSelectedItem().toString());
			
			route.getStops().remove(stopsList.getSelectedIndex()); // Removes stop from route
			stopsListModel.removeElementAt(stopsList.getSelectedIndex()); // Removes stop from list
		}
	}
	
	/**
	 * Moves the selected stop up or down depending on the button that has been pressed.
	 */
	private void swapStopList(int direction)
	{
		if (stopsList.getSelectedIndex() > -1) // Checks whether a stop has been selected
		{
			// Stores the position the selected stop should move to
			int newPosition = stopsList.getSelectedIndex() + direction;
			
			// Checks if the new position is within the bounds of the array
			if (newPosition > -1 && newPosition < stopsListModel.getSize())
			{
				swapElements(stopsList.getSelectedIndex(), newPosition); // Swaps the selected stop and new stop
				
				saveToFile(); // Saves changes to the file
				
				stopsList.setSelectedIndex(newPosition); // Sets the selected index to the new position
			}
		}
	}
	
	/**
	 * Swaps 2 elements in the stops list model.
	 * 
	 * @param index1 Currently selected index.
	 * @param index2 Swap index.
	 */
	private void swapElements(int index1, int index2)
	{
		String tempItem = stopsListModel.getElementAt(index1); // Stores the item before it is overridden
		
		stopsListModel.set(index1, stopsListModel.getElementAt(index2)); // Sets the item at index1 to item at index 2
		stopsListModel.set(index2, tempItem); // Sets the item at index2 to the temporary item
		
		// Attempts to find a route from the currently selected departing and destination stations
		Route route = stationsManager.findRoute(departingCombo.getSelectedItem().toString(), destinationCombo.getSelectedItem().toString());
		
		// Updates the stops in the route
		// So that it is the same as in the list
		route.getStops().set(index1, stopsListModel.get(index1));
		route.getStops().set(index2, stopsListModel.get(index2));
	}
}

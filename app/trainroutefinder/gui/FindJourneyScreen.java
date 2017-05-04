package app.trainroutefinder.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;

import app.trainroutefinder.gui.utils.GuiStyle;
import app.trainroutefinder.objects.JourneyInformation;
import app.trainroutefinder.objects.JourneyType;
import app.trainroutefinder.objects.Route;
import app.trainroutefinder.objects.StationsManager;
import app.trainroutefinder.utils.FileUtils;
import app.trainroutefinder.utils.Utils;

/**
 * Contains components for the "Find Journey" tab from the main screen.
 * 
 * @author William Baker
 *
 */
public class FindJourneyScreen extends BaseScreen
{
	private Container container;
	
	// Stores the stations information available to the user
	private StationsManager stationsManager;
	
	// Stores the journey information the user has currently chosen
	private JourneyInformation journeyInformation;
	
	// ComboBoxes, allows the user to pick an item from the given options
	private JComboBox<String> departingCombo, destinationCombo, journeyTypeCombo, stopsCombo;
	
	// Labels, displays information to the user
	private JLabel fileErrorLabel, departingLabel, destinationLabel, stopsListLabel, dateOutputLabel, dateErrorLabel, timeOutputLabel, costOutputLabel;
	
	// TextFields, allows the user to input text
	private JTextField dateTextField;
	
	// Buttons, allows the user to perform actions
	private JButton fileSelectButton;
	
	/**
	 * Initialises screen by adding components to the tab.
	 * 
	 * @param container The parent components to add the new components to.
	 * @param stationsManager Stations and Routes available.
	 */
	public FindJourneyScreen(Container container, StationsManager sm)
	{
		super(container, sm);
		this.container = container;
		stationsManager = sm;
		journeyInformation = new JourneyInformation();
		
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
		
		container.add(leftPanel);
		container.add(rightPanel);
		
		setJourneyInformation();
	}
	
	/**
	 * Adds components to the left panel.
	 * 
	 * @param panel Left panel.
	 */
	private void addLeftComponents(JPanel panel)
	{
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel titleLabel = new JLabel("Find Journey:");
		titleLabel.setFont(GuiStyle.Fonts.SUBHEADING);
		
		titlePanel.add(titleLabel);
		
		JPanel fileSelectPanel = new JPanel();
		fileSelectPanel.setBorder(new EmptyBorder(0, 0, 8, 0));
		fileSelectPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel fileSelectLabel = new JLabel("Select routes file:");
		
		fileSelectButton = new JButton(this.stationsManager.getSaveLocation().getName());
		fileSelectButton.setActionCommand("fileSelectButton");
		fileSelectButton.addActionListener(actionListener);
		
		fileSelectPanel.add(fileSelectLabel);
		fileSelectPanel.add(fileSelectButton);
		
		JPanel fileErrorPanel = new JPanel();
		fileErrorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		fileErrorLabel = new JLabel("Unable to select file, please try again.");
		fileErrorLabel.setForeground(GuiStyle.Colours.ERROR);
		fileErrorLabel.setVisible(false);
		
		fileErrorPanel.add(fileErrorLabel);
		
		JPanel subTitlePanel = new JPanel();
		subTitlePanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		subTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel subTitleLabel = new JLabel("Edit Journey:");
		subTitleLabel.setFont(GuiStyle.Fonts.SUBHEADING);
		
		subTitlePanel.add(subTitleLabel);
		
		// Date panel
		// Add date components to date panel
		JPanel datePanel = new JPanel();
		datePanel.setBorder(new EmptyBorder(0, 0, 8, 0));
		datePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel dateLabel = new JLabel("Travel Date:");
		dateLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		dateTextField = new JTextField(10);
		dateTextField.setText((new SimpleDateFormat("dd/MM/yyyy")).format(Calendar.getInstance().getTime())); // Sets text to current date
		dateTextField.getDocument().addDocumentListener(this.textFieldListener); // Sets listener for the textField
		
		datePanel.add(dateLabel);
		datePanel.add(dateTextField);
		
		JPanel dateErrorPanel = new JPanel();
		dateErrorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		dateErrorLabel = new JLabel("Date is in the incorrect format.");
		dateErrorLabel.setVisible(false);
		dateErrorLabel.setForeground(GuiStyle.Colours.ERROR);
		
		dateErrorPanel.add(dateErrorLabel);
		
		// Departing Station panel
		// Add departing components to departing panel
		JPanel departingPanel = new JPanel();
		departingPanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		departingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel departingLabel = new JLabel("Departing Station:");
		departingLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		departingCombo = new JComboBox<String>();
		departingCombo.setActionCommand("departingCombo"); // Sets unique action command
		departingCombo.addActionListener(this.actionListener); // Sets listener for the comboBox
		addDepartingStations(); // Adds initial departing stations
		journeyInformation.setDepartingStation(departingCombo.getSelectedItem().toString());
		
		departingPanel.add(departingLabel);
		departingPanel.add(departingCombo);
		
		// Destination Station panel
		// Add departing components to destination panel
		JPanel destinationPanel = new JPanel();
		destinationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel destinationLabel = new JLabel("Destination Station:");
		destinationLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		destinationCombo = new JComboBox<String>();
		destinationCombo.setActionCommand("destinationCombo"); // Sets unique action command
		destinationCombo.addActionListener(this.actionListener); // Sets listener for the comboBox
		addDestinationStations(); // Adds initial destination stations
		journeyInformation.setDestinationStation(destinationCombo.getSelectedItem().toString());
		
		destinationPanel.add(destinationLabel);
		destinationPanel.add(destinationCombo);
		
		// Journey Type panel
		// Add Journey Type components to Journey Type panel
		JPanel journeyTypePanel = new JPanel();
		journeyTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel journeyTypeLabel = new JLabel("Journey Type:");
		journeyTypeLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		journeyTypeCombo = new JComboBox<String>();
		journeyTypeCombo.addActionListener(this.actionListener);
		addJourneyTypes(); // Adds journey types
		
		journeyTypePanel.add(journeyTypeLabel);
		journeyTypePanel.add(journeyTypeCombo);
		
		// Add components to main panel
		panel.add(titlePanel);
		panel.add(fileSelectPanel);
		panel.add(fileErrorPanel);
		panel.add(createSeparatorPanel());
		panel.add(subTitlePanel);
		panel.add(datePanel);
		panel.add(dateErrorPanel);
		panel.add(createSeparatorPanel());
		panel.add(departingPanel);
		panel.add(destinationPanel);
		panel.add(createSeparatorPanel());
		panel.add(journeyTypePanel);
	}
	
	/**
	 * Adds components to the right panel.
	 * 
	 * @param panel Right panel.
	 */
	private void addRightComponents(JPanel panel)
	{
		// Image panel
		// Add image components to image panel
		JPanel imagePanel = new JPanel();
		
		JLabel imageLabel = new JLabel();
		addImageToLabel("trains.png", imageLabel);
		
		imagePanel.add(imageLabel);
		
		// Stations panel
		// Add stations components to stations panel
		JPanel stationsPanel = new JPanel();
		
		departingLabel = new JLabel();
		JLabel stationsLabel = new JLabel("TO");
		stationsLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		destinationLabel = new JLabel();
		
		stationsPanel.add(departingLabel);
		stationsPanel.add(stationsLabel);
		stationsPanel.add(destinationLabel);
		
		// Stops panel
		// Add stops components to stops panel
		JPanel stopsPanel = new JPanel();
		stopsPanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		stopsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel stopsLabel = new JLabel("Calling at:");
		stopsLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		
		stopsCombo = new JComboBox<String>(new String[] { "Route Order", "Alphabetically" });
		stopsCombo.addActionListener(this.actionListener);
		
		stopsPanel.add(stopsLabel);
		stopsPanel.add(stopsCombo);
		
		// Stations panel
		// Add stations components to stations panel
		JPanel stopsListPanel = new JPanel();
		stopsListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		stopsListLabel = new JLabel();
		
		stopsListPanel.add(stopsListLabel);
		
		// Stations panel
		// Add stations components to stations panel
		JPanel datePanel = new JPanel();
		datePanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		datePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel dateLabel = new JLabel("Date of Travel:");
		dateLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		dateOutputLabel = new JLabel();
		
		datePanel.add(dateLabel);
		datePanel.add(dateOutputLabel);
		
		// Stations panel
		// Add stations components to stations panel
		JPanel timePanel = new JPanel();
		timePanel.setBorder(new EmptyBorder(0, 0, 16, 0));
		timePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel timeLabel = new JLabel("Journey Time:");
		timeLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		timeOutputLabel = new JLabel();
		
		timePanel.add(timeLabel);
		timePanel.add(timeOutputLabel);
		
		// Stations panel
		// Add stations components to stations panel
		JPanel costPanel = new JPanel();
		costPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
		
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setFont(GuiStyle.Fonts.SUBHEADING2);
		costOutputLabel = new JLabel();
		
		costPanel.add(costLabel);
		costPanel.add(costOutputLabel);
		
		// Add components to main panel
		panel.add(imagePanel);
		panel.add(stationsPanel);
		panel.add(createSeparatorPanel());
		panel.add(stopsPanel);
		panel.add(stopsListPanel);
		panel.add(createSeparatorPanel());
		panel.add(datePanel);
		panel.add(timePanel);
		panel.add(costPanel);
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
				journeyInformation.setDepartingStation(departingCombo.getSelectedItem().toString());
				addDestinationStations();
				break;
			case "destinationCombo":
				journeyInformation.setDestinationStation(destinationCombo.getSelectedItem().toString());
				break;
			case "fileSelectButton":
				selectFile();
				break;
		}
		setJourneyInformation();
	}

	/**
	 * Called when a TextField is interacted with by the user.
	 */
	@Override
	protected void textFieldAction(DocumentEvent e)
	{
		try  // try-catch to attempt to parse a date and catch the exception if date is not formatted correctly
		{
			// Checks if the date is valid
			if (Utils.isDateValid(dateTextField.getText()))
			{
				journeyInformation.setDate(LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				dateErrorLabel.setVisible(false); // Hides error message to show user their input is valid
				setJourneyInformation(); // Updates information
			}
		}
		catch (Exception ex)
		{
			dateErrorLabel.setVisible(true); // Shows error message to show user their input is NOT valid
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

		journeyInformation.setDestinationStation(destinationCombo.getSelectedItem().toString());
	}
	
	/**
	 * Adds journey types to comboBox.
	 */
	private void addJourneyTypes()
	{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		// Adds all journey types to the model
		for (String type : JourneyType.getJourneyTypesAsStrings())
			model.addElement(type);
		
		journeyTypeCombo.setModel(model);
	}
	
	/**
	 * Displays the current journey information.
	 */
	private void setJourneyInformation()
	{
		departingLabel.setText(journeyInformation.getDepartingStation());
		destinationLabel.setText(journeyInformation.getDestinationStation());
		
		dateOutputLabel.setText(journeyInformation.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		// Attempts to find a route from the currently selected departing and destination stations
		Route route = stationsManager.findRoute(journeyInformation.getDepartingStation(), journeyInformation.getDestinationStation());
		
		if (route != null) // Checks whether the route exists
		{
			setStopsList(route);
			timeOutputLabel.setText(Utils.Format.formatJourneyTime(route.getJourneyTime()));
			setCostLabel(route);
		}
	}
	
	/**
	 * Sets stops list label to stops from currently selected route.
	 * 
	 * @param route Currently selected route.
	 */
	private void setStopsList(Route route)
	{
		String stopsList = ""; // Stores the output of the list of stops
		
		// Loops through each stop in the route
		// Adds stop to the string
		// Checks if current stop is first in the array, so does not add comma in front
		for (String stop : getSortedStops(route))
		{
			if (stopsList.equals("")) stopsList += stop;
			else stopsList += ", " + stop;
		}
		
		stopsListLabel.setText(stopsList); // Displays new information
	}
	
	/**
	 * Gets the list of stops in order of how they should be displayed to the user.
	 * Order depends on whether the user has selected Alphabetically or in Route Order.
	 * 
	 * @param route Currently selected route.
	 * @return Array of stops in the correct order for displaying.
	 */
	private List<String> getSortedStops(Route route)
	{
		if (stopsCombo.getSelectedItem().equals("Alphabetically")) // Checks whether the user wants to view the stops in alphabetical order
		{
			List<String> sortedList = new ArrayList<String>();
			
			for (String stop : route.getStops())
			{
				sortedList.add(stop);
			}
			Collections.sort(sortedList);
			
			return sortedList;
		}
		return route.getStops();
	}
	
	/**
	 * Updates the cost information of the current journey.
	 * 
	 * @param route Currently selected route.
	 */
	private void setCostLabel(Route route)
	{
		float cost; // Stores cost to be displayed
		
		// Checks which journey type has been selected
		// Sets correct cost
		switch (JourneyType.getJourneyTypeByName(journeyTypeCombo.getSelectedItem().toString()))
		{
			case RETURN:
				cost = route.getReturnCost();
				break;
			default:
				cost = route.getSingleCost();
				break;
		}
		
		String discountMessage = ""; // Stores discount message
		
		// Checks whether the current journey date is on the last day of the month
		if (Utils.MONTHS[journeyInformation.getDate().getMonthValue() - 1] == journeyInformation.getDate().getDayOfMonth())
		{
			cost *= 0.9F; // Reduces cost by 10%
			discountMessage = " (10% off!)"; // Sets discount message
		}
		
		costOutputLabel.setText("£" + Utils.Format.formatCost(cost) + discountMessage); // Displays new information
	}
	
	/**
	 * Allows the user to select a file, which should contain the routes available.
	 */
	private void selectFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); // Sets the directory of the fileChooser to the directory of the java application
		
		int result = fileChooser.showOpenDialog(container); // Gets the option the user has chosen
		
		if (result == JFileChooser.APPROVE_OPTION) // Checks whether the user has chosen to select a file
		{
			File selectedFile = fileChooser.getSelectedFile(); // Gets the chosen file directory
			
			try
			{
				stationsManager.setSaveLocation(selectedFile);
				stationsManager.routes = FileUtils.readRoutesFromFile(selectedFile.getAbsolutePath()); // Attempts to read stations from selected file
				fileSelectButton.setText(selectedFile.getName());
				fileErrorLabel.setVisible(false);
			}
			catch (Exception e)
			{
				fileErrorLabel.setVisible(true);
			}
		}
	}
}

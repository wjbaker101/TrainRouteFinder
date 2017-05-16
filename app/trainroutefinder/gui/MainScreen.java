package app.trainroutefinder.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import app.trainroutefinder.gui.utils.GuiStyle;
import app.trainroutefinder.objects.StationsManager;

/**
 * Main Screen for the user to view and interact with.
 * 
 * @author William Baker
 *
 */
public class MainScreen extends BaseScreen
{
	/**
	 * Initialises screen by adding components to the tab.
	 * 
	 * @param container The parent components to add the new components to.
	 * @param stationsManager Stations and Routes available.
	 */
	public MainScreen(Container container, StationsManager stationsManager)
	{
		super(container, stationsManager);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(16, 16, 16, 16));
		
		JLabel titleLabel = new JLabel("Train Route Finder");
		titleLabel.setFont(GuiStyle.Fonts.HEADING);
		
		titlePanel.add(titleLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFocusable(false); // Removes dotted border when focused
		
		JPanel journeyTabPanel = new JPanel();
		journeyTabPanel.setLayout(new GridLayout(0, 2, 0, 0));
		new FindJourneyScreen(journeyTabPanel, stationsManager); // Adds components from Find Journey screen
		
		// Makes journeyTabPanel scrollable
		JScrollPane findJourneyPane = new JScrollPane(journeyTabPanel);
		findJourneyPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // Removed border around scroll pane
		
		JPanel adminTabPanel = new JPanel();
		adminTabPanel.setLayout(new GridLayout(0, 2, 0, 0));
		new AdminScreen(adminTabPanel, stationsManager); // Adds components from Admin screen

		// Makes adminTabPanel scrollable
		JScrollPane adminPane = new JScrollPane(adminTabPanel);
		adminPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // Removed border around scroll pane
		
		// Adds tabs to tabbedPane
		tabbedPane.addTab("Find Journey", findJourneyPane);
		tabbedPane.addTab("Admin Tools", adminPane);
		
		// Adds components
		
		container.add(titlePanel, BorderLayout.NORTH);
		container.add(tabbedPane, BorderLayout.CENTER);
	}
}

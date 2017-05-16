package app.trainroutefinder.objects;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Stores the available stations and routes for the user to travel between.
 * 
 * @author William Baker
 *
 */
public class StationsManager
{
	/**
	 * List of stations.
	 */
	public List<String> stations;
	
	/**
	 * List of routes available between 2 stations.
	 */	
	public List<Route> routes;
	
	/**
	 * Stores the save location for the file storing the routes.
	 */
	private File saveLocation;
	
	/**
	 * Initialises the stations and routes.
	 * Creates default arrays for the stations and routes.
	 */
	public StationsManager()
	{
		// Create array of stations
		this.stations = Arrays.asList
		(
			"Leicester",
			"Loughborough",
			"Nottingham",
			"Derby",
			"York"
		);
		
		// Create array of routes
		this.routes = Arrays.asList
		(
			new Route("Leicester", "Loughborough", 2.50F, 4.00F, 10),
			new Route("Leicester", "Nottingham", 3.50F, 6.20F, 30),
			new Route("Leicester", "Derby", 3.70F, 7.00F, 48),
			new Route("Leicester", "York", 12.20F, 25.0F, 70),

			new Route("Loughborough", "Leicester", 2.50F, 4.00F, 10),
			new Route("Loughborough", "Nottingham", 1.50F, 2.50F, 15),
			new Route("Loughborough", "Derby", 2.00F, 2.50F, 25),
			new Route("Loughborough", "York", 12.00F, 20.00F, 60),
			
			new Route("Nottingham", "Leicester", 3.50F, 6.20F, 30),
			new Route("Nottingham", "Loughborough", 1.50F, 2.50F, 155),
			new Route("Nottingham", "Derby", 1.50F, 3.00F, 10),
			new Route("Nottingham", "York", 8.20F, 16.00F, 40),
			
			new Route("Derby", "Leicester", 3.70F, 7.00F, 48),
			new Route("Derby", "Loughborough", 1.25F, 2.50F, 23),
			new Route("Derby", "Nottingham", 2.50F, 3.00F, 12),
			new Route("Derby", "York", 11.20F, 16.00F, 75),
			
			new Route("York", "Leicester", 23.50F, 25.00F, 65),
			new Route("York", "Loughborough", 11.50F, 20.00F, 60),
			new Route("York", "Nottingham", 11.50F, 16.00F, 40),
			new Route("York", "Derby", 7.20F, 16.00F, 85)
		);
		
		// Sets the default save location to the stations.trf file
		// This will be in the same directory as the one the application is in
		this.setSaveLocation(new File("stations.trf"));
	}
	
	/**
	 * Finds a route that has the given departing and destination stations.
	 * 
	 * @param departing Departing Station.
	 * @param destination Destination Station.
	 */
	public Route findRoute(String departing, String destination)
	{
		// Loops through every route
		for (Route route : this.routes)
		{
			// Checks whether the departing and destination stations match
			if (route.getDepartingStation().equals(departing) && route.getDestinationStation().equals(destination))
				return route;
		}
		return null;
	}
	
	/**
	 * Gets the directory the current select file is location in.
	 * 
	 * @return The directory of the file.
	 */
	public File getSaveLocation() { return this.saveLocation; }
	
	/**
	 * Sets the location of the save file in a new directory.
	 * 
	 * @param newLocation New directory.
	 */
	public void setSaveLocation(File newLocation) { this.saveLocation = newLocation; }
}

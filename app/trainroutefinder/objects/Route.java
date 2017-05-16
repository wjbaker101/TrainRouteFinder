package app.trainroutefinder.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for a route between 2 stations.
 * Implements Serializable so it can be saved in a file.
 * 
 * @author William Baker
 *
 */
public class Route implements Serializable
{
	/**
	 * Serial ID for serialisation.
	 */
	private static final long serialVersionUID = 1130827680500960664L;

	/**
	 * The departing station of the route.
	 */
	private String departingStation;
	
	/**
	 * The destination station of the route.
	 */
	private String destinationStation;
	
	/**
	 * Single cost (in GBP) for the train journey.
	 */
	private float singleCost;
	
	/**
	 * Return cost (in GBP) for the train journey.
	 */
	private float returnCost;
	
	/**
	 * Length of the journey (in minutes).
	 */
	private int journeyTime;
	
	/**
	 * List of stops on this route.
	 */
	private List<String> stops;
	
	/**
	 * Constructor, initialises a route.
	 * 
	 * @param station Destination station.
	 * @param single Cost of a single journey (in GBP)
	 * @param returning Cost of a return journey (in GBP)
	 * @param time Journey time of the journey (in minutes)
	 */
	public Route(String depStation, String desStation, float single, float returning, int time)
	{
		this.departingStation = depStation;
		this.destinationStation = desStation;
		this.singleCost = single;
		this.returnCost = returning;
		this.journeyTime = time;
		this.stops = new ArrayList<String>();
	}
	
	/**
	 * Gets the departing station.
	 */
	public String getDepartingStation() { return this.departingStation; }
	
	/**
	 * Gets the destination station.
	 */
	public String getDestinationStation() { return this.destinationStation; }
	
	/**
	 * Gets the single journey cost.
	 */
	public float getSingleCost() { return this.singleCost; }
	
	/**
	 * Gets the return journey cost.
	 */
	public float getReturnCost() { return this.returnCost; }
	
	/**
	 * Gets the journey time.
	 */
	public int getJourneyTime() { return this.journeyTime; }
	
	/**
	 * Gets the stops in the route.
	 */
	public List<String> getStops() { return this.stops; }
}

package app.trainroutefinder.objects;

import java.time.LocalDate;

/**
 * Journey object, contains the information about a train journey.
 * 
 * @author William Baker
 *
 */
public class JourneyInformation
{
	/**
	 * Departing station of the current journey.
	 */
	private String departingStation;
	
	/**
	 * Destination station of the current journey.
	 */
	private String destinationStation;
	
	/**
	 * The type of journey.
	 * e.g. Single or Return
	 */
	private JourneyType journeyType;
	
	/**
	 * Travel date of the current journey.
	 */
	private LocalDate date;
	
	/**
	 * Constructor, initialises default values for a journey.
	 */
	public JourneyInformation()
	{
		this.departingStation = null; // No current departing station
		this.destinationStation = null; // No current destination station
		this.journeyType = JourneyType.SINGLE; // Default journey type is single
		this.date = LocalDate.now(); // Initial date is today
	}
	
	/**
	 * Sets the departing station to a new station.
	 * 
	 * @param newStation The new station.
	 */
	public void setDepartingStation(String newStation) { this.departingStation = newStation; }
	
	/**
	 * Gets the departing station.
	 * 
	 * @return The departing station object.
	 */
	public String getDepartingStation() { return this.departingStation; }
	
	/**
	 * Sets the destination station to a new station.
	 * 
	 * @param newStation The new station.
	 */
	public void setDestinationStation(String newStation) { this.destinationStation = newStation; }
	
	/**
	 * Gets the destination station.
	 * 
	 * @return The destination station object.
	 */
	public String getDestinationStation() { return this.destinationStation; }
	
	/**
	 * Gets the type of journey.
	 * 
	 * @return The type of journey enumerated value.
	 */
	public JourneyType getJourneyType() { return this.journeyType; }
	
	/**
	 * Sets the date of travel to a new date.
	 * 
	 * @param newDate New date of travel.
	 */
	public void setDate(LocalDate newDate) { this.date = newDate; }
	
	/**
	 * Gets the journey's date of travel.
	 * 
	 * @return Current date of travel.
	 */
	public LocalDate getDate() { return this.date; }
}

package app.trainroutefinder.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for useful functions that could be used anywhere in the application.
 * 
 * @author William Baker
 *
 */
public class Utils
{
	/**
	 * The number of days in each of the months.
	 */
	public static int[] MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/**
	 * Checks if the given date is in the correct format and is valid.
	 * Uses LocalDate.parse which will throw a DateTimeParseException if date is in the incorrect format.
	 * LocalDate.parse does not take into consideration some months have < 31 days.
	 * Checks if the date has the correct number of days in the current month.
	 * 
	 * @param date Date to check.
	 * 
	 * @return Whether the date is valid.
	 * 
	 * @throws DateTimeParseException
	 */
	public static boolean isDateValid(String date) throws DateTimeParseException
	{
		// Attempts to update the journey information to a new date with the format dd/MM/yyyy
		LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		String[] split = date.split("/"); // Splits the date at "/"s to separate day, month and year
		
		int day = Integer.parseInt(split[0]); // Gets the integer value of the day
		int month = Integer.parseInt(split[1]); // Gets the integer value of the month
		
		if (day <= MONTHS[month - 1])
			return day <= MONTHS[month - 1]; // Checks whether the day is less than or equal to the maximum number of days
		else
			throw new DateTimeParseException("Number of days is invalid.", date, 0); // Throws exception if number of days is invalid
	}
	
	/**
	 * Methods used to format.
	 * 
	 * @author William Baker
	 *
	 */
	public static class Format
	{
		/**
		 * Rounds a float number to a specified number of places.
		 * 
		 * @param number The number to round.
		 * @param places Number of decimal places.
		 * 
		 * @return Number rounded to the specified number of decimal places.
		 */
		public static float round(float number, int places)
		{
			// Find the multiplier that will multiply the float by
			// Rounds the multiplied number to get rid of unnecessary decimals
			// Divides the number by the same multiplier to form the rounded number to the correct number of decimal places
			float multiplier = (float)Math.pow(10, places);
			return Math.round(number * multiplier) / multiplier;
		}
		
		/**
		 * Formats the journey time to display it in hours and minutes.
		 * 
		 * @param minutes Journey time in minutes.
		 * 
		 * @return Journey time in hours and minutes.
		 */
		public static String formatJourneyTime(int minutes)
		{
			int hours = minutes / 60; // Gets the number of full hours
			
			if (hours > 0) // Checks whether there are actually hours to display
			{
				int remainingMinutes = minutes - (hours * 60); // Gets the remaining number of minutes
				return hours + "h" + remainingMinutes + "m"; // Returns the journey time in hours and minutes
			}
			else
			{
				return minutes + "m"; // Returns the journey time in minutes
			}
		}
		
		/**
		 * Formats the cost so that it will always be to 2 decimals places.
		 * 
		 * @param cost The cost to format.
		 * 
		 * @return The formatted cost as a string.
		 */
		public static String formatCost(float cost)
		{
			cost = Utils.Format.round(cost, 2); // Rounds the cost to 2 decimal places
			
			// Gets the cost as a string
			String formattedCost = "" + cost;
			// Gets the decimals of the cost
			String decimals = formattedCost.substring(formattedCost.indexOf(".") + 1);
			
			if (decimals.length() < 2) // Checks whether there are less than 2 decimal places
				formattedCost += "0"; // Add leading 0
			
			// Return formatted cost
			return formattedCost;
		}
	}
}

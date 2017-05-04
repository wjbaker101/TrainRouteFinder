package app.trainroutefinder.objects;

/**
 * The type of journey the user can choose between.
 * 
 * @author William Baker
 *
 */
public enum JourneyType
{
	/**
	 * Single journey type.
	 */
	SINGLE,
	
	/**
	 * Return journey type.
	 */
	RETURN;
	
	/**
	 * Gets the journey type from a string.
	 * Used for settings the value from a ComboBox.
	 * 
	 * @param name The search string value.
	 * 
	 * @return The type of journey.
	 */
	public static JourneyType getJourneyTypeByName(String name)
	{
		// Checks through a range of values, using a switch
		// .toLowerCase() makes the check case insensitive
		switch (name.toLowerCase())
		{
			case "return": // Whether the string is equal to "return"
				return RETURN;
			default: // If no values match, return a "Single" journey type by default
				return SINGLE;
		}
	}
	
	/**
	 * Gets a list of all possible journey types.
	 * 
	 * @return List of journey types.
	 */
	public static String[] getJourneyTypesAsStrings()
	{
		// Creates an array with the same size as the number of journey types
		String[] types = new String[JourneyType.values().length];
		
		// Adds each journey type as a string to the newly created array
		for (int i = 0; i < JourneyType.values().length; ++i)
		{
			types[i] = JourneyType.values()[i].toString();
		}
		return types;
	}
}

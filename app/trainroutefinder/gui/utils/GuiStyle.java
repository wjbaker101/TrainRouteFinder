package app.trainroutefinder.gui.utils;

import java.awt.Color;
import java.awt.Font;

/**
 * Stores styling for components.
 * 
 * @author William Baker
 *
 */
public class GuiStyle
{
	/**
	 * Stores fonts used on components in the window.
	 *
	 */
	public static class Fonts
	{
		/**
		 * Base font, used for components as a default font.
		 */
		public static final Font REGULAR = new Font("Arial", Font.PLAIN, 12);
		
		/**
		 * Heading font, used for first level headings.
		 */
		public static final Font HEADING = new Font("Arial", Font.BOLD, 18);
		
		/**
		 * Subheading font, used for second level headings.
		 */
		public static final Font SUBHEADING = new Font("Arial", Font.BOLD, 14);
		
		/**
		 * Subheading2 font, used for third level headings.
		 */
		public static final Font SUBHEADING2 = new Font("Arial", Font.BOLD, 12);
	}
	
	/**
	 * Stores colours used on components or text in the window.
	 *
	 */
	public static class Colours
	{
		/**
		 * Error colour, to show that an error has occured.
		 */
		public static final Color ERROR = new Color(230, 0, 0);
	}
}

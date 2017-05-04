package app.trainroutefinder.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import app.trainroutefinder.objects.Route;

/**
 * Methods used when needing to access or modify files to store stations.
 * 
 * @author William Baker
 *
 */
public class FileUtils
{
	/**
	 * Writes a list of stations to a file.
	 * 
	 * @param stations A list of stations to store in the file.
	 * @param fileName The name of the file to write to.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void writeStationsToFile(List<Route> routes, String fileName) throws IOException, FileNotFoundException
	{
		// Create and open output streams for writing
		FileOutputStream fileStream = new FileOutputStream(fileName);
		ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
		
		// Write the array into the file
		objectStream.writeObject(routes);
		
		// Close the output streams
		objectStream.close();
		fileStream.close();
	}
	
	/**
	 * Reads from a file and creates an array of stations that are stored in it.
	 * 
	 * @param fileName The name of the file to read.
	 * @return A list of stations.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static List<Route> readRoutesFromFile(String fileName) throws IOException, FileNotFoundException, ClassNotFoundException
	{
		// Create and open input streams for reading
		FileInputStream fileStream = new FileInputStream(fileName);
		ObjectInputStream objectStream = new ObjectInputStream(fileStream);
		
		// Read the object stored in the file
		Object fileObject = objectStream.readObject();
		
		// Close the input streams
		objectStream.close();
		fileStream.close();
		
		return (List<Route>)fileObject; // Return the object as an array of stations
	}
}
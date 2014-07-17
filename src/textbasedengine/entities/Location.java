package textbasedengine.entities;

import java.io.File;
import textbasedengine.entities.Player;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The representation of a position the player can move to. Contains information on the phrases that move the player to another room
 * @author Austin
 * @version 0.0.1
 */
public class Location {
	private static Location[] all;
	
	/**
	 * @return The first room for the player to start in
	 */
	public static Location start() { return all[0]; }
	
	/**
	 * Initializes the location data from the stored text file
	 */
	public static void Load() {
		File xml = new File("src/textbasedengine/entities/LocationData.xml");
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
			NodeList locations = doc.getElementsByTagName("Location");
			
			all = new Location[locations.getLength()];
			
			for (int i = 0; i < locations.getLength(); i++) {
				Element location = (Element)locations.item(i);
				
				NodeList values;
				
				String desc;
				String[] phrases;
				int[] connections;
				boolean[] open;
				String[] enterEvents;
				
				desc = location.getElementsByTagName("Description").item(0).getTextContent().trim();
				
				values = location.getElementsByTagName("Connection");
				phrases = new String[values.getLength()];
				connections = new int[values.getLength()];
				open = new boolean[values.getLength()];
				
				for (int x = 0; x < values.getLength(); x++) {
					Element data = (Element)values.item(x);
					connections[x] = Integer.parseInt(data.getAttribute("id"));
					phrases[x] = data.getAttribute("phrase");
					open[x] = !data.getAttribute("open").equals("false");
				}
				
				values = location.getElementsByTagName("OnEnter");
				if (values.getLength() > 0)
					enterEvents = values.item(0).getTextContent().trim().split("\\s+");
				else
					enterEvents = new String[0];
				
				all[i] = new Location(desc, phrases, connections, open, enterEvents);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Handles an array of events for a location
	 * @param events The events to handle
	 */

	public static void handleEvents(String[] events) {
		for (String event : events)
			switch (event.toUpperCase()) {
				case "DEATH":
					Stats.setHealth(0);
					break;
			}
	}
	
	private String desc;
	private String[] phrases;
	private int[] connections;
	private boolean[] open;
	private String[] enterEvents;
	
	/**
	 * @param desc The description given on the room being entered or looked at
	 * @param phrases The phrases that connect adjacent locations
	 * @param connections Ids of connected locations
	 * @param open Whether an adjacent location is open to be moved to
	 * @param enterEvents The events that occur when this room is entered
	 */
	public Location(String desc, String[] phrases, int[] connections, boolean[] open, String[] enterEvents) {
		this.desc = desc;
		this.phrases = phrases;
		this.connections = connections;
		this.open = open;
		this.enterEvents = enterEvents;
	}
	
	/**
	 * @return The description for this room
	 */
	public String getDescription() { return desc; }
	
	/**
	 * Determines if a given command matches any adjacent location phrases
	 * @param phrase The given command
	 * @return Whether there was a match found
	 */
	public boolean checkPhrase(String phrase) {
		for (int i = 0; i < phrases.length; i++)
			if (phrases[i].equals(phrase) && open[i]) {
				Player.moveTo(all[connections[i]]);
				return true;
			}
		
		return false;
	}
	
	/**
	 * Called when a location is entered, runs all onEnter events
	 */
	public void onEnter() { handleEvents(enterEvents); }
}

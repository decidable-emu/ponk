import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	//how rooms are referred to in-game
	private String name;
	//how rooms are described when they are entered
	private String description;
	//the things in the room the player can interact with
	private HashMap<String, Item> containedItems;
	//triggers for those things
	private HashMap<String, Item> triggers;
	//entrances/exits

	public Room(String n) {
		name = n;
		containedItems = new HashMap<>();
	}

	public void setDescription(String d) {
		description = d;
	}

	public void setContainedItems(Item ... items) {
		for (Item item : items) {
			containedItems.put(item.getName(), item);
		}
	}

	public void addTrigger(String s, Item i) {
		triggers.put(s, i);
	}

	public Item searchForItem(String answer) {
		return containedItems.get(answer);
	}

/*	public void doInteraction(String i) {
		Item relevantItem = containedItems.get(i);
		String result = relevantItem.getResult();
	}*/

	@Override
	public String toString() {
		return name;
	}
}

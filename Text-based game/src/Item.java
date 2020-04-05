import java.util.ArrayList;

public class Item {
	//what the item is referred to in the inventory
	private String name;
	//items have a description
	private String description;
	//the things the user can do with this item
	private ArrayList<Question> interactions = new ArrayList<>();
	//need to have a list of the Interactions(?) you can have with each item
		//eg: painting you can: look
			//looking involves
				//reading out a short description
	//each interaction has something Abbey says in response
	//if you don't say anything in that list, Abbey calls you out on it

	// take/pick up/carry

	public Item(String n) {
		name = n;
	}


	public String getName() {
		return name;
	}

	public void setDescription(String d) {
		description = d;
	}

	public void setInteractions(Question... is) {
		for (Question i : is ) {
			interactions.add(i);
		}
	}

	@Override
	public String toString() {
		return name;
	}
}

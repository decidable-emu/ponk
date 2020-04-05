import java.util.Scanner;

public class WorldMap {

/*	public static void main(String[] args) {
		Room cargoHold = new Room("cargo hold");
		cargoHold.setDescription("a small, squarish room");

		Item grate = new Item("grate");
		grate.setDescription("it's old and rusty. The screws bolting it to the wall are loose");
		Question open = new Question("open");
		grate.setInteractions(open);

		Item painting = new Item("Edvard Munch painting");
		painting.setDescription("it's some guy on a ship screaming his head off with a sunset in the background. " +
				"Has he never seen a sunset before??");
		Question look = new Question("look");
		look.setTriggerWords("look", "observe", "bask", "inspect", "see");
		look.setResult("You want me to look at this painting? ...........Great, I feel cultured. Now can we get a" +
				" move on already??");
		painting.setInteractions(look);

		cargoHold.setContainedItems(grate, painting);


		//so user is in the correct room
		//user types "painting"
			//this triggers a hashmap associated with the room
		//this triggers the painting Item
		//now you get a description of the item


		//so, say, looking at a painting
			//user types "look" or "observe"
			//Interation associated with that is "look"
			//result associated with that interaction is "great, now I feel cultured."

		Scanner scanner = new Scanner(System.in);
		Room currentRoom = cargoHold;
		System.out.println(currentRoom);
		//todo ??????
		while (true) {
			TextBasedGame.prompt("What would you like to look at?");
			Item currentItem = currentRoom.searchForItem(TextBasedGame.answer);
			System.out.println(currentItem);
		}
	}*/

}
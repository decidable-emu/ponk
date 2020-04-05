import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class BuildyBot {

	static Scanner scanner = new Scanner(System.in);
	//answer is used to store the user's response to prompts for later analysis
	static String answer;
	//validAnswer is used to determine whether the user needs to try answering again
	static boolean validAnswer;
	//this is the character with whom you're currently interacting with
	static String charSpeaking = "Computer";
	//this is the player's name
	static String playerName = "BuildyBot";
	//known commands
	private static ArrayList<Command> knownCommands = new ArrayList<>();

	static String italics(String string) {
		return "\033[3m" + string + "\033[0m";
	}

	//new line that prints the string specified and waits for the user to press enter
	static void nLine(String line) {
		//this gives a space between this line and the line before
		System.out.println();
		System.out.print(line);
		scanner.nextLine();
	}

	//prints a line without a space before, so it remains as part of the same paragraph
	static void cLine(String line) {
		System.out.print(line);
		scanner.nextLine();
	}

	//prompts the user to say something. This is displayed differently from the narration
	static void prompt(String line) {
		//this gives a space between this line and the line before
		System.out.println();
		System.out.print("\033[3m" + line + "\033[0m > ");
		answer = scanner.nextLine().toLowerCase();
	}

	//when a character is speaking, their name is displayed above their speech
	static void charSpeaking(String text) {
		System.out.println();
		System.out.println("[" + charSpeaking + "]");
		System.out.print("\"" +text+ "\"");
		scanner.nextLine();
	}

	static void continuesSpeaking(String text) {
		//no new lines
		System.out.print("\"" +text+ "\"");
		scanner.nextLine();
	}

	static void charSpeakingBeforePrompt(String text) {
		System.out.println();
		System.out.println("[" + charSpeaking + "]");
		System.out.println("\"" +text+ "\"");
		//no nextLine here
	}

	static void continuesSpeakingBeforePrompt(String text) {
		System.out.println("\"" +text+ "\"");
		//no nextLine here
	}


	private static void promptForCommand() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		prompt("Enter a command");

		//remove special characters
		String modifiedAnswer = "";
		for (int i = 0; i<answer.length(); i++) {
			char currentChar = answer.charAt(i);
			if (Character.isAlphabetic(currentChar)) {
				modifiedAnswer += answer.charAt(i);
			}
		}

		//find appropriate command and invoke it
		for (Command command : knownCommands) {
			if (modifiedAnswer.equals(command.getName())) {
				BuildyBot buildyBot = new BuildyBot();
				Method commandMethod = BuildyBot.class.getMethod(answer);
				commandMethod.invoke(buildyBot);
				return;
			}
		}
		charSpeakingBeforePrompt("Command not found.");
//		+
//				"Use \"help!\" to list your known commands. " +
//				"The exclamation mark is important.");
	}

	public static void help() {
		//must use punctuation!
		if (!answer.contains("!")) {
			System.out.println("Command not recognised. Please check your spelling and punctuation.");
			return;
		} else {
			String output = "Known commands\n";
			for (Command command : knownCommands) {
				output = output + "\n- " + command.getName() + " " + italics("(" + command.getDescription() + ")");
			}
			nLine(output);
		}
	}

	public static void where() {
		//must use punctuation!
		if (!answer.contains("!")) {
			System.out.println("Command not recognised. Please check your spelling and punctuation.");
			return;
		} else {
			charSpeaking("What would you like to know about?");
			prompt("Respond");
		}
	}





	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		charSpeaking("Hello, " + playerName + ". Welcome to the computer. To explore, you can use the " +
				"command \"where am I?\".");
		Command where = new Command("where?", "tells you where you are");
		knownCommands.add(where);

		while (true) {
			promptForCommand();
		}

		//upgade: power transfer

		/*		knownCommands.add("what?");

		charSpeaking("Hello, " + playerName + ". I am BigBot, your friendly AI mentor.");
		continuesSpeaking("Please be aware that I can only understand " +
				"certain specific commands such as \"yes\" and \"no\".");
		continuesSpeakingBeforePrompt("Are you satisfied with your assigned robot name? " +
						"I can change it if you wish.");
		validAnswer = false;
		outerLoop:
		while (!validAnswer) {
			prompt("Respond");
			switch (answer) {
				case "yes":
					validAnswer = true;
					//move on to the next section
					break outerLoop;
				case "no":

					middleLoop:
					while (true) {
						charSpeakingBeforePrompt("What would you like your robot name to be?");
						prompt("Respond");
						playerName = answer;

						charSpeakingBeforePrompt("Your name is now " + playerName + ". Is this satisfactory?");
						innerLoop:
						while (true) {
							prompt("Respond");
							switch (answer) {
								case "yes":
									//move on to the next section
									break outerLoop;
								case "no":
									//What would you like your robot name to be?
									continue middleLoop;
								default:
									//repeat the question
									charSpeakingBeforePrompt("Sorry, I do not understand. Please respond with " +
											"a with a \"yes\" or \"no\".");
									//stay within the inner while loop
									continue innerLoop;
							}
						}
					}
				default:
					validAnswer = false;
					//repeat the question
					charSpeakingBeforePrompt("Sorry, I do not understand. Please respond with a with " +
							"a \"yes\" or \"no\".");
					continue outerLoop;
			}

		}

		charSpeaking("Excellent. " + playerName + ", I am here to help you. " +
				"You can interact with me using various commands.");
		continuesSpeakingBeforePrompt("The first command you should be aware of is \"help!\". This lists all " +
				"of the commands you are currently aware of. Try using it now.");
		knownCommands.add(0, "help!");
		validAnswer = false;
		while (!validAnswer) {
			prompt("Input");
			switch (answer) {
				case "help!":
					validAnswer = true;
					break;
				default:
					validAnswer = false;
					charSpeakingBeforePrompt("Try entering the word \"help!\". " +
							"The exclamation mark is important.");
			}
		}

		String output = "Known commands\n";
		for (String command : knownCommands) {
			output = output + "\n- " + command;
		}
		nLine(output);

		charSpeaking("Very good. Each of these will perform a unique function when entered.");
		continuesSpeakingBeforePrompt("Try entering a command again.");

		while (true) {
			promptForCommand();
		}

		charSpeaking("The most basic command is \"what?\" It allows you to build up knowledge about the " +
				"Computer we live in.");
		continuesSpeaking("You can ask \"what?\" about anything I say that begins with a capital letter.");
		continuesSpeakingBeforePrompt("For example, in order to find out more information about the Computer " +
				"you could ask, \"what is the Computer?\" or \"what Computer?\" Give it a try.");
		prompt("Input");*/
	}
}
//game plot
	//start out being to do as little as possible
	//discover software and hardware upgrades

//todo remove characters from commands
//todo consider removing help! from the commands list
	//slows down the beginning of the game
//todo add info about not being able to pick up spelling errors
	//and moving "I can only understand specific commands" to after the user makes a mistake
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

class TextBasedGame {

	static Scanner scanner = new Scanner(System.in);
	//answer is used to store the user's response to prompts for later analysis
	static String answer;
	//validAnswer is used to determine whether the user needs to try answering again
	static boolean validAnswer;
	//this is the character with whom you're currently interacting with
	static String charSpeaking = "???";
	//this is the player's name
	static String playerName;

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
		answer = scanner.nextLine();
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

	//this method checks whether the last sentence the user typed in contains all of the specified words
	static boolean answerContains(String ... words) {

		for (String word : words) {
			if (!answer.toLowerCase().contains(word.toLowerCase())) {
				return false;
			}
		}

		return true;
	}

	//checking whether the user has responded "yes" to a question
	static boolean positiveAnswer() {
		return answerContains("yes") || answerContains("yeah") || answerContains("sure")
				|| answerContains("ok") || answerContains("absolutely")
				|| answerContains("why", "not") || answerContains("fine")
				|| answerContains("affirmative") || answerContains("naturally")
				|| answerContains("with", "pleasure") || answerContains("that's", "right")
				|| answerContains("thats", "right") || answerContains("that's right")
				|| answerContains("yup") || answerContains("all", "right")
				|| answerContains("very well") || answerContains("love", "to")
				|| answerContains("of", "course") || answerContains("correct")
				|| answerContains("you", "bet") || answerContains("you", "betcha")
				|| answerContains("uh")//uh-huh?
				|| answerContains("yeh")
				|| answerContains("as", "you", "wish")
				|| answerContains("actually"); //user may type this when asked to confirm a negative answer

	}

	static boolean negativeAnswer() {
		return answerContains("no") || answerContains("nah") || answerContains("not")
				|| answerContains("never") || answerContains("negative")
				|| answerContains("nope") || answerContains("na") //na-ah!
				|| answerContains("I'm", "good") || answerContains("Im", "good")
				|| answerContains("impossible") || answerContains("out", "of", "the", "question")
				|| answerContains("can't") || answerContains("cant")
				|| answerContains("won't") || answerContains("wont")
				|| answerContains("wouldn't") || answerContains("wouldnt")
				|| answerContains("don't") || answerContains("dont")
				|| answerContains("in", "your", "dreams")
				|| answerContains("stop") || answerContains("halt") || answerContains("wait");
	}

	static boolean numericAnswer() {
		return answerContains("0") || answerContains("1") || answerContains("2")
				|| answerContains("3") || answerContains("4") || answerContains("5")
				|| answerContains("6") || answerContains("7") || answerContains("8")
				|| answerContains("9") || answerContains("10")
				|| answerContains("zero") || answerContains("one") || answerContains("two")
				|| answerContains("three") || answerContains("four") || answerContains("five")
				|| answerContains("six") || answerContains("seven") || answerContains("eight")
				|| answerContains("nine") || answerContains("ten");
	}

	static int convertToInt(String answer) throws Exception {
		String answerToAnalyse = answer.toLowerCase();
		switch (answerToAnalyse) {
			case ("0"):
			case ("zero"):
				return 0;
			case ("1"):
			case ("one"):
				return 1;
			case ("2"):
			case ("two"):
				return 2;
			case ("3"):
			case ("three"):
				return 3;
			case ("4"):
			case ("four"):
				return 4;
			case ("5"):
			case ("five"):
				return 5;
			case ("6"):
			case ("six"):
				return 6;
			case ("7"):
			case ("seven"):
				return 7;
			case ("8"):
			case ("eight"):
				return 8;
			case ("9"):
			case ("nine"):
				return 9;
			case ("10"):
			case ("ten"):
				return 10;
			default:
				throw new Exception();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		nLine("You're floating in infinite blackness.");

		charSpeaking("...helloo??");
		continuesSpeaking("Oi! Wake up!");

		nLine("You gasp awake. Your eyes are open, but the world around you remains black.");

		charSpeaking("About time.");
		continuesSpeakingBeforePrompt("You awake? Say something.");

		prompt("Type something");
		while (answer.equals("")) {
			charSpeakingBeforePrompt("You deaf? Say something to me!");
			prompt("Type something");
		}

		charSpeaking("Hmmf. I was beginning to think I was the only intelligent life form on this spaceship.");
		continuesSpeaking("Name's Abbey. And you are?");

		charSpeaking = "Abbey";

		prompt("Respond");

		validAnswer = false;
		while (!validAnswer) {
			while (answer.equals("")) {
				charSpeakingBeforePrompt("Look, I don't have time for games. Answer the question already.");
				prompt("Respond");
			}

			playerName = answer;

			charSpeakingBeforePrompt("Your name's " + playerName + "? That right?");
			prompt("Answer");
			if (positiveAnswer()) {
				validAnswer = true;
				break;
			} else if (negativeAnswer()) {
				charSpeaking("Ok chump, let's try this again.");
				continuesSpeakingBeforePrompt("What's your name?");
				prompt("Answer");
			} else {
				charSpeakingBeforePrompt("What?? Give me a yes or a no already!");
				prompt("Answer");
			}
		}

		charSpeaking(playerName + ", we got a problem.");
		continuesSpeaking("We've been woken out of cryogenic sleep, but something's up.");
		continuesSpeaking("Place is deserted. Ship's not even moving. Everything's quiet.");
		continuesSpeaking("Now, I would have gone investigated this myself, no problem, it's just...");

		nLine("You hear her shuffling uncomfortably.");

		charSpeaking("I, er... haven't been able to figure out how to open the door.");
		continuesSpeaking("But I thought two heads would be better than one.");
		continuesSpeaking("So c'mon, come take a look at it.");

		nLine("");

		charSpeaking("You haven't moved. What's the matter with you?");

		nLine("You hear her approaching.");

		charSpeaking("Wait, what's this?");

		nLine("You feel her tugging at the name tag pinned to your shirt. She reads it aloud.");

		charSpeaking("Captain " + playerName + ". Please be advised: I am blind.");

		nLine("She lets it go and breathes heavily.");

		charSpeaking("UUUURRGGH. That's just my luck.");

		nLine("There's a pause. You can't see her, but you somehow manage to feel the killer glare she's giving you.");

		charSpeakingBeforePrompt("How does a BLIND PERSON become a spaceship captain anyway??");
		//uses the prompt font but doesn't allow the user to respond
		System.out.print("\033[3m" + "Respond" + "\033[0m > ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println();
		continuesSpeaking("Actually, I don't care. That's it! I'm done with you! Bye!");
		continuesSpeakingBeforePrompt("I'm putting you back into cryosleep. What're your last words??");

		prompt("Your last words");
		validAnswer = false;
		while (!validAnswer) {
			if (positiveAnswer()) {
				//game over
				validAnswer = true;
				nLine("You feel your eyes go heavy and you drift off to sleep for the last time.");
				nLine("You're floating in infinite blackness.");
				prompt("Press enter to end the game");
				return;

			} else if (negativeAnswer()) {
				validAnswer = true;
				//story continues after while loop
				break;

			} else {
				charSpeakingBeforePrompt("Sorry, let me rephrase that. What're your NON-DUMB last words??");
				//todo people don't get this!!
				prompt("Your last words");
			}
		}
		//valid answer = false???
		charSpeakingBeforePrompt("What?? You want to stay awake? You actually think you're going to " +
				"be useful??");

		prompt("Respond");
		validAnswer = false;
		while (!validAnswer) {
			if (negativeAnswer()) {
				//game over
				validAnswer = true;
				nLine("You feel your eyes go heavy and you drift off to sleep for the last time.");
				nLine("You're floating in infinite blackness.");
				prompt("Press enter to end the game");
				return;

			} else if (positiveAnswer()) {
				validAnswer = true;
				//story continues after while loop
				break;

			} else {
				charSpeakingBeforePrompt("You what, mate?");
				prompt("Respond");
			}
		}

		charSpeaking("Nah, you're not going to be useful. I'll prove it to you with a simple test:");
		continuesSpeakingBeforePrompt("How many fingers am I holding up?");
		prompt("Guess");
		validAnswer = false;
		while (!validAnswer) {
			if (numericAnswer()) {
				//game continues
				validAnswer = true;
				break;

			} else {
				charSpeakingBeforePrompt("Give me a proper answer. 10? 0?");
				prompt("Respond");
			}
		}

		charSpeaking("Pffff!! Tha... that was a lucky guess!!");
		continuesSpeaking("Alright, Captain Smartypants, if you're so useful how about you tell me what to do?");
		//todo asking a question here makes people think it's a prompt
		continuesSpeaking("I'll tell you what I can see.");

		nLine("You hear her walking around the room.");

		charSpeaking("I can see the locked door, a grate on the wall, and an Edvard Munch painting.");
		continuesSpeakingBeforePrompt("Any of those of interest?");

		prompt("Press enter to end the game");
		//end the game
		return;
//people expect the game to be able to understand more advanced language.
		//choose between limited options or yes/no questions
			//have the second player be the computer?
			//but then the player can't discover the computer?
			//or have the player BE the computer?
				//and discover upgrades?
					//talking?
					//having a body
						//walking?
						//seeing?
						//listening?

		//maybe have the player define what they're trigger words are going to be
		//or start really simple and build up into more complex sentences
			//some actions are only possible through advanced interactions
//Abbey is very blatant/bossy












	}

	//todo find a way to store your location
		//and move between places
	//todo find a way to make the text appear gradually?
		//and disappear gradually!
	//todo make more of a mystery out of discovering the computer
		//make abbey a stranger who you have to bond with
	//todo clean up having 4 talking methods


	//What do I want out of this game?
		//To have fun programming something I want to make for the first time
		//To tell an immersive story
		//Push what I can do and what I feel comfortable doing to a whole new level!

	//Ok, so story...
		//Shouldn't be too long - I want to finish this project
			//If it's short, I won't be able to have many characters
			//Ambiguous ending?
		//Has to be something I can tell without any visuals
			//blind person?
				//but how would he move around and stuff?
					//"computer!"
					//wakes up on a space station!
			//themes of darkness!
}
//last words: bye
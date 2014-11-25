package zuul;

import zuul.entities.Item;
import zuul.entities.Player;
import zuul.io.Command;
import zuul.io.CommandWord;
import zuul.io.Parser;
import zuul.rooms.Room;

import java.util.TooManyListenersException;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game {

	private Player player;
	private Parser parser;
	private Room currentRoom;

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		createRooms();
		parser = new Parser();
	}

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void createRooms() {
		Room outside, theater, pub, lab, office;

		// create the rooms
		outside = new Room("outside the main entrance of the university");
		theater = new Room("in a lecture theater");
		pub = new Room("in the campus pub");
		lab = new Room("in a computing lab");
		office = new Room("in the computing admin office");

		// initialise rooms exits
		outside.setExit(Room.Exits.EAST, theater);
		outside.setExit(Room.Exits.SOUTH, lab);
		outside.setExit(Room.Exits.WEST, pub);
		outside.addItem(new Item("Chocolate bar",1));


		lab.setExit(Room.Exits.EAST, office);

		currentRoom = outside; // start game outside
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		// TEST //
		Item item = new Item("an empty beer can", 0);
		//-TEST-//
		this.player = new Player(getPlayerName(), item);
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print the beginning of the welcome message and allows the player to type his name.
	 * @return String of player's name.
	 */
	private String getPlayerName() {
		System.out.println();
		System.out.println("Welcome to the World of Zuul!");
		System.out.println("First, tell me, what's your sweet name ?");
		return parser.getPlayerName();
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("As you can see, " + this.player.getName() + ", World of Zuul is a new, incredibly boring adventure game.");
		System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
		System.out.println();
		System.out.println(currentRoom.getLongDescription());
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		CommandWord commandWord = command.getCommandWord();

		switch (commandWord) {
		case UNKNOWN:
			System.out.println("I don't know what you mean...");
			break;

		case HELP:
			printHelp();
			break;

		case GO:
			goRoom(command);
			break;

		case DROP:
			dropItem(command);
			break;

		case PICK:
			pickItem(command);
			break;
			
		case INVENTORY:
			printInventory(command);
			break;

		case QUIT:
			wantToQuit = quit(command);
			break;
		}
		return wantToQuit;
	}




	// implementations of user commands:

	private void printInventory(Command command) {
		System.out.println("You actually carry : " + player.getInventoryContent());
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at the university.");
		System.out.println();
		System.out.println("Your command words are:");
		parser.showCommands();
	}

	/**
	 * Try to go in one direction. If there is an exit, enter the new rooms,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current rooms.
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.getLongDescription());
		}
	}

	/**
	 * Method allowing the player to drop an item in the current room.
	 * @param command
	 */
	private void dropItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("What to drop ?");
			return;
		}

		String itemName = command.getSecondWord();

		// Try to drop item in the current rooms.
		if(player.dropItem(currentRoom, new Item(itemName, 0))){
			System.out.println("successfully dropped " + itemName);
		}else{
			System.out.println("You don't carry : " + itemName);
			System.out.println("You actually carry : " + player.getInventoryContent());
		}
	}

	private void pickItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("What to pick up ?");
			return;
		}

		String itemName = command.getSecondWord();

		// Try to pick item in the current rooms.
		if(currentRoom.hasItem(new Item(itemName, 0))){
			player.pickUp(currentRoom,new Item(itemName, 0));
			System.out.println("successfully picked " + itemName);
		}else{
			System.out.println("There is no : " + itemName);
			System.out.println(currentRoom.getItemString());
		}
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 * 
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}
}

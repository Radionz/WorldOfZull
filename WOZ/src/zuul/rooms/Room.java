package zuul.rooms;

import zuul.entities.Item;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

/**
 * Class rooms - a rooms in an adventure game.
 * <p/>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p/>
 * A "rooms" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the rooms
 * stores a reference to the neighboring rooms.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this rooms
    private ArrayList<Item> items;

    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.items = new ArrayList<>(100);
    }

    /**
     * Define an exit from this rooms.
     *
     * @param direction The direction of the exit.
     * @param neighbor  The rooms to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the rooms
     * (the one that was defined in the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the rooms in the form:
     * You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this rooms
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the rooms's exits, for example
     * "Exits: north west".
     *
     * @return Details of the rooms's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the rooms that is reached if we go from this rooms in direction
     * "direction". If there is no rooms in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The rooms in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Return the list of all items present in the current room
     *
     * @return Array list of items
     */
    public ArrayList<Item> getItemsList() {
        return items;
    }

    /**
     * Method allowing the class Game or the player to add Items in the current room
     * @param item Item to add in the current room.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Return a boolean telling if an item is in this room or not.
     * @param item Item to check
     * @return If the item is in the room, or not.
     */
    public boolean hasItem(Item item){
        return this.items.contains(item);
    }

    /**
     * Return a String containing all items in this room.
     * @return Items in the room.
     */
    public String getItems() {
        String res = "";
        for(Item i : items){
            res += i.toString() + ", ";
        }

        return (res.length()>2)? res.substring(0, res.length()-2): res;
    }
}

